package com.achers.ascmake.room;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.achers.ascmake.R;
import com.achers.ascmake.room.db.BaseDB;
import com.achers.ascmake.room.db.entity.User;

import java.util.List;

public class RoomActivity extends AppCompatActivity {

    private BaseDB mAppDatabase;
    private TextView content;
    private EditText add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        content = findViewById(R.id.content);
        add = findViewById(R.id.add);

        mAppDatabase = Room.databaseBuilder(this, BaseDB.class, "user_db")
                .allowMainThreadQueries()
                .build();
    }

    public void query(View view) {
        List<User> users = mAppDatabase.userDao().queryUserName(add.getText().toString().trim());
        content.setText(users.toString());
    }

    public void queryAll(View view) {
        List<User> users = mAppDatabase.userDao().queryAllUsers();
        content.setText(users.toString());
    }

    public void insert(View view) {
        User user = new User();
        user.setName(add.getText().toString().trim());
        mAppDatabase.userDao().insertUser(user);

        List<User> users = mAppDatabase.userDao().queryAllUsers();
        content.setText(users.toString());
    }

    public void delete(View view) {
        mAppDatabase.userDao().deleteNameUser(add.getText().toString().trim());

        List<User> users = mAppDatabase.userDao().queryAllUsers();
        content.setText(users.toString());
    }

    public void deleteAll(View view) {
        mAppDatabase.userDao().deleteAll();

        List<User> users = mAppDatabase.userDao().queryAllUsers();
        content.setText(users.toString());

    }

    public void update(View view) {
        List<User> user = mAppDatabase.userDao().queryUserName(add.getText().toString().trim());
        user.get(0).setName("骚潘");
        mAppDatabase.userDao().updateUsers(user.get(0));

    }

}
