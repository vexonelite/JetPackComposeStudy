package com.gmail.vexonelite.jetpack.study.viewmodels

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


fun NavHostController.navigateSingleTopTo(
    route: String,
    specifiedStartDestination: String,
    restoreState: Boolean = true,
    launchSingleTop: Boolean = true,
    saveState: Boolean = true,
    inclusive: Boolean = false,
) =
    this.navigate(route) {

        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            specifiedStartDestination
        ) {
            this@popUpTo.saveState = saveState
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        this@navigate.launchSingleTop = launchSingleTop
        // Restore state when reselecting a previously selected item
        this@navigate.restoreState = restoreState
    }



fun NavHostController.navigateToExt(
    route: String,
    clearBackStack: Boolean = false,
    restoreState: Boolean = true,
    launchSingleTop: Boolean = true,
    inclusive: Boolean = true,
    saveState: Boolean = true,
) = this.navigate(route) {
    if(clearBackStack) {
        popUpTo(
            this@navigateToExt.graph.id
        ) {
            this@popUpTo.inclusive = inclusive
            this@popUpTo.saveState = saveState
        }
    }
    // Avoid multiple copies of the same destination when
    // reselecting the same item
    this@navigate.launchSingleTop = launchSingleTop
    // Restore state when reselecting a previously selected item
    this@navigate.restoreState = restoreState
}

