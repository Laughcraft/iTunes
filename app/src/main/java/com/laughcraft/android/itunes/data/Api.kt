package com.laughcraft.android.itunes.data

import com.laughcraft.android.itunes.data.entity.CollectionLookUpResponse
import com.laughcraft.android.itunes.data.entity.CollectionSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    
    @GET("/search")
    fun searchCollections(@Query("term") term: String,
                          @Query("entity") entity: String = "album"): Call<CollectionSearchResponse>
    
    @GET("/lookup")
    fun lookupCollection(@Query("id") id: Long,
                         @Query("entity") entity: String = "song"): Call<CollectionLookUpResponse>
}