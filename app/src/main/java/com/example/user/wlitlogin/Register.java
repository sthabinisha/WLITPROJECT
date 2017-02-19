package com.example.user.wlitlogin;


import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    Button btn_linktologin, btn_reg;
    RequestQueue requestQueue;
    EditText et_name, et_email, et_password;

    private static final String REGISTER_URL = "http://kapas.wlit.org.np/register";

    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_name = (EditText) findViewById(R.id.reg_name);
        et_email = (EditText) findViewById(R.id.reg_email);
        et_password = (EditText) findViewById(R.id.reg__password);
        btn_reg = (Button) findViewById(R.id.btnRegister);
        btn_linktologin = (Button) findViewById(R.id.btnLinkToLoginScreen);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
    }
}