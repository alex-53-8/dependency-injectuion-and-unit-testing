package io.alex538.testable.code.wiring.constructor;

import io.alex538.testable.code.wiring.Dependency;
import org.springframework.stereotype.Component;

@Component
public class Service {

    private final Dependency dependency;

    public Service(Dependency dependency) {
        this.dependency = dependency;

        // possible to take actions in a constructor after dependencies are created
        // no strong need to have a dedicated method which is called when properties are set
    }

    public void action1() {
        dependency.action();
    }

}
