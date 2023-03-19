package com.hellobot.ai;

import com.hellobot.ai.models.config.SlackBotConfig;
import io.dropwizard.core.Configuration;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloBotConfiguration extends Configuration {
    private SlackBotConfig slackBotConfig;
}
