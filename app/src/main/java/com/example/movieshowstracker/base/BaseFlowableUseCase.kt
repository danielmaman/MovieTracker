package com.example.movieshowstracker.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class BaseFlowableUseCase<T, R : BaseFlowableUseCase.Request> {

    protected abstract fun create(request: R): Flowable<T>

    fun execute(request: R): LiveData<T> {
        return LiveDataReactiveStreams.fromPublisher( create(request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()))
    }

    abstract class Request
}
