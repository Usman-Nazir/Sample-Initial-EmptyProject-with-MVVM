package com.mvvm.example.repository.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.mvvm.example.models.userLogin.User;

import java.util.List;

@Dao
public abstract class UsersDao {
    @Transaction
    public void deleteAndCreate(List<User> users) {
        deleteAll();
        insertAll(users);
    }

    @Query("DELETE FROM " + DBConstant.USERS_TABLE_NAME)
    public abstract void deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insertAll(List<User> items);

    @Query("SELECT * FROM " + DBConstant.USERS_TABLE_NAME)
    abstract List<User> getAll();

    @Query("SELECT * FROM " + DBConstant.USERS_TABLE_NAME + " WHERE id=:id LIMIT 1")
    abstract User getOne(int id);

    @Query("DELETE FROM " + DBConstant.USERS_TABLE_NAME)
    abstract int nukeTable();

    @Query("DELETE FROM " + DBConstant.USERS_TABLE_NAME + " WHERE id=:id")
    abstract int deleteUser(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract long insert(User user);
}
