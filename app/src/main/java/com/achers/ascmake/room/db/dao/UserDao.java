package com.achers.ascmake.room.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.achers.ascmake.room.db.entity.User;

import java.util.List;

/**
 * Created on 2019/7/25 16:06
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:
 */
@Dao
public interface UserDao {

    /**
     * 1. OnConflictStrategy.REPLACE：冲突策略是取代旧数据同时继续事务。
     *        2. OnConflictStrategy.ROLLBACK：冲突策略是回滚事务。
     *        3. OnConflictStrategy.ABORT：冲突策略是终止事务。
     *        4. OnConflictStrategy.FAIL：冲突策略是事务失败。
     *        5. OnConflictStrategy.IGNORE：冲突策略是忽略冲突。
     *
     * @param users
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User... users);

    @Insert
    void insertBothUsers(User user1, User user2);

    @Insert
    void insertUsersAndFriends(User user, List<User> friends);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updateUsers(User... users);

    @Delete
    void deleteUsers(User... users);

    @Query("DELETE FROM USER_TABLE WHERE user_name == :name")
    void deleteNameUser(String name);


    @Query("DELETE FROM USER_TABLE")
    void deleteAll();

    @Query("SELECT * FROM user_table ORDER BY id DESC LIMIT 10")
    List<User> queryAllUsers();

    @Query("SELECT * FROM  user_table WHERE user_name == :name")
    List<User> queryUserName(String name);

    @Query("SELECT * FROM  user_table WHERE id == :id")
    User queryUserId(int id);
}
