package com.mvvm.example.views.homeScreen.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mvvm.example.R
import com.mvvm.example.databinding.ItemCategoriesBinding


class ProcuriotCategoriesAdapter(var data: MutableList<String>, var context: Context, var viewTransactionCallBack: (String) -> Unit) : RecyclerView.Adapter<ProcuriotCategoriesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = ItemCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (data == null) 0
        else data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data.get(position).let {
            holder.bind(it)
        }

    }

    fun updateData(data: MutableList<String>) {

        this.data.clear()
        this.data = data
        notifyDataSetChanged()
    }

    init {
        data = arrayListOf()
    }

    inner class ViewHolder(var binding: ItemCategoriesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(it: String) {
            binding.title.text = "General"
            Glide.with(binding.logo).load(it).apply(RequestOptions().error(R.drawable.ic_restaurant)).into(binding.logo)

        }

        init {
            binding.rootLayout.setOnClickListener {
                data.getOrNull(adapterPosition)?.let {
                    viewTransactionCallBack.invoke(it)
                }
            }
        }
    }

}
