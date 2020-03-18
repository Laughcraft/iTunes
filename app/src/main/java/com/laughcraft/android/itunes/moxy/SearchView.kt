package com.laughcraft.android.itunes.moxy

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface SearchView<Entity>: MvpView {
    
    fun showError(message: String)
    fun updateList(collections: List<Entity>)
}