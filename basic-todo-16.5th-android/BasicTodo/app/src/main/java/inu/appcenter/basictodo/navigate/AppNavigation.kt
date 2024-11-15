package inu.appcenter.basictodo.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import inu.appcenter.basictodo.ui.auth.AuthViewModel
import inu.appcenter.basictodo.ui.auth.LoginScreen
import inu.appcenter.basictodo.ui.auth.SignupScreen
import inu.appcenter.basictodo.ui.main.AddTodoScreen
import inu.appcenter.basictodo.ui.main.MainScreen
import inu.appcenter.basictodo.ui.main.MainViewModel
import inu.appcenter.basictodo.ui.main.UpdateTodoScreen


@Composable
fun AppNavigation(
    authViewModel: AuthViewModel,
    mainViewModel: MainViewModel
) {

    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = AllDestination.Login.route,
    ) {
        composable(AllDestination.Login.route) {
            LoginScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }
        composable(AllDestination.Signup.route) {
            SignupScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }
        composable(
            AllDestination.Main.route,
        ) {
            MainScreen(
                mainViewModel = mainViewModel,
                navController = navController
            )
        }
        composable(
            AllDestination.AddTodo.route,
        ){
            AddTodoScreen(
                navController = navController,
                mainViewModel = mainViewModel
            )
        }
        composable(
            AllDestination.EditTodo.route,
        ){
            UpdateTodoScreen(
                navController = navController,
                mainViewModel = mainViewModel
            )
        }
    }
}