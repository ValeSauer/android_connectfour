package com.example.valentinsauer.connectfour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout[] columns = new LinearLayout[7];
    private Game game;
    private int gameMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        game = new Game();

        this.gameMode = getIntent().getExtras().getInt("player");

        LinearLayout toolbar = (LinearLayout) findViewById(R.id.game_toolbar);
        for (int i = 0; i < 7; i++) {
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);

            Button button = new Button(this);
            button.setText("" + (i + 1));
            button.setTag(i);
            button.setLayoutParams(llp);
            button.setOnClickListener(this);
            toolbar.addView(button);
        }

        LinearLayout columnsLayout = (LinearLayout) findViewById(R.id.game_columns);
        for (int j = 0; j < columnsLayout.getChildCount(); j++) {
            this.columns[j] = (LinearLayout) columnsLayout.getChildAt(j);
        }
    }

    public ImageView createImageView(int player){
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        ImageView iv = new ImageView(this);
        iv.setLayoutParams(llp);
        if(player == game.RED) {
            iv.setImageResource(R.drawable.red);
        }else{
            iv.setImageResource(R.drawable.yellow);
        }
        return iv;
    }

    @Override
    public void onClick(View view) {
        int columnIndex = Integer.parseInt(view.getTag().toString());
        Cell cell = game.move(columnIndex);
        if(cell.isValid()) {
            this.columns[cell.getCol()].addView(createImageView(cell.getStatus()), 0);
        }

        if (game.checkFinish(cell) != 0){
            showGameOver(cell);
            return;
        }

        if(this.gameMode == 1){
            Cell cellComputer = game.computerMove();
            this.columns[cellComputer.getCol()].addView(createImageView(cellComputer.getStatus()), 0);

            if (game.checkFinish(cellComputer) != 0){
                showGameOver(cell);
                return;
            }
        }

    }

    public void showGameOver(Cell cell){
        Intent intent = new Intent(this, GameoverActivity.class);
        intent.putExtra("gamestate", cell.getStatus());
        intent.putExtra("gamemode", this.gameMode);
        startActivity(intent);
    }

}
