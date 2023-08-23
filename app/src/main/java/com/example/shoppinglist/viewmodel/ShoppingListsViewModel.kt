package com.example.shoppinglist.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.R
import com.example.shoppinglist.model.SHOPPING_LISTS
import com.example.shoppinglist.model.ShoppingList
import com.example.shoppinlist.viewmodel.BaseViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class ShoppingListsViewModel(application: Application): BaseViewModel(application) {
    // TODO place the mutable live data here

    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    val shoppingLists = MutableLiveData<List<ShoppingList>>()
    private val disposable = CompositeDisposable()

    private fun fetchShoppingLists():Single<List<ShoppingList>> {
        return Single.create {emitter ->
            emitter.onSuccess(SHOPPING_LISTS)
        }

    }

    fun setup() {
        // TODO do any setup you might need
        loading.value = true
        disposable.add(  // to help garbage collector to do its job
            fetchShoppingLists()  // action to execute
                .subscribeOn(Schedulers.io())  // where the action is going to be performed
                .observeOn(AndroidSchedulers.mainThread())  // where you will receive the result
                .subscribeWith(object : DisposableSingleObserver<List<ShoppingList>>() {  // what you will do with the result once you receive it
                    override fun onSuccess(serverShoppingLists: List<ShoppingList>) {
                        loading.value = false
                        shoppingLists.value = serverShoppingLists
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        error.value = resources.getString(R.string.error_lists_fetching)
                    }
                })
        )
    }


}