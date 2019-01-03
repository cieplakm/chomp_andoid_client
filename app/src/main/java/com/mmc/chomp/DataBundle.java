package com.mmc.chomp;



public class DataBundle {
    private String gameCreatedResponse;

    public void gameCreated(String gameId){
        this.gameCreatedResponse = gameId;
    }

    public String getGameId() {
        return "ffbc2602-3603-4137-ab3f-3a6d1525cc37";//"gameCreatedResponse.getGameId();
    }
}
