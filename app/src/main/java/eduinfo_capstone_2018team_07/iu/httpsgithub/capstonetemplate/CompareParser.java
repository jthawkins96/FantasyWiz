package eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.CircularProgressDrawable;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bgorman on 1/26/18.
 */

public class CompareParser extends AsyncTask<Void, Void, Boolean> {
    Context c;
    String jsonData;
    ListView lv;
    Button compare;
    ProgressDialog pd;

    ArrayList<PlayerModel> players = new ArrayList<>();

    public CompareParser(Context c, String jsonData, ListView lv, Button compare) {
        this.c = c;
        this.jsonData = jsonData;
        this.lv = lv;
        this.compare = compare;
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
        return parseData();
    }

    @Override
    protected void onPostExecute(Boolean results) {
        pd.dismiss();
        super.onPostExecute(results);
        if (results){
            lv.setAdapter(new CompareAdapter(c, players, compare));
        }else {
            Toast.makeText(c, "Unable to Parse", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parseData(){
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            JSONObject jsonObject;

            players.clear();
            PlayerModel player;

            for (int i=0; i<jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);

                String name = jsonObject.getString("PlayerName");

                player = new PlayerModel();

                player.setName(name);
                player.setChecked(false);

                players.add(player);

            }
            return true;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;

    }
}
