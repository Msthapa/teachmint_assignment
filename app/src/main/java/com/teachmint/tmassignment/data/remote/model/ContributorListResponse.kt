package com.teachmint.tmassignment.data.remote.model

import com.teachmint.tmassignment.data.model.ContributorUiModel


class ContributorListResponse : ArrayList<ContributorListResponseItem>()


fun ContributorListResponse.toContributorListUiModel() : List<ContributorUiModel> {
    val contributorList = mutableListOf<ContributorUiModel>()
    this.forEach {
        contributorList.add(
            ContributorUiModel(
                login = it.login,
                avatarUrl = it.avatarUrl
            )
        )
    }
    return contributorList
}