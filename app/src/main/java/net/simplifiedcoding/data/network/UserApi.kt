package net.simplifiedcoding.data.network

import net.simplifiedcoding.data.responses.LoginResponseEmod
import okhttp3.ResponseBody
import retrofit2.http.POST

interface UserApi {

    /*@GET("user")
    suspend fun getUser(): LoginResponse*/

    @POST("authentication/mob_api")
    suspend fun getUser(): LoginResponseEmod

    @POST("logout")
    suspend fun logout(): ResponseBody

}