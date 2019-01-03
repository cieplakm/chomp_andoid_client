package com.mmc.chomp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    OkHttpClient client=new OkHttpClient();
    private int cols;
    private int rows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etRows = findViewById(R.id.etRows);
        final EditText etCols = findViewById(R.id.etCols);
        final SeekBar sbRows = findViewById(R.id.sbRows);
        final SeekBar sbCols = findViewById(R.id.sbCols);
        Button button = findViewById(R.id.button);

        sbRows.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Toast.makeText(MainActivity.this, "" + progress, Toast.LENGTH_SHORT).show();
                etRows.setText(" " + progress);
                rows = progress;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbCols.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Toast.makeText(MainActivity.this, "" + progress, Toast.LENGTH_SHORT).show();
                etCols.setText(" " + progress);

                cols = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbRows.incrementProgressBy(1);
        sbRows.setMax(7);
        sbCols.incrementProgressBy(1);
        sbCols.setMax(7);

       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, Board.class);
               intent.putExtra("rows", rows);
               intent.putExtra("cols", cols);
               startActivity(intent);
           }
       });



    }
}
