package com.teachmint.tmassignment.data.remote.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.teachmint.tmassignment.data.model.RepositoryUiModel

@Keep
data class RepoResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<RepoResponseItem>,
    @SerializedName("total_count")
    val totalCount: Int
)

fun RepoResponse.toRepositoryItemList() : List<RepositoryUiModel> {
    val repoList = mutableListOf<RepositoryUiModel>()
    items.forEach {
        repoList.add(
            RepositoryUiModel(
                repoId = it.id,
                repoName = it.name,
                ownerName = it.owner.login,
                description = it.description,
                ownerImageUrl = it.owner.avatarUrl,
                contributors_url = it.contributorsUrl,
                project_link = it.htmlUrl
            )
        )
    }
    return  repoList
}