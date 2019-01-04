package com.mmc.chomp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmc.chomp.app.response.GameCreatedResponse;
import com.mmc.chomp.app.response.GameOverResponse;
import com.mmc.chomp.app.response.GameStartedResponse;
import com.mmc.chomp.app.response.MoveResponse;
import com.mmc.chomp.app.response.PlayerJoinedResponse;
import com.mmc.chomp.app.response.PlayerLeftResponse;

import java.io.IOException;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

class ChompWebSocketListener extends WebSocketListener {

    private ObjectMapper objectMapper = new ObjectMapper();
    private Game game;

    public ChompWebSocketListener(Game game) {

        this.game = game;
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        super.onMessage(webSocket, text);
        com.mmc.chomp.app.response.Response response = null;
        try {
            response = objectMapper.readValue(text, com.mmc.chomp.app.response.Response.class);
        } catch (IOException e) {
            e.printStackTrace();
        }


        switch (response.getType()) {
            case "GAME_CREATED":
                game.onGameCreated((GameCreatedResponse) response);
                break;
            case "GAME_STARTED":
                game.onGameStarted((GameStartedResponse) response);
                break;
            case "MOVE":
                MoveResponse move = (MoveResponse) response;
                game.onMove(move);
                break;
            case "GAME_OVER":
                game.onGameOver((GameOverResponse) response);
                break;
            case "PLAYER_JOINED":
                game.onNewJoiner((PlayerJoinedResponse) response);
                break;
            case "PLAYER_LEFT":
                game.onPlayerLeft((PlayerLeftResponse) response);
                break;
        }
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        super.onMessage(webSocket, bytes);
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        super.onClosing(webSocket, code, reason);
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        super.onClosed(webSocket, code, reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        super.onFailure(webSocket, t, response);
    }
}
