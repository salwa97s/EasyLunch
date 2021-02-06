package com.example.easylunchapp.Begain;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.easylunchapp.Classes.User;
import com.example.easylunchapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText REmail , RPass , RName , RCity , RSchool;
    private Button Createbtn ;

    public static final String SHARED_PREFS = "userinfo" ;
    public static final String NAME = "Name";
    public static final String EMAIL = "Email";
    public static final String PASS = "Pass";
    public static final String CITY = "City";
    public static final String SCHOOL = "School";

    private List<String> StaffLst ;

    private User USER ;


    public static int ID = 0001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        REmail = (EditText)findViewById(R.id.REmail_txt_id);
        RPass = (EditText)findViewById(R.id.RPass_txt_id);
        RName = (EditText)findViewById(R.id.RName_txt_id);
        RCity = (EditText)findViewById(R.id.RCity_txt_id);
        RSchool = (EditText)findViewById(R.id.RSchool_txt_id);

        Createbtn = (Button)findViewById(R.id.Rcreate_btn_id);

        Createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
    }

    public void Register(){

        String emailT= REmail.getText().toString();
        String passwordT = RPass.getText().toString();

        mAuth.createUserWithEmailAndPassword(emailT, passwordT)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();

                            Toast.makeText(RegisterActivity.this, "Authentication Succeed.",
                                    Toast.LENGTH_SHORT).show();

                            // functions call !!!
                            savedata();
                            SetDataBase();
                            next();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }

    public void next(){
        Intent intent = new Intent (RegisterActivity.this , LoginActivity.class);
        startActivity(intent);
    }

    public void savedata(){

        SharedPreferences sp = getSharedPreferences(SHARED_PREFS ,MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit() ;

        //saving data
        editor.putString(NAME , RName.getText().toString());
        editor.putString(EMAIL , REmail.getText().toString());
        editor.putString(PASS , RPass.getText().toString());
        editor.putString(CITY , RCity.getText().toString());
        editor.putString(SCHOOL , RSchool.getText().toString());

        editor.apply();
    }

    public void SetDataBase(){

        // Write a message to the database
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        ManagerRole();

        String useremail  = REmail.getText().toString();

        for(int i=0 ; i <StaffLst.size();i++){
            String f = StaffLst.get(i);
            if(useremail.equals(f)){
                USER = new User(RName.getText().toString() ,REmail.getText().toString() , RPass.getText().toString(),RCity.getText().toString(),RSchool.getText().toString(),"Manager",uid) ;
                i= StaffLst.size();
            }
            else{
                USER = new User(RName.getText().toString() ,REmail.getText().toString() , RPass.getText().toString(),RCity.getText().toString(),RSchool.getText().toString(),"Student",uid) ;
            }
        }

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("user");
        DatabaseReference yourRef = rootRef.child(uid);
        yourRef.setValue(USER);

       /* DatabaseReference rootRef2 = FirebaseDatabase.getInstance().getReference("Requests");
        DatabaseReference yourRef2 = rootRef2.child(uid);
     //   DatabaseReference F =yourRef2.child("Food");
      //  DatabaseReference T =F.child("0");
       // DatabaseReference Y =F.child("1");
      //  T.setValue(USER);
      //  Y.setValue(USER);
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        // DatabaseReference myRef = database.getReference("users").child(uid).child("profile");
        /*DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference yourRef = rootRef.child("user").push();
        yourRef.setValue(USER);*/
    }

    public void ManagerRole(){
        SharedPreferences sp2 = getSharedPreferences("STAFF" , Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp2.getString("Emails" , null);
        Type typeT = new TypeToken<ArrayList<String>>() {}.getType();
        StaffLst = gson.fromJson(json , typeT );
        if(StaffLst == null){
            StaffLst = new ArrayList<>();
        }
    }

}
