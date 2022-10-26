package io.alex538.testable.code.wiring.constructor;

import io.alex538.testable.code.common.Dependency1;
import io.alex538.testable.code.common.Dependency2;
import org.springframework.stereotype.Component;

@Component
public class Service {

    private final Dependency1 dependency1;

    private final Dependency2 dependency2;

    public Service(Dependency1 dependency1, Dependency2 dependency2) {
        this.dependency1 = dependency1;
        this.dependency2 = dependency2;

        // possible to take actions in a constructor after dependencies are created
        // no strong need to have a dedicated method which is called when properties are set
    }

    public void action1() {
        dependency1.action();
    }

    public void action2() {
        dependency2.action();
    }

}
