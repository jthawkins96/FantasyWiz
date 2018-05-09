package eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class PlayerCompareList extends AppCompatActivity {

    String urlAddress = "http://cgi.soic.indiana.edu/~team07/searchPlayer.php";
    SearchView searchView;
    ListView listView;
    TextView noData, noServer;
    Button compare;
    Button home, rankings, news, profile;
    Toolbar toolbar;
    TextView username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_compare_list);

        listView = findViewById(R.id.listView);
        searchView = findViewById(R.id.searchView2);
        noData = findViewById(R.id.noData);
        noServer = findViewById(R.id.noServer);
        compare = findViewById(R.id.compareBtn);

        home = findViewById(R.id.homeBtn);
        profile = findViewById(R.id.profileBtn);
        rankings = findViewById(R.id.rankBtn);
        news = findViewById(R.id.newsBtn);

        CompareReciever cr = new CompareReciever(PlayerCompareList.this, urlAddress, "", listView, compare, noData, noServer);
        cr.execute();

        toolbar = findViewById(R.id.toolbar);
        username = findViewById(R.id.username);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("FantasyWiz");
        getUsername(username);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(PlayerCompareList.this, MainActivity.class);
                PlayerCompareList.this.startActivity(homeIntent);
            }
        });
        rankings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rankingsIntent = new Intent(PlayerCompareList.this, RankingsActivity.class);
                PlayerCompareList.this.startActivity(rankingsIntent);
            }
        });
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newsIntent = new Intent(PlayerCompareList.this, NewsActivity.class);
                PlayerCompareList.this.startActivity(newsIntent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(PlayerCompareList.this, ProfileActivity.class);
                PlayerCompareList.this.startActivity(profileIntent);
            }
        });
    }
    public void getUsername(TextView username){
        SharedPreferences preferences = getSharedPreferences("userName", MODE_PRIVATE);
        String userName = preferences.getString("Username", "");
        username.setText(userName);
    }
}
