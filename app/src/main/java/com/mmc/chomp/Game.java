package com.mmc.chomp;

import com.mmc.chomp.app.response.GameCreatedResponse;
import com.mmc.chomp.app.response.GameOverResponse;
import com.mmc.chomp.app.response.GameStartedResponse;
import com.mmc.chomp.app.response.MoveResponse;

public interface Game {
    void onGameCreatedCreated(GameCreatedResponse response);

    void onGameStarted(GameStartedResponse response);

    void onMove(MoveResponse move);

    void onGameOver(GameOverResponse response);
}
