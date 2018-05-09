package eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate;//package flo.avi.fantasywiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate.R;

public class ForgotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final TextView tvForgotMsg = (TextView) findViewById(R.id.tvForgotMsg);
        final Button btRecover = (Button) findViewById(R.id.btRecover);


        btRecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotActivity.this, LoginActivity.class);
                ForgotActivity.this.startActivity(intent);
            }
        });

    }
}
