package com.products100.di


import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DiRepositoryDatasourceModule {

//    @Singleton
//    @Binds
//    abstract fun repositoryAPI(repo: RepositoryApiImpementation): RepositoryAPIInterface
//
//    @Singleton
//    @Binds
//    abstract fun repositoryDAO(repo: RepositoryDAOImplementation): RepositoryDAOInterface
}