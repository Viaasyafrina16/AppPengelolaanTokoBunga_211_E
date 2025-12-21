package com.example.tokobungafloravie.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tokobungafloravie.R
import com.example.tokobungafloravie.session.SessionManager
import com.example.tokobungafloravie.view.route.DestinasiLogin
import com.example.tokobungafloravie.view.route.DestinasiPesanan
import com.example.tokobungafloravie.view.route.DestinasiProduk
import com.example.tokobungafloravie.view.route.DestinasiRekomendasi

@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(stringResource(R.string.home_title), style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(16.dp))

        Button(onClick = { navController.navigate(DestinasiProduk.route) }, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.menu_produk))
        }

        Spacer(Modifier.height(10.dp))

        Button(onClick = { navController.navigate(DestinasiRekomendasi.route) }, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.menu_rekomendasi))
        }

        Spacer(Modifier.height(10.dp))

        Button(onClick = { navController.navigate(DestinasiPesanan.route) }, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.menu_pesanan))
        }

        Spacer(Modifier.height(24.dp))

        OutlinedButton(
            onClick = {
                SessionManager.clear(context)
                navController.navigate(DestinasiLogin.route) { popUpTo(0) }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.logout))
        }
    }
}