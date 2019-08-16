package com.achers.ascmake.room.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created on 2019/7/25 16:06
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:
 */
@Entity(tableName = "user_table",indices = {@Index("user_name")})
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "user_name")
    public String name;

    @ColumnInfo(name = "child_age")
    public int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "id" + id  + "  name " + name + "  age" +age;
    }
}
