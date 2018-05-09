package eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class RegisterActivity extends AppCompatActivity{

    EditText firstName, lastName, username, email, password, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firstName = (EditText) findViewById(R.id.etFirstName);
        lastName = (EditText) findViewById(R.id.etLastName);
        username = (EditText) findViewById(R.id.etUsername);
        email = (EditText) findViewById(R.id.etEmail);
        password = (EditText) findViewById(R.id.etPassword);
        confirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
    }

    public void onRegisterClick(View view) {
        //System.out.println("Register button clicked");
        String firstNameString = firstName.getText().toString();
        String lastNameString = lastName.getText().toString();
        String usernameString = username.getText().toString();
        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();
        String confirmPasswordString = confirmPassword.getText().toString();
        String type = "register";
        //System.out.println(firstNameString + " " + lastNameString + " " + usernameString + " " + emailString + " " + passwordString + " " + confirmPasswordString);

        if (!(passwordString.equals(confirmPasswordString))) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Register Status");
            alertDialog.setMessage("Passwords do not match.");
            alertDialog.show();
        }
        else {
            BackgroundWorker backgroundWorkerReg = new BackgroundWorker(this);
            backgroundWorkerReg.execute(type, firstNameString, lastNameString, usernameString, emailString, passwordString);
        }
    }

}
