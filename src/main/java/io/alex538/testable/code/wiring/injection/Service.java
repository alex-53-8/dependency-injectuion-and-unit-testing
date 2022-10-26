package io.alex538.testable.code.wiring.injection;

import io.alex538.testable.code.wiring.Dependency;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Service implements InitializingBean {

    @Autowired
    private Dependency dependency;

    @Override
    public void afterPropertiesSet() throws Exception {
        // need to have a class initialization requires post actions after properties set
    }

    public void action1() {
        dependency.action();
    }

}
