package com.example.lista5

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lista5.ui.screens.AddScreen
import com.example.lista5.ui.screens.EditScreen
import com.example.lista5.ui.screens.MainScreen
import com.example.lista5.ui.theme.Lista5Theme
import com.example.lista5.viewModel.SubjectViewModel
import com.example.lista5.viewModel.SubjectViewModelProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lista5Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }


    @Composable
    fun Navigation(){
        val viewModel: SubjectViewModel = viewModel(
            LocalViewModelStoreOwner.current!!,
            "UserViewModel",
            SubjectViewModelProvider(LocalContext.current.applicationContext as Application)
        )
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Routes.Main.route) {
            composable(Routes.Main.route) {
                MainScreen(navController, viewModel)
            }
            composable(Routes.Edit.route+"/{index}")
                { navBackStackEntry ->
                    val ind = navBackStackEntry.arguments?.getString("index")
                    ind?.let { EditScreen(navController, viewModel, it.toInt()) }
            }
            composable(Routes.Add.route){
                AddScreen(navController, viewModel)
            }
        }
    }
}