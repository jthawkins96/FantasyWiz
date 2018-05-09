package eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

/**
 * Created by bgorman on 1/26/18.
 */

public class ComparePackage {
    String query;

    public ComparePackage(String query) {
        this.query = query;
    }
    public String packageData(){
        JSONObject jsonObject = new JSONObject();
        StringBuffer queryString = new StringBuffer();

        try {
            jsonObject.put("Query1", query);
            jsonObject.put("Query2", "");
            Boolean firstValue = true;
            Iterator it = jsonObject.keys();

            do {
                String key = it.next().toString();
                String value = jsonObject.get(key).toString();
                if (firstValue){
                    firstValue = false;
                }else {
                    queryString.append("&");
                }
                queryString.append(URLEncoder.encode(key, "UTF-8"));
                queryString.append("=");
                queryString.append(URLEncoder.encode(value, "UTF-8"));

            }while (it.hasNext());

            return queryString.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
