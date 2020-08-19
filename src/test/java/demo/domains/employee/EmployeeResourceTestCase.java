package demo.domains.employee;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import demo.shared.ApplicationResources;

@RunWith(Arquillian.class)
public class EmployeeResourceTestCase {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war").addPackage(Employee.class.getPackage())
                .addPackage(ApplicationResources.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsWebInfResource("jbossas-ds.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    @RunAsClient
    public void postCreateEmployeeTest(@ArquillianResteasyResource("api") final WebTarget webTarget) {

        CreateEmployeeRequest request = new CreateEmployeeRequest();
        request.setName("Alien");
        request.setSalary(1000);

        String response = webTarget.path("/employee").request(MediaType.APPLICATION_JSON).post(Entity.json(request))
                .readEntity(String.class);

        Assert.assertEquals("1", response);

    }

}