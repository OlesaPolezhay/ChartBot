package com.example.chartbot.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class ChatHandler extends BotCommand {

  public ChatHandler(@Value("help") String commandIdentifier, @Value(" ") String description) {
    super(commandIdentifier, description);
  }

  @Override
  public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
  }

  public void answerGeneration(AbsSender absSender, User user, Chat chat, String strings) {
    ChatGPT.listTokens();
    String systemContent = strings + "Дай відповідь у менше між 400 токенів";

    String query = String.format("""
        {
            "model": "gpt-3.5-turbo",
            "max_tokens": 500,
            "messages": [
                {
                    "role": "system",
                    "content": "%s"
                }
            ]
        }
        """, systemContent);
    String answer = ChatGPT.prompts(query);
    try {
      absSender.execute(new SendMessage(chat.getId().toString(), answer));
    } catch (TelegramApiException e) {
      throw new RuntimeException(e);
    }
  }
}
