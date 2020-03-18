package com.laughcraft.android.itunes.moxy

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.laughcraft.android.itunes.App.Companion.appContext
import com.laughcraft.android.itunes.App.Companion.isNetworkAvailable
import com.laughcraft.android.itunes.App.Companion.searchApi
import com.laughcraft.android.itunes.R
import com.laughcraft.android.itunes.data.entity.CollectionEntity
import com.laughcraft.android.itunes.data.entity.CollectionSearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@InjectViewState
class SearchPresenter : MvpPresenter<SearchView<CollectionEntity>>() {
    
    private val searchCallback = object : Callback<CollectionSearchResponse> {
        override fun onFailure(call: Call<CollectionSearchResponse>, t: Throwable) {
            if (t.message == null) {
                viewState.showError(appContext().getString(R.string.error))
            } else {
                viewState.showError(t.message!!)
            }
        }
        
        override fun onResponse(call: Call<CollectionSearchResponse>, response: Response<CollectionSearchResponse>) {
            if (response.code() / 100 == 2) {
                val results = response.body()?.results?.sortedBy { it.collectionName }
                viewState.updateList(results ?: arrayListOf())
            } else {
                viewState.showError(response.message())
            }
        }
    }
    
    fun search(query: String) {
        if (isNetworkAvailable()) {
            searchApi.searchCollections(query).enqueue(searchCallback)
        } else {
            viewState.showError(
                    appContext().getString(R.string.internet_connection_is_not_available))
        }
        
    }
    
}