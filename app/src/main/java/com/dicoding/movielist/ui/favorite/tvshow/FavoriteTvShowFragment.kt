package com.dicoding.movielist.ui.favorite.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.movielist.R
import com.dicoding.movielist.databinding.FragmentFavoriteMovieBinding
import com.dicoding.movielist.databinding.FragmentFavoriteTvShowBinding
import com.dicoding.movielist.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class FavoriteTvShowFragment : Fragment() {

    private lateinit var fragmentTvShowFavoriteBinding: FragmentFavoriteTvShowBinding
    private lateinit var viewModel: FavoriteTvShowViewModel
    private lateinit var tvShowAdapter: FavoriteTvShowAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentTvShowFavoriteBinding = FragmentFavoriteTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowFavoriteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(fragmentTvShowFavoriteBinding.rvFavoriteTvshow)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavoriteTvShowViewModel::class.java]

            tvShowAdapter = FavoriteTvShowAdapter()
            fragmentTvShowFavoriteBinding.progressBarFavorite.visibility = View.VISIBLE
            viewModel.getFavoritedTvShow().observe(this, Observer{ films ->
                if(films !=null){
                    fragmentTvShowFavoriteBinding.progressBarFavorite.visibility = View.GONE
                    tvShowAdapter.submitList(films)
                    tvShowAdapter.notifyDataSetChanged()
                }

            })

            with(fragmentTvShowFavoriteBinding.rvFavoriteTvshow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }
    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val courseEntity = tvShowAdapter.getSwipedData(swipedPosition)
                courseEntity?.let { viewModel.setFavorite(it) }
                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { v ->
                    courseEntity?.let { viewModel.setFavorite(it) }
                }
                snackbar.show()
            }
        }
    })
}