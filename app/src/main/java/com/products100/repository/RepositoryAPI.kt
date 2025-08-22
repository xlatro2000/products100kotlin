package com.products100.repository

import com.products100.api.APIDataSource
import com.products100.model.ResponseAPI
import javax.inject.Inject

interface RepositoryAPIInterface {
    suspend fun GetUsers():ResponseAPI
}


class RepositoryApiImpementation @Inject constructor(
    private val datasource: APIDataSource
): RepositoryAPIInterface
{
    override suspend fun GetUsers(): ResponseAPI {
        return datasource.getUsers()
    }
}