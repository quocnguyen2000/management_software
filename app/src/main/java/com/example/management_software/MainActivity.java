package com.example.management_software;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;


import com.example.management_software.Adapter.productAdapter;
import com.example.management_software.Callback.VolleyCallback;
import com.example.management_software.DAO.ProductsDAO;
import com.example.management_software.Model.products;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnThem, btnSua;
    public EditText txtName, txtPrice,txtDescription;
    public String id;
    ListView lv;
    products sp;
    ProductsDAO spDao;
    ArrayList<products> products;
    productAdapter adapter ;
    VolleyCallback<List<products>> volleyCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        lv=findViewById(R.id.lv1);
        txtName=findViewById(R.id.edtTen);
        txtPrice=findViewById(R.id.edtGia);
        txtDescription=findViewById(R.id.edtMota);
        btnThem=findViewById(R.id.btnThem);
        btnSua=findViewById(R.id.btnSua);

        spDao = new ProductsDAO(this);

        capnhatLV();


        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp = new products();
                sp.name=txtName.getText().toString();
                sp.price=Double.parseDouble(txtPrice.getText().toString());
                sp.description=txtDescription.getText().toString();

                spDao.insert(sp);

            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp = new products();
                sp.id = id;
                sp.name=txtName.getText().toString();
                sp.price=Double.parseDouble(txtPrice.getText().toString());
                sp.description=txtDescription.getText().toString();

                spDao.update(sp);
            }
        });

    }

    public void xoaSp(String id){

        spDao.delete(id);



    }

    public void capnhatLV(){

        spDao.getAll(new VolleyCallback<List<products>>() {
            @Override
            public void onReponse(List<products> reponse) {
                products = (ArrayList<products>)reponse;
                adapter = new productAdapter(MainActivity.this,products);
                lv.setAdapter(adapter);
            }

            @Override
            public void onError(String err) {
                products = new ArrayList<products>();
                adapter = new productAdapter(MainActivity.this,products);
                lv.setAdapter(adapter);
            }
        });


        }

}
