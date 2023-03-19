package com.hellobot.ai.command_handlers;

import com.slack.api.bolt.context.builtin.SlashCommandContext;
import com.slack.api.bolt.handler.builtin.SlashCommandHandler;
import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import com.slack.api.bolt.response.Response;
import com.slack.api.methods.SlackApiException;

import java.io.IOException;

public class QueryCommandHandler implements SlashCommandHandler {
    @Override
    public Response apply(SlashCommandRequest slashCommandRequest, SlashCommandContext slashCommandContext) throws IOException, SlackApiException {
        String userMention = String.format("<@%s>", slashCommandContext.getRequestUserId());
        String botResponse = String.format("%s %nThanks for asking. Will get back to you soon %s",
                slashCommandRequest.getPayload().getText(), userMention);
        return slashCommandContext.ack(botResponse);
    }
}
