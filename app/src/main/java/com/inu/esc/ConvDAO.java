package com.inu.esc;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ConvDAO {

    @Query("select * from Conv order by id desc")
    public List<Conv> getAllConv();

    @Insert
    public void addConv(Conv conv);

    @Query("delete from Conv")
    public void deleteAllConv();


}
