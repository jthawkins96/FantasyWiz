package eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate;//package flo.avi.fantasywiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate.R;

public class EditProfile extends AppCompatActivity {

    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        confirm = (Button)findViewById(R.id.btConfirm);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfile.this, ProfileActivity.class);
                EditProfile.this.startActivity(intent);
            }
        });

    }
}
