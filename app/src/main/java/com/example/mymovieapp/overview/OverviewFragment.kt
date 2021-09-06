package com.example.mymovieapp.overview

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.mymovieapp.R
import com.example.mymovieapp.databinding.OverviewFragmentBinding

class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = OverviewFragmentBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

//        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
//            if ( null != it ) {
//                this.findNavController().navigate(
//                    OverviewFragmentDirections.actionShowDetail(it))
//                viewModel.displayPropertyDetailsComplete()
//            }
//        })

} }
