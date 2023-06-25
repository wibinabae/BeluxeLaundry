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
import java.util.HashMap;
import java.util.List;

import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    AlertDialog.Builder dialog;
    List<Person>list = new ArrayList<>();
    Adapter adapter;
    DatabaseHelper db = new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
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
        listView = findViewById(R.id.list);
        db = new DatabaseHelper(getApplicationContext());

        adapter = new Adapter(MainActivity.this, list);

        //set adapter pada list view.
        listView.setAdapter(adapter);

//        Handling untuk mengedit dan menghapus

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String id = list.get(i).getId();
                final String nama = list.get(i).getNamana();
                final String kilo = list.get(i).getKiloan();
                final String harga = list.get(i).getHarga();
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(MainActivity.this,RegulerActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("nama", nama);
                                intent.putExtra("kilo", kilo);
                                intent.putExtra("harga", harga);
                                startActivity(intent);
                                break;
                            case 1:
                                db.deleteLaundry(Integer.parseInt(String.valueOf(id)));
                                list.clear();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
        getData();
    }

    private void getData(){
        ArrayList<HashMap<String, String>> rows = db.getAllLaundry();
        for (int i = 0; i<rows.size(); i++){
            String id = rows.get(i).get("id");
            String nama = rows.get(i).get("nama");
            String kilo = rows.get(i).get("kilo");
            String harga = rows.get(i).get("harga");


            Person data = new Person();
            data.setId(id);
            data.setNamana(nama);
            data.setHarga(harga);
            data.setKilo(kilo);
            list.add(data);
        }
        adapter.notifyDataSetChanged();
    }
    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        getData();
    }
}