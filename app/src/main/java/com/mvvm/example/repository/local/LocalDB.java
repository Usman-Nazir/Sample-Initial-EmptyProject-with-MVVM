package com.mvvm.example.repository.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mvvm.example.models.userLogin.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class LocalDB extends RoomDatabase {
    public abstract UsersDao usersDao();
}
