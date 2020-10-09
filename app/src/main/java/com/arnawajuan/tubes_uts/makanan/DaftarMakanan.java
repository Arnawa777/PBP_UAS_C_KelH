package com.arnawajuan.tubes_uts.makanan;

import com.arnawajuan.tubes_uts.R;

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
    }

    public static final Makanan NASGOR = new Makanan("Nasi Goreng Biasa", 12000,"Nasi Goreng dengan daging ayam suir", R.drawable.nasi_goreng);
    public static final Makanan BAKSO = new Makanan("Bakso Kuah", 10000,"Bakso nikmat tanpa pelengkap",R.drawable.bakso);
    public static final Makanan RENDANG = new Makanan("Rendang", 12000,"Rendang sudah sama nasi putih",R.drawable.rendang);
    public static final Makanan SATE = new Makanan("Sate", 10000,"Sate Ayam tidak kecil",R.drawable.sate);
    public static final Makanan SOTO = new Makanan("Soto", 8000,"Soto panas dengan sayur lengkap",R.drawable.soto);
}