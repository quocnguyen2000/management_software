package com.example.management_software;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText edtUsername,edtPassword;
    TextView tvCreateAccount;
    String strName, strPass;
    String url = "http://192.168.1.37/mob403/index.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mapping();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
        tvCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
    public void mapping(){
        btnLogin = findViewById(R.id.btnLogin);
        edtUsername = findViewById(R.id.edtNameLogin);
        edtPassword = findViewById(R.id.edtPassLogin);
        tvCreateAccount = findViewById(R.id.tvToRegister);
    }
    public void Login(){
        if (edtUsername.getText().toString().equals(""))
        {
            Toast.makeText(this, "Enter Username", Toast.LENGTH_SHORT).show();
        }
        else  if(edtUsername.getText().toString().trim().length() <= 4 || edtUsername.getText().toString().trim().length() >= 16)
        {
            Toast.makeText(this, "Username length is from 4 to 16 characters", Toast.LENGTH_SHORT).show();
        }
        else if (edtPassword.getText().toString().equals(""))
        {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }else  if(edtPassword.getText().toString().trim().length() <= 4 || edtPassword.getText().toString().trim().length() >= 16)
        {
            Toast.makeText(this, "Username length is from 4 to 16 characters", Toast.LENGTH_SHORT).show();
        }
        else{
            strName = edtUsername.getText().toString().trim();
            strPass = edtPassword.getText().toString().trim();
            final StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    switch (response) {
                        case "403":
                            Toast.makeText(LoginActivity.this, "no data", Toast.LENGTH_SHORT).show();
                            break;
                        case "400":
                            Toast.makeText(LoginActivity.this, " error", Toast.LENGTH_SHORT).show();
                            break;
                        case "200":
                            Toast.makeText(LoginActivity.this, "register successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            break;
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LoginActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    Log.d("register", error.getMessage().toString());
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
            requestQueue.add(request);
        }
    }
}