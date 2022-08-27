package com.calcroi.roi.presentation.di

import com.calcroi.roi.presentation.MainRoiViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val RoiPresentationModules = module {
    viewModel {
        MainRoiViewModel(interactor = get())
    }
}
