package com.example.chartbot.handler;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public  class ChatGPT {

  // Встановіть ваші ключі API та організаційні ключі тут
  private static final String API_KEY = "";
  private static final String ORG_KEY = "";
  private static final String REQUEST_JSON_PATH = "C:\\Users\\lesja\\Desktop\\Забезпечення якості програмних продуктів\\Chart\\src\\main\\java\\org\\example\\Request.json";

  public static void listTokens() {
    try {
      // URL для отримання моделей
      URL url = new URL("https://api.openai.com/v1/models");
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      con.setRequestProperty("Content-Type", "application/json");
      con.setRequestProperty("Accept", "application/json");
      con.setRequestProperty("Authorization", "Bearer " + API_KEY);
      con.setRequestProperty("OpenAI-Organization", ORG_KEY);

      int responseCode = con.getResponseCode();
      System.out.println("Response Code : " + responseCode);

      if (responseCode == HttpURLConnection.HTTP_OK) {
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
          response.append(inputLine);
        }
        in.close();
        System.out.println(response);
      } else {
        System.out.println("GET request failed");
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public static String prompts(String inputJson) {
    try {
      URL url = new URL("https://api.openai.com/v1/chat/completions");
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("POST");
      con.setRequestProperty("Content-Type", "application/json");
      con.setRequestProperty("Accept", "application/json");
      con.setRequestProperty("Authorization", "Bearer " + API_KEY);
      con.setRequestProperty("OpenAI-Organization", ORG_KEY);
      con.setDoOutput(true);

      String jsonInputString = inputJson;

      try (OutputStream os = con.getOutputStream()) {
        byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
        os.write(input, 0, input.length);
      }

      int responseCode = con.getResponseCode();
      //System.out.println("Response Code : " + responseCode);

      if (responseCode == HttpURLConnection.HTTP_OK) {
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
          response.append(inputLine);
        }
        in.close();

        // Розпарсити JSON відповідь
        JSONObject jsonResponse = new JSONObject(response.toString());
        String messageContent = jsonResponse
            .getJSONArray("choices")
            .getJSONObject(0)
            .getJSONObject("message")
            .getString("content");
        return (messageContent.trim());
      } else {
        System.out.println("POST request failed");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
          response.append(inputLine);
        }
        in.close();
        System.out.println(response);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return ("Помилка. Дай відповідь пізніше");
  }
}

