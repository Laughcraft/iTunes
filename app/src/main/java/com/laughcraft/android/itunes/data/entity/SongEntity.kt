package com.laughcraft.android.itunes.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*
Copyright (c) 2020 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */
@Parcelize
data class SongEntity (val wrapperType : String?,
                       val kind : String?,
                       val artistId : Int?,
                       val collectionId : Int?,
                       val trackId : Int?,
                       val artistName : String?,
                       val collectionName : String?,
                       val trackName : String?,
                       val collectionCensoredName : String?,
                       val trackCensoredName : String?,
                       val artistViewUrl : String?,
                       val collectionViewUrl : String?,
                       val trackViewUrl : String?,
                       val previewUrl : String?,
                       val artworkUrl30 : String?,
                       val artworkUrl60 : String?,
                       val artworkUrl100 : String?,
                       val collectionPrice : Double?,
                       val trackPrice : Double?,
                       val releaseDate : String?,
                       val collectionExplicitness : String?,
                       val trackExplicitness : String?,
                       val discCount : Int?,
                       val discNumber : Int?,
                       val trackCount : Int?,
                       val trackNumber : Int?,
                       val trackTimeMillis : Int?,
                       val country : String?,
                       val currency : String?,
                       val primaryGenreName : String?,
                       val isStreamable : Boolean?): Parcelable