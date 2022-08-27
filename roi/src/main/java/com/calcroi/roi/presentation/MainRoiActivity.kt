package com.calcroi.roi.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.calcroi.roi.R
import com.calcroi.roi.presentation.di.RoiDomainModules
import com.calcroi.roi.presentation.di.RoiPresentationModules
import org.koin.core.context.startKoin

class MainRoiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listOf(
            RoiDomainModules,
            RoiPresentationModules
        ) + loadKoin()
    }

    private fun loadKoin() {
        listOf(
            RoiDomainModules,
            RoiPresentationModules
        )
    }
}