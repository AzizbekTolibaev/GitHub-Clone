package com.example.githubclone.presentation.adapters.searchwithrepositoriesfragmentadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.githubclone.data.SearchWithRepositoriesItemsData
import com.example.githubclone.databinding.ItemSearchWithRepositoriesBinding
import com.squareup.picasso.Picasso

class SearchWithRepositoriesAdapter:
    ListAdapter<SearchWithRepositoriesItemsData, SearchWithRepositoriesAdapter.SearchWithRepositoriesViewHolder>(myDiffUtil) {

    inner class SearchWithRepositoriesViewHolder(private val binding: ItemSearchWithRepositoriesBinding): ViewHolder(binding.root) {
        fun bind(position: Int) {
            val d = getItem(position)

            binding.tvUserName.text = d.owner.userName
            binding.tvRepoName.text = d.name
            if (d?.description?.isNotEmpty() == true) {
                binding.tvRepoDescription.visibility = View.VISIBLE
                binding.tvRepoDescription.text  = d.description
            }
            binding.tvStarNumber.text = d.starCount.toString()
            binding.tvRepoLanguage.text = d.language
            Picasso.get().load(d.owner.avatarUrl).into(binding.imgProfileImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchWithRepositoriesViewHolder {
        return SearchWithRepositoriesViewHolder(
            ItemSearchWithRepositoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchWithRepositoriesViewHolder, position: Int) {
        holder.bind(position)
    }

    private object myDiffUtil: DiffUtil.ItemCallback<SearchWithRepositoriesItemsData>() {
        override fun areItemsTheSame(oldItem: SearchWithRepositoriesItemsData, newItem: SearchWithRepositoriesItemsData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: SearchWithRepositoriesItemsData, newItem: SearchWithRepositoriesItemsData): Boolean {
            return oldItem.name == newItem.name && oldItem.language == newItem.language
                    && oldItem.description == newItem.description && oldItem.starCount == newItem.starCount
                    && oldItem.owner == newItem.owner
        }
    }
}