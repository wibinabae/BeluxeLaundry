package com.example.beluxe;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Person> list;

    public Adapter(Activity activity, List<Person> list){
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(inflater == null){
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(view == null && inflater != null){
            view = inflater.inflate(R.layout.activity_reguler, null);
        }
        if(view != null){
            TextView nama = view.findViewById(R.id.textNama);
            TextView kilo = view.findViewById(R.id.textKiloan);
            TextView harga = view.findViewById(R.id.textHarga);
            Person person = list.get(i);
            nama.setText(person.getNamana());
            kilo.setText(person.getKiloan());
            harga.setText(person.getHarga());
        }
        return view;
    }
}

