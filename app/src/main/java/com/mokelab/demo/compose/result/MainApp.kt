package com.mokelab.demo.compose.result

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.mokelab.demo.compose.result.screen.form.FormScreen
import com.mokelab.demo.compose.result.screen.home.HomeScreen
import com.mokelab.demo.compose.result.screen.prev.PrevHomeScreen
import com.mokelab.demo.compose.result.screen.ResultViewModel
import com.mokelab.demo.compose.result.screen.scoped.ScopedMainScreen

@Composable
fun MainApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "/") {
        composable("/") { entry ->
            val result = entry
                .savedStateHandle
                .getLiveData<String>("result")
                .observeAsState()
            HomeScreen(
                result = result.value,
                toForm = {
                    navController.navigate("/form1")
                },
                toScopedMain = {
                    navController.navigate("/scoped")
                },
                toPrevMain = {
                    navController.navigate("/prev")
                }
            )
        }
        composable("/form1") {
            FormScreen(back = {
                navController.popBackStack()
            }, backWithResult = { result ->
                // https://stackoverflow.com/questions/66837132/jetpack-compose-navigate-for-result
                // and github copilot wrote this..
                navController.previousBackStackEntry?.savedStateHandle?.set("result", result)
                navController.popBackStack()
            })
        }
        navigation(startDestination = "/scoped/main", route = "/scoped") {
            composable("/scoped/main") { backStackEntry ->
                // navigation scoped view model
                // https://developer.android.com/topic/libraries/architecture/viewmodel/viewmodel-apis#vm-api-navgraph
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("/scoped")
                }
                val resultVM: ResultViewModel = viewModel(parentEntry)
                ScopedMainScreen(
                    resultVM = resultVM,
                    back = {
                        navController.popBackStack()
                    },
                    toForm = {
                        navController.navigate("/scoped/form")
                    })
            }
            composable("/scoped/form") { backStackEntry ->
                // navigation scoped view model
                // https://developer.android.com/topic/libraries/architecture/viewmodel/viewmodel-apis#vm-api-navgraph
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("/scoped")
                }
                val resultVM: ResultViewModel = viewModel(parentEntry)
                FormScreen(back = {
                    navController.popBackStack()
                }, backWithResult = { result ->
                    resultVM.setResult(result)
                    navController.popBackStack()
                })
            }
        } // end of navigation

        composable("/prev") {
            val resultVM: ResultViewModel = viewModel()
            PrevHomeScreen(
                result = resultVM.result.value,
                back = {
                    navController.popBackStack()
                },
                toForm = {
                    navController.navigate("/prev/form")
                },
            )
        }
        composable("/prev/form") {
            val parentEntry = navController.previousBackStackEntry
            val resultVM: ResultViewModel? =
                if (parentEntry == null) null else viewModel(parentEntry)
            FormScreen(back = {
                navController.popBackStack()
            }, backWithResult = { result ->
                resultVM?.setResult(result)
                navController.popBackStack()
            })
        }
    }
}