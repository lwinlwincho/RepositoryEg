package com.llc.repositoryeg

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RepositoryApplication : Application() {
    /*lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        //to store the DefaultAppContainer object.
        container = DefaultAppContainer()
    }*/
}