package com.hellobot.ai.command_handlers;

import com.hellobot.ai.models.response.SlackBotResponse;
import com.hellobot.ai.models.response.SlackTextType;
import com.slack.api.bolt.context.builtin.SlashCommandContext;
import com.slack.api.bolt.handler.builtin.SlashCommandHandler;
import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import com.slack.api.bolt.response.Response;
import com.slack.api.methods.SlackApiException;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;

@Slf4j
public class HelloCommandHandler implements SlashCommandHandler {
    @Override
    public Response apply(SlashCommandRequest slashCommandRequest, SlashCommandContext slashCommandContext) throws IOException, SlackApiException {
        log.info("Slack request data - {} {}", slashCommandRequest.getContext(), slashCommandRequest.getRequestBodyAsString());
        String userMessage = String.format(":wave: Hello! <@%s>", slashCommandContext.getRequestUserId());
        SlackBotResponse slackBotResponse = new SlackBotResponse(SlackTextType.MARKDOWN, userMessage);
        return slashCommandContext.ack(slashCommandContext.toJson(slackBotResponse));
    }
}
