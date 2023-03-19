package com.hellobot.ai.models.response;

public enum SlackTextType {
  MARKDOWN("mrkdwn"),
  SECTION("section");
  private String type;

  SlackTextType(String envUrl) {
    this.type = envUrl;
  }

  public String getType() {
    return type;
  }
}
