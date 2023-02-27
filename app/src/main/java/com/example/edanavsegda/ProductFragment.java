package com.example.edanavsegda;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.edanavsegda.adapters.StoreFoodAdapter;
import com.example.edanavsegda.models.StoreFood;

import java.util.ArrayList;
import java.util.List;


public class ProductFragment extends Fragment {
    ArrayList<StoreFood> typeOfStore = new ArrayList<>(4);
    Dialog dialog;
    StoreFood currentStoreFood;
    Button btn;

    public ProductFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StoreFood food = new StoreFood("Вся еда", R.drawable.ic_baseline_home_24,  0);
        StoreFood food2 = new StoreFood("Холодильник", R.drawable.ic_baseline_home_24,  1);
        StoreFood food3 = new StoreFood("Морозилка", R.drawable.ic_baseline_home_24,  2);
        StoreFood food4 = new StoreFood("Кладовая", R.drawable.ic_baseline_home_24, 3);

        typeOfStore.add(food);
        typeOfStore.add(food2);
        typeOfStore.add(food3);
        typeOfStore.add(food4);

        currentStoreFood = food;

        for (int i = 0; i < typeOfStore.size(); i++) {
            int res = typeOfStore.get(i).getId();
            // узнать количество продуктов в хранилище ...
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        btn = (Button) getView().findViewById(R.id.chooseStore);
        btn.setText(currentStoreFood.title);
        btn.setOnClickListener(View -> {
            createModal();
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    public void createModal() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());

        dialogBuilder.setView(getLayoutInflater().inflate(R.layout.alert_store_products, null));
        dialogBuilder.setTitle("Хранилище");
        dialogBuilder.setNegativeButton("Закрыть", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });

        RecyclerView recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        StoreFoodAdapter storeFoodAdapter = new StoreFoodAdapter(dialogBuilder.getContext(), typeOfStore);
        recyclerView.setAdapter(storeFoodAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                try {
                    View childView = rv.findChildViewUnder(e.getX(), e.getY());
                    if (childView != null) {
                        int pos = rv.getChildAdapterPosition(childView);
                        currentStoreFood = typeOfStore.get(pos);
                        btn.setText(currentStoreFood.title);
                    }
                    dialog.dismiss();
                } catch (IllegalStateException err) {
                    Log.i("msg", err.toString());
                }
                return false;
            }
            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {}

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {}
        });

        dialogBuilder.setView(recyclerView);
        dialog = dialogBuilder.create();
        dialog.show();
    }
}