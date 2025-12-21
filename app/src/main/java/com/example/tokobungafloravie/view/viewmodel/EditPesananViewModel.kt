package com.example.tokobungafloravie.view.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tokobungafloravie.repository.RepositoriFloravie
import com.example.tokobungafloravie.room.entity.Pesanan
import kotlinx.coroutines.launch

data class EditPesananUiState(
    val id: Int = 0,
    val namaPemesan: String = "",
    val produkId: Int = 0,
    val qty: String = "",
    val tanggal: String = "",
    val errorMessage: String = ""
)

class EditPesananViewModel(
    savedStateHandle: SavedStateHandle,
    private val repo: RepositoriFloravie
) : ViewModel() {

    private val idArg: Int = savedStateHandle["idPesanan"] ?: 0

    var uiState by mutableStateOf(EditPesananUiState())
        private set

    fun load() {
        viewModelScope.launch {
            val data = repo.getPesananById(idArg)
            if (data != null) {
                uiState = uiState.copy(
                    id = data.id,
                    namaPemesan = data.namaPemesan,
                    produkId = data.produkId,
                    qty = data.qty.toString(),
                    tanggal = data.tanggal,
                    errorMessage = ""
                )
            }
        }
    }

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
            repo.updatePesanan(
                Pesanan(
                    id = uiState.id,
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