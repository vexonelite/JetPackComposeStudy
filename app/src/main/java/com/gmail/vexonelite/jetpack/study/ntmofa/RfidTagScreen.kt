package com.gmail.vexonelite.jetpack.study.ntmofa


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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.gmail.vexonelite.jetpack.study.HolderItemClickDelegate
import tw.com.futaba.mis.android.compose.Grey80
import tw.com.futaba.mis.android.compose.ImmutableObjectList
import tw.com.futaba.mis.android.compose.theAppButtonColor01
import com.gmail.vexonelite.jetpack.study.viewmodels.Article
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 8.dp, end = 8.dp),
            //horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {},
                enabled = true,
                shape = ButtonDefaults.shape, // | elevatedShape | outlinedShape | textShape
                colors = tw.com.futaba.mis.android.compose.theAppButtonColor01(),
            ) {
                Text(
                    text = "列印資料",
                    fontSize = 20.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 0.dp, horizontal = 20.dp)
                )
            }

            Button(
                onClick = {},
                enabled = true,
                shape = ButtonDefaults.shape, // | elevatedShape | outlinedShape | textShape
                colors = tw.com.futaba.mis.android.compose.theAppButtonColor01(),
            ) {
                Text(
                    text = "登入2",
                    fontSize = 20.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 0.dp, horizontal = 20.dp)
                )
            }
        }


        Text(
            text = "共${rfidTagList.size}件",
            fontSize = 20.sp,
            color = Color.Black,
            textAlign = TextAlign.End,
            maxLines = 2,
            modifier = Modifier
                .fillMaxWidth()
                .background(tw.com.futaba.mis.android.compose.Grey80)
                .padding(vertical = 8.dp)
        )

        LazyColumn() {
            items(
                items = tw.com.futaba.mis.android.compose.ImmutableObjectList<RfidTagModel>(
                    rfidTagList
                ).objectList,
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
                .background(tw.com.futaba.mis.android.compose.Grey80)
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
                .background(tw.com.futaba.mis.android.compose.Grey80)
                .padding(vertical = 4.dp)
        )
    }

}


