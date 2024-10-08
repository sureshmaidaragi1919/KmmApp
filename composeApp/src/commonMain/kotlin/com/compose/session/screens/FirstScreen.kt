package com.compose.session.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.compose.session.Content
import com.compose.session.ScreenName
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun FirstScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        var move = remember { mutableStateOf(false) }
        Button(onClick = {
            move.value = true
            navController.navigate(
               "secondScreen/${Json.encodeToString(Content(name = "suresh", price = 1.0))}"
            )

        }) {
            Text("Goto 2nd Screen")

        }

        Text("1st Screen")


    }
}