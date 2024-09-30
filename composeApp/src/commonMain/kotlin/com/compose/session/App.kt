package com.compose.session

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.compose.session.screens.FirstScreen
import com.compose.session.screens.SecondScreen
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf


@Composable
fun App() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("TopAppBar")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        println("Hi testing")
                    }) {
                        Icon(
                            contentDescription = null,
                            imageVector = Icons.Default.Menu
                        )
                    }
                }
            )

        },
        bottomBar = {
            BottomAppBar(

            ) {
                Text("Hi")
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                IconButton(onClick = {
                    println("Hi FAB")
                }) {
                    Icon(
                        contentDescription = null,
                        imageVector = Icons.Default.Star
                    )
                }
            }
        }
    ) {

        val navController = rememberNavController()

        NavHost(  // Multiple NavHost Next session
            navController = navController,
            startDestination = ScreenName.FirstScreen,
            modifier = Modifier.padding(it)
        ) {

            composable<ScreenName.FirstScreen> {
                FirstScreen(navController = navController)
            }

            composable (
                route = "secondScreen/{content}",
                arguments = listOf(navArgument("content") {
                    type = CustomNavType.ContentType
                })
            ) {
                val arguments = it.toRoute<ScreenName.SecondScreen>()
                println("Arguments: ${arguments.content}")
                SecondScreen(navController = navController)
            }

        }

    }

}


@Serializable
sealed class ScreenName {

    @Serializable
    data object FirstScreen : ScreenName()

    @Serializable
    data class SecondScreen(val content: Content) : ScreenName()
}

@Serializable
data class Content(
    val name: String,
    val price: Double
)