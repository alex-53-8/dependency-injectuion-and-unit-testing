package io.alex538.testable.code.wiring.constructor;

import io.alex538.testable.code.wiring.Dependency;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ServiceTest {

    private final Dependency dependency = mock(Dependency.class);

    private final Service subject = new Service(dependency);

    @Test
    public void testAction1() {
        subject.action1();

        verify(dependency, times(1)).action();
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