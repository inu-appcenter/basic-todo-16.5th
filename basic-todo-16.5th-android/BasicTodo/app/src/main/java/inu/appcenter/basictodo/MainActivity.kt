package inu.appcenter.basictodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import inu.appcenter.basictodo.navigate.AppNavigation
import inu.appcenter.basictodo.ui.auth.AuthViewModel
import inu.appcenter.basictodo.ui.main.MainViewModel
import inu.appcenter.basictodo.ui.theme.BasicTodoTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val authViewModel : AuthViewModel by viewModel()
            val mainViewModel : MainViewModel by viewModel()

            BasicTodoTheme {
                Box{
                    AppNavigation(
                        authViewModel = authViewModel,
                        mainViewModel = mainViewModel,
                    )
                }

            }
        }
    }
}

