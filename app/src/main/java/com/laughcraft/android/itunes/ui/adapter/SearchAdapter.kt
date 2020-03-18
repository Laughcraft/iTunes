package com.laughcraft.android.itunes.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.laughcraft.android.itunes.R
import com.laughcraft.android.itunes.data.entity.CollectionEntity

import com.squareup.picasso.Picasso

class SearchAdapter(var collections: List<CollectionEntity>,
                    var onItemClick: (position: Int)-> Unit) :
        RecyclerView.Adapter<SearchAdapter.SearchResultHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultHolder {
        val itemHolder: SearchResultHolder
        
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_result_item, parent, false)
        
        itemHolder = SearchResultHolder(view)
        return itemHolder
    }
    
    override fun getItemCount(): Int = collections.size
    
    override fun onBindViewHolder(holder: SearchResultHolder, position: Int) {
        holder.bind(collections[position], position)
    }
    
    inner class SearchResultHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.findViewById(R.id.image_view_search_result_image)
        
        private val textViewTitle: TextView = view.findViewById(R.id.text_view_search_result_title)
        private val textViewArtistName: TextView = view.findViewById(R.id.text_view_search_result_artist_name)
        private val textViewYear: TextView = view.findViewById(R.id.text_view_search_result_year)

        fun bind(collection: CollectionEntity, position: Int) {
            textViewTitle.text = collection.collectionName ?: ""
            textViewArtistName.text = collection.artistName ?: ""
            textViewYear.text = collection.releaseDate?.substring(0..3)

            view.setOnClickListener {
                onItemClick.invoke(position)
            }
            
            Picasso.with(view.context)
                .load(collection.artworkUrl100)
                .fit()
                .error(R.drawable.error)
                .into(imageView)
        }
    }
    
    
}