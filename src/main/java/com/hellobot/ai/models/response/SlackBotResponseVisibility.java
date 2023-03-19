package com.hellobot.ai.models.response;

public enum SlackBotResponseVisibility {
  IN_CHANNEL("in_channel"),
  EPHEMERAL("ephemeral"); // default value is ephemeral
  private String visibility;

  SlackBotResponseVisibility(String envUrl) {
    this.visibility = envUrl;
  }

  public String getVisibility() {
    return visibility;
  }
}
