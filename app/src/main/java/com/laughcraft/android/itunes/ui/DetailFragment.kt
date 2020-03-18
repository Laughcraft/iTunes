package com.laughcraft.android.itunes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.laughcraft.android.itunes.R
import com.laughcraft.android.itunes.data.entity.CollectionEntity
import com.laughcraft.android.itunes.data.entity.SongEntity
import com.laughcraft.android.itunes.moxy.DetailsPresenter
import com.laughcraft.android.itunes.moxy.DetailsView
import com.laughcraft.android.itunes.ui.adapter.SongsAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.*

class DetailFragment : MvpAppCompatFragment(), DetailsView<CollectionEntity, SongEntity>{
    
    @InjectPresenter
    lateinit var presenter: DetailsPresenter
    
    @ProvidePresenter
    fun provideDetailsPresenter(): DetailsPresenter{
        return DetailsPresenter(DetailFragmentArgs.fromBundle(arguments!!).collection)
    }
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view_details_songs.layoutManager = LinearLayoutManager(context)
        recycler_view_details_songs.adapter = SongsAdapter(arrayListOf()){}
    }
    
    override fun updateList(list: List<SongEntity>) {
        activity?.runOnUiThread{
            (recycler_view_details_songs.adapter as SongsAdapter).songs = list
            recycler_view_details_songs.adapter!!.notifyDataSetChanged()
            progress_bar_details.visibility = View.GONE
        }
    }
    
    override fun updateUi(entity: CollectionEntity) {
        activity?.runOnUiThread{
            Picasso.with(context)
                .load(entity.artworkUrl100)
                .fit()
                .error(R.drawable.error)
                .into(image_view_details)
    
            text_view_details_title.text = entity.collectionCensoredName
            text_view_details_artist_name.text = entity.artistName
            text_view_details_genre.text = entity.primaryGenreName
            text_view_details_year.text = entity.releaseDate?.substring(0..3)
            text_view_details_explicitness.text = entity.collectionExplicitness
            text_view_details_copyright.text = entity.copyright
            text_view_details_track_count.text = entity.trackCount.toString()
            text_view_details_price.text = "${entity.collectionPrice.toString()} ${entity.currency}"
        }
    }
    
    override fun showError(message: String) {
        activity?.runOnUiThread {
            AlertDialog
                .Builder(activity!!)
                .setTitle(R.string.error)
                .setMessage(message)
                .create()
                .show()
        }
    }
}