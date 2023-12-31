package com.mokelab.demo.compose.result.screen.scoped

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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mokelab.demo.compose.result.screen.ResultViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScopedMainScreen(
    resultVM: ResultViewModel,
    back: () -> Unit,
    toForm: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Scoped") },
                navigationIcon = {
                    IconButton(onClick = back) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
        ) {
            Text(resultVM.result.value)
            Button(onClick = toForm) {
                Text("Go to form")
            }
        }
    }
}