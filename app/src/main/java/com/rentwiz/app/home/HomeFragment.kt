package com.rentwiz.app.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.Text
import androidx.fragment.app.Fragment
import com.rentwiz.app.databinding.FragmentHomeBinding
import com.rentwiz.app.network.DataSource
import com.rentwiz.app.network.Task
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    val disposables = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //simpleObserver()
        //createObservable()
        implementNetworkCall()
        binding.composeView.setContent {
           Text(text = "Incred")
        }
        //observableOnButton()
        //taskList()
    }

    private fun simpleObserver() {
        val list = listOf("A", "B", "C")
        val observable = Observable.fromIterable(list)

        observable.subscribe(object: Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.d("Cheezy","onSubscribe is called")
            }

            override fun onError(e: Throwable) {
                Log.d("Cheezy","onError is called - ${e.message}")
            }

            override fun onComplete() {
                Log.d("Cheezy","onComplete is called")
            }

            override fun onNext(t: String) {
                Log.d("Cheezy","onNext is called - $t")
            }

        })
    }

    private fun createObservable() {
        val observable = Observable.create {
            it.onNext("One")
            it.onError(IllegalStateException("Invalid State"))
            it.onNext("Two")
            it.onComplete()
        }
        observable.subscribe(object: Observer<String>{
            override fun onSubscribe(d: Disposable) {
                Log.d("Cheezy","onSubscribe is called")
            }

            override fun onError(e: Throwable) {
                Log.d("Cheezy","onError is called - ${e.message}")
            }

            override fun onComplete() {
                Log.d("Cheezy","onComplete is called")
            }

            override fun onNext(t: String) {
                Log.d("Cheezy","onNext is called - $t")
            }

        })

    }

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private fun implementNetworkCall() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://mocki.io/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

    }

    private fun taskList() {
        val taskObservable = Observable
            .fromIterable(DataSource.createTaskList())
            .subscribeOn(Schedulers.io())
            .filter {
                Log.d("Cheezy","Filter is called - ${Thread.currentThread().name}")
                it.isComplete
            }
            .observeOn(AndroidSchedulers.mainThread())

        taskObservable.subscribe(object: Observer<Task>{
            override fun onSubscribe(d: Disposable) {
                Log.d("Cheezy","onSubscribe is called")
                disposables.add(d)
            }

            override fun onError(e: Throwable) {
                Log.d("Cheezy","onError is called - ${e.message}")
            }

            override fun onComplete() {
                Log.d("Cheezy","onComplete is called")
                disposables.dispose()
            }

            override fun onNext(t: Task) {
                Log.d("Cheezy","onNext is called - ${Thread.currentThread().name}")
                Log.d("Cheezy","onNext is called - ${t.description}")
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

}