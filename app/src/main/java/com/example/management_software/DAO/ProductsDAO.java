package com.example.management_software.DAO;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.management_software.Adapter.productAdapter;
import com.example.management_software.Callback.VolleyCallback;
import com.example.management_software.MainActivity;
import com.example.management_software.Model.products;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsDAO {

    Context context;
    String insertUrl = "http://10.82.78.155/mob403/createproduct.php";
    String getAllUrl = "http://10.82.78.155/mob403/getallproducts.php";
    String updateUrl = "http://10.82.78.155/mob403/updateproduct.php";
    String deleteUrl = "http://10.82.78.155/mob403/deleteproduct.php";

    List<products> list = new ArrayList<products>();


    public ProductsDAO(Context context) {
        this.context = context;
    }



    public void  insert(final products sp) {


        StringRequest stringrequest = new StringRequest(
                Request.Method.POST, insertUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonobject=new JSONObject(response);
                    String thanhcong=jsonobject.getString("thanhcong");

                    //doc tat ca du lieu tu json bo vao ArrayList

                    if(Integer.parseInt(thanhcong)==1)//thanh cong
                    {

                        Toast.makeText(context, "Add Done", Toast.LENGTH_SHORT).show();
                        ((MainActivity)context).capnhatLV();

                    }
                    else //that bai
                    {

                        Toast.makeText(context, "Add Fail", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "Volley error", Toast.LENGTH_SHORT).show();
                Log.d("loi",error.toString());

            }
        }
        ) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param=new HashMap<String,String>();

                param.put("name",sp.name);
                param.put("price",sp.price.toString());
                param.put("description",sp.description);


                return param;
            }
        };
        Volley.newRequestQueue(context).add(stringrequest);

    }

    public void getAll(final VolleyCallback<List<products>> volleyCallback){



        StringRequest stringrequest = new StringRequest(getAllUrl
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    list.clear();
                    JSONObject jsonobject=new JSONObject(response);
                    String thanhcong=jsonobject.getString("thanhcong");

                    //doc tat ca du lieu tu json bo vao ArrayList
                    if(Integer.parseInt(thanhcong)==1)//thanh cong
                    {
                        Toast.makeText(context, "getAll Done", Toast.LENGTH_SHORT).show();

                        JSONArray sps=jsonobject.getJSONArray("sanpham");

                        for(int i=0;i<sps.length();i++) {
                            JSONObject spsJSONObject = sps.getJSONObject(i);

                            products sp = new products();
                            sp.id = spsJSONObject.getString("id");
                            sp.name = spsJSONObject.getString("name");
                            sp.price = Double.parseDouble(spsJSONObject.getString("price"));
                            sp.description = spsJSONObject.getString("description");

                            list.add(sp);



                        }
                        volleyCallback.onReponse(list);
                    }
                    else //that bai
                    {
//                        Toast.makeText(context, "Khong co product", Toast.LENGTH_SHORT).show();
                        volleyCallback.onError("Fail");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    volleyCallback.onError("Fail");
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "Volley error", Toast.LENGTH_SHORT).show();
                Log.d("loi",error.toString());
                volleyCallback.onError("Fail");

            }
        }
        ) ;
        Volley.newRequestQueue(context).add(stringrequest);


    }

    public void update(final products sp) {

        StringRequest stringrequest = new StringRequest(
                Request.Method.POST, updateUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonobject=new JSONObject(response);
                    String thanhcong=jsonobject.getString("thanhcong");

                    //doc tat ca du lieu tu json bo vao ArrayList
                    if(Integer.parseInt(thanhcong)==1)//thanh cong
                    {
                        Toast.makeText(context, "Update Done", Toast.LENGTH_SHORT).show();
                        ((MainActivity)context).capnhatLV();

                    }
                    else //that bai
                    {
                        Toast.makeText(context, "Update Fail", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "Volley error", Toast.LENGTH_SHORT).show();
                Log.d("loi",error.toString());

            }
        }
        ) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param=new HashMap<String,String>();

                param.put("id",sp.id);
                param.put("name",sp.name);
                param.put("price",sp.price.toString());
                param.put("description",sp.description);


                return param;
            }
        };
        Volley.newRequestQueue(context).add(stringrequest);

    }

    public void delete(final String id) {



        StringRequest stringrequest = new StringRequest(
                Request.Method.POST, deleteUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonobject=new JSONObject(response);
                    String thanhcong=jsonobject.getString("thanhcong");

                    //doc tat ca du lieu tu json bo vao ArrayList
                    if(Integer.parseInt(thanhcong)==1)//thanh cong
                    {
                        Toast.makeText(context, "Delete Done", Toast.LENGTH_SHORT).show();
                        ((MainActivity)context).capnhatLV();


                    }
                    else //that bai
                    {
                        Toast.makeText(context, "Delete Fail", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "Volley error", Toast.LENGTH_SHORT).show();
                Log.d("loi",error.toString());

            }
        }
        ) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param=new HashMap<String,String>();

                param.put("id",id);


                return param;
            }
        };
        Volley.newRequestQueue(context).add(stringrequest);

    }
}
