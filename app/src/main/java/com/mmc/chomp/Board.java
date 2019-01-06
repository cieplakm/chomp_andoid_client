package com.mmc.chomp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.mmc.chomp.app.response.GameCreatedResponse;
import com.mmc.chomp.app.response.GameOverResponse;
import com.mmc.chomp.app.response.GameStartedResponse;
import com.mmc.chomp.app.response.MoveResponse;
import com.mmc.chomp.app.response.PlayerJoinedResponse;
import com.mmc.chomp.app.response.PlayerLeftResponse;
import com.mmc.chomp.commands.CreateCommand;
import com.mmc.chomp.commands.JoinCommand;
import com.mmc.chomp.commands.MoveCommand;
import com.mmc.chomp.commands.StartCommand;
import com.mmc.chomp.commands.WantToPlayCommand;

public class Board extends AppCompatActivity implements Game {

    public static final String USER_ID = IoC.getUserId();

    // @BindView(R.id.gv_board)
    GridView grid;

    ProgressBar progressBar;

    private BoardViewAdapter baseAdapter;

    private Client client;

    private DataBundle bundle = new DataBundle();

    private OnChocolateChooseListener onChocolateChooseListener;

    private AlertDialog alertDialog;
    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        IoC.createClient(this);
        client = IoC.getClient();

        //ButterKnife.bind(this);
        grid = findViewById(R.id.gv_board);
        progressBar = findViewById(R.id.progressBar);
        tvInfo = findViewById(R.id.tvInfo);
        tvInfo.setText("Czekaj na przeciwnika...");

        Bundle extras = getIntent().getExtras();

        int rows = extras.getInt("rows", 5);
        int cols = extras.getInt("cols", 5);

        client.send(new WantToPlayCommand(USER_ID, rows, cols));

        baseAdapter = new BoardViewAdapter(Board.this);
        grid.setAdapter(baseAdapter);
    }

    @Override
    public void onGameCreated(final GameCreatedResponse response) {

    }

    @Override
    public void onGameStarted(final GameStartedResponse response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setupProgressBar(response.isMyTurn());
                tvInfo.setText(response.isMyTurn()?"Twój ruch!":"Czekaj na ruch przeciwnika");

                onChocolateChooseListener = new OnChocolateChooseListener() {
                    @Override
                    public void onChoose(Chocolate chocolate) {
                        if (response.isMyTurn()) {
                            client.send(new MoveCommand("MOVE", USER_ID, bundle.getGameId(), chocolate.getRow(), chocolate.getCol()));
                        }
                    }
                };

                baseAdapter.setListener(onChocolateChooseListener);
                setupGrid(response.getGameState().getBoard(), response.getGameState().getCols());

                if (alertDialog != null) {
                    alertDialog.hide();
                }
            }
        });
    }

    @Override
    public void onNewJoiner(PlayerJoinedResponse response) {

    }

    @Override
    public void onGameOver(final GameOverResponse response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showMessage(response.isWon() ? "Wygrales.":"Przegrales. Twój ranking to: 124324 pkt." );

            }
        });
    }

    @Override
    public void onMove(final MoveResponse response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                onChocolateChooseListener = new OnChocolateChooseListener() {
                    @Override
                    public void onChoose(Chocolate chocolate) {
                        if (response.isMyTourTurn()) {
                            client.send(new MoveCommand("MOVE", USER_ID, bundle.getGameId(), chocolate.getRow(), chocolate.getCol()));
                        }
                    }
                };

                baseAdapter.setListener(onChocolateChooseListener);

                setupGrid(response.getGameState().getBoard(), response.getGameState().getCols());

                boolean myTourTurn = response.isMyTourTurn();
                setupProgressBar(myTourTurn);
                tvInfo.setText(response.isMyTourTurn()?"Twój ruch!":"Czekaj na ruch przeciwnika");

            }
        });

    }

    @Override
    public void onPlayerLeft(PlayerLeftResponse response) {

    }

    private void setupProgressBar(boolean myTourTurn) {
        if (myTourTurn) {
            progressBar.setVisibility(View.GONE);
            //tvInfo.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.VISIBLE);
            //tvInfo.setVisibility(View.VISIBLE);
        }
    }

    private void setupGrid(boolean[][] board, int cols) {
        grid.setNumColumns(cols);
        baseAdapter.newSetOfData(board);
    }

    public AlertDialog showMessage(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Board.this.finish();
                    }
                });
        return builder.create();
    }

}
