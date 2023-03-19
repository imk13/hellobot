package com.hellobot.ai.utils;

import com.slack.api.bolt.request.Request;
import com.slack.api.bolt.request.RequestHeaders;
import com.slack.api.bolt.util.QueryStringParser;
import com.slack.api.bolt.util.SlackRequestParser;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SlackBotUtils {
    /**
     * Convert the raw HTTP request to Bolt's abstraction data
     * @param requestParser
     * @param req
     * @param rawRequestBody
     * @return com.slack.api.bolt.request.Request
     */
    @SuppressWarnings({"java:S3824"})
    public static Request<?> buildSlackRequest(final SlackRequestParser requestParser, HttpServletRequest req, InputStream rawRequestBody) {
        // Your Bolt app needs to access the raw request body to verify the x-slack-signature header.
        // Refer to https://api.slack.com/authentication/verifying-requests-from-slack for details.
        Scanner s = new Scanner(rawRequestBody).useDelimiter("\\A");
        String requestBody = s.hasNext() ? s.next() : "";
        TreeMap<String, List<String>> headersMap = new TreeMap<>();
        Iterator<String> headerIterator = req.getHeaderNames().asIterator();

        while (headerIterator.hasNext()){
            String key = headerIterator.next();
            if(!headersMap.containsKey(key)) {
                headersMap.put(key, new ArrayList<>());
            }
            Iterator<String> headerValuesIterator = req.getHeaders(key).asIterator();
            List<String> headerValues = new ArrayList<>();
            while(headerValuesIterator.hasNext()) {
                headerValues.add(headerValuesIterator.next());
            }
            headersMap.get(key).addAll(headerValues);
            headersMap.put(key, headersMap.get(key));
        }
        RequestHeaders headers = new RequestHeaders(headersMap);

        SlackRequestParser.HttpRequest rawRequest = SlackRequestParser.HttpRequest.builder()
                .requestUri(req.getRequestURI())
                .queryString(QueryStringParser.toMap(req.getQueryString()))
                .headers(headers)
                .requestBody(requestBody)
                .remoteAddress(req.getRemoteAddr())
                .build();
        return requestParser.parse(rawRequest);
    }
}
