package com.example.chartbot.query;

public class QueryMessage {

  public static String query1 = """
      {
          "model": "gpt-3.5-turbo",
          "max_tokens": 60,
          "messages": [
              {
                  "role": "system",
                  "content": "Яка зараз сама моржинальна ніша? Напиши тільки назви топ 3"
              }
          ]
      }
      """;
  public static String query2 = """
      {
          "model": "gpt-3.5-turbo",
          "max_tokens": 500,
          "messages": [
              {
                  "role": "system",
                  "content": "Які найефективніші рекламні кампанії для стартапів? Топ 3. Опишіть стратегію коротко у 350 токенах "
              }
          ]
      }
      """;
  public static String query3 = """
      {
          "model": "gpt-3.5-turbo",
          "max_tokens": 400,
          "messages": [
              {
                  "role": "system",
                  "content": "Як налаштувати гнучкий бюджет? Опишіть стратегію коротко у 350 токенах"
              }
          ]
      }
      """;
  public static String query4 = """
      {
          "model": "gpt-3.5-turbo",
          "max_tokens": 400,
          "messages": [
              {
                  "role": "system",
                  "content": "Які ліцензії та дозволи необхідно отримати для відкриття власного бізнесу?. Дай коротку відповідь у 350 токенах"
              }
          ]
      }
      """;
}

