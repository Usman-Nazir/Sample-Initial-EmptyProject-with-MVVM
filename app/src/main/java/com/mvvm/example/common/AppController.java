package com.mvvm.example.common;

import androidx.room.Room;

import com.mvvm.example.repository.Repository;
import com.mvvm.example.repository.local.DBConstant;
import com.mvvm.example.repository.local.LocalDB;
import com.mvvm.example.repository.local.LocalRepository;
import com.mvvm.example.repository.local.MyPrefManager;
import com.mvvm.example.repository.remote.RemoteRepository;

public class AppController extends android.app.Application {
    private static AppController mInstance;
    public static MyPrefManager prefManager;
    private static Repository mRepository;
    private static LocalRepository localRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        prefManager = new MyPrefManager(this);
        mInstance = this;
        RemoteRepository remoteRepository = new RemoteRepository();
        LocalDB localDB = Room
                .databaseBuilder(getApplicationContext(), LocalDB.class, DBConstant.DB_NAME)
                .fallbackToDestructiveMigration()
                .build();
        localRepository = new LocalRepository(localDB, prefManager);
        mRepository = new Repository(remoteRepository, localRepository);
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public static synchronized Repository getRepository() {
        return mRepository;
    }

    public static synchronized MyPrefManager getMyPrefManager() {
        return prefManager;
    }
}
