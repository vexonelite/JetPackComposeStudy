package com.gmail.vexonelite.jetpack.study


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



/**
 * https://foso.github.io/Jetpack-Compose-Playground/foundation/lazyverticalgrid/
 */
@Composable
fun LazyVerticalGridDemo01(){
    val list = (1..10).map { it.toString() }

    LazyVerticalGrid(
        //columns = GridCells.Adaptive(128.dp),
        columns = GridCells.Fixed(3),

        // content padding
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
        content = {
            items(list.size) { index ->
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                ) {
                    Text(
                        text = list[index],
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    )
}


/**
 * use span
 * * [Ref1](https://stackoverflow.com/questions/65981114/does-jetpack-composes-lazyverticalgrid-have-span-strategy)
 */
@Composable
fun LazyVerticalGridDemo02() {

    val names = listOf<String>("Alice", "Bob", "Cindy", "Doug", "Ernie", "Fred", "George", "Harris")
    val maxSpan = 3

    val lazyGridState = rememberLazyGridState()

    //val spanX: (LazyGridItemSpanScope) -> GridItemSpan = { GridItemSpan(CELL_COUNT) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(maxSpan),
        state = lazyGridState
    ) {
        items(
            names,
            span = { nameItem ->
                val lowercase = nameItem.lowercase()
                val span = if (lowercase.startsWith("a") || lowercase.lowercase().startsWith("b") || lowercase.lowercase().startsWith("d")) {
                    maxSpan
                }
                else { 1 }
                GridItemSpan(span)
            },
            //key = { article -> article.id },
            //contentType = { article -> article.contentType },
        ) { item ->
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(10.dp)
                .background(Color.Black) // like stroke
                .padding(2.dp) // stoke width
                .background(Color.White)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = item,
                    fontSize = 18.sp
                )
            }
        }
    }
}




