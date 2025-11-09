package ru.otus.arch.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import arch.composeapp.generated.resources.Res
import arch.composeapp.generated.resources.back
import arch.composeapp.generated.resources.close
import arch.composeapp.generated.resources.ic_back
import arch.composeapp.generated.resources.input_login
import arch.composeapp.generated.resources.input_password
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.otus.arch.basicauth.data.BasicAuthGesture
import ru.otus.arch.basicauth.data.BasicAuthUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    state: BasicAuthUiState.ForgotPassword,
    onGesture: (BasicAuthGesture) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Forgot Password") }, // Hardcoded title for now
                navigationIcon = {
                    IconButton(onClick = { onGesture(BasicAuthGesture.Back) }) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_back),
                            contentDescription = stringResource(Res.string.back)
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
                .padding(horizontal = AppDimens.marginHorizontal, vertical = AppDimens.marginVertical),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(AppDimens.marginVerticalSmall)
        ) {
            Text(
                text = stringResource(Res.string.input_login),
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = state.username,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = stringResource(Res.string.input_password),
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = state.password,
                style = MaterialTheme.typography.titleLarge
            )

            Button(
                onClick = { onGesture(BasicAuthGesture.Action) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(Res.string.close))
            }
        }
    }
}