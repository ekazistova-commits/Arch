package ru.otus.arch.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import arch.composeapp.generated.resources.Res
import arch.composeapp.generated.resources.ic_back
import arch.composeapp.generated.resources.input_login
import arch.composeapp.generated.resources.input_password
import arch.composeapp.generated.resources.login
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.otus.arch.basicauth.data.BasicAuthGesture
import ru.otus.arch.basicauth.data.BasicAuthUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    state: BasicAuthUiState.Login,
    onGesture: (BasicAuthGesture) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(Res.string.login)) },
                navigationIcon = {
                    IconButton(onClick = { onGesture(BasicAuthGesture.Back) }) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_back),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = state.username,
                onValueChange = { onGesture(BasicAuthGesture.UsernameChanged(it)) },
                label = { Text(stringResource(Res.string.input_login)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = state.password,
                onValueChange = { onGesture(BasicAuthGesture.PasswordChanged(it)) },
                label = { Text(stringResource(Res.string.input_password)) },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )

            if (state.error != null) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    )
                ) {
                    Text(
                        text = state.error?.message ?: "Unknown error",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            Button(
                onClick = { onGesture(BasicAuthGesture.Action) },
                enabled = state.isEnabled,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(Res.string.login))
            }
        }
    }
}
