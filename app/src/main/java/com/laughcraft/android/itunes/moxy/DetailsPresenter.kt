package com.laughcraft.android.itunes.moxy

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.laughcraft.android.itunes.App
import com.laughcraft.android.itunes.App.Companion.appContext
import com.laughcraft.android.itunes.R
import com.laughcraft.android.itunes.data.entity.CollectionEntity
import com.laughcraft.android.itunes.data.entity.CollectionLookUpResponse
import com.laughcraft.android.itunes.data.entity.SongEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@InjectViewState
class DetailsPresenter(collectionEntity: CollectionEntity)
    : MvpPresenter<DetailsView<CollectionEntity, SongEntity>>() {
    
    private val lookupCallback = object : Callback<CollectionLookUpResponse> {
        
        override fun onResponse(call: Call<CollectionLookUpResponse>,
                                response: Response<CollectionLookUpResponse>) {
            if (response.code() / 100 == 2){
                val songs = response.body()?.results
                if (songs != null){
                    // first object in json array is NOT a SongEntity
                    (songs as MutableList<SongEntity>).removeAt(0)
                    viewState.updateList(songs)
                }
            } else {
                viewState.showError(response.message())
            }
        }
    
        override fun onFailure(call: Call<CollectionLookUpResponse>, t: Throwable) {
            if (t.message == null){
                viewState.showError(appContext().getString(R.string.error))
            } else {
                viewState.showError(t.message!!)
            }
        }
    }
    
    init {
        viewState.updateUi(collectionEntity)
        App.searchApi
            .lookupCollection(collectionEntity.collectionId!!)
            .enqueue(lookupCallback)
    }
}