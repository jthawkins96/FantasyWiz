package eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate.ForgotActivity;
import eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate.R;

public class LoginActivity extends AppCompatActivity {


    private EditText username, password;
    private Button btLogin, registerBtn;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.etUsername);
        password = (EditText) findViewById(R.id.etPassword);
        btLogin = (Button) findViewById(R.id.btLogin);
        registerBtn = (Button) findViewById(R.id.btRegister);
    }
    public void onLoginClick(View view) {
        String usernameString = username.getText().toString();
        String passwordString = password.getText().toString();
        String type = "login";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, usernameString, passwordString);
    }
    public void registerClick(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

}
