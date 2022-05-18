package com.mvvm.example.models.userLogin;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.mvvm.example.repository.local.DBConstant;
import com.mvvm.example.repository.remote.RemoteRepository;

@Entity(tableName ="Users")
public class User {
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = DBConstant.USER_ID)
    private int id;

    @SerializedName("firstName")
    @ColumnInfo(name = DBConstant.USER_FIRST_NAME)
    private String firstName;

    @SerializedName("lastName")
    @ColumnInfo(name = DBConstant.USER_LAST_NAME)
    private String lastName;

    @SerializedName("organization_id")
    @ColumnInfo(name = DBConstant.USER_ORGANIZATION)
    private int organization;

    @SerializedName("organizationName")
    @ColumnInfo(name = DBConstant.USER_ORGANIZATION_NAME)
    private String organizationName;

    @SerializedName("email")
    @ColumnInfo(name = DBConstant.USER_EMAIL)
    private String email;

    @SerializedName("image")
    @ColumnInfo(name = DBConstant.USER_IMAGE)
    private String imgUrl;

    @SerializedName("feature")
    @ColumnInfo(name = DBConstant.USER_FEATURE)
    private String featureUrl;

    @SerializedName("password")
    @ColumnInfo(name = DBConstant.PASSWORD)
    private String password;

    @SerializedName("isAdmin")
    @ColumnInfo(name = DBConstant.IS_ADMIN)
    public Boolean isAdmin;

    @SerializedName("isVisitor")
    @ColumnInfo(name = DBConstant.IS_VISITOR)
    public Boolean isVisitor;

//    @SerializedName("errors")
//    @TypeConverters(ErrorConverter.class)
//    public List<String> errors;

    public Boolean getVisitor() {
        return isVisitor;
    }

    public void setVisitor(Boolean visitor) {
        isVisitor = visitor;
    }

//    public List<String> getErrors() {
//        return errors;
//    }

//    public void setErrors(List<String> errors) {
//        this.errors = errors;
//    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getOrganization() {
        return organization;
    }

    public void setOrganization(int organization) {
        this.organization = organization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String fullName() {
        return firstName + " " + lastName;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String getImgUrl() {
        if (imgUrl != null) {
            return RemoteRepository.BASE_URL + imgUrl;
        }
        return null;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getFeatureUrl() {
        if (featureUrl != null) {
            return RemoteRepository.BASE_URL + featureUrl;
        }
        return null;
    }

    public void setFeatureUrl(String featureUrl) {
        this.featureUrl = featureUrl;
    }

    public String getImgName() {
        if (imgUrl != null) {
            return imgUrl.replaceAll(".jpg", "");
        }
        return null;
    }

    public String getFeatureName() {
        if (featureUrl != null) {
            String[] strings = featureUrl.split("/");

            return strings[strings.length - 1];
        }
        return null;
    }

    public String getImageName() {
        if (imgUrl != null) {
            String[] strings = imgUrl.split("/");

            return strings[strings.length - 1];
        }
        return null;
    }
}


