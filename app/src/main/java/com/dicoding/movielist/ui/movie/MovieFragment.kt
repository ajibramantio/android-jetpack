package com.dicoding.movielist.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.movielist.databinding.FragmentMovieBinding
import com.dicoding.movielist.viewmodel.ViewModelFactory
import com.dicoding.movielist.vo.Status

class MovieFragment : Fragment() {

    private var _fragmentFilmBinding: FragmentMovieBinding? = null
    private val binding get() = _fragmentFilmBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _fragmentFilmBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            val filmAdapter = MovieAdapter()
            viewModel.getMovie().observe(this, Observer{ films ->
                if(films != null){
                    when(films.status){
                        Status.LOADING ->   binding?.progressBar?.visibility = View.VISIBLE
                        Status.SUCCESS ->{
                            binding?.progressBar?.visibility = View.GONE
                            filmAdapter.setFilm(films.data)
                            filmAdapter.notifyDataSetChanged()

                        }
                        Status.ERROR->{
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(binding?.rvFilm) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = filmAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentFilmBinding = null
    }
}