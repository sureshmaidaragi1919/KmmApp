package com.compose.session.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.compose.session.ScreenName

@Composable
fun SecondScreen(navController: NavHostController) {

    Column(modifier = Modifier) {


        Button(onClick = {
            navController.navigate(ScreenName.FirstScreen) {
                popUpTo(ScreenName.FirstScreen) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }) {
            Text("Goto 1st Screen")
        }
        Text("2nd Screen")

    }
}