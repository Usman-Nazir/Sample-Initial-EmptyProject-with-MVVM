package com.mvvm.example.repository.remote

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

//import za.co.brightspace.bidms_cloud.model.Answer

interface RemoteServices {
//    @GET("terminal/{id}/users")
//    fun getUsers(@Path("id") terminalId: Int): Observable<List<User?>?>?
//
//    @POST("terminal/login")
//    fun loginUser(@Body userCredentials: UserCredentials?): Observable<UserLoginResult?>?
//
//    @POST("/register")
//    fun registerUser(@Body registerItem: RegisterModel?): Observable<Response<UserLoginResult?>?>?
//
//    @Multipart
//    @POST("/file")
//    fun uploadFile(@Part("user_id") id: RequestBody?, @Part("organization_id") organization: RequestBody?, @Part("destination") destination: RequestBody?, @Part body: Part?): Observable<Response<Int?>?>?
//
//    @POST("/register/{id}")
//    fun registerUserInternal(@Path("id") organization_id: Int, @Body registerItem: RegisterModel?): Observable<Response<User?>?>?
//
//    @GET("/image")
//    fun download(@Query("id") id: Int, @Query("organization_id") organizationId: Int): Observable<Response<ResponseBody?>?>?
//
//    @PUT("/user/{id}")
//    fun updateUser(@Path("id") userId: Int, @Body registerItem: User?): Observable<User?>?
//
//    @DELETE("/user/{id}")
//    fun deleteUser(@Path("id") userId: Int): Observable<Response<Void?>?>?
//
//    @GET("/answer/{id}")
//    fun userLoggedStatus(@Path("id") id: Int): Observable<Response<UserAnswerStatus?>?>?
//
//    @POST("/answer/{id}")
//    fun saveAnswer(@Path("id") id: Int, @Body answer: Answer?): Observable<Response<Void?>?>?
//
//    @GET("/record")
//    fun getAllUsersRecords(@Query("page") page: Int, @Query("pageSize") pageSize: Int, @Query("date") date: String?): Call<UserRecordResponse?>?
//
//    @GET("/answer")
//    fun getAnswer(@Query("user_id") userId: Int, @Query("date") date: String?): Observable<Response<Answer?>?>?
//
//    @GET("/record/user")
//    fun getAllUsersRecords(@Query("date") mDate: String?, @Query("user_id") userId: Int): Observable<List<UserRecord?>?>?
//
//    @GET("/user/{id}")
//    fun getUser(@Path("id") userId: Int): Observable<Response<User?>?>?
//
//    @get:GET("/organizations")
//    val allOrganizations: Observable<Response<List<Any?>?>?>?
//
//    @get:GET("terminal/organizations")
//    val userOrganizations: Observable<Response<List<Any?>?>?>?
//
//    @GET("terminal/organization/{id}/departments")
//    fun getUserOrganizationDepartments(@Path("id") organizationId: Int): Observable<Response<List<Department?>?>?>?
//
//    @GET("terminal/{id}")
//    fun getDeviceMetaData(@Path("id") hardwareId: Int): Observable<Response<DeviceMetaData?>?>?
//
//    @POST("terminal")
//    fun saveDeviceMetaData(@Body deviceMetaData: DeviceMetaData?): Observable<Response<DeviceMetaData?>?>?
//
//    @GET("feature/{id}")
//    fun getFeature(@Path("id") name: String?): Observable<Response<ResponseBody?>?>?
//
//    @PUT("terminal/{id}")
//    fun updateDeviceMetaData(@Body deviceMetaData: DeviceMetaData?, @Path("id") terminalId: Int): Observable<Response<DeviceMetaData?>?>?
//
//    @POST("terminal/{id}/record")
//    fun saveRecord(@Body record: Record?, @Path("id") terminalId: Int): Observable<Response<Void?>?>?

//    @Headers("Content-Type:application/x-www-form-urlencoded")
//    @FormUrlEncoded
    @GET("get/venues")
    suspend fun getAllVenues(): Response<ResponseBody>

    @GET("get/gateways/{venueId}")
    suspend fun getGateWays(
        @Path("venueId") venueId:String
    ): Response<ResponseBody>

    @GET("get/beacons/{gateWayId}")
    suspend fun getBeacons(
        @Path("gateWayId") gateWayId:String
    ): Response<ResponseBody>

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("email") name:String,
        @Field("password") password:String,
    ): Response<ResponseBody>

    @FormUrlEncoded
    @POST("auth/social-login")
    suspend fun socialLogin(
        @Field("email") name:String,
        @Field("name") password:String,
    ): Response<ResponseBody>

    @FormUrlEncoded
    @POST("auth/register")
    suspend fun register(
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("password") password:String,
        @Field("password_confirmation") passwordConfirmation:String,
    ): Response<ResponseBody>

    @FormUrlEncoded
    @POST("auth/forgot-password")
    suspend fun forgotPassword(
        @Field("email") email:String,
    ): Response<ResponseBody>

    @FormUrlEncoded
    @POST("auth/verify-pin")
    suspend fun verifyPin(
        @Field("email") email:String,
        @Field("pin") pin:String,
    ): Response<ResponseBody>

    @FormUrlEncoded
    @POST("auth/reset-password")
    suspend fun updateNewPassword(
        @Field("email") email:String,
        @Field("password") password:String,
        @Field("password_confirmation") passwordConfirmation:String,
    ): Response<ResponseBody>

    @GET("get-list")
    suspend fun searchList(
        @Header("Authorization") token:String,
        @Query("long") long:String,
        @Query("lat") lat:String,
        @Query("name") name:String,
    ): Response<ResponseBody>

    @GET("get-beacons/{beacon}")
    suspend fun getBeaconByVenue(
        @Header("Authorization") token:String,
        @Path("beacon") beacon:String
    ): Response<ResponseBody>

    @GET("get-location")
    suspend fun getLocation(
        @Header("Authorization") token:String,
        @Query("long") long:String,
        @Query("lat") lat:String,
    ): Response<ResponseBody>
}