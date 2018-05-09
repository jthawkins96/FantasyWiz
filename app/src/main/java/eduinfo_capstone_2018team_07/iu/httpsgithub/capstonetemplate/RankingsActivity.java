package eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class RankingsActivity extends AppCompatActivity {
    String urlAddress = "http://cgi.soic.indiana.edu/~team07/searchPlayer.php";
    ListView listView;
    TextView noData, noServer;
    String query2;
    Button all, qb, rb, wr, te, dst, k;
    SearchView searchView;
    Button compare;
    TextView filter;
    Toolbar toolbar;
    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rankings);

        final Button home = findViewById(R.id.homeBtn);
        final Button rankings = findViewById(R.id.rankBtn);
        final Button news = findViewById(R.id.newsBtn);
        final Button profile = findViewById(R.id.profileBtn);

        listView = findViewById(R.id.listView);
        searchView = findViewById(R.id.searchView);
        noData = findViewById(R.id.noData);
        noServer = findViewById(R.id.noServer);
        filter = findViewById(R.id.filterTV);

        compare = findViewById(R.id.compareBtn);
        all = findViewById(R.id.ALL);
        qb = findViewById(R.id.QB);
        rb = findViewById(R.id.RB);
        wr = findViewById(R.id.WR);
        te = findViewById(R.id.TE);
        dst = findViewById(R.id.DST);
        k = findViewById(R.id.K);

        query2 = "";

        toolbar = findViewById(R.id.toolbar);
        username = findViewById(R.id.username);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("FantasyWiz");
        getUsername(username);

        new SenderReciever(RankingsActivity.this, urlAddress, "", query2, listView, noData, noServer).execute();


        compare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent compareIntent = new Intent(RankingsActivity.this, PlayerCompareList.class);
                RankingsActivity.this.startActivity(compareIntent);

            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(RankingsActivity.this, MainActivity.class);
                RankingsActivity.this.startActivity(homeIntent);
            }
        });
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newsIntent = new Intent(RankingsActivity.this, NewsActivity.class);
                RankingsActivity.this.startActivity(newsIntent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(RankingsActivity.this, ProfileActivity.class);
                RankingsActivity.this.startActivity(profileIntent);
            }
        });





        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query2 = "";
                new SenderReciever(RankingsActivity.this, urlAddress, "", query2, listView, noData, noServer).execute();
                filter.setText("ALL");
            }
        });

        qb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query2 = "QB";
                new SenderReciever(RankingsActivity.this, urlAddress, "", query2, listView, noData, noServer).execute();
                filter.setText("QB");
            }
        });

        rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query2 = "RB";
                new SenderReciever(RankingsActivity.this, urlAddress, "", query2, listView, noData, noServer).execute();
                filter.setText("RB");
            }
        });

        wr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query2 = "WR";
                new SenderReciever(RankingsActivity.this, urlAddress, "", query2, listView, noData, noServer).execute();
                filter.setText("WR");
            }
        });

        te.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query2 = "TE";
                new SenderReciever(RankingsActivity.this, urlAddress, "", query2, listView, noData, noServer).execute();
                filter.setText("TE");
            }
        });

        dst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query2 = "DST";
                new SenderReciever(RankingsActivity.this, urlAddress, "", query2, listView, noData, noServer).execute();
                filter.setText("DST");
            }
        });

        k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query2 = "K";
                new SenderReciever(RankingsActivity.this, urlAddress, "", query2, listView, noData, noServer).execute();
                filter.setText("K");
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query1) {
                SenderReciever sr = new SenderReciever(RankingsActivity.this, urlAddress, query1, query2, listView, noData, noServer);
                sr.execute();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query1) {
                SenderReciever sr = new SenderReciever(RankingsActivity.this, urlAddress, query1, query2, listView, noData, noServer);
                sr.execute();
                return false;
            }
        });
    }
    public void getUsername(TextView username){
        SharedPreferences preferences = getSharedPreferences("userName", MODE_PRIVATE);
        String userName = preferences.getString("Username", "");
        username.setText(userName);
    }
}
