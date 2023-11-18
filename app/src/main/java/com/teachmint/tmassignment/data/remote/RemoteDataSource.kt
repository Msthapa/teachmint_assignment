package com.teachmint.tmassignment.data.remote

import com.teachmint.tmassignment.util.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor( private val apiService: ApiService ) : BaseDataSource() {

    suspend fun searchRepositories(query : String, perPage : Int, pageNo : Int) =  handleApiCall {
          apiService.searchRepositories(query, perPage, pageNo)
        }



}