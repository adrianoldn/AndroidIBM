package com.example.androidibm.main

import android.os.Handler
import android.view.View
import com.example.androidibm.contract.MainContract
import com.example.androidibm.model.Event
import com.example.androidibm.presenter.MainPresenter
import com.example.androidibm.repository.MainRepository
import com.example.testecathodev.network.AppRetrofit
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.Response
import org.junit.Before
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.*
import java.util.concurrent.Callable
import javax.security.auth.callback.Callback


class MainServiceTest {



    @Mock
    lateinit var mainPresenter: MainContract.Presenter

    @Mock
    lateinit var mainView: MainContract.View

    @Mock
    lateinit var mainRepository: MainRepository



    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler: Callable<Scheduler?>? -> Schedulers.trampoline() }
        MockitoAnnotations.initMocks(this)
        mainRepository = MainRepository(AppRetrofit())
        mainPresenter = MainPresenter(mainView)

    }

    private fun <T> anyObject(): T {
        Mockito.anyObject<T>()
        return uninitialized()
    }

    private fun <T> uninitialized(): T = null as T

    @Test
    fun getEvents() {
        mainPresenter.getEvents()
        Mockito.verify(mainView).showLabelAnswer(View.GONE)
        Mockito.verify(mainView).showLoadDialog(View.VISIBLE)


    }









}