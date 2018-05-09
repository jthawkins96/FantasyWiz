package eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bgorman on 2/3/18.
 */

public class ImportParser extends AsyncTask<Void, Void, Boolean> {

        Context c;
        String jsonData;
        ListView lv;
        Button Import;
        ProgressDialog pd;

        ArrayList<PlayerModel> players = new ArrayList<>();

    public ImportParser(Context c, String jsonData, ListView lv, Button anImport) {
        this.c = c;
        this.jsonData = jsonData;
        this.lv = lv;
        Import = anImport;
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
            lv.setAdapter(new ImportAdapter(c, players, Import));
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
