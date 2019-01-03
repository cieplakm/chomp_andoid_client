package com.mmc.chomp;

import com.mmc.chomp.app.response.GameCreatedResponse;
import com.mmc.chomp.app.response.GameOverResponse;
import com.mmc.chomp.app.response.GameStartedResponse;
import com.mmc.chomp.app.response.MoveResponse;
import com.mmc.chomp.app.response.PlayerJoinedResponse;
import com.mmc.chomp.app.response.PlayerLeftResponse;

public interface ViewListener {

    void onGameCreated(GameCreatedResponse response);

    void onGameStarted(GameStartedResponse response);

    void onNewJoiner(PlayerJoinedResponse response);

    void onGameOver(GameOverResponse response);

    void onMove(MoveResponse move);

    void onPlayerLeft(PlayerLeftResponse response);
}
