package com.example.beluxe;

public class Person {


    private String id;
    private String namana;
    private String kiloan;
    private String harga;

    public Person() {}

    public Person(String id, String nama, String kilo, String harga) {
        this.id = id;
        this.namana = nama;
        this.kiloan = kilo;
        this.harga = harga;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamana() {
        return namana;
    }

    public void setNamana(String nama) {
        this.namana = nama;
    }

    public String getKiloan() {
        return kiloan;
    }

    public void setKilo(String kilo) {
        this.kiloan = kilo;
    }

    public String getHarga(){ return harga;};

    public void setHarga(String harga) { this.harga = harga; }


}
