package com.matheus.doglovers.auth.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.matheus.doglovers.core.presentation.R
import com.matheus.doglovers.core.presentation.theme.DogLoversTheme

@Composable
fun AuthenticationScreen(
    onAuthenticationSuccessful: () -> Unit,
    viewModel: AuthenticationViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    LifecycleEventEffect(event = Lifecycle.Event.ON_CREATE) {
        viewModel.handleScreenEvents(AuthenticationScreenEvent.CheckAuthState)
    }

    uiState.user?.let {
        LaunchedEffect(Unit) {
            onAuthenticationSuccessful()
        }
    }

    Scaffold(modifier = modifier) { innerPadding ->
        Image(
            painter = painterResource(R.drawable.app_background),
            modifier = Modifier.fillMaxSize(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
        )

        AuthenticationScreenContent(
            onSignInClick = { email, pass ->
                viewModel.handleScreenEvents(
                    AuthenticationScreenEvent.SigninUser(
                        email,
                        pass
                    )
                )
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun AuthenticationScreenContent(
    onSignInClick: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_sign_in),
                tint = Color.White,
                contentDescription = null
            )
            Text(
                text = stringResource(com.matheus.doglovers.auth.presentation.R.string.auth_sign_in_label),
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 80.dp)) {
            Text(stringResource(com.matheus.doglovers.auth.presentation.R.string.auth_email_label))
            var email by remember { mutableStateOf("") }
            TextField(
                email,
                onValueChange = { email = it },
                label = {
                    Text(stringResource(com.matheus.doglovers.auth.presentation.R.string.auth_email_placeholder_label))
                },
                trailingIcon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = null)
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(top = 20.dp))

            Text(stringResource(com.matheus.doglovers.auth.presentation.R.string.auth_password_label))
            var password by remember { mutableStateOf("") }
            TextField(
                password,
                onValueChange = { password = it },
                label = {
                    Text(stringResource(com.matheus.doglovers.auth.presentation.R.string.auth_password_placeholder_label))
                },
                trailingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(com.matheus.doglovers.auth.presentation.R.drawable.ic_eye),
                        contentDescription = null
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(top = 80.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Button(
                    onClick = {
                        onSignInClick.invoke("doglovers_testuser+01@gmail.com", "Login12345@@")
                        //FirebaseAuthInvalidCredentialsException
                    },
                    colors = ButtonDefaults.buttonColors()
                        .copy(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp)
                    ) {
                        Text(
                            stringResource(com.matheus.doglovers.auth.presentation.R.string.auth_sign_in_label),
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.padding(start = 8.dp))
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_sign_in),
                            tint = Color.White,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }

    }
}

@Preview
@Composable
private fun AuthenticationScreenContentPreview() {
    DogLoversTheme {
        AuthenticationScreenContent({ _, _ -> })
    }
}