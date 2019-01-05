package com.mmc.chomp.commands;

public class WantToPlayCommand {
    private String requestType;
    private String userId;
    private int rows;
    private int cols;

    public WantToPlayCommand(String userId, int rows, int cols) {
        this.requestType = "WANT_TO_PLAY";
        this.userId = userId;
        this.rows = rows;
        this.cols = cols;
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

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }
}
