package com.example.edanavsegda.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.edanavsegda.R;
import com.example.edanavsegda.activities.ProductFind;
import com.example.edanavsegda.activities.ProductSettings;
import com.example.edanavsegda.models.Product;

import java.util.ArrayList;

public class FindProductAdapter extends RecyclerView.Adapter<FindProductAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final ArrayList<Product> states;
    ProductFind productFind;
    Context ctx;

    public FindProductAdapter(Context context, ArrayList<Product> states, ProductFind productFind) {
        this.states = states;
        this.inflater = LayoutInflater.from(context);
        this.productFind = productFind;
        this.ctx = context;
    }

    @Override
    public FindProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.product_find_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FindProductAdapter.ViewHolder holder, int position) {
        Product product = states.get(position);
        holder.itemView.setOnClickListener(View -> {
            Intent intent = new Intent(productFind, ProductSettings.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("name", product.name);
            intent.putExtra("productID", product.getProductID());

            if (product.logo != 0) intent.putExtra("img", product.logo);
            if (product.expirationDefault != null) intent.putExtra("expirationDefault", product.expirationDefault);
            ctx.startActivity(intent);
        });
        holder.title.setText(product.name);
        holder.expiration.setText(product.getExpiration());
        // + image
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView expiration;
        public ImageView img;

        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.product_find_title);
            expiration = view.findViewById(R.id.product_find_exp);
            img = view.findViewById(R.id.product_find_img);
        }
    }
}
