package com.choimuhtadin.githubuserfinder.domain.utils

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler
}