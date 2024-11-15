package inu.appcenter.basictodo.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import inu.appcenter.basictodo.R
import inu.appcenter.basictodo.navigate.AllDestination
import inu.appcenter.basictodo.ui.auth.common.LoginTextField
import inu.appcenter.basictodo.ui.theme.fontFamilyKanit
import inu.appcenter.basictodo.ui.theme.fontFamilyRoboto

@Composable
fun LoginScreen(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val uiState = authViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .padding(horizontal = 50.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(130.dp))
        Image(
            painter = painterResource(R.drawable.appcenter_logo),
            contentDescription = "앱센터로고",
            modifier = Modifier
                .width(258.dp)
                .height(169.dp)
        )
        Spacer(Modifier.height(46.dp))
        LoginTextField(
            label = "EMAIL",
            value = uiState.value.loginEmail,
            onValueChange = {
                authViewModel.updateLoginEmail(it)
            },
            type = "EMAIL"
        )
        Spacer(Modifier.height(24.dp))
        LoginTextField(
            label = "PASSWORD",
            value = uiState.value.loginPassword,
            onValueChange = {
                authViewModel.updateLoginPassword(it)
            },
            type = "PASSWORD"
        )
        Spacer(Modifier.height(22.dp))
        Button(
            onClick = {
                navController.navigate(AllDestination.Main.route) {
                    popUpTo(AllDestination.Login.route) { inclusive = true }
                }
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF034A9A),
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(
                vertical = 12.dp
            )
        ) {
            Text(
                text = "로그인",
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 20.sp,
                    fontFamily = fontFamilyRoboto,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.25.sp,
                )
            )
        }
        Spacer(Modifier.height(14.dp))
        Text(
            text = "회원가입",
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 20.sp,
                fontFamily = fontFamilyRoboto,
                fontWeight = FontWeight(400),
                color = Color(0xFF909090),
                textAlign = TextAlign.Center,
                letterSpacing = 0.25.sp,
                textDecoration = TextDecoration.Underline,
            ),
            modifier = Modifier
                .clickable {
                    navController.navigate(AllDestination.Signup.route)
                }
        )
    }
}

