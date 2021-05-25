package com.dicoding.movielist.ui.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.movielist.databinding.FragmentTvShowBinding
import com.dicoding.movielist.viewmodel.ViewModelFactory
import com.dicoding.movielist.vo.Status

class TvShowFragment : Fragment() {

    private var _fragmentTvShowBinding: FragmentTvShowBinding? = null
    private val binding get() = _fragmentTvShowBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            val tvShowAdapter = TvShowAdapter()
            viewModel.getTvShow().observe(this, Observer{ films ->
                if(films != null){
                    when(films.status){
                        Status.LOADING ->   binding?.progressBar?.visibility = View.VISIBLE
                        Status.SUCCESS ->{
                            binding?.progressBar?.visibility = View.GONE
                            tvShowAdapter.setTvShow(films.data)
                            tvShowAdapter.notifyDataSetChanged()

                        }
                        Status.ERROR->{
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(binding?.rvTvshow) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = tvShowAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentTvShowBinding = null
    }
}