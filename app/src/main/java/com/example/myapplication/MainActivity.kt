package com.example.myapplication


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
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
                    val task = Task()
                    task.id = 1L
                    task.title = "title"
                    task.date = Date()
                    list.add(task)

                    NavHost(navController = navController,
                        startDestination = "main") {
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
    LazyColumn(Modifier.padding(16.dp)) {
        taskList.forEach {
            item {
                Text(text = it.title, fontSize = 20.sp)
                Text(text = sdf.format(it.date).toString(), fontSize = 20.sp)
                Divider()
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Register() {
    val options = listOf("0分(指定なし)", "5分前", "10分前", "15分前", "30分前", "1時間前")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }
    var showDialog by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    if (showDialog) {
        ShowDatePicker()
    } else if (showTimePicker) {
        ShowTimePicker()
    } else {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("タスク登録・編集") }
                )
            }
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "タスク名(必須)"
                )
                val taskNameValue = remember { mutableStateOf("") }
                OutlinedTextField(
                    value = taskNameValue.value,
                    onValueChange = { newValue ->
                        taskNameValue.value = newValue
                    })
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "詳細"
                )
                val fieldValue = remember { mutableStateOf("") }
                OutlinedTextField(
                    value = fieldValue.value,
                    onValueChange = { newValue ->
                        fieldValue.value = newValue
                    })
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "タスク期限"
                )
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val hourValue = remember { mutableStateOf("") }
                    TextField(
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                showDialog = true
                            },
                        value = hourValue.value,
                        enabled = false,
                        onValueChange = { newValue ->
                            hourValue.value = newValue
                        })
                    val minutesValue = remember { mutableStateOf("") }
                    TextField(
                        modifier = Modifier
                            .weight(1f)
                            .padding(16.dp, 0.dp)
                            .clickable {
                                showTimePicker = true
                            },
                        value = minutesValue.value,
                        enabled = false,
                        onValueChange = { newValue ->
                            minutesValue.value = newValue
                        })
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "通知時間"
                )
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    }
                ) {
                    OutlinedTextField(
                        readOnly = true,
                        value = selectedOptionText,
                        onValueChange = { },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = expanded
                            )
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = {
                            expanded = false
                        }
                    ) {
                        options.forEach { selectionOption ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedOptionText = selectionOption
                                    expanded = false
                                }
                            ) {
                                Text(text = selectionOption)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ShowDatePicker() {
    val dialogState = rememberMaterialDialogState()
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        datepicker { date ->
        }
    }
    dialogState.show()
}

@Composable
fun ShowTimePicker() {
    val dialogState = rememberMaterialDialogState()
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        timepicker { time ->
        }
    }
    dialogState.show()
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