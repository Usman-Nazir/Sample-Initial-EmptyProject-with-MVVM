package com.mvvm.example.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.example.databinding.ItemIntroScreenBinding

class IntroAdapter(var contextt: Context, var data: MutableList<IntroScreensModel>) : RecyclerView.Adapter<IntroAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: ItemIntroScreenBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
        }

        fun bind(item: IntroScreensModel) {
            binding.logoImg.setImageResource(item.image)
            binding.title.text = item.labelTop
            binding.description.text = item.labelSubTitle
            if (item.labelTop?.isEmpty())
                binding.title.visibility = View.GONE

            if (item.labelSubTitle?.isEmpty())
                binding.description.visibility = View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_intro_screen, parent, false)
        var binding = ItemIntroScreenBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item: IntroScreensModel = data[position]
        holder.bind(item)
    }
}


data class IntroScreensModel(val image: Int, val labelTop: String, val labelSubTitle: String, val pageNo: Int)
