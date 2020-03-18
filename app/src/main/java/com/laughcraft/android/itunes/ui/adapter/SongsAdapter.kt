package com.laughcraft.android.itunes.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.laughcraft.android.itunes.R
import com.laughcraft.android.itunes.data.entity.SongEntity
import java.text.SimpleDateFormat
import java.util.*

class SongsAdapter(var songs: List<SongEntity>,
                   var onItemClick: (position: Int)-> Unit) :
        RecyclerView.Adapter<SongsAdapter.SongHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.song_item, parent, false)
        return SongHolder(view)
    }
    
    override fun getItemCount(): Int = songs.size
    
    override fun onBindViewHolder(holder: SongHolder, position: Int) {
        holder.bind(songs[position], position)
    }
    
    inner class SongHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val textViewSongIndex: TextView = view.findViewById(R.id.text_view_song_index)
        private val textViewSongName: TextView = view.findViewById(R.id.text_view_song_name)
        private val textViewSongDuration: TextView = view.findViewById(R.id.text_view_song_duration)

        fun bind(song: SongEntity, position: Int) {
            textViewSongIndex.text = song.trackNumber?.toString()
            textViewSongName.text = song.trackName ?: ""
            
            if (song.trackTimeMillis != null){
                val date = Date(song.trackTimeMillis.toLong())
                val duration = SimpleDateFormat("mm:ss").format(date)
                textViewSongDuration.text = duration
            }
            
            view.setOnClickListener {
                onItemClick.invoke(position)
            }
        }
    }
}