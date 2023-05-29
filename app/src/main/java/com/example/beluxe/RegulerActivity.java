
package com.example.beluxe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

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

//        AddData();
//        viewAll();
//        UpdateData();
//        deleteData();

        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(txtNama.getText().toString(),
                        txtKilo.getText().toString(),
                        txtHarga.getText().toString());
                if (isInserted == true)
                    Toast.makeText(RegulerActivity.this, "Data Iserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(RegulerActivity.this, "Data Not Iserted", Toast.LENGTH_LONG).show();
            }
        });

    }

//    public void deleteData() {
//        btnDelete.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
//                        if (deletedRows > 0)
//                            Toast.makeText(RegulerActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
//                        else
//                            Toast.makeText(RegulerActivity.this, "Data Failed to Deleted!", Toast.LENGTH_LONG).show();
//                    }
//                }
//        );
//    }
//
//    //fungsi update
//    public void UpdateData() {
//        btnUpdate.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        boolean isUpdate = myDb.updateData(editTextId.getText().toString(),
//                                txtNama.getText().toString(),
//                                txtKilo.getText().toString(),
//                                txtHarga.getText().toString());
//                        if (isUpdate == true)
//                            Toast.makeText(RegulerActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
//                        else
//                            Toast.makeText(RegulerActivity.this, "Data Failed to Update", Toast.LENGTH_LONG).show();
//                    }
//                }
//        );
//    }
//
//    //fungsi tambah
//    public void AddData() {
//
//    }
//    //fungsi menampilkan data
//
//    public void viewAll() {
//        btnViewAll.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Cursor res = myDb.getAllData();
//                        if (res.getCount() == 0) {
//                            // show message
//                            showMessage("Error", "Noting Found");
//                            return;
//                        }
//                        StringBuffer buffer = new StringBuffer();
//                        while (res.moveToNext()) {
//                            buffer.append("Id :" + res.getString(0) + "\n");
//                            buffer.append("Nama :" + res.getString(1) + "\n");
//                            buffer.append("Kilo :" + res.getString(2) + "\n");
//                            buffer.append("Harga :" + res.getString(3) + "\n\n");
//                        }
//                        // show all data
//                        showMessage("Data", buffer.toString());
//                    }
//                }
//        );
//    }
//
//    //membuat alert dialog
//    public void showMessage(String title, String Message) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setCancelable(true);
//        builder.setTitle(title);
//        builder.setMessage(Message);
//        builder.show();
//    }
}