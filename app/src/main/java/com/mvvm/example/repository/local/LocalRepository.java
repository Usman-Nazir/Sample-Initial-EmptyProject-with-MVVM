package com.mvvm.example.repository.local;

import com.mvvm.example.models.userLogin.User;


import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LocalRepository {
    private final MyPrefManager prefManager;
    private final LocalDB localDB;
    private UsersDao usersDao;

    public LocalRepository(LocalDB localDB, MyPrefManager app_data) {
        this.usersDao = localDB.usersDao();
        this.localDB = localDB;
        this.prefManager = app_data;
    }

    public MyPrefManager getPrefManager() {
        return prefManager;
    }

    public Observable<List<User>> getAllUsers() {
        return Observable.fromCallable(() -> usersDao.getAll())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public void addUsers(List<User> users) {
        usersDao.insertAll(users);
    }

//    public void setUserCredentials(UserLoginResult result) {
//        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
//
//        Gson gson = new Gson();
//
//        String json = gson.toJson(result.user);
//
//        prefsEditor.remove(Constants.USER_CREDENTIALS);
//
//        if (result.user != null) {
//            prefsEditor.putString(Constants.USER_CREDENTIALS, json);
//        }
//        if (result.accessToken != null) {
//            prefsEditor.putString(Constants.USER_ACCESS_TOKEN, result.accessToken);
//        }
//        if (result.refreshToken != null) {
//            prefsEditor.putString(Constants.USER_REFRESH_TOKEN, result.refreshToken);
//        }
//        prefsEditor.apply();
//    }

    public void setDeviceMetaData(User result) {
//        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(result);
//        prefsEditor.remove(Constants.DEVICE_META_DATA);
//        prefsEditor.putString(Constants.DEVICE_META_DATA, json);
//        prefsEditor.apply();
    }

//    public void setDeviceMetaData(DeviceMetaData result) {
//        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(result);
//        prefsEditor.remove(Constants.DEVICE_META_DATA);
//        prefsEditor.putString(Constants.DEVICE_META_DATA, json);
//        prefsEditor.apply();
//    }
//
//    public DeviceMetaData getDeviceMetaData() {
//        if (sharedPreferences.contains(Constants.DEVICE_META_DATA)) {
//            final Gson gson = new Gson();
//            return gson.fromJson(sharedPreferences.getString(Constants.DEVICE_META_DATA, ""), DeviceMetaData.class);
//        }
//        return null;
//    }

    public User getUserCredentials() {
//        if (sharedPreferences.contains(Constants.USER_CREDENTIALS)) {
//            final Gson gson = new Gson();
//            return gson.fromJson(sharedPreferences.getString(Constants.USER_CREDENTIALS, ""), User.class);
//        }
        return null;
    }

    public Observable<User> getUser(int userId) {
        return Observable.fromCallable(() -> usersDao.getOne(userId));
    }

    public int nukeTable() {
        return usersDao.nukeTable();
    }

    public void deleteAndInsert(List<User> users) {
        usersDao.deleteAndCreate(users);
    }

    public Observable<Integer> deleteUser(int id) {
        return Observable.create((emitter) -> {
            Integer value = usersDao.deleteUser(id);

            emitter.onNext(value);
        });
    }

    public void addUser(User user) {
        usersDao.insert(user);
    }

    public void savePassword(String text) {
//        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
//        prefsEditor.remove(Constants.PASSWORD);
//        prefsEditor.putString(Constants.PASSWORD, text);
//        prefsEditor.apply();
    }

    public String getPassword() {
//        if (sharedPreferences.contains(Constants.PASSWORD)) {
//            final Gson gson = new Gson();
//            return sharedPreferences.getString(Constants.PASSWORD, "");
//        }
        return null;
    }
}
