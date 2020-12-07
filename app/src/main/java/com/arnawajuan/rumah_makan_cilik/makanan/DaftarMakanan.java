package com.arnawajuan.rumah_makan_cilik.makanan;

import com.arnawajuan.rumah_makan_cilik.R;

import java.util.ArrayList;

public class DaftarMakanan {
    public ArrayList<Makanan> MAKANAN;


    public DaftarMakanan(){
        MAKANAN = new ArrayList();
        MAKANAN.add(NASGOR);
        MAKANAN.add(BAKSO);
        MAKANAN.add(RENDANG);
        MAKANAN.add(SATE);
        MAKANAN.add(SOTO);
        MAKANAN.add(ESTEH);
        MAKANAN.add(ESJERUK);
        MAKANAN.add(SOPBUAH);
    }

    public static final Makanan NASGOR = new Makanan("Nasi Goreng Biasa", 12000,"Nasi Goreng dengan daging ayam suir", R.drawable.makanan_nasigoreng);
    public static final Makanan BAKSO = new Makanan("Bakso Kuah", 10000,"Bakso urat/telur/biasa",R.drawable.makanan_bakso);
    public static final Makanan RENDANG = new Makanan("Rendang", 12000,"Rendang dengan nasi putih",R.drawable.makanan_rendang);
    public static final Makanan SATE = new Makanan("Sate", 10000,"Daging ayam pilihan 7 tusuk",R.drawable.makanan_sate);
    public static final Makanan SOTO = new Makanan("Soto", 8000,"Soto panas dengan suwiran ayam, sayur lengkap",R.drawable.makanan_soto);
    public static final Makanan ESTEH = new Makanan("Es Teh", 2000,"Teh Manis/Tawar",R.drawable.minuman_esteh);
    public static final Makanan ESJERUK = new Makanan("Es Jeruk", 3000,"Campuran jeruk peras dan gula",R.drawable.minuman_esjeruk);
    public static final Makanan SOPBUAH = new Makanan("Sop Buah", 6000,"Aneka buah dicampur sirup dan susu",R.drawable.minuman_sopbuah);
}