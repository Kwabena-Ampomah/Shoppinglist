package com.example.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.ui.theme.ShoppinglistTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppinglistTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    TaskList()
                }
            }
        }
    }
}
@Preview
@Composable
fun TaskList() {
    var taskText by remember { mutableStateOf("") }
    var taskList by remember { mutableStateOf(listOf<String>()) }
    //profs code
    LazyColumn {
        item {
            // Input TextField for Task
            TextField(
                value = taskText,
                onValueChange = { taskText = it },
                label = { Text("Enter item") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Add Task Button
            Button(
                onClick = {
                    if (taskText.isNotBlank()) {
                        taskList = taskList + taskText // Add task to the list
                        taskText = "" // Clear input after adding
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add item")
            }
            //extra component for the rubric
            Button(
                onClick = { taskList = emptyList() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Delete All items")
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // replaced the display Task List for items from profs code Lazy column jetpack site
        items(taskList) { task ->
            var isChecked by remember { mutableStateOf(false) }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = task)
                /* citation for checkboxes https://owlbuddy.com/checkboxes-in-jetpack-compose/ */
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { checked ->
                        isChecked = checked
                        if (checked) {
                            taskList = taskList - task
                        }
                    }
                )
                Text("Hit the checkbox to del")
            }

            Spacer(modifier = Modifier.height(8.dp))
        }



    }
}
