package com.arnawajuan.rumah_makan_cilik.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.arnawajuan.rumah_makan_cilik.model.Reservation;

@Database(entities = {Reservation.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ReservationDao reservationDao();
}
