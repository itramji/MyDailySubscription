package com.bornbytes.mydailysubscription.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseRecyclerViewAdapter<T : Any, VB : ViewBinding> : RecyclerView.Adapter<BaseRecyclerViewAdapter<T, VB>.BaseRecyclerViewHolder>() {

    abstract fun getItemLayout(): Int

    var items = mutableListOf<T>()

    var itemListener: ((item: T, position: Int) -> Unit)? = null

    override fun getItemCount() = items.size

    fun addItems(list: List<T>) {
        items = list as MutableList<T>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BaseRecyclerViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), getItemLayout(), parent, false))

    inner class BaseRecyclerViewHolder(val binding: VB) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemListener?.let { listener ->
                binding.root.setOnClickListener {
                    listener.invoke(items[adapterPosition], adapterPosition)
                }
            }
        }
    }
}