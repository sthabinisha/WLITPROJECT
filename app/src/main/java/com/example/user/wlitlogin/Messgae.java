package com.example.user.wlitlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Messgae extends AppCompatActivity {
    TextView txt_message;
    Button btn_login,btn_reg;
    Intent i,j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messgae);
        txt_message=(TextView)findViewById(R.id.txt_message);
//        img_press=(ImageButton)findViewById(R.id.btn_next);
        txt_message.setText("Please Login/Sign up to upload picture ");
//        img_press.setOnClickListener(new View.OnClickListener() {
//            @Override
//
//            public void onClick(View view) {
//                Intent intent=new Intent(Messgae.this,Register.class);
//                startActivity(intent);
//            }
//        });
        btn_login=(Button)findViewById(R.id.btn_login);
        btn_reg=(Button)findViewById(R.id.btn_reg);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=new Intent(Messgae.this,MainActivity.class);
               startActivity(i);
            }
        });
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                j= new Intent(Messgae.this,Register.class );
                startActivity(j
                );
            }
        });






    }
}
