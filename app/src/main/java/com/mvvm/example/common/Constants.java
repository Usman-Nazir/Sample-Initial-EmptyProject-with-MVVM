package com.mvvm.example.common;

public class Constants {

    /**
     * IR预览数据相对于RGB预览数据的横向偏移量，注意：是预览数据，一般的摄像头的预览数据都是 width > height
     */
    public static final int HORIZONTAL_OFFSET = 0;
    /**
     * IR预览数据相对于RGB预览数据的纵向偏移量，注意：是预览数据，一般的摄像头的预览数据都是 width > height
     */
    public static final int VERTICAL_OFFSET = 0;

    public static final String SHARED_PREFERENCES = "APP_DATA";

    public static final String USER_CREDENTIALS = "USER_CREDENTIALS";

    public static final String DEVICE_META_DATA = "DEVICE_META_DATA";

    public static final String SIMILAR_THRESHOLD_OPTION = "SIMILAR_THRESHOLD_OPTION";

    public static final String USER_ACCESS_TOKEN = "USER_ACCESS_TOKEN";

    public static final String USER_REFRESH_TOKEN = "USER_REFRESH_TOKEN";

    public static String PASSWORD = "EYE_CANDY";
}
