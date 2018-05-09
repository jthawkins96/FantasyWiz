package eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button home = findViewById(R.id.homeBtn);
        final Button rankings = findViewById(R.id.rankBtn);
        final Button news = findViewById(R.id.newsBtn);
        final Button profile = findViewById(R.id.profileBtn);

        toolbar = findViewById(R.id.toolbar);
        username = findViewById(R.id.username);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("FantasyWiz");
        getUsername(username);

        rankings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rankingsIntent = new Intent(MainActivity.this, RankingsActivity.class);
                MainActivity.this.startActivity(rankingsIntent);
            }
        });
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newsIntent = new Intent(MainActivity.this, NewsActivity.class);
                MainActivity.this.startActivity(newsIntent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(MainActivity.this, ProfileActivity.class);
                MainActivity.this.startActivity(profileIntent);
            }
        });
    }

    public void getUsername(TextView view){
        SharedPreferences preferences = getSharedPreferences("userName", MODE_PRIVATE);
        String username = preferences.getString("Username", "");
        view.setText(username);
    }
}
