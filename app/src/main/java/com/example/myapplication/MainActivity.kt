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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : ComponentActivity() {
    private var taskList = mutableListOf<Task>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
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

                    HomeContent(taskList = list)
                }
            }
        }
    }
}

@Composable
fun HomeContent(taskList: List<Task>) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("タスク一覧") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                          /*TODO*/
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
@Preview
fun DefaultPreview() {
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val list = mutableListOf<Task>()
            val aaa = Task()
            aaa.id = 1L
            aaa.title = "memomemomemo"
            list.add(aaa)

            HomeContent(list)
        }
    }
}