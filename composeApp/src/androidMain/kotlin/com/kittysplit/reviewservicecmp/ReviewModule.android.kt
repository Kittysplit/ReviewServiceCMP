package com.kittysplit.reviewservicecmp

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual fun reviewModule(): Module = module {
    singleOf(::AndroidReviewService)
}
