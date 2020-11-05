package net.simplifiedcoding.data.repository

import net.simplifiedcoding.data.UserPreferences
import net.simplifiedcoding.data.network.AuthApi

class AuthRepository(
    private val api: AuthApi,
    private val preferences: UserPreferences
) : BaseRepository(){

    /*suspend fun login(
        email: String,
        password: String
    ) = safeApiCall {
        api.login(email, password)
    }*/

    suspend fun login(
        student_id: String,
        mobile: String
    ) = safeApiCall {
        api.login(student_id, mobile)
    }

    suspend fun saveAuthToken(token: String){
        preferences.saveAuthToken(token)
    }

}