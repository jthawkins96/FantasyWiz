package eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

public class ImportTeam extends AppCompatActivity {

    String urlAddress = "http://cgi.soic.indiana.edu/~team07/searchPlayer.php";
    SearchView searchView;
    ListView listView;
    TextView noData, noServer;
    Button Import;
    Button home, rankings, news, profile;
    Toolbar toolbar;
    TextView username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_team);

        listView = findViewById(R.id.listView);
        searchView = findViewById(R.id.searchView2);
        noData = findViewById(R.id.noData);
        noServer = findViewById(R.id.noServer);
        Import = findViewById(R.id.importBtn);

        home = findViewById(R.id.homeBtn);
        profile = findViewById(R.id.profileBtn);
        rankings = findViewById(R.id.rankBtn);
        news = findViewById(R.id.newsBtn);

        ImportReceiver importReceiver = new ImportReceiver(ImportTeam.this, urlAddress, "", listView, Import, noData, noServer);
        importReceiver.execute();

        toolbar = findViewById(R.id.toolbar);
        username = findViewById(R.id.username);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("FantasyWiz");
        getUsername(username);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(ImportTeam.this, MainActivity.class);
                ImportTeam.this.startActivity(homeIntent);
            }
        });
        rankings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rankingsIntent = new Intent(ImportTeam.this, RankingsActivity.class);
                ImportTeam.this.startActivity(rankingsIntent);
            }
        });
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newsIntent = new Intent(ImportTeam.this, NewsActivity.class);
                ImportTeam.this.startActivity(newsIntent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(ImportTeam.this, ProfileActivity.class);
                ImportTeam.this.startActivity(profileIntent);
            }
        });
    }
    public void getUsername(TextView username){
        SharedPreferences preferences = getSharedPreferences("userName", MODE_PRIVATE);
        String userName = preferences.getString("Username", "");
        username.setText(userName);
    }
}
