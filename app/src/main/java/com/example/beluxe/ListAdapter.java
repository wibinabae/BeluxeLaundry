package com.example.beluxe;

import android.app.Activity;
import com.example.beluxe.Person;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends BaseAdapter {
    private Context context;
    private List<Person> persons;

    private static class ViewHolder {
        TextView Nama;
        TextView Kilo;
    }
    public ListAdapter(Activity activity, List<Person> persons) {
        this.context = activity.getBaseContext();
        this.persons = persons;
    }

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Object getItem(int id) {
        return persons.get(id);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int pos, View v, ViewGroup vGroup) {
        // TODO Auto-generated method stub

        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (v == null) {
            holder = new ViewHolder();

            v = inflater.inflate(R.layout.listorder, vGroup, false);

            holder.Nama = v.findViewById(R.id.txtNama);
            holder.Kilo = v.findViewById(R.id.txtKilo);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();

        }

        Person p = persons.get(pos);

        holder.Nama.setText(p.getNamana());
        holder.Kilo.setText(p.getKiloan());


        return v;
    }

}
