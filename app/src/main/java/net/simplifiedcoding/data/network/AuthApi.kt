package net.simplifiedcoding.data.network

import net.simplifiedcoding.data.responses.LoginResponseEmod
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {
    /*@FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ) : LoginResponse*/

    @FormUrlEncoded
    @POST("authentication/mob_api")
    suspend fun login(
        @Field("student_id") student_id: String,
        @Field("mobile") mobile: String
    ) : LoginResponseEmod

}