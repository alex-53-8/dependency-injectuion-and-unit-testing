# Dependency injection and unit testing

![](/home/alex538/sources/java/write-testable-code/resources/title.jpg)

Any developer, who works with object-oriented programming language and writes unit tests for implementations,
encounters with a choice how to provide dependencies to a class. Some languages provide few choices,
some languages provide just one way via constructor arguments.

Let's take a Java language as an example, where dependencies can be provided in two ways and how writing unit tests
can be different in both cases. What pros and cons could each approach has.

So, our tasks could look like:
- class fields must be assigned with corresponding instances;
- a unit test should be written.

Here is our initial code snippet to be modified. The code snippet has one dependency to be populated in runtime.

```java
public class Service {

    Dependency1 dependency;

    public void action() {
        dependency.action();
    }

}
```

## Assign dependencies to class field by DI framework

A sample above can be changed in something like this if we use for instance `Spring` framework.

```java
@Component
public class Service {

    @Autowired
    private Dependency1 dependency;

    public void action() {
        dependency.action();
    }

}
```

Looks quite sharp, nothing "redundant". Few things bother me here. 

The first thing - Java reflection is used to assign dependencies. Not a big deal, but providing a value to a private field
somehow in background does not look really simple and clean - private fields should be assigned from the inside of a class only.

The second thing - fields of the class are not final. Technically, it means that fields can be modified from the inside 
of the class during a life cycle of an instance of the class. In other words, the class partially looses immutability 
and that is not good - state changes and behavior changes as well. It would be difficult to write a good and clean unit test.

Okay, so far it does not look too bad, but let's try to write a unit test for an implementation above. 

Here are tasks for a unit test:
- assign a dependency to a class field;
- check if a dependency's method is actually called.

Current implementation does not suppose direct assignment of dependencies to class fields, so we would DI to assign
instance of a dependency to a private class field. It is possible to do with only `mockito` extension 
or `mockito + Spring testing` extension. Each way has pros and cons, but as result a dependency will be assigned
to a desired field, and we are able to write a unit test to verify our implementation.

```java
@ExtendWith(MockitoExtension.class)
class ServiceTest {

    @Mock
    private Dependency1 dependency;

    @InjectMocks
    private Service subject;

    @Test
    public void testAction1() {
        subject.action();

        verify(dependency, times(1)).action();
    }

}
```

I would highlight that it is quite smooth way: initialization and assignment happens invisibly. The only one thing - 
we need a mediator to be able to mock and assign instances to class fields. We do not see and control how and 
when mocking and assignment happen. This fact already makes such approach a bit foggy: looks like we "see" everything,
but not clearly. Shapes in a fog can be tricky ;)

## Assign dependencies to class field via a class constructor by DI framework

Let's check out another way of providing dependencies - via class constructor. A sample Java class can be transformed in a following form.

```java
@Component
public class Service {
    
    private final Dependency1 dependency;
    
    public Service(Dependency1 dependency) {
        this.dependency = dependency;
    }

    public void action() {
        dependency.action();
    }

}
```

All dependencies are provided via a constructor explicitly, class fields are marked as `final` and are immutable
during entire life cycle of an instance of a class. Maybe constructor takes more space in a class definition
just because we use it for assigning dependencies.

Let's write a unit test for such implementation and follow the same tasks as for wiring dependencies via DI directly to class fields:
- assign a dependency to a class field;
- check if a dependency's method is actually called.

```java
class ServiceTest {

    private final Dependency dependency = mock(Dependency.class);
    
    private final Service subject = new Service(dependency);

    @Test
    public void testAction() {
        subject.action();

        verify(dependency, times(1)).action();
    }

}
```

Since we pass dependencies to a class via constructor we can use following approach below. Test implementation does not
require any special extension to run a test: dependency and test subject creation happens "manually". Such implementation
has no invisible actions, everything is controlled by us. Implementation looks in different way, dependency creation is seen,
passing dependencies is seen also, code is a bit longer than a previous implementation. Definitely it is more obvious what happens here.

## Why `final` matters here?
A keyword `final` applied to a class field indicated immutability. If a class field has no such keyword there is a possibility
to modify value of a class field. That means in some specific cases our class technically can behave differently, and
it is not good at all - it is a way to produce problems in production rather than favor. Since a class looses immutability 
then unit testing results get unpredictable and can be described as `flaky`. So, `final` makes an object `immutable`, 
unit tests produce the same results, and application in production behaves as it is intended to.

## Conclusion
Essential part of unit testing is simplicity and stability :) Simplicity facilitates easy understanding of implementation, 
stability provides the same results in multiple invocation, and both lead to stable work in production. Considering approaches
from the above, I would personally choose the second one. As of me it looks more simple, clean and straightforward.
The second has no "magic" in tests - everything is on a table.