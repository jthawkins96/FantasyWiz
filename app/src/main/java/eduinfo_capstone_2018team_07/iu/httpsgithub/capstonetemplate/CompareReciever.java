package eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

/**
 * Created by bgorman on 1/26/18.
 */

public class CompareReciever extends AsyncTask<Void, Void, String> {

    Context c;
    String urlAddress;
    String query;
    ListView listView;
    TextView noData, noServer;
    Button compare;

    public CompareReciever(Context c, String urlAddress, String query, ListView listView, Button compare, TextView...details) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.query = query;
        this.listView = listView;
        this.noData = details[0];
        this.noServer = details[1];
        this.compare = compare;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {
        return sendAndReceive();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s != null){
            if (!s.contains("null")){
                CompareParser p = new CompareParser(c, s, listView, compare);
                p.execute();
                noData.setVisibility(View.INVISIBLE);
                noServer.setVisibility(View.INVISIBLE);
            }else {
                noServer.setVisibility(View.INVISIBLE);
                noData.setVisibility(View.VISIBLE);
            }
        }else {
            noData.setVisibility(View.INVISIBLE);
            noServer.setVisibility(View.VISIBLE);
        }
    }

    private String sendAndReceive(){
        HttpURLConnection conn = Connector.connect(urlAddress);

        if (conn==null){
            return null;
        }else{
            try {
                OutputStream os = conn.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
                bw.write(new  ComparePackage(query).packageData());
                bw.flush();

                bw.close();
                os.close();

                int responseCode = conn.getResponseCode();

                if (responseCode == conn.HTTP_OK){
                    InputStream is = conn.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));

                    String line;
                    StringBuffer response = new StringBuffer();

                    if (br != null){
                        while ((line=br.readLine()) != null){
                            response.append(line+"\n");
                        }
                    }else {
                        return null;
                    }
                    return response.toString();
                }else {
                    return String.valueOf(responseCode);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

