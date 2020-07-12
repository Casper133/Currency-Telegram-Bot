package com.casper.bot.property;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class BotProperties {

  private String botUsername;
  private String botToken;

  private static final String BOT_USERNAME_VARIABLE = "BOT_USERNAME";
  private static final String BOT_TOKEN_VARIABLE = "BOT_TOKEN";

  public BotProperties() {
    init();
  }

  private void init() {
    String username = System.getenv(BOT_USERNAME_VARIABLE);
    String token = System.getenv(BOT_TOKEN_VARIABLE);

    if (username == null || username.isBlank()) {
      throw new IllegalStateException("Bot username is empty.");
    }

    if (token == null || token.isBlank()) {
      throw new IllegalStateException("Bot token is empty.");
    }

    this.botUsername = username;
    this.botToken = token;
    log.info("Bot initialized successfully!");
  }

}
