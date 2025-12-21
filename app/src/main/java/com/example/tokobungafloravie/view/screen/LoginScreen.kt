package com.example.tokobungafloravie.view.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tokobungafloravie.R
import com.example.tokobungafloravie.session.SessionManager
import com.example.tokobungafloravie.view.route.DestinasiHome
import com.example.tokobungafloravie.view.route.DestinasiRegister
import com.example.tokobungafloravie.view.viewmodel.LoginViewModel
import com.example.tokobungafloravie.view.viewmodel.provider.PenyediaViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    vm: LoginViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val context = LocalContext.current
    val state = vm.uiState

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.login), style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = state.email,
            onValueChange = { vm.updateEmail(it) },
            label = { Text(stringResource(R.string.email)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(10.dp))

        OutlinedTextField(
            value = state.password,
            onValueChange = { vm.updatePassword(it) },
            label = { Text(stringResource(R.string.password)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        if (state.errorMessage.isNotEmpty()) {
            Spacer(Modifier.height(10.dp))
            Text(text = state.errorMessage, color = MaterialTheme.colorScheme.error)
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                vm.login(
                    onError = { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() },
                    onSuccess = { ok ->
                        if (ok) {
                            SessionManager.setLogin(context, true)
                            navController.navigate(DestinasiHome.route) {
                                popUpTo(0)
                            }
                        } else {
                            Toast.makeText(context, context.getString(R.string.login_failed), Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.login))
        }

        TextButton(onClick = { navController.navigate(DestinasiRegister.route) }) {
            Text(stringResource(R.string.register))
        }
    }
}