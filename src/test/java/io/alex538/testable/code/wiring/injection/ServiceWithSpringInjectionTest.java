package io.alex538.testable.code.wiring.injection;

import io.alex538.testable.code.wiring.Dependency;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Dependency.class, Service.class})
class ServiceWithSpringInjectionTest {

    @MockBean
    private Dependency dependency;

    @Autowired
    private Service subject;

    @Test
    public void testAction1() {
        subject.action1();

        verify(dependency, times(1)).action();
    }


}
