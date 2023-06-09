package com.example.beluxe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.beluxe.Person;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        ListView list = findViewById(R.id.list);
        DatabaseHelper db = new DatabaseHelper(this);
        List data = db.getAllLaundry();
        ListAdapter adapter = new ListAdapter(this, data);

        //set adapter pada list view.
        list.setAdapter(adapter);
        com.google.android.material.card.MaterialCardView mcdReguler = findViewById(R.id.btnReguler);
        com.google.android.material.card.MaterialCardView mcdPremium = findViewById(R.id.btnPremium);
        com.google.android.material.card.MaterialCardView mcdAbout = findViewById(R.id.aboutMe);
        com.google.android.material.card.MaterialCardView mcdDaftarHarga = findViewById(R.id.daftarHarga);

        mcdReguler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegulerActivity.class);
                startActivity(intent);
            }
        });
        mcdPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PremiumActivity.class);
                startActivity(intent);
            }
        });
        mcdDaftarHarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HargaActivity.class);
                startActivity(intent);
            }
        });

        mcdAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Logika refresh di sini

                // Setelah selesai, panggil method setRefreshing(false) untuk menghentikan animasi refresh
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Apa yang akan dilakukan?")
                        .setMessage("Apa yang akan kamu lakukan untuk data ini?" + item.toString())
                        .setIcon(R.drawable.logoapp);
                builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, RegulerActivity.class);
                        intent.putExtra("data", item.toString());
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Hapus Datana
                    }
                });
            }
        });

    }
}