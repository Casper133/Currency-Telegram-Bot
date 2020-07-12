package com.casper.bot.command;

import lombok.Setter;

@Setter
public class CommandInvoker {

  private Command command;

  public void executeCommand() {
    command.execute();
  }

}
