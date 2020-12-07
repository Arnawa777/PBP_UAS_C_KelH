package com.arnawajuan.rumah_makan_cilik.makanan;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class Makanan {
    public String nama;
    public int harga;
    public String deskripsi;
    public int gambar;

    public Makanan(String nama, int harga,String deskripsi,int gambar) {
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

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
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
