package com.ipotoday.ipotoday.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ipotoday.ipotoday.data.model.IPOModel
import com.ipotoday.ipotoday.databinding.PagerItemIpoBinding

class HotIPOPagerAdapter(private val list: List<IPOModel>) : RecyclerView.Adapter<HotIPOPagerAdapter.PageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        return PageViewHolder(PagerItemIpoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class PageViewHolder(private val binding: PagerItemIpoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: IPOModel) {
            binding.ipoModel = item

            binding.executePendingBindings()
        }
    }
}