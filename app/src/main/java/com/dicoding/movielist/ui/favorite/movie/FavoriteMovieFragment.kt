package com.dicoding.movielist.ui.favorite.movie

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
import com.dicoding.movielist.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class FavoriteMovieFragment : Fragment() {

    private lateinit var _fragmentFavoriteMovieBinding: FragmentFavoriteMovieBinding
    private lateinit var viewModel: FavoriteMovieViewModel
    private lateinit var filmAdapter: FavoriteMovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _fragmentFavoriteMovieBinding = FragmentFavoriteMovieBinding.inflate(layoutInflater, container, false)
        return _fragmentFavoriteMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(_fragmentFavoriteMovieBinding.rvFavoriteMovie)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]

            filmAdapter = FavoriteMovieAdapter()
            _fragmentFavoriteMovieBinding.progressBarFavorite.visibility = View.VISIBLE
            viewModel.getFavoritedMovie().observe(this, Observer{ films ->
                if(films !=null){
                    _fragmentFavoriteMovieBinding.progressBarFavorite.visibility = View.GONE
                    filmAdapter.submitList(films)
                    filmAdapter.notifyDataSetChanged()
                }
            })

            with(_fragmentFavoriteMovieBinding.rvFavoriteMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = filmAdapter
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
                val courseEntity = filmAdapter.getSwipedData(swipedPosition)
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