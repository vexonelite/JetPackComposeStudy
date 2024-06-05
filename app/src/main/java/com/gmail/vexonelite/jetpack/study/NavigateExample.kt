package com.gmail.vexonelite.jetpack.study


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue007
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey001
import com.gmail.vexonelite.jetpack.study.ui.theme.ImmutableObjectList
import com.gmail.vexonelite.jetpack.study.ui.theme.JetPackComposeStudyTheme
import com.gmail.vexonelite.jetpack.study.ui.theme.Pink001
import com.gmail.vexonelite.jetpack.study.ui.theme.Purple003
import com.gmail.vexonelite.jetpack.study.ui.theme.Yellow001
import com.gmail.vexonelite.jetpack.study.ui.theme.navigateSingleTopTo
import com.gmail.vexonelite.jetpack.study.ui.theme.navigateToExt


sealed interface RouteDestination {
    val theRoute: String
    val theTitle: String
    val theColor: Color

    data object Login: RouteDestination {
        override val theRoute: String = "_login"
        override val theTitle: String = "Login"
        override val theColor: Color = Purple003
    }

    data object Home: RouteDestination {
        override val theRoute: String = "_home"
        override val theTitle: String = "Home"
        override val theColor: Color = Grey001
    }

    data object Vendue: RouteDestination {
        override val theRoute: String = "_vendue"
        override val theTitle: String = "Vendue"
        override val theColor: Color = Pink001
    }

    data object Transaction: RouteDestination {
        override val theRoute: String = "_transaction"
        override val theTitle: String = "Transaction"
        override val theColor: Color = Yellow001
    }
}


//fun RouteDestination.theColor(): Color =
//    when(this) {
//        RouteDestination.Login -> {  Purple003 }
//        RouteDestination.Home -> {  Grey001 }
//        RouteDestination.Vendue -> {  Pink001 }
//        RouteDestination.Transaction -> {  Yellow001 }
//    }


//fun RouteDestination.theTitle(): String =
//    when(this) {
//        RouteDestination.Login -> { "Login" }
//        RouteDestination.Home -> {  "Home" }
//        RouteDestination.Vendue -> {  "Vendue" }
//        RouteDestination.Transaction -> {  "Transaction" }
//    }


@Composable
fun LoginScreen(
    navController: NavHostController,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Button(
            onClick = {
                //navController.navigate(RouteDestination.Home.theRoute)
                navController.navigateToExt(RouteDestination.Home.theRoute, clearBackStack = true)
            },
        ) {
            Text("Login")
        }
    }
}


@Composable
fun HomeMenuItem(
    routeDestination: RouteDestination,
    onCellClick: (RouteDestination) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .padding(all = 8.dp)
            .clickable(onClick = {
                onCellClick.invoke(routeDestination)
            })
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(10.dp),
            color = Grey001,
            border = BorderStroke(2.dp, Blue007),
        ){}
        Spacer(modifier = Modifier.padding(vertical = 6.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(routeDestination.theTitle)
        }
    }
}


@Composable
fun HomeScreen(
    navController: NavHostController,
    menuItems: ImmutableObjectList<RouteDestination>,
) {
    val onCellClick: (RouteDestination) -> Unit = { routeDestination ->
        when(routeDestination) {
            RouteDestination.Vendue -> {
                navController.navigateSingleTopTo(routeDestination.theRoute, RouteDestination.Home.theRoute)
            }
            RouteDestination.Transaction -> {
                navController.navigateSingleTopTo(routeDestination.theRoute, RouteDestination.Home.theRoute)
            }
            else -> {}
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Box(modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    //navController.navigate(RouteDestination.Login.theRoute)
                    navController.navigateToExt(RouteDestination.Login.theRoute, clearBackStack = true)
                },
            ) {
                Text("Log out")
            }
        }


        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp),
        ) {
            items(
                items = menuItems.objectList,
                key = { routeDestination -> routeDestination.theRoute },
                //contentType = { routeDestination -> routeDestination },
            ) { routeDestination ->
                HomeMenuItem(routeDestination, onCellClick)
            }
        }
    }

}


@Composable
fun SingleTextScreen(navController: NavHostController, title: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = title)
    }
}


@Composable
fun VendueScreen(navController: NavHostController) {
    SingleTextScreen(navController, RouteDestination.Vendue.theTitle)
}


@Composable
fun TransactionScreen(navController: NavHostController) {
    SingleTextScreen(navController, RouteDestination.Transaction.theTitle)
}


@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = RouteDestination.Login.theRoute,
        modifier = modifier
    ) {
        composable(route = RouteDestination.Login.theRoute) {
            LoginScreen(navController)
        }
        composable(RouteDestination.Home.theRoute) {
            val menuItemList: List<RouteDestination> = listOf(
                RouteDestination.Vendue, RouteDestination.Transaction
            )
            val menuItems: ImmutableObjectList<RouteDestination> = ImmutableObjectList(menuItemList)
            HomeScreen(navController = navController, menuItems = menuItems)
        }
        composable(route = RouteDestination.Vendue.theRoute) {
            VendueScreen(navController)
        }
        composable(route = RouteDestination.Transaction.theRoute) {
            TransactionScreen(navController)
        }
    }
}


@Composable
fun MyApp(navController: NavHostController,) {
    JetPackComposeStudyTheme {
        val screenList = listOf<RouteDestination>(
            RouteDestination.Login, RouteDestination.Home, RouteDestination.Vendue, RouteDestination.Transaction,
        )
        val screens = remember { mutableStateOf(screenList) }

        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen =
            screens.value.find { it.theRoute == currentDestination?.route } ?: RouteDestination.Login

        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(all = 4.dp)
        )
    }
}


