package com.calcroi.roi.presentation.di

import com.calcroi.roi.domain.interactor.RoiInteractor
import com.calcroi.roi.domain.interactor.RoiInteractorImpl
import org.koin.dsl.module

val RoiDomainModules = module {
    factory<RoiInteractor> {
        RoiInteractorImpl()
    }
}
