package io.alex538.testable.code.wiring.constructor;

import io.alex538.testable.code.common.Dependency1;
import io.alex538.testable.code.common.Dependency2;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ServiceTest {

    private final Dependency1 dependency1 = mock(Dependency1.class);

    private final Dependency2 dependency2 = mock(Dependency2.class);

    private final Service subject = new Service(dependency1, dependency2);

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
 * no need in anything else then junit and mockito
 *
 * pros:
 * - simple to create
 * - dependencies are created and assigned to class fields explicitly
 *
 * cons:
 * - manual mocking of dependencies
 * */