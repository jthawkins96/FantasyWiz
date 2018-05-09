package eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

/**
 * Created by bgorman on 2/4/18.
 */

public class ImportAdapter extends BaseAdapter {
    Context c;
    ArrayList<PlayerModel> players;
    ArrayList<String> checks;
    Button Import;

    public ImportAdapter(Context c, ArrayList<PlayerModel> players, Button anImport) {
        this.c = c;
        this.players = players;
        Import = anImport;
    }

    @Override
    public int getCount() {
        return players.size();
    }

    @Override
    public Object getItem(int i) {
        return players.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        if (view == null) {
            view = LayoutInflater.from(c).inflate(R.layout.compare_model, viewGroup, false);
        }

        final TextView playerName = view.findViewById(R.id.playerModelTV);
        final CheckBox check = view.findViewById(R.id.chkBox);

        final PlayerModel player = (PlayerModel) this.getItem(i);

        checks = new ArrayList<>();

        final String name = player.getName();

        playerName.setText(name);


        check.setChecked(false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (check.isChecked()) {
                    check.setChecked(false);
                    checks.remove(name);
                    if (checks.size()>0){
                        Import.setVisibility(View.VISIBLE);
                    }else {
                        Import.setVisibility(View.INVISIBLE);
                    }

                }else {
                    check.setChecked(true);
                    checks.add(name);
                    Import.setVisibility(View.VISIBLE);
                }
            }
        });

        final Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                for (int j = 0; j <checks.size() ; j++) {
                    Toast.makeText(c, "Added "+checks.get(j), Toast.LENGTH_LONG).show();
                }
            }
        };

        Import.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = getUsername();
                addPlayersRequest request = new addPlayersRequest(user, checks, listener);
                RequestQueue queue = Volley.newRequestQueue(c);
                queue.add(request);

            }
        });

        return view;
    }

    public String getUsername(){
        SharedPreferences preferences = c.getSharedPreferences("userName", Context.MODE_PRIVATE);
        String userName = preferences.getString("Username", "");
        return userName;
    }


}
