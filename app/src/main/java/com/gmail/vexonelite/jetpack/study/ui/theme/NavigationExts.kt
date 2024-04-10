package com.gmail.vexonelite.jetpack.study.ui.theme

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.gmail.vexonelite.jetpack.study.RouteDestination


fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {

        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }


fun NavHostController.navigateSingleTopTo(route: String, specifiedStartDestination: String) =
    this.navigate(route) {

        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            specifiedStartDestination
        ) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }



fun NavHostController.navigateToExt(route: String, clearBackStack: Boolean = false) =
    this.navigate(route) {
        if(clearBackStack) {
            popUpTo(
                this@navigateToExt.graph.id
            ) {
                inclusive  = true
            }
        }

        launchSingleTop = true
    }

