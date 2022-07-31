package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme


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
                    TaskList(taskList = taskList)
                }
            }
        }
    }
}

@Composable
private fun TaskList(taskList: List<Task>){
    LazyColumn(Modifier.fillMaxSize()) {
        taskList.forEach {
            item {
                Text(text = it.title, fontSize = 20.sp)
            }
        }
    }
    Divider()
}


@Composable
@Preview
fun DefaultPreview() {
    MyApplicationTheme {
        TaskList(listOf())
    }
}