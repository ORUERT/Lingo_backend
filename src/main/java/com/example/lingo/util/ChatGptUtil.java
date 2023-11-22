package com.example.lingo.util;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import okhttp3.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

public class ChatGptUtil {
    private String apiKey = "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";

    public String getAnswer(String content){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //代理服务器的IP和端口号
        builder.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890)));
        OkHttpClient client = builder.build();

        JsonObject json = new JsonObject();
        json.addProperty("model", "gpt-3.5-turbo");
        json.add("messages", new JsonArray());
        json.addProperty("temperature", 0.8);
        json.addProperty("max_tokens", 50);
//        json.get("messages").getAsJsonArray().add(buildMessage("system", "あなたは日本語教師で、);
        json.get("messages").getAsJsonArray().add(buildMessage("system","You are a English teacher and are in a position to teach English to students."));
        json.get("messages").getAsJsonArray().add(buildMessage("user", content));
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                json.toString()
        );

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .post(body)
                .addHeader("Authorization", "Bearer " + apiKey)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
            System.out.println(jsonObject);
//            System.out.println(jsonObject.get("choices").getAsJsonArray().get(0).getAsJsonObject().get("message").getAsJsonObject().get("content"));
            return jsonObject.get("choices").getAsJsonArray().get(0).getAsJsonObject().get("message").getAsJsonObject().get("content").toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "something wrong in chatgptutil";
    }

    public static void main(String[] args) {
        ChatGptUtil chatGptUtil = new ChatGptUtil();
        System.out.println(chatGptUtil.getAnswer("I am a student."));

    }

    private static JsonObject buildMessage(String role, String content) {
        JsonObject message = new JsonObject();
        message.addProperty("role", role);
        message.addProperty("content", content);
        return message;
    }
}
