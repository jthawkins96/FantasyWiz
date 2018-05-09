package eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayerCompare extends AppCompatActivity {
    Button compare;
    String player1, player2;
    TextView p1TV, p2TV;
    Toolbar toolbar;
    TextView username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_compare);

        compare = findViewById(R.id.compareBtn);
        p1TV = findViewById(R.id.player1TV);
        p2TV = findViewById(R.id.player2TV);

        toolbar = findViewById(R.id.toolbar);
        username = findViewById(R.id.username);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("FantasyWiz");
        getUsername(username);


        Intent intent = getIntent();

        player1 = intent.getExtras().getString("PLAYER1_KEY");
        player2 = intent.getExtras().getString("PLAYER2_KEY");

        p1TV.setText(player1);
        p2TV.setText(player2);


        compare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayerCompare.this, PlayerCompareList.class);
                PlayerCompare.this.startActivity(intent);
            }
        });
    }

    public void getUsername(TextView username){
        SharedPreferences preferences = getSharedPreferences("userName", MODE_PRIVATE);
        String userName = preferences.getString("Username", "");
        username.setText(userName);
    }
}
