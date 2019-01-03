package com.mmc.chomp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
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


public class Board extends AppCompatActivity implements ViewListener {

    public String USER_ID = LoginKeeper.getInstance().getUserId();
    //@BindView(R.id.btn_start)
    Button start;
   // @BindView(R.id.gv_board)
    GridView grid;

    ProgressBar progressBar;
    private BoardViewAdapter baseAdapter;
    private Client client;
    private DataBundle bundle = new DataBundle();
    private OnChocolateChooseListener onChocolateChooseListener;
    private Button join;
    private Button create;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        //ButterKnife.bind(this);
        start = findViewById(R.id.btn_start);
        join = findViewById(R.id.btn_join);
        create = findViewById(R.id.btn_create);
        grid = findViewById(R.id.gv_board);
        progressBar = findViewById(R.id.progressBar);

        USER_ID = LoginKeeper.getInstance().getUserId2();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartClicked();
            }
        });

        client = new Client(USER_ID, new ChompWebSocketListener(this));
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.send(new CreateCommand("CREATE", USER_ID,5,5));
            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                client.send(new JoinCommand("JOIN", USER_ID,bundle.getGameId()));
            }
        });


    }

    //@OnClick(R.id.btn_start)
    public void onStartClicked(){
        client.send(new StartCommand("START", USER_ID, bundle.getGameId()));
    }

    @Override
    public void onGameCreated(final GameCreatedResponse response){

        baseAdapter = new BoardViewAdapter(this, new OnChocolateChooseListener() {
            @Override
            public void choosed(Chocolate chocolate) {
                client.send(new MoveCommand("MOVE", USER_ID, bundle.getGameId(), chocolate.getRow(), chocolate.getCol()));
            }
        }, response.getBoard());

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                grid.setAdapter(baseAdapter);

                grid.setNumColumns(response.getCols());
            }
        });



        bundle.gameCreated(response);
    }

    @Override
    public void onGameStarted(GameStartedResponse response){

    }

    @Override
    public void onNewJoiner(PlayerJoinedResponse response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Board.this, "dołączył do gry", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onGameOver(GameOverResponse response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Board.this, "Koniec gry", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    public void onMove(final MoveResponse move) {


        runOnUiThread(new Runnable() {
            @Override
            public void run() {


                    onChocolateChooseListener = new OnChocolateChooseListener() {
                        @Override
                        public void choosed(Chocolate chocolate) {
                            if (move.isMyTourTurn()){
                                client.send(new MoveCommand("MOVE", USER_ID, bundle.getGameId(), chocolate.getRow(), chocolate.getCol()));
                            }
                        }
                    };

                    baseAdapter = new BoardViewAdapter(Board.this, onChocolateChooseListener, move.getBoardState());


                grid.setAdapter(baseAdapter);
                grid.setNumColumns(5);

                baseAdapter.newSetOfData(move.getBoardState());

                if (move.isMyTourTurn()){
                    progressBar.setVisibility(View.GONE);
                }else {
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });


    }

    @Override
    public void onPlayerLeft(PlayerLeftResponse response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Board.this, "opuścił grę", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
