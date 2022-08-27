package com.costcalc.calculadoradacostureira.views.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.calcroi.roi.presentation.MainRoiActivity
import com.costcalc.calculadoradacostureira.R
import kotlinx.android.synthetic.main.calcular_produto.*

class ProductFragmentScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.calcular_produto, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CalcularCalc.setOnClickListener {
        }
    }
}