package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : ComponentActivity() {
    private var taskList = mutableListOf<Task>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val list = mutableListOf<Task>()
                    val aaa = Task()
                    aaa.id = 1L
                    aaa.title = "title"
                    aaa.date = Date()
                    list.add(aaa)

                    NavHost(navController = navController, startDestination = "main") {
                        composable("main") {
                            HomeContent(taskList = list, navController = navController)
                        }
                        composable("second") {
                            Register()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeContent(taskList: List<Task>, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("タスク一覧") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                          navController.navigate("second")
                          },
                content = {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add"
                    )
                }
            )
        }
    ) {
        TaskList(taskList = taskList)
    }
}

@Composable
private fun TaskList(taskList: List<Task>){
    val sdf = SimpleDateFormat("yyyy/M/d HH:mm")
    LazyColumn(Modifier.fillMaxSize()) {
        taskList.forEach {
            item {
                Text(text = it.title, fontSize = 20.sp)
                Text(text = sdf.format(it.date).toString(), fontSize = 20.sp)
            }
        }
    }
    Divider()
}

@Composable
private fun Register() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("タスク登録・編集") }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "詳細画面"
            )
        }
    }
}


@Composable
@Preview
fun DefaultPreview() {
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val navController = rememberNavController()
            val list = mutableListOf<Task>()
            val aaa = Task()
            aaa.id = 1L
            aaa.title = "memomemomemo"
            list.add(aaa)

            NavHost(navController = navController, startDestination = "main") {
                composable("main") {
                    HomeContent(taskList = list, navController = navController)
                }
                composable("second") {
                    Register()
                }
            }
        }
    }
}