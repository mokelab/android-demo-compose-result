package com.mokelab.demo.compose.result.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    result: String?,
    toForm: () -> Unit,
    toScopedMain: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Top") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Button(onClick = {
                toForm()
            }) {
                Text("Go to form")
            }

            Button(onClick = {
                toScopedMain()
            }) {
                Text("Go to scoped main screen")
            }
        }
    }

    LaunchedEffect(result) {
        result?.let {
            println("result=$it")
        }
    }
}
