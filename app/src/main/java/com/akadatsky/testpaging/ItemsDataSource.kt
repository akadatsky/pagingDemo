package com.akadatsky.testpaging

import android.arch.paging.PageKeyedDataSource
import io.reactivex.Completable
import io.reactivex.functions.Action

class ItemsDataSource : PageKeyedDataSource<Int, MyItem>() {


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, MyItem>) {
        /*
        val items = mutableListOf<MyItem>()
        val currentIndex = 0

        for (i in 1..10) {
            items.add(MyItem("asdf $currentIndex $i"))
        }

        callback.onResult(items, null, currentIndex + 1)
        */

        setRetry(Action { loadInitial(params, callback) })
    }

    private var retryCompletable: Completable? = null

    private fun setRetry(action: Action?) {
        if (action == null) {
            this.retryCompletable = null
        } else {
            this.retryCompletable = Completable.fromAction(action)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MyItem>) {

        val items = mutableListOf<MyItem>()

        if (params.key <= 5) {
            for (i in 1..10) {
                items.add(MyItem("asdf ${params.key} $i"))
            }
        }

        Thread.sleep(500)

        callback.onResult(items, params.key + 1)


    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MyItem>) {
    }

}