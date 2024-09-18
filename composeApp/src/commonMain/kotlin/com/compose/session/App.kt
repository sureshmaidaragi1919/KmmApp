package com.compose.session

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.DrawableResource

data class BottomBarItem(
    val title: String,
    var currentRoute: String = ScreenName.FirstScreen.toString(),
    val icon: DrawableResource? = null
)

@Composable
fun App() {
    val navController = rememberNavController()

    val bottomBarList = listOf(
        BottomBarItem(
            title = "Home",
            icon = null,
            currentRoute = ScreenName.FirstScreen::class.qualifiedName!!
        ), BottomBarItem(
            title = "Settings",
            icon = null,
            currentRoute = ScreenName.SecondScreen::class.qualifiedName!!
        )
    )
    MaterialTheme {
        Scaffold(modifier = Modifier, topBar = {
            TopAppBar(
                title = {
                    Text("TopAppBar title")
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.Menu, contentDescription = ""
                    )
                },
                modifier = Modifier,
            )
        }, floatingActionButton = {
            FloatingActionButton(onClick = {
                println("Testing")
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ExitToApp, contentDescription = ""
                )
            }
        }, bottomBar = {
            BottomAppBar {
                val destination =
                    navController.currentBackStackEntryAsState().value?.destination?.route
                bottomBarList.forEachIndexed { index, item ->
                    BottomNavigationItem(selected = item.currentRoute == destination, icon = {

                    }, onClick = {
                        if (item.currentRoute != destination) {
                            navController.navigate(
                                route = item.currentRoute
                            )
                            println("Testing ${item.title}")
                        }
                    }, label = {

                        Text(text = item.title)
                    })
                }
            }
        }) { paddingValues ->

            NavHost(navController = navController,
                startDestination = ScreenName.FirstScreen,
                route = null,
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { 0 }, animationSpec = tween(durationMillis = 200)
                    )
                },
                exitTransition = {
                    fadeOut(animationSpec = tween(durationMillis = 1))
                }) {

                composable<ScreenName.FirstScreen> {
                    FirstScreen(navController)
                }

                composable<ScreenName.SecondScreen> {
                    SecondScreen(navController)
                }
            }
        }


    }
}


@Composable
fun FirstScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("1st Screen")
        Button(onClick = {
            navController.navigate(ScreenName.SecondScreen)
        }) {
            Text("Go to 2nd Screen")
        }
    }
}

@Composable
fun SecondScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("2nd Screen")
        Button(onClick = {
            navController.navigate(ScreenName.FirstScreen)
        }) {
            Text("Go to 1st Screen")
        }
    }
}

open class ScreenName {

    @Serializable
    data object FirstScreen : ScreenName()

    @Serializable
    data object SecondScreen : ScreenName()
}

