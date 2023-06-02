package com.example.beluxe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class AboutActivity extends AppCompatActivity {
    com.google.android.material.card.MaterialCardView openWa, openIg, openGithub, openLinkedin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        openWa = findViewById(R.id.mcdWa);
        openIg = findViewById(R.id.mcdIg);
        openGithub = findViewById(R.id.mcdGithub);
        openLinkedin = findViewById(R.id.mcdLinkedin);

        openWa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomorWa = "85860753942";
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.addCategory(Intent.CATEGORY_BROWSABLE);
                i.setData(Uri.parse("https://wa.me/62" + nomorWa));
                startActivity(i);
            }
        });

        openIg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ig = new Intent();
                ig.setAction(Intent.ACTION_VIEW);
                ig.addCategory(Intent.CATEGORY_BROWSABLE);
                ig.setData(Uri.parse("https://www.instagram.com/"));
                startActivity(ig);
            }
        });

        openGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gthb = new Intent();
                gthb.setAction(Intent.ACTION_VIEW);
                gthb.addCategory(Intent.CATEGORY_BROWSABLE);
                gthb.setData(Uri.parse("https://github.com/beliarizki26"));
                startActivity(gthb);
            }
        });

        openLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent linkedin = new Intent();
                linkedin.setAction(Intent.ACTION_VIEW);
                linkedin.addCategory(Intent.CATEGORY_BROWSABLE);
                linkedin.setData(Uri.parse("https://www.linkedin.com/in/belia-rizki-nurapriyani-158b94256/"));
                startActivity(linkedin);
            }
        });
    }
}