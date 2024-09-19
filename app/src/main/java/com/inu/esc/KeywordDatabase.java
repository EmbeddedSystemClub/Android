package com.inu.esc;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(version = 1,entities = {Keyword.class})
public abstract class KeywordDatabase extends RoomDatabase {
    private static KeywordDatabase INSTANCE;
    public abstract KeywordDAO keywordDAO();

    public static KeywordDatabase getDatabase(Context context){
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context,KeywordDatabase.class,"keyword.db").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}