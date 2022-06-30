package com.kotproj.mvvmsearchpicfieasif.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.kotproj.mvvmsearchpicfieasif.R
import com.kotproj.mvvmsearchpicfieasif.data.GridImageFiePhoto
import com.kotproj.mvvmsearchpicfieasif.databinding.ItemUnsplashPhotoBinding


class GridImageFieAdapter :
    PagingDataAdapter<GridImageFiePhoto, GridImageFieAdapter.PhotoViewHolder>(PHOTO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding =
            ItemUnsplashPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class PhotoViewHolder(private val binding: ItemUnsplashPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: GridImageFiePhoto) {
            binding.apply {
                Glide.with(itemView)
                    .load(photo.urls.regular)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(imageView)

                textViewUserName.text = photo.user.username
            }
        }
    }

    /*
    Helps in improving list performance.
    */
    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<GridImageFiePhoto>() {
            override fun areItemsTheSame(oldItem: GridImageFiePhoto, newItem: GridImageFiePhoto) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: GridImageFiePhoto, newItem: GridImageFiePhoto) =
                oldItem == newItem
        }
    }
}