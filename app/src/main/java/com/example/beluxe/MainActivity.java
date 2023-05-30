package com.example.beluxe;

import androidx.appcompat.app.AppCompatActivity;

import com.example.beluxe.Person;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
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
        ListView list = findViewById(R.id.list);
        ListAdapter adapter = new ListAdapter(this, createPersons());

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
    }

    private List<Person> createPersons() {
        List<Person> data = new ArrayList<>();
        data.add(new Person("Rizky", "8 Kilo"));
        data.add(new Person("Apriyani", "10 Kilo"));
        data.add(new Person("Rizky", "8 Kilo"));
        data.add(new Person("Apriyani", "10 Kilo"));
        data.add(new Person("Rizky", "8 Kilo"));
        data.add(new Person("Apriyani", "10 Kilo"));
        data.add(new Person("Rizky", "8 Kilo"));
        data.add(new Person("Apriyani", "10 Kilo"));
        data.add(new Person("Rizky", "8 Kilo"));
        data.add(new Person("Apriyani", "10 Kilo"));
        data.add(new Person("Rizky", "8 Kilo"));
        data.add(new Person("Apriyani", "10 Kilo"));
        return data;
    }

}