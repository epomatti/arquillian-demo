package solid.domains.employee;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import solid.shared.ApplicationResources;

@RunWith(Arquillian.class)
public class EmployeeRepositoryTestCase {

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackage(Employee.class.getPackage())
				.addPackage(ApplicationResources.class.getPackage())
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsWebInfResource("jbossas-ds.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@PersistenceContext
	EntityManager em;

	@Inject
	UserTransaction utx;

	@Inject
	EmployeeRepository repository;

	Integer employeeId;

	@Before
	public void preparePersistenceTest() throws Exception {
		utx.begin();
		em.joinTransaction();

		CreateEmployeeRequest request = new CreateEmployeeRequest();
		request.setName("New Employee");
		request.setSalary(1000);

		employeeId = repository.creteEmployee(request);

		utx.commit();
		em.clear();
	}

	@Test
	public void createEmployeeTest() throws Exception {
		Employee e = em.find(Employee.class, employeeId);
		Assert.assertNotNull(e);
		Assert.assertEquals(e.getName(), "New Employee");
		Assert.assertEquals(e.getSalary(), Integer.valueOf(1000));
		Assert.assertTrue(e.getEnabled());
	}

	@Test
	public void getSalaryTest() throws Exception {
		Integer actual = repository.getSalary(employeeId);
		Assert.assertEquals(1000, (int) actual);
	}

	@Test
	public void disableEmployeeTest() throws Exception {
		Integer disable = repository.disable(employeeId);
		Assert.assertEquals(1, (int) disable);

		Employee e = em.find(Employee.class, employeeId);
		Assert.assertFalse(e.getEnabled());
	}

	@Test
	public void deleteEmployeeTest() throws Exception {
		Integer actual = repository.deleteEmployee(employeeId);
		Assert.assertEquals(1, (int) actual);
	}

	@Test
	public void findAllTest() throws Exception {
		List<Employee> employees = repository.findAll();
		Assert.assertEquals(1, (int) employees.size());
	}

	@After
	public void commitTransaction() throws Exception {
		utx.begin();
		em.joinTransaction();
		em.createQuery("delete from Employee").executeUpdate();
		utx.commit();
	}

}