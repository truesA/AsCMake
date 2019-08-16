package com.achers.ascmake.room.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.achers.ascmake.room.db.dao.UserDao;
import com.achers.ascmake.room.db.entity.User;

/**
 * Created on 2019/7/25 16:05
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:
 */
@Database(entities = {User.class},version = 1)
abstract public class BaseDB extends RoomDatabase {

   public abstract UserDao userDao();

}
