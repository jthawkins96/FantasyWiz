package eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
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
 * Created by bgorman on 1/17/18.
 */

public class SenderReciever extends AsyncTask<Void, Void, String>{
    Context c;
    String urlAddress;
    String query1;
    String query2;
    ListView listView;
    TextView noData, noServer;

    public SenderReciever(Context c, String urlAddress, String query1, String query2, ListView listView, TextView...textViews){
        this.c = c;
        this.urlAddress = urlAddress;
        this.query1 = query1;
        this.query2 = query2;
        this.listView = listView;
        this.noData = textViews[0];
        this.noServer = textViews[1];
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {
        return this.sendAndReceive();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        listView.setAdapter(null);
        if (s != null){
            if (!s.contains("null")){
                DataParser p = new DataParser(c, s, listView);
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
        }
        try {
            OutputStream os = conn.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write(new DataPackager(query1, query2).packageData());
            bw.flush();

            bw.close();
            os.close();

            int responseCode = conn.getResponseCode();

            if (conn.getResponseCode()==conn.HTTP_OK){

                InputStream is  = conn.getInputStream();
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
        return null;
    }
}
