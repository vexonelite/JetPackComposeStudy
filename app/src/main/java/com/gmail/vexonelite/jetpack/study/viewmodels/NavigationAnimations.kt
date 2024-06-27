package com.gmail.vexonelite.jetpack.study.viewmodels


import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.navigation.NavBackStackEntry


fun AnimatedContentTransitionScope<NavBackStackEntry>.fadeInExt(
    durationMillis: Int = 350,
): EnterTransition = fadeIn(tween(durationMillis = durationMillis, easing = LinearEasing))


fun AnimatedContentTransitionScope<NavBackStackEntry>.fadeOutExt(
    durationMillis: Int = 350,
): ExitTransition = fadeOut(tween(durationMillis = durationMillis, easing = LinearEasing))


fun AnimatedContentTransitionScope<NavBackStackEntry>.expandInExt(
    durationMillis: Int = 350,
): EnterTransition = expandIn(tween(durationMillis = durationMillis, easing = LinearEasing))


fun AnimatedContentTransitionScope<NavBackStackEntry>.shrinkOutExt(
    durationMillis: Int = 350,
): ExitTransition = shrinkOut(tween(durationMillis = durationMillis, easing = LinearEasing))


fun AnimatedContentTransitionScope<NavBackStackEntry>.slideInExt(
    towards: AnimatedContentTransitionScope.SlideDirection = AnimatedContentTransitionScope.SlideDirection.Start,
    durationMillis: Int = 350,
): EnterTransition =
    slideIntoContainer(towards, tween(durationMillis = durationMillis, easing = LinearEasing)) // EaseIn


fun AnimatedContentTransitionScope<NavBackStackEntry>.slideOutExt(
    towards: AnimatedContentTransitionScope.SlideDirection = AnimatedContentTransitionScope.SlideDirection.End,
    durationMillis: Int = 350,
): ExitTransition =
    slideOutOfContainer(towards, tween(durationMillis = durationMillis, easing = LinearEasing)) // EaseOut


fun AnimatedContentTransitionScope<NavBackStackEntry>.fadeInAndSlideInExt(
    towards: AnimatedContentTransitionScope.SlideDirection = AnimatedContentTransitionScope.SlideDirection.Start,
    durationMillis: Int = 350,
): EnterTransition =
    fadeIn(
        animationSpec = tween(durationMillis = durationMillis, easing = LinearEasing)
    ) + slideIntoContainer(
        animationSpec = tween(durationMillis = durationMillis, easing = EaseIn),
        towards = towards,
    )


fun AnimatedContentTransitionScope<NavBackStackEntry>.fadeOutAndSlideOutExt(
    towards: AnimatedContentTransitionScope.SlideDirection = AnimatedContentTransitionScope.SlideDirection.End,
    durationMillis: Int = 350,
): ExitTransition =
    fadeOut(
        animationSpec = tween(
            durationMillis = durationMillis, easing = LinearEasing
        )
    ) + slideOutOfContainer(
        animationSpec = tween(durationMillis = durationMillis, easing = EaseOut),
        towards = towards
    )


