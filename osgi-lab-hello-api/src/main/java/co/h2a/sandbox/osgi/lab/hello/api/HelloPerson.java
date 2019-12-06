package co.h2a.sandbox.osgi.lab.hello.api;

import java.util.Collection;

/**
 * Simple interface describing the hello to person service.
 */
public interface HelloPerson {

    Collection<Person> list();

    Person get(Long id);

    void add(Person person);
    
    void update(Person person);
    
    void remove(Long id);
    
    String sayHello(Person person);
    String sayHello(Long id);
    String sayHello(String name);
}
