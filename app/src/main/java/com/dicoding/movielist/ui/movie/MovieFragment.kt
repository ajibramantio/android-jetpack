package com.dicoding.movielist.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.movielist.databinding.FragmentMovieBinding
import com.dicoding.movielist.viewmodel.ViewModelFactory


class MovieFragment : Fragment() {
    private lateinit var fragmentFilmBinding: FragmentMovieBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentFilmBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentFilmBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            val films = viewModel.getMovie()
            val filmAdapter = MovieAdapter()
            filmAdapter.setFilm(films)
            with(fragmentFilmBinding.rvFilm) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = filmAdapter
            }
        }
    }
}