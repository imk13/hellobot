package com.hellobot.ai.health;


import com.codahale.metrics.health.HealthCheck;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SlackAppHealthCheck extends HealthCheck {
    @Override
    protected Result check() {
        log.info("Slack app is healthy");
        return Result.healthy();
    }
}
