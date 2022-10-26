package io.alex538.testable.code.wiring.injection;

import io.alex538.testable.code.common.Dependency1;
import io.alex538.testable.code.common.Dependency2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ServiceWithMockitoInjectionTest {

    @Mock
    private Dependency1 dependency1;

    @Mock
    private Dependency2 dependency2;

    @InjectMocks
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