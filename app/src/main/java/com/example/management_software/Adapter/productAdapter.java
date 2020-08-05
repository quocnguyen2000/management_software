package com.example.management_software.Adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.management_software.MainActivity;
import com.example.management_software.Model.products;
import com.example.management_software.R;

import java.util.ArrayList;

public class productAdapter extends ArrayAdapter {
    TextView tvName,tvPrice,tvDecription;
    Button btnXoa;
    Context context;
    ArrayList<products> products;

    public productAdapter(@NonNull Context context, ArrayList<products> products) {
        super(context, 0,products);
        this.context = context;
        this.products = products;
    }


    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View v = convertView;

        if (v == null){
            v= LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);
        }

        final products product = products.get(position);
        if (product !=null) {
            //anh xa
            tvName = (TextView) v.findViewById(R.id.tvName);
            tvPrice = (TextView) v.findViewById(R.id.tvPrice);
            tvDecription = (TextView) v.findViewById(R.id.tvDecripton);
            btnXoa = (Button)v.findViewById(R.id.btnXoa);
            //set data len layout custom

            tvName.setText(product.name);
            tvPrice.setText(product.price.toString());
            tvDecription.setText(product.description);

        }
        // click vao item

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Ban vua click dong "+position, Toast.LENGTH_SHORT).show();
                ((MainActivity)context).txtName.setText(product.name);
                ((MainActivity)context).txtPrice.setText(product.price.toString());
                ((MainActivity)context).txtDescription.setText(product.description);
                ((MainActivity)context).id = product.id.toString();
            }
        });


        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Truy cap den bien nao do trong MainActivity ((MainActivity)context)
                ((MainActivity)context).xoaSp(product.id);

            }
        });




        return v;
    }
}
