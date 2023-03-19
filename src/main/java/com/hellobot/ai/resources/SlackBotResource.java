package com.hellobot.ai.resources;

import com.hellobot.ai.utils.SlackBotUtils;
import com.slack.api.bolt.App;
import com.slack.api.bolt.request.Request;
import com.slack.api.bolt.request.RequestHeaders;
import com.slack.api.bolt.response.Response;
import com.slack.api.bolt.util.QueryStringParser;
import com.slack.api.bolt.util.SlackRequestParser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import lombok.extern.slf4j.Slf4j;
import java.io.InputStream;

@Path("/slack/events")
@Slf4j
public class SlackBotResource{

    private final App app;
    private final SlackRequestParser slackRequestParser;
    public SlackBotResource(App app) {
        this.app = app;
        this.slackRequestParser = new SlackRequestParser(app.config());
    }

    // Handle the incoming requests from Slack
    @POST
    @SuppressWarnings({"java:S3740"})
    public String slackCommand(@Context HttpServletRequest request, InputStream body) throws Exception {
        // Convert the raw HTTP request to Bolt's abstraction data
        Request<?> boltRequest = SlackBotUtils.buildSlackRequest(this.slackRequestParser, request, body);
        // Run the middleware / listeners in your App
        Response boltResponse = app.run(boltRequest);

        return boltResponse.getBody();
    }
}
