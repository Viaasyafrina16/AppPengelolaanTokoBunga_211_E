package com.example.tokobungafloravie.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tokobungafloravie.room.dao.AdminDao
import com.example.tokobungafloravie.room.dao.PesananDao
import com.example.tokobungafloravie.room.dao.ProdukDao
import com.example.tokobungafloravie.room.entity.Admin
import com.example.tokobungafloravie.room.entity.Pesanan
import com.example.tokobungafloravie.room.entity.Produk


@Database(
    entities = [Admin::class, Produk::class, Pesanan::class],
    version = 1,
    exportSchema = false
)
abstract class FloravieDatabase : RoomDatabase() {

    abstract fun adminDao(): AdminDao
    abstract fun produkDao(): ProdukDao
    abstract fun pesananDao(): PesananDao

    companion object {
        @Volatile
        private var INSTANCE: FloravieDatabase? = null

        fun getDatabase(context: Context): FloravieDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FloravieDatabase::class.java,
                    "floravie_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
