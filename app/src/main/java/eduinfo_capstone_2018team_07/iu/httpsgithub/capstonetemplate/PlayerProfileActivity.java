package eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlayerProfileActivity extends AppCompatActivity {
    Button home, news, profile, rankings;
    TextView nameTV, positionTV, teamTV;
    ImageView imageView;
    TableLayout projectionData;
    TableLayout statisticsData;
    Button add;
    Switch dataSwitch;
    Toolbar toolbar;
    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_profile);

        home = findViewById(R.id.homeBtn);
        news = findViewById(R.id.newsBtn);
        profile = findViewById(R.id.profileBtn);
        rankings = findViewById(R.id.rankBtn);
        nameTV = findViewById(R.id.nameTV);
        positionTV = findViewById(R.id.positionTV);
        teamTV = findViewById(R.id.teamTV);
        imageView = findViewById(R.id.headshotIV);
        dataSwitch = findViewById(R.id.playerSwitch);
        add = findViewById(R.id.addBtn);

        toolbar = findViewById(R.id.toolbar);
        username = findViewById(R.id.username);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("FantasyWiz");
        getUsername(username);

        //Get Intent
        Intent intent = getIntent();

        //Get Data
        final String name = intent.getExtras().getString("NAME_KEY");
        String position = intent.getExtras().getString("POSITION_KEY");
        //String team = intent.getExtras().getString("TEAM_KEY");
        final String picture = intent.getExtras().getString("PICTURE_KEY");

        //Load Data
        nameTV.setText(name);
        positionTV.setText(position);
        //teamTV.setText(team);

        Picasso.with(getApplicationContext()).load(picture).into(imageView);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(PlayerProfileActivity.this, MainActivity.class);
                PlayerProfileActivity.this.startActivity(homeIntent);
            }
        });
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newsIntent = new Intent(PlayerProfileActivity.this, NewsActivity.class);
                PlayerProfileActivity.this.startActivity(newsIntent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(PlayerProfileActivity.this, ProfileActivity.class);
                PlayerProfileActivity.this.startActivity(profileIntent);
            }
        });
        rankings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rankIntent = new Intent(PlayerProfileActivity.this, RankingsActivity.class);
                PlayerProfileActivity.this.startActivity(rankIntent);
            }
        });

        dataSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    projectionData.setVisibility(View.VISIBLE);
                    statisticsData.setVisibility(View.INVISIBLE);
                }else {
                    projectionData.setVisibility(View.INVISIBLE);
                    statisticsData.setVisibility(View.VISIBLE);
                }
            }
        });

        //Response Listener for projections. Wraps SQL data into TableRow and appends to the page.
        Response.Listener<String> projectionListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    projectionData = findViewById(R.id.projData);
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject;
                    for (int i=0; i<jsonArray.length(); i++){
                        jsonObject = jsonArray.getJSONObject(i);


                        String year = jsonObject.getString("Year");
                        String site = jsonObject.getString("Platform");
                        String real = jsonObject.getString("RealPts");
                        String proj = jsonObject.getString("ProjPts");
                        String acc = jsonObject.getString("Accuracy");


                        TableRow row = new TableRow(PlayerProfileActivity.this);

                        TextView yearPD = new TextView(PlayerProfileActivity.this);
                        yearPD.setText(year);
                        yearPD.setTextSize(20);
                        yearPD.setPadding(20, 10, 20, 0);
                        row.addView(yearPD);

                        TextView sitePD = new TextView(PlayerProfileActivity.this);
                        sitePD.setText(site);
                        sitePD.setTextSize(20);
                        sitePD.setPadding(20, 10, 20, 0);
                        row.addView(sitePD);

                        TextView realPD = new TextView(PlayerProfileActivity.this);
                        realPD.setText(real);
                        realPD.setTextSize(20);
                        realPD.setPadding(20, 10, 20, 0);
                        row.addView(realPD);

                        TextView projPD = new TextView(PlayerProfileActivity.this);
                        projPD.setText(proj);
                        projPD.setTextSize(20);
                        projPD.setPadding(20, 10, 20, 0);
                        row.addView(projPD);

                        TextView accPD = new TextView(PlayerProfileActivity.this);
                        accPD.setText(acc);
                        accPD.setTextSize(20);
                        accPD.setPadding(20, 10, 20, 0);
                        row.addView(accPD);

                        projectionData.addView(row);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        final Response.Listener<String> addListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(PlayerProfileActivity.this, "Added Player "+name, Toast.LENGTH_LONG).show();
            }
        };
        final String user = username.getText().toString();

        //Initalize Volley Before Add button is given functionality
        final RequestQueue queue = Volley.newRequestQueue(PlayerProfileActivity.this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PlayerProfileActivity.this);
                builder.setTitle("Confirm Player");
                builder.setMessage("Are you sure you want to add this player to your roster?");
                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addPlayerRequest request = new addPlayerRequest(user, name, addListener);
                        queue.add(request);

                        dialog.cancel();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        //Response Listener for statistics. Wraps SQL data into TableRow and appends to the page.

        statisticsData = findViewById(R.id.statData);

        QBResponse qbResponse = new QBResponse(PlayerProfileActivity.this, statisticsData, teamTV);
        Response.Listener<String> statisticsListener = qbResponse.statisticsListener;



        StatisticsRequest statisticsRequest = new StatisticsRequest(name, statisticsListener);
        ProjectionRequest projectionRequest = new ProjectionRequest(name, projectionListener);



        queue.add(statisticsRequest);
        queue.add(projectionRequest);
    }
    public void getUsername(TextView username){
        SharedPreferences preferences = getSharedPreferences("userName", MODE_PRIVATE);
        String userName = preferences.getString("Username", "");
        username.setText(userName);
    }
}
