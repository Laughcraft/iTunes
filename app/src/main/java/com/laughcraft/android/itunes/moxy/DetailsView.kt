package com.laughcraft.android.itunes.moxy

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface DetailsView<C, S>: MvpView {
    
    fun updateUi(entity: C)
    fun updateList(list: List<S>)
    fun showError(message: String)
}