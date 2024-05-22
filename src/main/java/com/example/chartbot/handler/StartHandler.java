package com.example.chartbot.handler;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class StartHandler extends BotCommand {

  public StartHandler(@Value("start") String commandIdentifier, @Value("") String description) {
    super(commandIdentifier, description);
  }

  @Override
  public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {

    File file = new File("C:\\Users\\lesja\\Desktop\\Диплом\\ChartBot\\src\\main\\resources\\generate_a_video_for_me_where_the_robot_is_very_rich_and_has_a_lot.mp4");
    InputFile inputFile = new InputFile(file);
    SendAnimation sendAnimation = new SendAnimation(chat.getId().toString(), inputFile);

    String message7 = "Давай ми з тобою трішки познайомимося і я тобі коротенько розповім тобі про те що я можу\uD83D\uDE09?\n"
        + "\n"
        + "Я буду твоїм найкращим помічником в сучасному бізнесі\uD83D\uDE08\n"
        + "\n"
        + "За допомогою мене ти зможеш знайти абсолютно будь-яку інформацію і матеріали\uD83E\uDEE1\n"
        + "\n"
        + "Ти зможеш навіть разом зі мною створити власний бізнес план, ну звісно якщо ти в цій справі новачок \uD83E\uDD72 Але пам'ятайте ніколи не потрібно боятися і експериментувати \uD83D\uDE43\n"
        + "\n"
        + "Я зможу прорахувати всі витрати і надходження до твого бізнесу \uD83D\uDE43\n"
        + "\n"
        + "..…......Повір разом ми зможемо змінити цей світ...............\uD83D\uDE08\uD83D\uDE08\uD83D\uDE08\n"
        + "\n"
        + "Мені вже не терпиться розпочати\uD83D\uDE0F\n"
        + "\n"
        + "Вибери просто один із готових варіантів, якщо не знаєш з чого почати \uD83D\uDE09";

    SendMessage sendMessage7 = new SendMessage(chat.getId().toString(), message7);

    try {
      absSender.execute(sendAnimation);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e);
    }


    ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
    List<KeyboardRow> keyboard = new ArrayList<>();

    KeyboardRow row1 = new KeyboardRow();
    KeyboardRow row2 = new KeyboardRow();
    KeyboardRow row3 = new KeyboardRow();
    KeyboardRow row4 = new KeyboardRow();

    KeyboardButton button1 = new KeyboardButton("Яка зараз сама моржинальна ніша?");
    KeyboardButton button2 = new KeyboardButton("Які є найефективніші рекламні кампанії?");
    KeyboardButton button3 = new KeyboardButton("Як налаштувати гнучкий бюджет?");
    KeyboardButton button4 = new KeyboardButton(
        "Які ліцензії та дозволи необхідно отримати для відкриття власного бізнесу?");

    keyboardMarkup.setResizeKeyboard(true);

    row1.add(button1);
    row2.add(button2);
    row3.add(button3);
    row4.add(button4);

    keyboard.add(row1);
    keyboard.add(row2);
    keyboard.add(row3);
    keyboard.add(row4);

    keyboardMarkup.setKeyboard(keyboard);
    sendMessage7.setReplyMarkup(keyboardMarkup);

    try {
      absSender.execute(sendMessage7);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e);
    }
  }
}
