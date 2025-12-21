package com.example.tokobungafloravie.repository

import android.content.Context
import androidx.room.Database
import com.example.tokobungafloravie.room.FloravieDatabase

/**
 * ContainerApp: menyiapkan dependency (Database, DAO, Repository).
 * Tujuannya supaya semua ViewModel ambil Repository dari 1 tempat yang konsisten.
 */
interface ContainerApp {
    val repositoriFloravie: RepositoriFloravie
}

class ContainerDataApp(private val context: Context) : ContainerApp {

    private val database: FloravieDatabase by lazy {
        FloravieDatabase.getDatabase(context)
    }

    override val repositoriFloravie: RepositoriFloravie by lazy {
        RepositoriFloravie(
            adminDao = database.adminDao(),
            produkDao = database.produkDao(),
            pesananDao = database.pesananDao()
        )
    }
}