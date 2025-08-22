package com.products100.di

import android.content.Context
import androidx.room.Room

import com.products100.api.APIDataSource
import com.products100.constants.APPTitlesAndDesc
import com.products100.data.AppDataBase

import com.products100.repository.RepositoryAPIInterface
import com.products100.repository.RepositoryApiImpementation



import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DiDatasourceModule {

    //API data source Retrofit

    @Singleton
    @Provides
    @Named("BaseURL")
    fun provideBaseURL() = "https://randomuser.me/api/"

    @Singleton
    @Provides
    fun provideRetrofit(@Named("BaseURL") baseURL:String):Retrofit{
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(baseURL).build()
    }

    @Singleton
    @Provides
    fun dataSourceAPI(instanciaRetrofit:Retrofit): APIDataSource = instanciaRetrofit.create(APIDataSource::class.java)

    //aqui se indica que implementacion del repositorio se desea usar cuando se inyecte la instancia de la interfaz
    @Provides
    @Singleton
    fun provideRepositoryAPIInterface(): RepositoryAPIInterface = RepositoryApiImpementation(dataSourceAPI(provideRetrofit(provideBaseURL())))


    //DAO Data Source
    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDataBase::class.java, APPTitlesAndDesc.DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideBooksDAO(db:AppDataBase) = db.booksDAO()


}