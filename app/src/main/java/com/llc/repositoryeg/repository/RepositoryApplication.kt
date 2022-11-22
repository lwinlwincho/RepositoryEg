package com.llc.repositoryeg.repository

import android.app.Application
import com.llc.repositoryeg.AppContainer
import com.llc.repositoryeg.DefaultAppContainer

class RepositoryApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        //to store the DefaultAppContainer object.
        container = DefaultAppContainer()
    }
}