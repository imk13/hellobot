package com.hellobot.ai.models.response;


public class SlackBotResponse {

  private SlackTextType type;
  private String text;

  public SlackBotResponse(SlackTextType type, String text) {
    this.type = type;
    this.text = text;
  }
}