package io.alex538.testable.code.wiring.injection;

import io.alex538.testable.code.common.Dependency1;
import io.alex538.testable.code.common.Dependency2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Dependency1.class, Dependency2.class, Service.class})
class ServiceWithSpringInjectionTest {

    @MockBean
    private Dependency1 dependency1;

    @MockBean
    private Dependency2 dependency2;

    @Autowired
    private Service subject;

    @Test
    public void testAction1() {
        subject.action1();

        verify(dependency1, times(1)).action();
    }

    @Test
    public void testAction2() {
        subject.action2();

        verify(dependency2, times(1)).action();
    }

}
/*
* need to have special spring extension, mock dependencies and wire them with usage of spring extension
* mock creation is hidden, wiring dependencies is hidden
*
* pros:
* - simple to create
*
* cons:
* - a unit test needs part of DI framework to run test especially when a field is annotation with @Qualifier
* - a unit test's initialization becomes complex
* */