package com.gmail.vexonelite.jetpack.study.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gmail.vexonelite.jetpack.study.HolderItemClickDelegate
import com.gmail.vexonelite.jetpack.study.R
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey80
import com.gmail.vexonelite.jetpack.study.viewmodels.AspectRatioReference
import com.gmail.vexonelite.jetpack.study.viewmodels.ImmutableObjectList
import com.gmail.vexonelite.jetpack.study.viewmodels.ListViewModel
import com.gmail.vexonelite.jetpack.study.viewmodels.MenuItemContentType
import com.gmail.vexonelite.jetpack.study.viewmodels.MenuItemModel
import com.gmail.vexonelite.jetpack.study.viewmodels.aspectRatioReference
import com.gmail.vexonelite.jetpack.study.viewmodels.generateType2List
import java.util.logging.Level
import java.util.logging.Logger


@Preview
@Composable
fun MenuScreen01(viewModel: ListViewModel = viewModel()) {
    val itemClickCallback = HolderItemClickDelegate<MenuItemModel> { dataObject, action, position ->
        Logger.getLogger("MenuScreen01").log(Level.INFO, "itemClickCallback - delegate: [${dataObject.description}], action: [$action], position: [$position]")
    }
    val context = LocalContext.current
    val menuItemList = ImmutableObjectList<MenuItemModel>(generateType2List())
    MenuScreenContent(menuItemList = menuItemList, itemClickCallback = itemClickCallback)
}


/**
 * https://foso.github.io/Jetpack-Compose-Playground/foundation/lazyverticalgrid/
 */
//@Preview
@Composable
fun MenuScreenContent(
    menuItemList: ImmutableObjectList<MenuItemModel> = ImmutableObjectList<MenuItemModel>(listOf()),
    itemClickCallback: HolderItemClickDelegate<MenuItemModel>? = null
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        // content padding
//        contentPadding = PaddingValues(
//            start = 12.dp,
//            top = 16.dp,
//            end = 12.dp,
//            bottom = 16.dp
//        ),
    ) {
        items(
            menuItemList.objectList,
            key = { menuItem -> menuItem.id },
            contentType = { menuItem -> menuItem.contentType },
        ) { menuItem ->
            when(menuItem.contentType) {
                MenuItemContentType.TYPE1 -> { ColorMenuItemGrid01(menuItem, itemClickCallback) }
                MenuItemContentType.TYPE2 -> { ImageMenuItemGrid01(menuItem, itemClickCallback) }
                MenuItemContentType.TYPE3 -> { ImageMenuItemGrid02(menuItem, itemClickCallback) }
            }
        }
    }
}


//@Preview
@Composable
fun ColorMenuItemGrid01(
    menuItem: MenuItemModel = MenuItemModel(),
    itemClickCallback: HolderItemClickDelegate<MenuItemModel>? = null
) {
    Box(
        modifier = Modifier
            .clickable { itemClickCallback?.onHolderItemClicked(menuItem, menuItem.action, -1) }
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Surface(
                modifier = Modifier
                    //.aspectRatio(ratio = 1f)
                    .aspectRatioReference(
                        ratioWidth = 16f,
                        ratioHeight = 9f,
                        reference = AspectRatioReference.MIN_PARENT_WIDTH_PARENT_HEIGHT,
                    ),
                    //.fillMaxWidth(),
                color = menuItem.color,
                //shape = RoundedCornerShape(10.dp),
                //border = BorderStroke(2.dp, Blue007),
            ){}
            Text(
                text = menuItem.description,
                fontSize = 20.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                maxLines = 2,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Grey80)
                    .padding(vertical = 12.dp)
            )
        }
    }
}


//@Preview
@Composable
fun ImageMenuItemGrid01(
    menuItem: MenuItemModel = MenuItemModel(),
    itemClickCallback: HolderItemClickDelegate<MenuItemModel>? = null
) {
    Box(
        modifier = Modifier
            .clickable { itemClickCallback?.onHolderItemClicked(menuItem, menuItem.action, -1) }
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Box( // Child
                modifier = Modifier
                    .aspectRatioReference(
                        ratioWidth = 1f,
                        ratioHeight = 1f,
                        AspectRatioReference.MIN_PARENT_WIDTH_PARENT_HEIGHT
                    ),
                    //.align(Alignment.Center),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(menuItem.imageUrl)
                        .crossfade(true)
                        .build(),
                    // java.lang.IllegalArgumentException: Only VectorDrawables and rasterized asset types are supported ex. PNG, JPG, WEBP
                    //cannot use xml drawable!!
                    //placeholder = painterResource(R.drawable.rect_gray_01),
                    contentDescription = stringResource(R.string.image_description),
                    //contentScale = ContentScale.Crop,
                    //modifier = Modifier.clip(CircleShape)
                    modifier = Modifier.fillMaxSize(),
                )
            }
//            AsyncImage(
//                model = ImageRequest.Builder(LocalContext.current)
//                    .data(menuItem.action)
//                    .crossfade(true)
//                    .build(),
//                // java.lang.IllegalArgumentException: Only VectorDrawables and rasterized asset types are supported ex. PNG, JPG, WEBP
//                //cannot use xml drawable!!
//                //placeholder = painterResource(R.drawable.rect_gray_01),
//                contentDescription = stringResource(R.string.image_description),
//                //contentScale = ContentScale.Crop,
//                //modifier = Modifier.clip(CircleShape)
//                modifier = Modifier.aspectRatio(ratio = 1.333f).fillMaxWidth(),
//            )

            Text(
                text = menuItem.description,
                fontSize = 20.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                maxLines = 2,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Grey80)
                    .padding(vertical = 12.dp)
            )
        }
    }
}


//@Preview
@Composable
fun ImageMenuItemGrid02(
    menuItem: MenuItemModel = MenuItemModel(),
    itemClickCallback: HolderItemClickDelegate<MenuItemModel>? = null
) {
    Box(
        modifier = Modifier
            .clickable { itemClickCallback?.onHolderItemClicked(menuItem, menuItem.action, -1) }
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Box( // Child
                modifier = Modifier
                    .aspectRatioReference(
                        ratioWidth = 5f,
                        ratioHeight = 3f,
                        AspectRatioReference.MIN_PARENT_WIDTH_PARENT_HEIGHT
                    ),
                //.align(Alignment.Center),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(menuItem.imageResId),
                    contentDescription = "Contact profile picture",
                )
            }
            Text(
                text = menuItem.description,
                fontSize = 20.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                maxLines = 2,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Grey80)
                    .padding(vertical = 12.dp)
            )
        }
    }
}


