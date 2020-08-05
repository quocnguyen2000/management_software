package com.example.management_software;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin, btnRegister;
    EditText txtEmail, txtPass;

    String loginUrl = "http://10.82.134.216/mob403/index.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        txtEmail = findViewById(R.id.edtNameLogin);
        txtPass = findViewById(R.id.edtPassLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringRequest stringrequest = new StringRequest(
                        Request.Method.POST, loginUrl,
                        new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        xulyLogin(response);


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(LoginActivity.this, "Volley error", Toast.LENGTH_SHORT).show();
                        Log.d("loi",error.toString());

                    }
                }
                ) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> param=new HashMap<String,String>();


                        param.put("email",txtEmail.getText().toString());
                        param.put("password",txtPass.getText().toString());
                        param.put("tag","login");

                        return param;
                    }
                };
                Volley.newRequestQueue(LoginActivity.this).add(stringrequest);
            }
        });
    }

    private void xulyLogin(String response) {
        String thanhcong="";
        String name = "";
        try {
            JSONObject jsonobject=new JSONObject(response);
            thanhcong=jsonobject.getString("thanhcong");

            //doc tat ca du lieu tu json bo vao ArrayList
            if(Integer.parseInt(thanhcong)==1)//thanh cong
            {
                JSONObject user = jsonobject.getJSONObject("user");
                name = user.getString("name");
                Toast.makeText(this, "Login Done", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
            else //that bai
            {
                Log.d("login","LoginFail");
                Toast.makeText(this, "Login Fail", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
