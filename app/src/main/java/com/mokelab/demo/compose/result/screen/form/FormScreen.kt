package com.mokelab.demo.compose.result.screen.form

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(
    back: () -> Unit,
    backWithResult: (String) -> Unit,
) {
    var value by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Form") },
                navigationIcon = {
                    IconButton(onClick = back) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            TextField(value = value, onValueChange = { value = it })
            Button(onClick = {
                backWithResult(value)
            }) {
                Text("Submit")
            }
        }
    }
}
