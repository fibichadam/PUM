package com.example.lista5

sealed class Routes(val route: String) {
    object Main : Routes("Main")
    object Edit : Routes("Edit")
    object Add : Routes("Add")
}
