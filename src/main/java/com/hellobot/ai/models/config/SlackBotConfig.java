package com.hellobot.ai.models.config;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SlackBotConfig {
    private String botToken;
    private String signingSecret;
}
