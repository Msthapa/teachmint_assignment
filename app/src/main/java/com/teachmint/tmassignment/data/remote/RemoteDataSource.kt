package com.teachmint.tmassignment.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor( private val apiService: ApiService )  {

    suspend fun searchRepositories(query : String, perPage : Int, pageNo : Int) = apiService.searchRepositories(query, perPage, pageNo)





}