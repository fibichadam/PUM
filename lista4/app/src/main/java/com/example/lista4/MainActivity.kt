package com.example.lista4

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import java.util.Random
import kotlin.math.roundToInt


var ListOfLists: ArrayList<ExerciseList> = ArrayList()
var subjectInfos: ArrayList<SubjectInfo> = ArrayList()

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fillData()

        setContent {
            Navigation()
        }
    }
}

class Exercise{
    var content: String = LoremIpsum(Random().nextInt(30) + 15).values.iterator().next()
    var points: Int = Random().nextInt(10) + 1
}

class Subject {
    var name: String = mutableListOf(
        "Matematyka",
        "PUM",
        "Fizyka",
        "Elektronika",
        "Algorytmy"
    )[Random().nextInt(5)]
}

class ExerciseList {
    var exercises: ArrayList<Exercise> = ArrayList()
    init{
        val exercisesCount = Random().nextInt(10) + 1
        for (e in 0 until exercisesCount) exercises.add(Exercise())
    }

    var subject: Subject = Subject()
    var grade: Float = mutableListOf(3f, 3.5f, 4f, 4.5f, 5f)[Random().nextInt(5)]
    var listNumber : Int = 1
}

class SubjectInfo(subject: Subject){
    var subject: Subject? = subject
    var averageGrade = 0f
    var listCount = 1
}

sealed class Screens(val route: String) {
    object ExerciseListsScreen : Screens("exerciseLists")
    object GradesScreen : Screens("grades")
    object ListScreen : Screens("list/{listNumber}")
}

sealed class BottomBar(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Home: BottomBar(Screens.ExerciseListsScreen.route, "Moje listy zadan", Icons.Default.List)
    object Grades: BottomBar(Screens.GradesScreen.route, "Moje oceny", Icons.Default.Create)
}


fun fillData(){
    for (i in 0 until 20) {
        ListOfLists.add(ExerciseList())
        var containsSubject = false
        var j = 0
        while (j < subjectInfos.size) {
            if (subjectInfos[j].subject!!.name == ListOfLists[i].subject.name) {
                subjectInfos[j].listCount++
                subjectInfos[j].averageGrade += ListOfLists[i].grade
                containsSubject = true
            }
            j++
        }
        if (!containsSubject) {
            subjectInfos.add(SubjectInfo(ListOfLists[i].subject))
            subjectInfos[j].averageGrade = ListOfLists[i].grade
        }
    }

    for (i in subjectInfos.indices) {
        subjectInfos[i].averageGrade = (2 * subjectInfos[i].averageGrade / subjectInfos[i].listCount).roundToInt().toFloat() / 2
    }

    ListOfLists.sortBy { it.subject.name }
    var listNumber = 1
    ListOfLists[0].listNumber = 1
    for (i in 1 until ListOfLists.size) {
        if (ListOfLists[i].subject.name != ListOfLists[i - 1].subject.name) {
            listNumber = 1
            ListOfLists[i].listNumber = listNumber
        } else ListOfLists[i].listNumber = ++listNumber
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomMenu(navController = navController)},
        content = { BottomNavGraph(navController = navController) }
    )
}

@Composable
fun BottomNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screens.ExerciseListsScreen.route
    ) {
        composable(route = Screens.ExerciseListsScreen.route){ ExerciseLists(navController) }
        composable(route = Screens.GradesScreen.route){ Grades() }
        composable(route = Screens.ListScreen.route){ navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString("listNumber")
            id?.let {
                SingleList(it.toInt())
            }
        }
    }
}

@Composable
fun BottomMenu(navController: NavHostController){
    val screens = listOf(
        BottomBar.Home, BottomBar.Grades
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar{
        screens.forEach{screen ->
            NavigationBarItem(
                label = { Text(text = screen.title)},
                icon = {Icon(imageVector = screen.icon, contentDescription = "icon")},
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {navController.navigate(screen.route)}
            )
        }
    }
}

@Composable
fun ExerciseLists(navHostController: NavHostController) {
    Column{
        Text(
            text = "Moje listy zadan",
            textAlign = TextAlign.Center,
            fontSize = TextUnit(30f, TextUnitType.Sp),
            modifier = Modifier.fillMaxWidth()
        )

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .padding(bottom = 80.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)

        ) {
            items(ListOfLists.size) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color(200, 150, 150))
                    .height(80.dp)
                    .clickable { navHostController.navigate("list/$it") }

                ) {

                    Text(
                        text = "Ocena: " + ListOfLists[it].grade,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(bottom = 10.dp, end = 10.dp)
                    )
                    Text(
                        text = "Liczba zadan: " + ListOfLists[it].exercises.size,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(bottom = 10.dp, start = 10.dp)
                    )
                    Text(
                        text = "Lista " + ListOfLists[it].listNumber,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 10.dp, end = 10.dp)
                    )
                    Text(
                        text = ListOfLists[it].subject.name,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(top = 10.dp, start = 10.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun Grades(){
    Text(
        text = "Moje oceny",
        textAlign = TextAlign.Center,
        fontSize = TextUnit(30f , TextUnitType.Sp),
        modifier = Modifier.fillMaxWidth()
    )

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(start = 20.dp, end = 20.dp, top = 60.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        items(subjectInfos.size){
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color(200, 150, 150))
                .height(80.dp)
            ) {
                Text(
                    text = "Liczba list: "+subjectInfos[it].listCount,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(bottom = 10.dp, start = 10.dp)
                )
                Text(
                    text = "Srednia: "+ subjectInfos[it].averageGrade,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 10.dp, end = 10.dp)
                )
                Text(
                    text = subjectInfos[it].subject!!.name,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(top = 10.dp, start = 10.dp)
                )
            }
        }
    }
}

@Composable
fun SingleList(listNumber: Int){
    Log.d("Args", "ExerciseLists: $listNumber")

    Text(
        text = ListOfLists[listNumber].subject.name+ "\nLista "+ ListOfLists[listNumber].listNumber,
        textAlign = TextAlign.Center,
        fontSize = TextUnit(30f , TextUnitType.Sp),
        modifier = Modifier.fillMaxWidth()
    )

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(start = 20.dp, end = 20.dp, top = 100.dp, bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        items(ListOfLists[listNumber].exercises.size){
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color(200, 150, 150))
                .wrapContentSize()
            ) {
                Text(
                    text = ListOfLists[it].exercises[0].content,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(top = 50.dp, bottom = 10.dp, start = 10.dp)
                )
                Text(
                    text = "Pkt: "+ ListOfLists[it].exercises[0].points,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 10.dp, end = 10.dp)
                )
                Text(
                    text = "Zadanie "+(it+1),
                    fontSize = 24.sp,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(top = 10.dp, start = 10.dp)
                )
            }
        }
    }
}