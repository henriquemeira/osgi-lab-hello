
package co.h2a.sandbox.osgi.lab.hello.service;

import org.osgi.service.component.annotations.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import co.h2a.sandbox.osgi.lab.hello.api.HelloPerson;
import co.h2a.sandbox.osgi.lab.hello.api.Person;

@Path("/helloperson")
@Component(service = HelloPersonService.class, property = { "osgi.jaxrs.resource=true" })
public class HelloPersonService implements HelloPerson {
    
    private final Map<Long, Person> persons = new HashMap<>();

    @Override
    @Path("/")
    @Produces("application/json")
    @GET
    public Collection<Person> list() {
        return persons.values();
    }

    @Override
    @Path("/{id}")
    @Produces("application/json")
    @GET
    public Person get(@PathParam("id") Long id) {
        return persons.get(id);
    }
    
    @Override
    @Path("/")
    @Consumes("application/json")
    @POST
    public void add(Person person) {
        persons.put(person.getId(), person);
    }

    @Override
    @Path("/")
    @Consumes("application/json")
    @PUT
    public void update(Person person) {
        persons.remove(person.getId());
        persons.put(person.getId(), person);
    }

    @Override
    @Path("/{id}")
    @DELETE
    public void remove(@PathParam("id") Long id) {
        persons.remove(id);
    }

    @Override
    @Path("/sayhello")
    @Consumes("application/json")
    @POST
    public String sayHello(Person person) {
        return "Hello " + persons.get(person.getId()).getName();
    }

    @Override
    @Path("/sayhello/{id}")
    @GET
    public String sayHello(@PathParam("id") Long id) {
        return "Hello " + persons.get(id).getName();
    }

    @Override
    @Path("/sayhello/{name}")
    @Consumes("application/json")
    @POST
    public String sayHello(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
