package eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by bgorman on 1/26/18.
 */

public class CompareAdapter extends BaseAdapter {
    Context c;
    ArrayList<PlayerModel> players;
    ArrayList<String> checks;
    Button compare;

    public CompareAdapter(Context c, ArrayList<PlayerModel> players, Button compare) {
        this.c = c;
        this.players = players;
        this.compare = compare;
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

        final TextView playerName = (TextView) view.findViewById(R.id.playerModelTV);
        final CheckBox check = (CheckBox) view.findViewById(R.id.chkBox);

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
                    if (checks.size()==2){
                        compare.setVisibility(View.VISIBLE);
                        compare.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                openCompare(checks.get(0), checks.get(1));
                            }
                        });
                        Toast.makeText(c, name+" Removed", Toast.LENGTH_SHORT).show();
                    }else{
                        compare.setVisibility(View.INVISIBLE);
                        Toast.makeText(c, name+" Removed", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    check.setChecked(true);
                    checks.add(name);
                    if(checks.size()==2){
                        //openCompare(checks.get(0), checks.get(1));
                        compare.setVisibility(View.VISIBLE);
                        compare.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                openCompare(checks.get(0), checks.get(1));
                            }
                        });
                        Toast.makeText(c, name+" Added", Toast.LENGTH_SHORT).show();
                    }else if(checks.size()>2) {
                        compare.setVisibility(View.INVISIBLE);
                        Toast.makeText(c, "Too many players selected, please remove a player", Toast.LENGTH_LONG).show();
                    }else {
                        compare.setVisibility(View.INVISIBLE);
                        Toast.makeText(c, name+" Added", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        return view;
    }
    private void openCompare(String...details){
        Intent intent = new Intent(c, PlayerCompare.class);

        intent.putExtra("PLAYER1_KEY", details[0]);
        intent.putExtra("PLAYER2_KEY", details[1]);
        c.startActivity(intent);
    }


}
