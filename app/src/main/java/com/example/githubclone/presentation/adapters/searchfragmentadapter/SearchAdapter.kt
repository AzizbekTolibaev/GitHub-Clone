package com.example.githubclone.presentation.adapters.searchfragmentadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.githubclone.data.dao.entity.SearchData
import com.example.githubclone.databinding.ItemSearchBinding

class SearchAdapter: ListAdapter<SearchData, SearchAdapter.SearchViewHolder>(myDiffUtil) {

    private var itemClickListener: ((SearchData) -> Unit)? = null

    fun setItemClickListener(block: (SearchData) -> Unit) {
        itemClickListener = block
    }

    inner class SearchViewHolder(private val binding: ItemSearchBinding): ViewHolder(binding.root) {
        fun bind(position: Int) {
            val d = getItem(position)

            binding.tvName.text = d.name

            binding.root.setOnClickListener {
                itemClickListener?.invoke(d)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(position)
    }

    private object myDiffUtil: DiffUtil.ItemCallback<SearchData>() {
        override fun areItemsTheSame(oldItem: SearchData, newItem: SearchData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: SearchData, newItem: SearchData): Boolean {
            return oldItem.id == newItem.id && oldItem.name == newItem.name
        }
    }

    fun removeItem(item: SearchData){
        val currentList =  currentList.toMutableList()
        currentList.remove(item)
    }
}