package com.example.githubclone.presentation.adapters.profilefragmentadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.githubclone.data.RepositoryResponseData
import com.example.githubclone.databinding.ItemHorizontalRepositoryBinding
import com.squareup.picasso.Picasso

class HorizontalRepositoryAdapter :
    ListAdapter<RepositoryResponseData, HorizontalRepositoryAdapter.HorizontalRepositoryViewHolder>(myDiffUtil) {

    inner class HorizontalRepositoryViewHolder(private val binding: ItemHorizontalRepositoryBinding) :
        ViewHolder(binding.root) {
        fun bind(position: Int) {
            val d = getItem(position)

            binding.tvUserName.text = d.owner.userName
            binding.tvRepoName.text = d.name
            binding.tvStarNumber.text = d.starCount.toString()
            binding.tvRepoLanguage.text = d.language
            if (d?.description?.isNotEmpty() == true) {
                binding.tvRepoDescription.visibility = View.VISIBLE
                binding.tvRepoDescription.text = d.description
            }
            Picasso.get().load(d.owner.avatar_url).into(binding.imgProfileImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalRepositoryViewHolder {
        return HorizontalRepositoryViewHolder(
            ItemHorizontalRepositoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HorizontalRepositoryViewHolder, position: Int) {
        holder.bind(position)
    }

    private object myDiffUtil : DiffUtil.ItemCallback<RepositoryResponseData>() {
        override fun areItemsTheSame(oldItem: RepositoryResponseData, newItem: RepositoryResponseData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RepositoryResponseData, newItem: RepositoryResponseData): Boolean {
            return oldItem.owner.userName == newItem.owner.userName && oldItem.name == newItem.name
                    && oldItem.starCount == newItem.starCount && oldItem.language == newItem.language
                    && oldItem.owner.avatar_url == newItem.owner.avatar_url
        }
    }
}
