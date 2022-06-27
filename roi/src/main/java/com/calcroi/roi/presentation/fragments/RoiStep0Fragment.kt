package com.calcroi.roi.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.calcroi.roi.R
import com.calcroi.roi.databinding.FragmentRoiStep0Binding
import com.calcroi.roi.presentation.MainRoiViewModel

class RoiStep0Fragment(
    private val viewModel: MainRoiViewModel
) : Fragment() {

    private val binding: FragmentRoiStep0Binding = FragmentRoiStep0Binding.inflate(layoutInflater)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_roi_step0, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            next.setOnClickListener {
                it.findNavController().navigate(R.id.action_roiStep0Fragment_to_roiStep1Fragment)
            }
        }
    }

    companion object {
    }
}