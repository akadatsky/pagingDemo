package com.akadatsky.testpaging

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MyAdapter : PagedListAdapter<MyItem, MyAdapter.MyViewHolder>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<MyItem>() {

            override fun areItemsTheSame(oldItem: MyItem, newItem: MyItem): Boolean =
                oldItem.text == newItem.text

            override fun areContentsTheSame(oldItem: MyItem, newItem: MyItem): Boolean =
                oldItem == newItem

        }
    }

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_my, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.textView.text = item?.text
    }

}