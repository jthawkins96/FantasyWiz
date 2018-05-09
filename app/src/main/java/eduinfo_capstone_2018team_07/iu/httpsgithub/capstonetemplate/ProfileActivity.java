package eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    Button home, rankings, news, profile, impt, edit;
    RadioButton pass, rcv, rush;
    TableLayout userStats;
    Toolbar toolbar;
    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        home = findViewById(R.id.homeBtn);
        rankings = findViewById(R.id.rankBtn);
        news = findViewById(R.id.newsBtn);
        profile = findViewById(R.id.profileBtn);

        impt = findViewById(R.id.importTeamBtn);
        edit = findViewById(R.id.editProfileBtn);

        toolbar = findViewById(R.id.toolbar);
        username = findViewById(R.id.username);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("FantasyWiz");
        getUsername(username);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(ProfileActivity.this, MainActivity.class);
                ProfileActivity.this.startActivity(homeIntent);
            }
        });
        rankings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rankingsIntent = new Intent(ProfileActivity.this, RankingsActivity.class);
                ProfileActivity.this.startActivity(rankingsIntent);
            }
        });
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newsIntent = new Intent(ProfileActivity.this, NewsActivity.class);
                ProfileActivity.this.startActivity(newsIntent);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, EditProfile.class);
                ProfileActivity.this.startActivity(intent);
            }
        });
        impt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ImportTeam.class);
                ProfileActivity.this.startActivity(intent);
            }
        });
    }

    public void getUsername(TextView username){
        SharedPreferences preferences = getSharedPreferences("userName", MODE_PRIVATE);
        String userName = preferences.getString("Username", "");
        username.setText(userName);
    }
}
