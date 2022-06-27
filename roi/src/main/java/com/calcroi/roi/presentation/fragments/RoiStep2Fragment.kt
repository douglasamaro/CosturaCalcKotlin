package com.calcroi.roi.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.calcroi.roi.R
import com.calcroi.roi.databinding.FragmentRoiStep2Binding
import com.calcroi.roi.presentation.MainRoiViewModel

class RoiStep2Fragment(
    private val viewModel: MainRoiViewModel
) : Fragment() {

    private val binding: FragmentRoiStep2Binding = FragmentRoiStep2Binding.inflate(layoutInflater)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_roi_step2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            next.setOnClickListener {
                it.findNavController().navigate(R.id.action_roiStep2Fragment_to_roiStep0Fragment)
            }
        }
    }

    companion object {
    }
}
