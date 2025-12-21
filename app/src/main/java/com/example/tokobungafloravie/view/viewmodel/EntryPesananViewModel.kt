package com.example.tokobungafloravie.view.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tokobungafloravie.repository.RepositoriFloravie
import com.example.tokobungafloravie.room.entity.Pesanan
import kotlinx.coroutines.launch

data class EntryPesananUiState(
    val namaPemesan: String = "",
    val produkId: Int = 0,
    val qty: String = "",
    val tanggal: String = "",
    val errorMessage: String = ""
)

class EntryPesananViewModel(
    private val repo: RepositoriFloravie
) : ViewModel() {

    var uiState by mutableStateOf(EntryPesananUiState())
        private set

    fun updateNamaPemesan(v: String) { uiState = uiState.copy(namaPemesan = v, errorMessage = "") }
    fun updateProdukId(v: Int) { uiState = uiState.copy(produkId = v, errorMessage = "") }
    fun updateQty(v: String) { uiState = uiState.copy(qty = v, errorMessage = "") }
    fun updateTanggal(v: String) { uiState = uiState.copy(tanggal = v, errorMessage = "") }

    fun simpan(onSuccess: () -> Unit) {
        val nama = uiState.namaPemesan.trim()
        val qtyInt = uiState.qty.toIntOrNull()
        val tanggal = uiState.tanggal.trim()

        if (nama.isEmpty() || qtyInt == null || qtyInt <= 0 || tanggal.isEmpty() || uiState.produkId <= 0) {
            uiState = uiState.copy(errorMessage = "Data pesanan belum lengkap")
            return
        }

        viewModelScope.launch {
            repo.insertPesanan(
                Pesanan(
                    namaPemesan = nama,
                    produkId = uiState.produkId,
                    qty = qtyInt,
                    tanggal = tanggal
                )
            )
            onSuccess()
        }
    }
}