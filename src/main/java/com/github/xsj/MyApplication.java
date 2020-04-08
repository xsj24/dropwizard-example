package com.github.xsj;

import com.github.xsj.health.hello.TemplateHealthCheck;
import com.github.xsj.resources.hello.SayingResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class MyApplication extends Application<MyConfiguration> {

    public static void main(final String[] args) throws Exception {
        new MyApplication().run(args);
    }

    @Override
    public String getName() {
        return "dropwizard-example";
    }

    @Override
    public void initialize(final Bootstrap<MyConfiguration> bootstrap) {

    }

    @Override
    public void run(final MyConfiguration configuration,
                    final Environment environment) {
        final SayingResource resource = new SayingResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);

        environment.jersey().register(resource);
    }

}
