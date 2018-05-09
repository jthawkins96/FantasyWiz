package eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

public class NewsActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        final Button home = findViewById(R.id.homeBtn);
        final Button rankings = findViewById(R.id.rankBtn);
        final Button news = findViewById(R.id.newsBtn);
        final Button profile = findViewById(R.id.profileBtn);

        toolbar = findViewById(R.id.toolbar);
        username = findViewById(R.id.username);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("FantasyWiz");
        getUsername(username);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(NewsActivity.this, MainActivity.class);
                NewsActivity.this.startActivity(homeIntent);
            }
        });
        rankings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rankingsIntent = new Intent(NewsActivity.this, RankingsActivity.class);
                NewsActivity.this.startActivity(rankingsIntent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(NewsActivity.this, ProfileActivity.class);
                NewsActivity.this.startActivity(profileIntent);
            }
        });
    }
    public void getUsername(TextView view){
        SharedPreferences preferences = getSharedPreferences("userName", MODE_PRIVATE);
        String username = preferences.getString("Username", "");
        view.setText(username);
    }
}
