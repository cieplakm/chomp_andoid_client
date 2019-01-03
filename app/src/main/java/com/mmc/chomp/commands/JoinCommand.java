package com.mmc.chomp.commands;

public class JoinCommand {
    private String requestType;
    private String userId;
    private String gameId;

    public JoinCommand(String requestType, String userId, String gameId) {
        this.requestType = requestType;
        this.userId = userId;
        this.gameId = gameId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

}
