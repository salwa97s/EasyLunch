package com.example.easylunchapp.Begain;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.easylunchapp.MenuActivity;
import com.example.easylunchapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    //firebase
    private FirebaseAuth mAuth;

    //xml
    private EditText LEmail , LPass;
    private Button Loginbtn , Registerbtn , kk ;
    //database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        LEmail = (EditText)findViewById(R.id.LEmail_txt_id);
        LPass = (EditText)findViewById(R.id.LPass_txt_id);

        Loginbtn = (Button)findViewById(R.id.Login_btn_id);
        Registerbtn = (Button)findViewById(R.id.Register_btn_id);

        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
        Registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (LoginActivity.this , RegisterActivity.class);
                startActivity(intent);
            }
        });

        kk=(Button)findViewById(R.id.kk);
        kk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });
    }

    public void Login(){

        String emailT= LEmail.getText().toString();
        String passwordT = LPass.getText().toString();

        mAuth.signInWithEmailAndPassword(emailT, passwordT)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "Authentication succeed.",
                                    Toast.LENGTH_SHORT).show();
                            next();

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    public void next(){

        Intent intent = new Intent (LoginActivity.this , MenuActivity.class);
        startActivity(intent);
    }
}
