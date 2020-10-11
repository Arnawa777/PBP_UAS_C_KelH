package com.arnawajuan.tubes_uts.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.arnawajuan.tubes_uts.model.Reservation;

import java.util.List;


@Dao
public interface ReservationDao {

    @Query("SELECT * FROM reservation")
    List<Reservation> getAll();

    @Insert
    void insert(Reservation reservation);

    @Update
    void update(Reservation reservation);

    @Delete
    void delete(Reservation reservation);

}