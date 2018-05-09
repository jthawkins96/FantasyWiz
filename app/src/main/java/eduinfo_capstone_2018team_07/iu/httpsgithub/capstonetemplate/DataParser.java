package eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bgorman on 1/7/18.
 */

public class DataParser extends AsyncTask<Void, Void, Boolean> {
    Context c;
    String jsonData;
    ListView lv;

    ProgressDialog pd;
    ArrayList<Player> players = new ArrayList<>();

    public DataParser(Context c, String jsonData, ListView lv){
        this.c = c;
        this.jsonData = jsonData;
        this.lv = lv;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(c);
        pd.setTitle("Loading");
        pd.setMessage("Getting Players from Database...Please Wait");
        pd.show();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        pd.dismiss();
        if (result){
            lv.setAdapter(new CustomAdapter(c, players));
        }else {
            Toast.makeText(c, "Unable to Parse", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parseData(){
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            JSONObject jsonObject;

            players.clear();
            Player player;

            for (int i = 0; i < jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);

                String name = jsonObject.getString("PlayerName");
                String position = jsonObject.getString("Position");
                //String team = jsonObject.getString("team");
                String picture = jsonObject.getString("Pic_URL");
                //String projection = jsonObject.getString("Projections");

                player = new Player();

                player.setName(name);
                player.setPosition(position);
                //player.setTeam(team);
                player.setPicture(picture);
                //player.setProjections(projection);

                players.add(player);
            }
            return true;

        } catch (JSONException e){
            e.printStackTrace();
            return false;
        }
    }
}
