package com.example.tokobungafloravie.repository

import android.app.Application

/**
 * AplikasiFloravie hidup sejak aplikasi dibuka.
 * Isinya container untuk dependency injection sederhana.
 */
class AplikasiFloravie : Application() {

    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)
    }
}