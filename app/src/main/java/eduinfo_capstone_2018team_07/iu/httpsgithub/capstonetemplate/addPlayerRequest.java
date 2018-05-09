package eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bgorman on 2/8/18.
 */

public class addPlayerRequest extends StringRequest{
    private static final String URL = "http://cgi.soic.indiana.edu/~team07/addPlayer.php";
    private Map<String, String> params;

    public addPlayerRequest(String username, String playername, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        params = new HashMap<>();
        params.put("User", username);
        params.put("Players", playername);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
