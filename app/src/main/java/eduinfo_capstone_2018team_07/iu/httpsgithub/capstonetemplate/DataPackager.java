package eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

/**
 * Created by bgorman on 1/17/18.
 */

public class DataPackager {
    String query1;
    String query2;
    public DataPackager(String query1, String query2){
        this.query1 = query1;
        this.query2 = query2;
    }
    public String packageData() {

        JSONObject jsonObject = new JSONObject();
        StringBuffer queryString = new StringBuffer();
        try {
            jsonObject.put("Query1", query1);
            jsonObject.put("Query2", query2);
            Boolean firstValue = true;
            Iterator iterator = jsonObject.keys();
            do {
                String key = iterator.next().toString();
                String value = jsonObject.get(key).toString();
                if (firstValue) {
                    firstValue = false;
                } else {
                    queryString.append("&");
                }
                queryString.append(URLEncoder.encode(key, "UTF-8"));
                queryString.append("=");
                queryString.append(URLEncoder.encode(value, "UTF-8"));

            } while (iterator.hasNext());
            return queryString.toString();

        } catch (JSONException e) {
            e.printStackTrace();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;

    }
}
