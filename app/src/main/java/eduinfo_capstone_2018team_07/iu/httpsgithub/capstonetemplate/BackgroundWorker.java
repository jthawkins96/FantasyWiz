package eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate;

import android.app.AlertDialog;
import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class BackgroundWorker extends AsyncTask<String, Void, String> {


    AlertDialog alertDialog;
    Context context;
    BackgroundWorker (Context ctx) {context = ctx;}
    String username;
    String type;

    @Override
    protected String doInBackground(String... params) {
        type = params[0];
        String login_url = "http://cgi.soic.indiana.edu/~team07/login.php";
        String register_url = "http://cgi.soic.indiana.edu/~team07/register.php";


        if(type.equals("login")) {
            try {
                String usernameString = params[1];
                String passwordString = params[2];

                username = usernameString;
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("usernameString", "UTF-8")+"="+URLEncoder.encode(usernameString, "UTF-8")+"&"
                        +URLEncoder.encode("passwordString", "UTF-8")+"="+URLEncoder.encode(passwordString, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while((line = bufferedReader.readLine()) != null) {
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (type.equals("register")) {
            try {
                String firstNameString = params[1];
                String lastNameString = params[2];
                String usernameString = params[3];
                String emailString = params[4];
                String passwordString = params[5];
                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("firstNameString", "UTF-8") + "=" + URLEncoder.encode(firstNameString, "UTF-8") + "&"
                        + URLEncoder.encode("lastNameString", "UTF-8") + "=" + URLEncoder.encode(lastNameString, "UTF-8") + "&"
                        + URLEncoder.encode("usernameString", "UTF-8") + "=" + URLEncoder.encode(usernameString, "UTF-8") + "&"
                        + URLEncoder.encode("emailString", "UTF-8") + "=" + URLEncoder.encode(emailString, "UTF-8") + "&"
                        + URLEncoder.encode("passwordString", "UTF-8") + "=" + URLEncoder.encode(passwordString, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Status");
    }

    @Override
    protected  void onPostExecute(String result) {
        Log.d("FUCK ME", result);
        if (result.equals("Login Successful")) {
            alertDialog.setMessage(result);
            alertDialog.show();
            SharedPreferences preferences = context.getSharedPreferences("userName", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("Username",username);
            editor.apply();
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        }
        else if (result.equals("Email is not verified")) {
            alertDialog.setMessage(result);
            alertDialog.show();
        }
        else if (result.equals("User Registered")) {
            alertDialog.setMessage(result);
            alertDialog.show();
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
        }
        else if (result.equals("Username is already in use")) {
            alertDialog.setMessage(result);
            alertDialog.show();
        }
        else if (result.equals("Email is already in use")) {
            alertDialog.setMessage(result);
            alertDialog.show();
        }

    }

    /*@Override
    protected void onProgessUpdate(Void... values) {
        super.onProgressUpdate(values);
    }*/


}


