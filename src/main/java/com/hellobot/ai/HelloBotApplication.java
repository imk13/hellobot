package com.hellobot.ai;

import com.hellobot.ai.command_handlers.HelloCommandHandler;
import com.hellobot.ai.command_handlers.QueryCommandHandler;
import com.hellobot.ai.health.SlackAppHealthCheck;
import com.hellobot.ai.middleware.SlackRequestVerification;
import com.hellobot.ai.resources.SlackBotResource;
import com.slack.api.app_backend.SlackSignature;
import com.slack.api.bolt.App;
import com.slack.api.bolt.AppConfig;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloBotApplication extends Application<HelloBotConfiguration> {
    public static void main(final String[] args) throws Exception {
        new HelloBotApplication().run(args);
    }

    @Override
    public String getName() {
        return "helloBot";
    }

    @Override
    public void initialize(final Bootstrap<HelloBotConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final HelloBotConfiguration configuration,
                    final Environment environment) throws Exception {
        environment.healthChecks().register("slackAppHeathCheck", new SlackAppHealthCheck());

        App app = new App(AppConfig.builder()
                .singleTeamBotToken(configuration.getSlackBotConfig().getBotToken())
                .signingSecret(configuration.getSlackBotConfig().getSigningSecret())
                .build());

        app.use(new SlackRequestVerification(new SlackSignature.Verifier(new SlackSignature.Generator(configuration.getSlackBotConfig().getSigningSecret()))));
        app.command("/hello", new HelloCommandHandler());
        app.command("/query", new QueryCommandHandler());
        environment.jersey().register(new SlackBotResource(app));
    }

}
