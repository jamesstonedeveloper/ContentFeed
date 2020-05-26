package com.jamesstonedeveloper.contentfeed.ui.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jamesstonedeveloper.contentfeed.data.entities.Post
import com.jamesstonedeveloper.contentfeed.databinding.ItemPostLayoutBinding

class FeedListAdapter: ListAdapter<Post, FeedListAdapter.ItemViewHolder>(FeedDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemPostLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = holder.bind(getItem(position))

    inner class ItemViewHolder(private val binding: ItemPostLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Post) = with(itemView) {
            binding.tvTitle.text = item.title
            binding.tvBody.text = item.body
        }
    }

    override fun submitList(list: List<Post>?) {
        val sortedList: List<Post> = list?.sortedBy { it.createdAt } ?: listOf()
        super.submitList(sortedList.reversed())
    }

}

class FeedDiffCallback: DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id?.equals(newItem.id) ?: false
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.title.equals(newItem.title) && oldItem.body.equals(newItem.body)
    }
}