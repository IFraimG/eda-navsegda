package com.example.edanavsegda.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.edanavsegda.R;
import com.example.edanavsegda.models.PostProduct;
import com.example.edanavsegda.models.Product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ProductSettings extends AppCompatActivity {
    PostProduct product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_settings);

        String name = getIntent().getStringExtra("name");
        String productID = getIntent().getStringExtra("productID");
        int img = getIntent().getIntExtra("img", 0);
        String expirationDefault = getIntent().getStringExtra("expirationDefault");

        this.product = new PostProduct(name);
        product.setOriginalID(productID);
        if (img != 0) product.setLogo(img);
        if (expirationDefault != null) product.setExpirationDefault(expirationDefault);

        TextView title = findViewById(R.id.product_title_settings);
        title.setText(name);

        setBtnDate();

        final String[] arr = this.getResources().getStringArray(R.array.list_storages);

        ListView listOfStorages = (ListView) findViewById(R.id.product_settings_storages);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.storage_settings_item, arr);
        listOfStorages.setAdapter(adapter);
        listOfStorages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View viewSelected = listOfStorages.getSelectedView();
                if (viewSelected != null) {
                    viewSelected.setBackgroundColor(getColor(R.color.custom_green_extra_light));
                    viewSelected.setSelected(false);
                }
                product.addProductToStorageByPos(position);
                view.setBackgroundColor(getColor(R.color.custom_green_extra_extra_light));
                view.setSelected(true);
            }
        });
    }

    public void setBtnDate() {
        Button btnDateNow = (Button) findViewById(R.id.product_settings_date);
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("MM.dd.yyyy");
        btnDateNow.setText(DateFor.format(date));
    }

    public void returnFoundProduct(View view) {
        finish();
    }
}
