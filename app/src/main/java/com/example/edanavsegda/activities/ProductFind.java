package com.example.edanavsegda.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edanavsegda.R;
import com.example.edanavsegda.adapters.FindProductAdapter;
import com.example.edanavsegda.models.Product;

import java.util.ArrayList;

public class ProductFind extends AppCompatActivity {
    ArrayList<Product> products = new ArrayList<>();
    String inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_find);


        EditText nameProduct = findViewById(R.id.findProductInput);
        nameProduct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inputText = nameProduct.getText().toString();
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        // запрос на поиск продуктов
        Product product = new Product("Гречка");
        product.setLogo(R.drawable.ic_baseline_fastfood_24);
        products.add(product);
        products.add(product);
        products.add(product);
        products.add(product);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_find_product);
        FindProductAdapter findProductAdapter = new FindProductAdapter(getApplicationContext(), products, this);
        recyclerView.setAdapter(findProductAdapter);
        recyclerView.setVerticalScrollBarEnabled(true);
    }

    public void backProducts(View view) {
        finish();
    }

    public void hideFoundProducts(View view) {
        openProductSettings(new Product(inputText));
    }

    public void openProductSettings(Product info) {
        Intent intent = new Intent(ProductFind.this, ProductSettings.class);
        intent.putExtra("name", info.name);
        intent.putExtra("productID", info.getProductID());
        if (info.logo != 0) intent.putExtra("img", info.logo);
        if (info.expirationDefault != null) intent.putExtra("expirationDefault", info.expirationDefault);
        startActivity(intent);
    }
}
