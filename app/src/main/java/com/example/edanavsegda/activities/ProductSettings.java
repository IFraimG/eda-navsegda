package com.example.edanavsegda.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.edanavsegda.MainActivity;
import com.example.edanavsegda.ProductFragment;
import com.example.edanavsegda.R;
import com.example.edanavsegda.models.PostProduct;
import com.example.edanavsegda.models.Product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

        Button openCalendar = (Button) findViewById(R.id.product_settings_open_calendar);

        CalendarView calendarView = (CalendarView) findViewById(R.id.product_settings_calendar);
        openCalendar.setOnClickListener(View -> {
            calendarView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        });
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                try {
                    String selectedDate = String.format("%02d.%04d.%04d", year, month + 1, dayOfMonth);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    Date date = sdf.parse(selectedDate);
                    product.setExpirationDefault(date.toString());
                    calendarView.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
                    TextView dateInfoText = (TextView) findViewById(R.id.product_settings_exp);
                    dateInfoText.setText(date.toString());
                } catch (Exception e) {}
            }
        });

        Button sendInfo = (Button) findViewById(R.id.product_settings_send);
        sendInfo.setOnClickListener(View -> {
            // запрос на сервер с передачей данных
            Intent intent = new Intent(ProductSettings.this, MainActivity.class);
            intent.putExtra("goToProducts", true);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }

    public void setBtnDate() {
        Button btnDateNow = (Button) findViewById(R.id.product_settings_date);
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("dd.MM.yyyy");
        btnDateNow.setText(DateFor.format(date));
    }

    public void returnFoundProduct(View view) {
        finish();
    }
}
