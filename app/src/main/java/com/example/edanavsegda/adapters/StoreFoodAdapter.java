package com.example.edanavsegda.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.edanavsegda.R;
import com.example.edanavsegda.models.StoreFood;

import java.util.ArrayList;

public class StoreFoodAdapter extends RecyclerView.Adapter<StoreFoodAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final ArrayList<StoreFood> states;

    public StoreFoodAdapter(Context context, ArrayList<StoreFood> states) {
        this.states = states;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public StoreFoodAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_store, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StoreFoodAdapter.ViewHolder holder, int position) {
        StoreFood state = states.get(position);

        holder.imageView.setImageResource(state.logo);
        holder.title.setText(state.title);
        holder.description.setText(state.getCountProducts());
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView title;
        public TextView description;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.logo_types_store);
            title = view.findViewById(R.id.item_store_title);
            description = view.findViewById(R.id.item_store_description);
        }

    }
}
