package eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bgorman on 1/31/18.
 */

public class ProjectionRequest extends StringRequest {

    private static final String URL = "http://cgi.soic.indiana.edu/~brgorman/projectionMatch.php";
    private Map<String, String> params;

    public ProjectionRequest(String playername, Response.Listener<String> listener){
        super(Request.Method.POST, URL, listener, null);
        params = new HashMap<>();
        params.put("Query", playername);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
