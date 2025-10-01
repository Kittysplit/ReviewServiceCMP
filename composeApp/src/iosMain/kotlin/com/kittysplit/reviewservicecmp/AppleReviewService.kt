package com.kittysplit.reviewservicecmp

import platform.StoreKit.SKStoreReviewController
import platform.UIKit.UIApplication
import platform.UIKit.UISceneActivationStateForegroundActive
import platform.UIKit.UIWindowScene

class AppleReviewService : ReviewService {
    override suspend fun requestInAppReview(activityContainer: ActivityContainer): ReviewResult = try {
        val scene = UIApplication.sharedApplication.connectedScenes
            .filterIsInstance<UIWindowScene>()
            .firstOrNull { it.activationState == UISceneActivationStateForegroundActive }

        if (scene != null) {
            SKStoreReviewController.requestReviewInScene(scene)
        } else {
            SKStoreReviewController.requestReview()
        }
        ReviewResult.Requested
    } catch (_: Exception) {
        ReviewResult.Failed
    }
}
