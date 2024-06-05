package com.gmail.vexonelite.jetpack.study.ntmofa


import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gmail.vexonelite.jetpack.study.HolderItemClickDelegate
import com.gmail.vexonelite.jetpack.study.R
import com.gmail.vexonelite.jetpack.study.screens.ColorMenuItemGrid01
import com.gmail.vexonelite.jetpack.study.screens.TextCenterScreenContent
import com.gmail.vexonelite.jetpack.study.ui.theme.DarkerGray
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey80
import com.gmail.vexonelite.jetpack.study.ui.theme.HoloBlueLight
import com.gmail.vexonelite.jetpack.study.ui.theme.HoloGreenLight
import com.gmail.vexonelite.jetpack.study.ui.theme.HoloOrangeLight
import com.gmail.vexonelite.jetpack.study.ui.theme.HoloPurple
import com.gmail.vexonelite.jetpack.study.ui.theme.HoloRedLight
import com.gmail.vexonelite.jetpack.study.ui.theme.ImmutableObjectList
import com.gmail.vexonelite.jetpack.study.viewmodels.Article
import com.gmail.vexonelite.jetpack.study.viewmodels.AspectRatioReference
import com.gmail.vexonelite.jetpack.study.viewmodels.MenuItemModel
import com.gmail.vexonelite.jetpack.study.viewmodels.aspectRatioReference
import java.util.UUID
import java.util.logging.Level
import java.util.logging.Logger


data class RfidTagModel(
    /** 登錄號 */
    val theGalleryCode: String = UUID.randomUUID().toString(),
    /** 登錄號 */
    val theEPC: String = "",
    val description: String = "Test",
)


fun generateTestRfidTagList1(): List<RfidTagModel> {
    val uuid = UUID.randomUUID().toString()
    return listOf<RfidTagModel>(
        RfidTagModel(
            theGalleryCode = "{$uuid}_1",
            theEPC = "javascript-cannot-find-module",
            description = "AAAAA",
        ),
        RfidTagModel(
            theGalleryCode = "{$uuid}_2",
            theEPC = "javascript-cannot-find-module",
            description = "BBBBB",
        ),
        RfidTagModel(
            theGalleryCode = "{$uuid}_3",
            theEPC = "javascript-cannot-find-module",
            description = "CCCCC",
        ),
        RfidTagModel(
            theGalleryCode = "{$uuid}_4",
            theEPC = "javascript-cannot-find-module",
            description = "DDDDD",
        ),
        RfidTagModel(
            theGalleryCode = "{$uuid}_5",
            theEPC = "javascript-cannot-find-module",
            description = "EEEEE",
        ),
        RfidTagModel(
            theGalleryCode = "{$uuid}_6",
            theEPC = "javascript-cannot-find-module",
            description = "FFFFF",
        ),
    )
}


@Preview
@Composable
fun RfidTagScreenContent(
) {
    val context = LocalContext.current
    val rfidTagList = generateTestRfidTagList1()

    val itemClickCallback = HolderItemClickDelegate<RfidTagModel> { dataObject, action, position ->
        Logger.getLogger("RfidTagScreenContent").log(Level.INFO, "itemClickCallback - delegate: [${dataObject.description}], action: [$action], position: [$position]")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "共${rfidTagList.size}件",
            fontSize = 20.sp,
            color = Color.Black,
            textAlign = TextAlign.End,
            maxLines = 2,
            modifier = Modifier
                .fillMaxWidth()
                .background(Grey80)
                .padding(vertical = 8.dp)
        )

        LazyColumn() {
            items(
                items = ImmutableObjectList<RfidTagModel>(rfidTagList).objectList,
                key = { rfidTagModel: RfidTagModel -> rfidTagModel.theGalleryCode },
            ) { rfidTagModel: RfidTagModel ->
                RfidTagItem(rfidTagModel, itemClickCallback)
            }
        }
    }

}



@Preview
@Composable
fun ArticleItem2(
    article: Article = Article(),
    itemClickCallback: HolderItemClickDelegate<Article>? = null
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clickable { itemClickCallback?.onHolderItemClicked(article, "TAP", -1) }
                .padding(8.dp)
        ) {
            Text(
                text = article.name,
                color = Color.Magenta,
                fontSize = 16.sp
            )
        }

    }
}





@Preview
@Composable
fun RfidTagItem(
    rfidTagModel: RfidTagModel = RfidTagModel(),
    itemClickCallback: HolderItemClickDelegate<RfidTagModel>? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "登錄號: ${rfidTagModel.theGalleryCode}",
            fontSize = 20.sp,
            color = Color.Black,
            textAlign = TextAlign.Start,
            maxLines = 2,
            modifier = Modifier
                .fillMaxWidth()
                .background(Grey80)
                .padding(vertical = 12.dp)
        )
        Text(
            text = "EPC: ${rfidTagModel.theEPC}",
            fontSize = 20.sp,
            color = Color.Black,
            textAlign = TextAlign.Start,
            maxLines = 2,
            modifier = Modifier
                .fillMaxWidth()
                .background(Grey80)
                .padding(vertical = 4.dp)
        )
    }

}


