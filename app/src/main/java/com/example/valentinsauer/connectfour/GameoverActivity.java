package com.example.valentinsauer.connectfour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class GameoverActivity extends AppCompatActivity implements View.OnClickListener {

    int gamemode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        Button b = (Button) findViewById(R.id.button_newgame);
        b.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras.containsKey("gamestate")) {
            TextView text_game_over = (TextView) findViewById(R.id.text_gameover);
            int gameState = extras.getInt("gamestate");
            gamemode = extras.getInt("gamemode");

            if(gameState == Game.YELLOW) {
                text_game_over.setText("GAME OVER: YELLOW WINS");
            } else if (gameState == Game.RED) {
                text_game_over.setText("GAME OVER: RED WINS");
            } else{
                text_game_over.setText("GAME OVER: DRAW");
            }
        }

    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("player", gamemode);
        startActivity(intent);
    }
}
