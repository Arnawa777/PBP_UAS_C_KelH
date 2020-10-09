package com.arnawajuan.tubes_uts.makanan;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class Makanan {
    public String nama;
    public double harga;
    public String deskripsi;
    public int gambar;

    public Makanan(String nama, double harga,String deskripsi,int gambar) {
        this.nama = nama;
        this.harga = harga;
        this.deskripsi=deskripsi;
        this.gambar=gambar;
    }

    @BindingAdapter("android:src")
    public static void setImageResource(ImageView imageView, int gambar){
        imageView.setImageResource(gambar);
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }
    public String getStringharga() {return String.valueOf(harga);}

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getGambar() {
        return gambar;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }
}
