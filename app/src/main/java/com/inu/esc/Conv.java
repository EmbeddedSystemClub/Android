package com.inu.esc;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Conv {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo()
    public String msg;

    @ColumnInfo()
    public int dgr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getDgr() {
        return dgr;
    }

    public void setDgr(int dgr) {
        this.dgr = dgr;
    }
}
