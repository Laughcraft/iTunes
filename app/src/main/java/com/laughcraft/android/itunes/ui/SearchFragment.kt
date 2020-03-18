package com.laughcraft.android.itunes.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.laughcraft.android.itunes.R
import com.laughcraft.android.itunes.data.entity.CollectionEntity
import com.laughcraft.android.itunes.moxy.SearchPresenter
import com.laughcraft.android.itunes.ui.adapter.SearchAdapter
import kotlinx.android.synthetic.main.fragment_list.*

class SearchFragment : MvpAppCompatFragment(), com.laughcraft.android.itunes.moxy.SearchView<CollectionEntity> {
    
    @InjectPresenter
    lateinit var presenter: SearchPresenter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler_view_search_results.layoutManager = LinearLayoutManager(activity)
        recycler_view_search_results.adapter = SearchAdapter(arrayListOf()) {}
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
    
    override fun updateList(collections: List<CollectionEntity>) {
        activity?.runOnUiThread {
            recycler_view_search_results.adapter = SearchAdapter(collections){
                itemClick ->
                val action = SearchFragmentDirections.actionListFragmentToDetailFragment(collections[itemClick])
                findNavController().navigate(action)
            }
            recycler_view_search_results.adapter!!.notifyDataSetChanged()
        }
    }
    
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        
        val searchMenuItem = menu.findItem(R.id.action_search)
        val searchView = searchMenuItem.actionView as SearchView
        searchView.isIconified = false
        
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return if (query.isNullOrEmpty() || query.isBlank()){
                    false
                } else {
                    presenter.search(query)
                    true
                }
            }
    
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
}