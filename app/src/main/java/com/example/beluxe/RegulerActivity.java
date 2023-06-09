
package com.example.beluxe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.NumberFormat;
import java.util.Locale;

public class RegulerActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    private TextInputEditText txtNama, txtKilo, txtHarga, editTextId;
    Button btnAddData;
    Button btnViewAll;
    Button btnUpdate;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reguler);

        myDb = new DatabaseHelper(this);
        txtNama = findViewById(R.id.textNama);
        txtKilo = findViewById(R.id.textKiloan);
        txtHarga = findViewById(R.id.textHarga);
        btnAddData = findViewById(R.id.btnAdd);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        String[] items = new String[]{"Beluxe Reguler", "Beluxe Reguler++", "Beluxe Clean", "Beluxe Fast", "Beluxe Clean and Fast", "Beluxe Express+", "Beluxe Express++"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, items);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                int convKilo = Integer.parseInt(txtKilo.getText().toString());
                if (selectedItem.equals("Beluxe Reguler")) {
                    int hasil = convKilo * 10000;
                    txtHarga.setText("" + hasil);
                } else if (selectedItem.equals("Beluxe Reguler++")) {
                    int hasil = convKilo * 20000;
                    txtHarga.setText("" + hasil);
                } else if (selectedItem.equals("Beluxe Clean")) {
                    int hasil = convKilo * 30000;
                    txtHarga.setText("" + hasil);
                }
            }
        });
        txtHarga.addTextChangedListener(new TextWatcher() {
            private String current = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals(current)) {
                    txtHarga.removeTextChangedListener(this);

                    String cleanString = s.toString().replaceAll("[Rp,.\\s]", "");
                    double parsed = Double.parseDouble(cleanString);
                    NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
                    formatter.setMaximumFractionDigits(0); // Jika ingin menghilangkan digit desimal
                    String formatted = formatter.format(parsed);

                    current = formatted;
                    txtHarga.setText(formatted);
                    txtHarga.setSelection(formatted.length());

                    txtHarga.addTextChangedListener(this);
                }
            }
        });
//        AddData();
//        viewAll();
//        UpdateData();
//        deleteData();

        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertDataLaundry(txtNama.getText().toString(),
                        txtKilo.getText().toString(),
                        txtHarga.getText().toString());
                if (isInserted == true)
                    showMessage("Berhasil", "Data Berhasil Di Simpan");
                else
                    showMessage("Gagal", "Data Gagal Di Simpan");

                txtNama.setText("");
                txtHarga.setText("0");
                txtKilo.setText("0");
                autoCompleteTextView.setText("Pilih Paket");
            }
        });

    }


    //membuat alert dialog
    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.setIcon(R.drawable.logoapp);
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                
            }
        });
        builder.show();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}