package com.inu.esc;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(version = 1,entities = {Conv.class})
public abstract class ConvDatabase extends RoomDatabase {
    private static ConvDatabase INSTANCE;
    public abstract ConvDAO convDAO();
    public static ConvDatabase getDatabase(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context,ConvDatabase.class,"conv.db").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
