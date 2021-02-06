package com.example.easylunchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class test extends AppCompatActivity {

    TextView text ;

    private  int Type1;
    public static final String SHARED_TYPE = "TYPE" ;
    public static final String TYPE = "Type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        text=(TextView)findViewById(R.id.testid);

        SharedPreferences sp = getSharedPreferences(SHARED_TYPE , MODE_PRIVATE);
        Type1 = sp.getInt(TYPE ,9);

        if(Type1 == 0){
            text.setText("0");
        }
        if(Type1 == 1){
            text.setText("1");
        }
        if(Type1 == 2){
            text.setText("2");
        }



    }
}
