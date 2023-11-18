package com.teachmint.tmassignment.di

import android.content.Context
import com.google.gson.Gson
import com.teachmint.tmassignment.BuildConfig.BASE_URL_GIT
import com.teachmint.tmassignment.data.local.LocalDataSource
import com.teachmint.tmassignment.data.remote.ApiService
import com.teachmint.tmassignment.data.remote.RemoteDataSource
import com.teachmint.tmassignment.repository.AssignmentRepository
import com.teachmint.tmassignment.repository.AssignmentRepositoryImpl
import com.teachmint.tmassignment.util.AppConstants.JOB_TIMEOUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchGitRepoAppModule {

    @Singleton
    @Provides
    fun providesApplicationContext(@ApplicationContext context: Context) = context

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()

        okHttpClient.connectTimeout(JOB_TIMEOUT, TimeUnit.MILLISECONDS)
        okHttpClient.readTimeout(JOB_TIMEOUT, TimeUnit.MILLISECONDS)
        okHttpClient.writeTimeout(JOB_TIMEOUT, TimeUnit.MILLISECONDS)
        okHttpClient.retryOnConnectionFailure(true)

        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)


    @Provides
    @Singleton
    fun provideRetrofit(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_GIT)
            .client(okhttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAssignmentRepository(
        assignLocalDataSource : LocalDataSource,
        assignNetworkDataSource : RemoteDataSource
    ): AssignmentRepository {
        return AssignmentRepositoryImpl( assignLocalDataSource, assignNetworkDataSource)
    }

}