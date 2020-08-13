package com.example.movieshowstracker.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class BaseCompletableUseCase<R : BaseCompletableUseCase.Request> {

    protected abstract fun create(request: R): Completable

    fun execute(request: R): LiveData<Boolean> {
        return LiveDataReactiveStreams.fromPublisher( create(request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toFlowable())
    }

    abstract class Request
}
