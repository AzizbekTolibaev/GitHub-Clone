package com.example.githubclone.presentation.adapters.searchwithusernamefragmentadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.githubclone.data.SearchWithUsernameItemsData
import com.example.githubclone.databinding.ItemSearchWithUsernameBinding
import com.squareup.picasso.Picasso

class SearchWithUsernameAdapter :
    ListAdapter<SearchWithUsernameItemsData, SearchWithUsernameAdapter.SearchWithUsernameViewHolder>(myDiffUtil) {

    inner class SearchWithUsernameViewHolder(private val binding: ItemSearchWithUsernameBinding) :
        ViewHolder(binding.root) {
        fun bind(position: Int) {
            val d = getItem(position)

            binding.tvUserName.text = d.userName
            Picasso.get().load(d.avatarUrl).into(binding.imgProfileImage)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchWithUsernameViewHolder {
        return SearchWithUsernameViewHolder(
            ItemSearchWithUsernameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchWithUsernameViewHolder, position: Int) {
        holder.bind(position)
    }

    private object myDiffUtil: DiffUtil.ItemCallback<SearchWithUsernameItemsData>() {
        override fun areItemsTheSame(
            oldItem: SearchWithUsernameItemsData,
            newItem: SearchWithUsernameItemsData
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: SearchWithUsernameItemsData,
            newItem: SearchWithUsernameItemsData
        ): Boolean {
            return oldItem.userName == newItem.userName && oldItem.avatarUrl == newItem.avatarUrl
        }
    }
}
