package com.teachmint.tmassignment.data.model

data class RepositoryUiModel (
    val repoId : Int,
    val repoName : String,
    val repoFullName : String,
    val ownerName : String,
    val description : String?,
    val ownerImageUrl : String,
    val contributors_url : String,
    val project_link : String,
    )