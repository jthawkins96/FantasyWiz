package eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by bgorman on 1/7/18.
 */

public class Downloader extends AsyncTask<Void, Void, String>{
    Context c;
    String urlAddress;
    ListView lv;
    ProgressDialog pd;

    public Downloader(Context c, String urlAddress, ListView lv){
        this.c = c;
        this.urlAddress = urlAddress;
        this.lv = lv;
    }


    @Override
    protected String doInBackground(Void... params) {
        return this.downloadData();
    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);
        pd.dismiss();
        if (jsonData.startsWith("Error")){
            Toast.makeText(c,"Unsuccessful "+jsonData,Toast.LENGTH_SHORT).show();
        }else{
            new DataParser(c,jsonData,lv).execute();

        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(c);
        pd.setTitle("Retrieve");
        pd.setMessage("Retrieving Data");
        pd.show();
    }
    private String downloadData(){
        Object connection = Connector.connect(urlAddress);
        if (connection.toString().startsWith("Error")){
            return connection.toString();
        }
        try{
            //Establish connection
            HttpURLConnection con = (HttpURLConnection) connection;
            if (con.getResponseCode() == con.HTTP_OK){
                //Get Input from Steam
                InputStream is = new BufferedInputStream(con.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                String line;
                StringBuffer jsonData = new StringBuffer();

                //Read Data
                while ((line=br.readLine()) !=null){
                    jsonData.append(line+"\n");
                }
                //Close
                br.close();
                is.close();

                return jsonData.toString();
            }else {
                return "Error "+con.getResponseMessage();
            }
        }catch (IOException e){
            e.printStackTrace();
            return "Error "+e.getMessage();
        }
    }
}
