package ua.kpi.practical_example_11.composables

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.kpi.practical_example_11.medium.AuthViewModel
import ua.kpi.practical_example_11.medium.AuthViewModelFactory


@Composable
fun MediumApp() {
    val context = LocalContext.current


    val authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(context)
    )
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        if (authViewModel.token == null) {
            Text("Login", style = MaterialTheme.typography.titleLarge)

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    authViewModel.login(username, password) { error ->
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }
        } else {
            Text("Logged in as ${authViewModel.role}")
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                authViewModel.loadProtectedData { error ->
                    Log.d("response", error.toString())
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Load Protected Data")
            }
            authViewModel.protectedData?.let {

                Text(it.message)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { authViewModel.logout() }) {
                Text("Logout")
            }
        }
    }
}
