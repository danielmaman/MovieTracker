package com.example.movieshowstracker.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class BaseUseCase<T, R : BaseUseCase.Request> {

    protected abstract fun create(request: R): Single<T>

    fun execute(request: R): LiveData<T> {
        return LiveDataReactiveStreams.fromPublisher( create(request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toFlowable())
    }

    abstract class Request
}
