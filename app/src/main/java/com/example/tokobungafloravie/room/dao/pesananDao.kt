package com.example.tokobungafloravie.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.tokobungafloravie.room.entity.Pesanan
import com.example.tokobungafloravie.room.entity.PesananItem
import kotlinx.coroutines.flow.Flow

@Dao
interface PesananDao {

    @Insert
    suspend fun insert(pesanan: Pesanan)

    @Update
    suspend fun update(pesanan: Pesanan)

    @Delete
    suspend fun delete(pesanan: Pesanan)

    @Query("""
        SELECT ps.id, ps.namaPemesan, ps.produkId,
               pr.nama AS namaProduk, pr.event AS event, pr.harga AS harga,
               ps.qty, ps.tanggal
        FROM pesanan ps
        INNER JOIN produk pr ON pr.id = ps.produkId
        ORDER BY ps.tanggal DESC, ps.id DESC
    """)
    fun getAllWithProduk(): Flow<List<PesananItem>>

    @Query("SELECT * FROM pesanan ORDER BY tanggal DESC")
    fun getAll(): Flow<List<Pesanan>>

    @Query("SELECT * FROM pesanan WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): Pesanan?
}