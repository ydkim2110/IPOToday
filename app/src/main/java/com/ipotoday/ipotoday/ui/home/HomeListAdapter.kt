package com.ipotoday.ipotoday.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ipotoday.ipotoday.data.model.HotIPOModel
import com.ipotoday.ipotoday.data.model.IPOModel
import com.ipotoday.ipotoday.databinding.ListItemIpoHeaderBinding
import com.ipotoday.ipotoday.databinding.ListItemIpoHotBinding
import com.ipotoday.ipotoday.databinding.ListItemIpoNormalBinding

class HomeListAdapter : ListAdapter<Any, RecyclerView.ViewHolder>(IPOListDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER_IPO -> IPOHeaderViewHolder(
                ListItemIpoHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            TYPE_HOT_IPO -> IPOHotViewHolder(
                ListItemIpoHotBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            TYPE_NORMAL_IPO -> IPOListViewHolder(
                ListItemIpoNormalBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw NoSuchElementException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is IPOHeaderViewHolder -> holder.bind(getItem(position) as String)
            is IPOHotViewHolder -> holder.bind(getItem(position) as HotIPOModel)
            is IPOListViewHolder -> holder.bind(getItem(position) as IPOModel)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is String -> TYPE_HEADER_IPO
            is HotIPOModel -> TYPE_HOT_IPO
            is IPOModel -> TYPE_NORMAL_IPO
            else -> throw NoSuchElementException()
        }
    }

    inner class IPOHeaderViewHolder(private val binding: ListItemIpoHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.title = item
        }
    }

    inner class IPOHotViewHolder(private val binding: ListItemIpoHotBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HotIPOModel) {
            binding.viewPager.adapter = HotIPOPagerAdapter(item.list)
        }
    }

    inner class IPOListViewHolder(private val binding: ListItemIpoNormalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: IPOModel) {
            // TODO https://yuar.tistory.com/85
        }
    }

    companion object {
        private const val TYPE_HEADER_IPO = 0
        private const val TYPE_HOT_IPO = 1
        private const val TYPE_NORMAL_IPO = 2
    }
}

private class IPOListDiffCallback : DiffUtil.ItemCallback<Any>() {
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return if (oldItem is HotIPOModel && newItem is HotIPOModel) {
            oldItem.id == newItem.id
        } else if (oldItem is IPOModel && newItem is IPOModel) {
            oldItem.id == newItem.id
        } else {
            throw IllegalArgumentException()
        }
    }
}