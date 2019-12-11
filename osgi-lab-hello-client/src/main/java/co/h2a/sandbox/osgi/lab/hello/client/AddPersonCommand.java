package co.h2a.sandbox.osgi.lab.hello.client;

import co.h2a.sandbox.osgi.lab.hello.api.HelloPerson;
import co.h2a.sandbox.osgi.lab.hello.api.Person;
import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.lifecycle.Reference;
import org.apache.karaf.shell.api.action.lifecycle.Service;

@Command(scope = "labhello", name = "add", description = "Add person")
@Service
public class AddPersonCommand implements Action {
    
    @Argument(index = 0, name = "id", description = "Person ID", required = true, multiValued = false)
    long id;

    @Argument(index = 1, name = "name", description = "Person name", required = true, multiValued = false)
    String name;
    
    // Get a bean implementation of HelloPerson interface
    @Reference
    private HelloPerson helloPersonService;

    @Override
    public Object execute() throws Exception {
        Person person  = new Person();
        person.setId(id);
        person.setName(name);
        helloPersonService.add(person);
        return person;
    }
    
    
}
