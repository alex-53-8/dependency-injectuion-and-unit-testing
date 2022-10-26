package io.alex538.testable.code.wiring.injection;

import io.alex538.testable.code.common.Dependency1;
import io.alex538.testable.code.common.Dependency2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Service implements InitializingBean {

    @Autowired
    private Dependency1 dependency1;

    @Autowired
    private Dependency2 dependency2;

    @Override
    public void afterPropertiesSet() throws Exception {
        // need to have a class initialization requires post actions after properties set
    }

    public void action1() {
        dependency1.action();
    }

    public void action2() {
        dependency2.action();
    }

}
