package com.example.management_software;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    Button btnRegister;
    TextInputEditText edtUsername,edtPassword,edtEmail;
    TextView tvToLogin;
    String strName, strPass, strEmail;
    String url = "http://192.168.1.37/mob403/index.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mapping();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();

            }
        });
        tvToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    public void mapping(){
        btnRegister = findViewById(R.id.btnRegister);
        edtUsername = findViewById(R.id.edtNameRegister);
        edtPassword = findViewById(R.id.edtPassRegister);
        edtEmail = findViewById(R.id.edtEmailRegister);
        tvToLogin = findViewById(R.id.tvToLogin);
    }
    public void Register(){
        String regex = "^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$";
        if (edtUsername.getText().toString().equals(""))
        {
            Toast.makeText(this, "Enter Username", Toast.LENGTH_SHORT).show();
        }
        else  if(edtUsername.getText().toString().trim().length() < 6 || edtUsername.getText().toString().trim().length() > 16)
        {
            Toast.makeText(this, "Username length is from 6 to 16 characters", Toast.LENGTH_SHORT).show();
        }
        else if (edtEmail.getText().toString().equals(""))
        {
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        }else if(!edtEmail.getText().toString().trim().matches(regex))
        {
            Toast.makeText(this, "Email is wrong", Toast.LENGTH_SHORT).show();
        }
        else if (edtPassword.getText().toString().equals(""))
        {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }else  if(edtPassword.getText().toString().trim().length() < 6 || edtPassword.getText().toString().trim().length() > 16)
        {
            Toast.makeText(this, "Username length is from 6 to 16 characters", Toast.LENGTH_SHORT).show();
        }
        else
            {
            strName = edtUsername.getText().toString().trim();
            strPass = edtPassword.getText().toString().trim();
            strEmail = edtEmail.getText().toString().trim();

            final StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    switch (response){
                        case "403":
                            Toast.makeText(RegisterActivity.this, "no data", Toast.LENGTH_SHORT).show();
                            break;
                        case "401":
                            Toast.makeText(RegisterActivity.this, "username is exist", Toast.LENGTH_SHORT).show();
                            break;
                        case "402":
                            Toast.makeText(RegisterActivity.this, "email is exist", Toast.LENGTH_SHORT).show();
                            break;
                        case "400":
                            Toast.makeText(RegisterActivity.this, " error", Toast.LENGTH_SHORT).show();
                            break;
                        case "200":
                            Toast.makeText(RegisterActivity.this, "register successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            break;
                    }
                    //Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RegisterActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    Log.d("register", error.getMessage().toString());
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("name",strName);
                    params.put("email",strEmail);
                    params.put("pass",strPass);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
            requestQueue.add(request);
        }
    }
}