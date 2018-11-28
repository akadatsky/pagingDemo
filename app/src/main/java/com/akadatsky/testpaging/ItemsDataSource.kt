package com.akadatsky.testpaging

import android.arch.paging.PageKeyedDataSource

class ItemsDataSource : PageKeyedDataSource<Int, MyItem>() {


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, MyItem>) {

        val currentIndex = 0

        val items = mutableListOf<MyItem>()
        for (i in 1..10) {
            items.add(MyItem("asdf $currentIndex $i"))
        }

        Thread.sleep(1000)

        callback.onResult(items, null, currentIndex + 1)

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MyItem>) {

        val items = mutableListOf<MyItem>()
        for (i in 1..10) {
            items.add(MyItem("asdf ${params.key} $i"))
        }

        Thread.sleep(1000)

        callback.onResult(items, params.key + 1)


    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MyItem>) {
    }

}