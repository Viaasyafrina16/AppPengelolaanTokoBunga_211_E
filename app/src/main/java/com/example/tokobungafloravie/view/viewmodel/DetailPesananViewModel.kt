package com.example.tokobungafloravie.view.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tokobungafloravie.repository.RepositoriFloravie
import com.example.tokobungafloravie.room.entity.Pesanan
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailPesananViewModel(
    savedStateHandle: SavedStateHandle,
    private val repo: RepositoriFloravie
) : ViewModel() {

    private val id: Int = savedStateHandle["idPesanan"] ?: 0

    private val _pesanan = MutableStateFlow<Pesanan?>(null)
    val pesanan: StateFlow<Pesanan?> = _pesanan

    fun load() {
        viewModelScope.launch {
            _pesanan.value = repo.getPesananById(id)
        }
    }

    fun hapus(onSuccess: () -> Unit) {
        val data = _pesanan.value ?: return
        viewModelScope.launch {
            repo.deletePesanan(data)
            onSuccess()
        }
    }
}