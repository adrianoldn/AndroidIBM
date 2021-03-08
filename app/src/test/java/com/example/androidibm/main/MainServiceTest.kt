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

    @Captor
    lateinit var calbackCaptor: ArgumentCaptor<(events: List<Event>) -> Unit>

    @Captor
    lateinit var calbackCErroaptor: ArgumentCaptor<(msg: String) -> Unit>


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
    fun getEventsSucess() {
        val event =
            Event(listOf(), 2323, "Event", 32.03232, -3232.3232, "wwew", 32.44, "3232", "221")
        val lista = mutableListOf<Event>()
        lista.add(event)
        MokitoRepository(Observable.just(lista)).fetchEvents({
            mainView.setEventList(it)
        },{
            mainView.showLabelAnswer(View.VISIBLE)
        })

        var app = mock(AppRetrofit::class.java)
        val mainRepository = mock(MokitoRepository::class.java)
        mainRepository.lista = Observable.just(lista)




        `when`(mainRepository.fetchEvents({anyObject()},{anyObject()})).thenAnswer {
              (it.arguments[0] as ( list:  List<Event>) -> Unit).invoke(lista)
              (it.arguments[1] as (msg:  String) -> Unit).invoke("")

        }
        mainPresenter.getEvents()
//        Mockito.verify(mainView).showLabelAnswer(View.GONE)
//        Mockito.verify(mainView).showLoadDialog(View.VISIBLE)
        verify(mainRepository).fetchEvents({
            print(it)
        },{
            print(it)
        })


    }

    class MokitoRepository (var lista: Observable<List<Event>>) {
        fun fetchEvents(callback: (events: List<Event>) -> Unit, callbackErro: (msg: String) -> Unit){
            lista.subscribe({
                callback.invoke(it)
            },{
                callbackErro("")
            })
        }
    }







}