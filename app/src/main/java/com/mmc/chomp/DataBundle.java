package com.mmc.chomp;

import com.mmc.chomp.app.response.GameCreatedResponse;

class DataBundle {


    private GameCreatedResponse gameCreatedResponse;

    void gameCreated(GameCreatedResponse gameId){

        this.gameCreatedResponse = gameId;
    }

    public String getGameId() {
        return "ffbc2602-3603-4137-ab3f-3a6d1525cc37";//"gameCreatedResponse.getGameId();
    }
}
