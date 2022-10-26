package io.alex538.testable.code.wiring.injection;

import io.alex538.testable.code.wiring.Dependency;
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
    private Dependency dependency;

    @InjectMocks
    private Service subject;

    @Test
    public void testAction1() {
        subject.action1();

        verify(dependency, times(1)).action();
    }


}
