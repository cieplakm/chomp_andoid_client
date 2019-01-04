package com.mmc.chomp;

import com.mmc.chomp.app.response.GameCreatedResponse;
import com.mmc.chomp.app.response.GameOverResponse;
import com.mmc.chomp.app.response.GameStartedResponse;
import com.mmc.chomp.app.response.MoveResponse;
import com.mmc.chomp.app.response.PlayerJoinedResponse;
import com.mmc.chomp.app.response.PlayerLeftResponse;

public interface Game {

    void onGameCreated(GameCreatedResponse response);

    void onNewJoiner(PlayerJoinedResponse response);

    void onGameStarted(GameStartedResponse response);

    void onMove(MoveResponse move);

    void onGameOver(GameOverResponse response);

    void onPlayerLeft(PlayerLeftResponse response);
}
