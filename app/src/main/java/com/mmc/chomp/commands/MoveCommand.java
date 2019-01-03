package com.mmc.chomp.commands;

public class MoveCommand {
    private String requestType;
    private String userId;
    private String gameId;
    private int row;
    private int col;

    public MoveCommand(String requestType, String userId, String gameId, int row, int col) {
        this.requestType = requestType;
        this.userId = userId;
        this.gameId = gameId;
        this.row = row;
        this.col = col;
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

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
