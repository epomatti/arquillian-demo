package solid.domains.employee;

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

import solid.domains.security.Access;
import solid.shared.ApplicationResources;

@RunWith(Arquillian.class)
public class EmployeeRepositoryTestCase {

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackage(Employee.class.getPackage())
				.addPackage(Access.class.getPackage())
				.addPackage(ApplicationResources.class.getPackage())
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource("jbossas-ds.xml")
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

		employeeId = repository.creteEmployee("New Employee", 1000);

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

	@After
	public void commitTransaction() throws Exception {
		utx.begin();
		em.joinTransaction();
		em.createQuery("delete from Employee").executeUpdate();
		utx.commit();
	}

}