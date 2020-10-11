package com.arnawajuan.tubes_uts.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.arnawajuan.tubes_uts.model.Reservation;

@Database(entities = {Reservation.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ReservationDao reservationDao();
}
