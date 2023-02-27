package com.example.edanavsegda;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edanavsegda.adapters.StoreFoodAdapter;

public class StoreDialog extends DialogFragment {


    public AlertDialog onCreateDialog(Bundle savedInstanceState) {
//        RecyclerView recyclerView = (R.id.list_types_store);
//        StoreFoodAdapter storeFoodAdapter = new StoreFoodAdapter(dialogBuilder.getContext(), typeOfStore);
//        recyclerView.setAdapter(storeFoodAdapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder.create();
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.alert_store_products, null);
    }
}