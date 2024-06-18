package com.gmail.vexonelite.jetpack.study.ntmofa

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.gmail.vexonelite.jetpack.study.HolderItemClickDelegate
import com.gmail.vexonelite.jetpack.study.R
import com.gmail.vexonelite.jetpack.study.screens.MenuScreenContent
import com.gmail.vexonelite.jetpack.study.ui.theme.DarkerGray
import com.gmail.vexonelite.jetpack.study.ui.theme.ImmutableObjectList
import com.gmail.vexonelite.jetpack.study.viewmodels.navigateSingleTopTo
import com.gmail.vexonelite.jetpack.study.viewmodels.MenuItemContentType
import com.gmail.vexonelite.jetpack.study.viewmodels.MenuItemModel
import java.util.UUID
import java.util.logging.Level
import java.util.logging.Logger


object NtmofaMenuAction {
    const val FUNC1 = "menu_action_func1"
    const val FUNC2 = "menu_action_func2"
    const val FUNC3 = "menu_action_func3"
    const val FUNC4 = "menu_action_func4"
    const val FUNC5 = "menu_action_func5"
    const val FUNC6 = "menu_action_func6"
}


fun generateType3List(context: Context): List<MenuItemModel> {
    val dataList: MutableList<MenuItemModel> = mutableListOf()

    val uuid = UUID.randomUUID().toString()
    val descriptionArray = arrayOf<String>(
        context.getString(R.string.menu_func1),
        context.getString(R.string.menu_func2),
        context.getString(R.string.menu_func3),
        context.getString(R.string.menu_func4),
        context.getString(R.string.menu_func5),
        context.getString(R.string.menu_func6),
    )
    val imageResIdArray = arrayOf<Int>(
        R.drawable.ic_query,
        R.drawable.ic_locate,
        R.drawable.ic_inventory,
        R.drawable.ic_loan,
        R.drawable.ic_storage,
        R.drawable.transponder_256,
    )
    val actionArray = arrayOf<String>(
        NtmofaMenuAction.FUNC1,
        NtmofaMenuAction.FUNC2,
        NtmofaMenuAction.FUNC3,
        NtmofaMenuAction.FUNC4,
        NtmofaMenuAction.FUNC5,
        NtmofaMenuAction.FUNC6
    )

    for (i in imageResIdArray.indices) {
        val identifier = "${uuid}_${i + 1}"
        val delegate = MenuItemModel(
            id = identifier,
            description = descriptionArray[i],
            contentType = MenuItemContentType.TYPE3,
            action = actionArray[i],
            color = DarkerGray,
            imageResId = imageResIdArray[i],
        )
        dataList.add(delegate)
    }

    return dataList
}


@Composable
fun NtmofaMenuScreen(
    navController: NavHostController,
) {
    val itemClickCallback = HolderItemClickDelegate<MenuItemModel> { dataObject, action, position ->
        Logger.getLogger("NtmofaMenuScreen").log(Level.INFO, "itemClickCallback - delegate: [${dataObject.description}], action: [$action], position: [$position]")
        when(action) {
            NtmofaMenuAction.FUNC1 -> {
                navController.navigateSingleTopTo(
                    NtmofaRouteDestination.Query.theRoute,
                    NtmofaRouteDestination.Menu.theRoute
                )
            }
            NtmofaMenuAction.FUNC2 -> {
                navController.navigateSingleTopTo(
                    NtmofaRouteDestination.Location.theRoute,
                    NtmofaRouteDestination.Menu.theRoute
                )
            }
            NtmofaMenuAction.FUNC3 -> {
                navController.navigateSingleTopTo(
                    NtmofaRouteDestination.Inventory.theRoute,
                    NtmofaRouteDestination.Menu.theRoute
                )
            }
            NtmofaMenuAction.FUNC4 -> {
                navController.navigateSingleTopTo(
                    NtmofaRouteDestination.Loan.theRoute,
                    NtmofaRouteDestination.Menu.theRoute
                )
            }
            NtmofaMenuAction.FUNC5 -> {
                navController.navigateSingleTopTo(
                    NtmofaRouteDestination.Storage.theRoute,
                    NtmofaRouteDestination.Menu.theRoute
                )
            }
            NtmofaMenuAction.FUNC6 -> {
                navController.navigateSingleTopTo(
                    NtmofaRouteDestination.RfidTag.theRoute,
                    NtmofaRouteDestination.Menu.theRoute
                )
            }
        }
    }

    val context = LocalContext.current
    val menuItemList = ImmutableObjectList<MenuItemModel>(generateType3List(context))
    MenuScreenContent(menuItemList = menuItemList, itemClickCallback = itemClickCallback)
}

