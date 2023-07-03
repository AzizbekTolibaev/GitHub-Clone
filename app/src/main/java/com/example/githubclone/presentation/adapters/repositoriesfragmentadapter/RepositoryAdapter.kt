package com.example.githubclone.presentation.adapters.repositoriesfragmentadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.githubclone.data.RepositoryResponseData
import com.example.githubclone.databinding.ItemRepositoryBinding
import com.squareup.picasso.Picasso

class RepositoryAdapter: ListAdapter<RepositoryResponseData, RepositoryAdapter.RepositoryViewHolder>(myDiffUtil) {

    inner class RepositoryViewHolder(private val binding: ItemRepositoryBinding): ViewHolder(binding.root) {
        fun bind(position: Int) {
            val d = getItem(position)
            binding.tvUserName.text = d.owner.userName
            binding.tvRepoName.text = d.name
            Picasso.get().load(d.owner.avatar_url).into(binding.imgProfileImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(
            ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(position)
    }

    private object myDiffUtil: DiffUtil.ItemCallback<RepositoryResponseData>() {
        override fun areItemsTheSame(oldItem: RepositoryResponseData, newItem: RepositoryResponseData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RepositoryResponseData, newItem: RepositoryResponseData): Boolean {
            return oldItem.name == newItem.name && oldItem.language == newItem.language
                    && oldItem.starCount == newItem.starCount && oldItem.owner.userName == newItem.owner.userName
                    && oldItem.owner.avatar_url == newItem.owner.avatar_url
        }
    }
}
