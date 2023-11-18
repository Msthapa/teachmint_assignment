package com.teachmint.tmassignment.data.remote

import com.teachmint.tmassignment.BuildConfig
import com.teachmint.tmassignment.data.remote.model.ContributorListResponse
import com.teachmint.tmassignment.data.remote.model.RepoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") query : String,
        @Query("per_page") perPage : Int,
        @Query("page") pageNo : Int,
        @Header("Authorization") token : String = BuildConfig.PERSONAL_ACCESS_TOKEN
    ) : Response<RepoResponse>


    @GET
    suspend fun getContributorsList(
        @Url url: String
    ): Response<ContributorListResponse>

}