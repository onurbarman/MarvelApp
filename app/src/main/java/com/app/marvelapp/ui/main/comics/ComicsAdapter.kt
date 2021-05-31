package com.app.marvelapp.ui.main.comics

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.marvelapp.R
import com.app.marvelapp.data.model.comics.ComicsResults
import com.app.marvelapp.utils.GlideUtils


class ComicsAdapter(private var comics: List<ComicsResults>)
    : RecyclerView.Adapter<ComicsAdapter.ComicsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        val view = LayoutInflater .from(parent.context)
            .inflate(R.layout.item_comics, parent, false)

        return ComicsViewHolder(view)
    }
    override fun getItemCount(): Int {
        return comics.size
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        holder.bind(comics[position])
    }

    fun updateComics(comics: List<ComicsResults>) {
        this.comics = comics
        notifyDataSetChanged()
    }

    fun clearAdapter() {
        this.comics = listOf()
        notifyDataSetChanged()
    }

    inner class ComicsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgComic : ImageView = itemView.findViewById(R.id.imgComic)
        private val textComicName : TextView = itemView.findViewById(R.id.textComicName)

        @SuppressLint("SetTextI18n")
        fun bind(comic: ComicsResults) {
            itemView.animation= AnimationUtils.loadAnimation(itemView.context,R.anim.anim_vertical_recyclerview)

            comic.thumbnail.run {
                GlideUtils.urlToImageView(imgComic.context, "$path.$extension",imgComic)
            }

            textComicName.text=comic.title
        }
    }
}