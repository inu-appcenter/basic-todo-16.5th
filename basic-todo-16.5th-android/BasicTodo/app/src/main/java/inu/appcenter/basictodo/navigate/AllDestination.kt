package inu.appcenter.basictodo.navigate

sealed class AllDestination(val route: String) {
    data object Login : AllDestination("login")
    data object Signup : AllDestination("signup")
    data object Main : AllDestination("main")
    data object AddTodo : AllDestination("addTodo")
    data object EditTodo : AllDestination("editTodo")

}