package com.example.beluxe;

import java.io.Serializable;

public class Laundry implements Serializable {
    private int id;
    private String nama;
    private float kilo, harga;


    public void setId(int id) {
        this.id = id;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public void setHarga(float harga) {
        this.harga = harga;
    }
    public void setKilo(float kilo) {
        this.kilo = kilo;
    }

    public int getId() {
        return id;
    }
    public String getNama() {
        return nama;
    }
    public float getHarga() {
        return harga;
    }
    public float getKilo() {
        return kilo;
    }
}
