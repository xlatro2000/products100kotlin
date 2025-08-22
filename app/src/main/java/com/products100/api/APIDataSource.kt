package com.products100.api

import com.products100.model.ResponseAPI
import retrofit2.http.GET

interface APIDataSource {
    @GET(/* value = */ "?results=20")
    suspend fun getUsers(): ResponseAPI
}