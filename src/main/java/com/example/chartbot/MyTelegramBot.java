package com.example.chartbot;

import com.example.chartbot.handler.ChatGPT;
import com.example.chartbot.handler.ChatHandler;
import com.example.chartbot.handler.StartHandler;
import com.example.chartbot.query.QueryMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class MyTelegramBot extends TelegramLongPollingCommandBot {

  private final String username;
  @Autowired
  private StartHandler startHandler;

  @Autowired
  private ChatHandler chatHandler;

  @Override
  public String getBotUsername() {
    return username;
  }

  public MyTelegramBot(@Value("${bot.token}") String botToken,
      @Value("${bot.username}") String username) {
    super(botToken);
    this.username = username;
  }

  @Override
  public void processNonCommandUpdate(Update update) {
    if (update.hasMessage() && update.getMessage().hasText()) {
      String messageText = update.getMessage().getText();
      if (messageText.equals("/start")) {
        startHandler.execute(this, update.getMessage().getFrom(), update.getMessage().getChat(),
            null);
      } else if (messageText.equals("Яка зараз сама моржинальна ніша?")) {
        ChatGPT.listTokens();
        String answer = ChatGPT.prompts(QueryMessage.query1);
        System.out.println(answer);
        try {
          execute(new SendMessage(update.getMessage().getChat().getId().toString(), answer));
        } catch (TelegramApiException e) {
          throw new RuntimeException(e);
        }
      } else if (messageText.equals("Які є найефективніші рекламні кампанії?")) {
        ChatGPT.listTokens();
        String answer = ChatGPT.prompts(QueryMessage.query2);
        System.out.println(answer);
        try {
          execute(new SendMessage(update.getMessage().getChat().getId().toString(), answer));
        } catch (TelegramApiException e) {
          throw new RuntimeException(e);
        }
      } else if (messageText.equals("Як налаштувати гнучкий бюджет?")) {
        ChatGPT.listTokens();
        String answer = ChatGPT.prompts(QueryMessage.query3);
        System.out.println(answer);
        try {
          execute(new SendMessage(update.getMessage().getChat().getId().toString(), answer));
        } catch (TelegramApiException e) {
          throw new RuntimeException(e);
        }
      } else if (messageText.equals(
          "Які ліцензії та дозволи необхідно отримати для відкриття власного бізнесу?")) {
        ChatGPT.listTokens();
        String answer = ChatGPT.prompts(QueryMessage.query4);
        System.out.println(answer);
        try {
          execute(new SendMessage(update.getMessage().getChat().getId().toString(), answer));
        } catch (TelegramApiException e) {
          throw new RuntimeException(e);
        }
      } else {
        chatHandler.answerGeneration(this, update.getMessage().getFrom(),
            update.getMessage().getChat(),
            messageText);
      }
    }
  }
}
