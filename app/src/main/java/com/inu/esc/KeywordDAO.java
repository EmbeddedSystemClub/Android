package com.inu.esc;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface KeywordDAO {
    @Query("Select * from Keyword")
    public List<Keyword> getAllKeywords();
    @Query("select * from Keyword where  :word like '%' || keyword || '%'")
    public List<Keyword> findKeyword(String word);

    @Insert
    public void addKeyword(Keyword keyword);

    @Query("delete from Keyword where id = :idx")
    public void deleteKeyword(long idx);

}
