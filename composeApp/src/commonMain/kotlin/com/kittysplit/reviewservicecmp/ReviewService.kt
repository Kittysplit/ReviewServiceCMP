package com.kittysplit.reviewservicecmp

interface ActivityContainer

data object EmptyActivityContainer : ActivityContainer

sealed class ReviewResult() {
    data object Requested : ReviewResult()
    data object Failed : ReviewResult()
    data class RequestError(val code: Int) : ReviewResult()
}

interface ReviewService {
    suspend fun requestInAppReview(
        activityContainer: ActivityContainer = EmptyActivityContainer
    ): ReviewResult
}
