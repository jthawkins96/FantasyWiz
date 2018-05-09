package eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate;

import android.content.Context;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by bgorman on 2/3/18.
 */

public class QBResponse {
    Context c;
    TableLayout statisticsData;
    TextView team;



    public QBResponse(Context c, TableLayout statisticsData, TextView team) {
        this.c = c;
        this.statisticsData = statisticsData;
        this.team = team;

    }
    //The Yeezus of Table Creation
    Response.Listener<String> statisticsListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Initialize the totals for Career Sums
                int sumCmp = 0;
                int sumAtt =0;
                int sumPyds = 0, sumPtds =0, sumInt=0, sumRat = 0, sumRuy=0, sumRut=0;
                float sumQbr =0;

                try {
                    //Get the JSON Array of Sums for stats grouped by year
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject;

                    for (int i = 0; i <jsonArray.length() ; i++) {
                        jsonObject = jsonArray.getJSONObject(i);

                        int year = jsonObject.getInt("Year");
                        int cmp = jsonObject.getInt("SUM(Cmp)");
                        int att = jsonObject.getInt("SUM(PAtt)");
                        int pyds = jsonObject.getInt("SUM(PYds)");
                        int ptds = jsonObject.getInt("SUM(PTD)");
                        int interception = jsonObject.getInt("SUM(Interceptions)");
                        int rat = jsonObject.getInt("SUM(Rating)");
                        int ruA = jsonObject.getInt("SUM(RuAtt)");
                        int ruY = jsonObject.getInt("SUM(RuYds)");
                        int ruT = jsonObject.getInt("SUM(RuTD)");
                        String tm = jsonObject.getString("Tm");

                        team.setText(tm);

                        //Do some quick math to get completion percentage and QBR

                        double per = (double)cmp/att;
                        double qbr = (double)rat/16;

                        sumCmp += cmp;
                        sumAtt += att;
                        sumPyds += pyds;
                        sumPtds += ptds;
                        sumInt += interception;
                        sumRat += ruA;
                        sumRuy += ruY;
                        sumRut += ruT;
                        sumQbr += qbr;

                        //Add each stat to a new row in the table

                        TableRow row1 = new TableRow(c);

                        TextView yearPD = new TextView(c);
                        yearPD.setText(year+"");
                        yearPD.setTextSize(20);
                        yearPD.setGravity(Gravity.LEFT);
                        row1.addView(yearPD);

                        TextView cmpPD = new TextView(c);
                        cmpPD.setText(cmp+"");
                        cmpPD.setTextSize(20);
                        cmpPD.setGravity(Gravity.LEFT);
                        row1.addView(cmpPD);

                        TextView attPD = new TextView(c);
                        attPD.setText(att+"");
                        attPD.setTextSize(20);
                        attPD.setGravity(Gravity.LEFT);
                        row1.addView(attPD);

                        TextView perPD = new TextView(c);
                        perPD.setText(String.valueOf(Math.round(per * 100.0)/100.0));
                        perPD.setTextSize(20);
                        perPD.setGravity(Gravity.LEFT);
                        row1.addView(perPD);

                        TextView ydsPD = new TextView(c);
                        ydsPD.setText(pyds+"");
                        ydsPD.setTextSize(20);
                        ydsPD.setGravity(Gravity.LEFT);
                        row1.addView(ydsPD);

                        TextView ptdPD = new TextView(c);
                        ptdPD.setText(ptds+"");
                        ptdPD.setTextSize(20);
                        ptdPD.setGravity(Gravity.LEFT);
                        row1.addView(ptdPD);

                        TextView intPd = new TextView(c);
                        intPd.setText(interception+"");
                        intPd.setTextSize(20);
                        intPd.setGravity(Gravity.LEFT);
                        row1.addView(intPd);

                        TextView qbrPD = new TextView(c);
                        qbrPD.setText(Math.round(qbr*100.0)/100.0+"");
                        qbrPD.setTextSize(20);
                        qbrPD.setGravity(Gravity.LEFT);
                        row1.addView(qbrPD);

                        TextView ruaPD = new TextView(c);
                        ruaPD.setText(ruA+"");
                        ruaPD.setTextSize(20);
                        ruaPD.setGravity(Gravity.LEFT);
                        row1.addView(ruaPD);

                        TextView ruyPD = new TextView(c);
                        ruyPD.setText(ruY+"");
                        ruyPD.setTextSize(20);
                        ruyPD.setGravity(Gravity.LEFT);
                        row1.addView(ruyPD);

                        TextView rutPD = new TextView(c);
                        rutPD.setText(ruT+"");
                        rutPD.setTextSize(20);
                        rutPD.setGravity(Gravity.LEFT);
                        row1.addView(rutPD);

                        statisticsData.addView(row1);

                    }

                    //Add career totals to the player table

                    double per = (double)sumCmp/sumAtt;
                    double TQBR = (double)sumQbr/jsonArray.length();


                    TableRow row2 = new TableRow(c);

                    TextView yPD = new TextView(c);
                    yPD.setText("Career");
                    yPD.setTextSize(20);
                    yPD.setGravity(Gravity.LEFT);
                    yPD.setPadding(10, 0, 10, 0);
                    row2.addView(yPD);

                    TextView cPD = new TextView(c);
                    cPD.setText(sumCmp+"");
                    cPD.setTextSize(20);
                    cPD.setGravity(Gravity.LEFT);
                    yPD.setPadding(10, 0, 10, 0);
                    row2.addView(cPD);

                    TextView aPD = new TextView(c);
                    aPD.setText(sumAtt+"");
                    aPD.setTextSize(20);
                    aPD.setGravity(Gravity.LEFT);
                    yPD.setPadding(10, 0, 10, 0);
                    row2.addView(aPD);

                    TextView pPD = new TextView(c);
                    pPD.setText(String.valueOf(Math.round(per * 100.0)/100.0));
                    pPD.setTextSize(20);
                    pPD.setGravity(Gravity.LEFT);
                    yPD.setPadding(10, 0, 10, 0);
                    row2.addView(pPD);

                    TextView ydPD = new TextView(c);
                    ydPD.setText(sumPyds+"");
                    ydPD.setTextSize(20);
                    ydPD.setGravity(Gravity.LEFT);
                    yPD.setPadding(10, 0, 10, 0);
                    row2.addView(ydPD);

                    TextView dPD = new TextView(c);
                    dPD.setText(sumPtds+"");
                    dPD.setTextSize(20);
                    dPD.setGravity(Gravity.LEFT);
                    yPD.setPadding(10, 0, 10, 0);
                    row2.addView(dPD);

                    TextView iPd = new TextView(c);
                    iPd.setText(sumInt+"");
                    iPd.setTextSize(20);
                    dPD.setGravity(Gravity.LEFT);
                    yPD.setPadding(10, 0, 10, 0);
                    row2.addView(iPd);

                    TextView qbPD = new TextView(c);
                    qbPD.setText(String.valueOf(Math.round(TQBR * 100.0)/100.0)+"");
                    qbPD.setTextSize(20);
                    qbPD.setGravity(Gravity.LEFT);
                    yPD.setPadding(10, 0, 10, 0);
                    row2.addView(qbPD);

                    TextView ruaPD = new TextView(c);
                    ruaPD.setText(sumRat+"");
                    ruaPD.setTextSize(20);
                    ruaPD.setGravity(Gravity.LEFT);
                    yPD.setPadding(10, 0, 10, 0);
                    row2.addView(ruaPD);

                    TextView ruyPD = new TextView(c);
                    ruyPD.setText(sumRuy+"");
                    ruyPD.setTextSize(20);
                    ruyPD.setGravity(Gravity.LEFT);
                    yPD.setPadding(10, 0, 10, 0);
                    row2.addView(ruyPD);

                    TextView rutPD = new TextView(c);
                    rutPD.setText(sumRut+"");
                    rutPD.setTextSize(20);
                    rutPD.setGravity(Gravity.LEFT);
                    yPD.setPadding(10, 0, 10, 0);
                    row2.addView(rutPD);

                    statisticsData.addView(row2);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        };

}

