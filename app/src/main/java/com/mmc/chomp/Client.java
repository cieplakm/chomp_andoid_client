package com.mmc.chomp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class Client {

    private Request request;
    private OkHttpClient client = new OkHttpClient();
    private ObjectMapper objectMapper = new ObjectMapper();
    private WebSocket webSocket;
    private String userId;

    private ChompWebSocketListener chompWebSocketListener;

    public Client(String userId, ChompWebSocketListener chompWebSocketListener) {
        this.userId = userId;
        this.chompWebSocketListener = chompWebSocketListener;
        setup();
    }

    public Client(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private void setup() {
        request = new Request.Builder()
                .url("ws://10.0.2.2:8080/chomp")
                .header("UserId", userId).build();


        webSocket = client.newWebSocket(request, chompWebSocketListener);

        client.dispatcher().executorService().shutdown();
    }

    public void send(Object o){
        String msg = "{}";
        try {
            msg = objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        webSocket.send(msg);
    }

}
