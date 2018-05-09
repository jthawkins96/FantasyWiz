package eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by bgorman on 1/10/18.
 */

public class CustomAdapter extends BaseAdapter {
    Context c;
    ArrayList<Player> players;

    public CustomAdapter (Context c, ArrayList<Player> players){
        this.c = c;
        this.players = players;
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
        if (view==null){
            view = LayoutInflater.from(c).inflate(R.layout.model, viewGroup, false);
        }
        TextView nameTxt = view.findViewById(R.id.nameModel);
        //TextView projectionTxt = (TextView) view.findViewById(R.id.projectionModel);
        TextView rankTxt = view.findViewById(R.id.rankModel);

        Player player = (Player) this.getItem(i);
        long count = this.getItemId(i)+1;

        final String rank = Long.toString(count);
        final String name = player.getName();
        final String position = player.getPosition();
        //final String team = player.getTeam();
        final String picture = player.getPicture();
        //final String projection = player.getProjection();

        nameTxt.setText(name);
        //projectionTxt.setText(projection);
        rankTxt.setText(rank);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Open Profile
                openProfile(name, position, picture);
            }
        });
        return view;
    }
    //Store JSON Data
    private void openProfile(String...details){
        Intent intent = new Intent(c, PlayerProfileActivity.class);

        intent.putExtra("NAME_KEY", details[0]);
        intent.putExtra("POSITION_KEY", details[1]);
        intent.putExtra("PICTURE_KEY", details[2]);
        c.startActivity(intent);
    }
}
