package com.kittysplit.reviewservicecmp

import android.app.Activity
import android.content.Context
import com.google.android.play.core.review.ReviewException
import com.google.android.play.core.review.ReviewManagerFactory
import kotlinx.coroutines.tasks.await

data class AndroidContextHolder(val activity: Activity) : ActivityContainer

fun ActivityContainer.activity(): Activity? = (this as? AndroidContextHolder)?.activity

class AndroidReviewService(val context: Context) : ReviewService {
    private val reviewManager = ReviewManagerFactory.create(context)

    override suspend fun requestInAppReview(activityContainer: ActivityContainer): ReviewResult = try {
        val activity = activityContainer.activity()
        if (activity != null) {
            val reviewInfo = reviewManager.requestReviewFlow().await()
            reviewManager.launchReviewFlow(activity, reviewInfo).await()
            ReviewResult.Requested
        } else {
            ReviewResult.Failed
        }
    } catch (error: Exception) {
        if (error is ReviewException) {
            ReviewResult.RequestError(error.errorCode)
        } else {
            ReviewResult.Failed
        }
    }
}
