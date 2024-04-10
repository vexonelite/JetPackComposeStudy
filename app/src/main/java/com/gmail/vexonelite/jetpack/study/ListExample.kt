package com.gmail.vexonelite.jetpack.study



import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gmail.vexonelite.jetpack.study.viewmodels.Article
import com.gmail.vexonelite.jetpack.study.viewmodels.ArticleList
import com.gmail.vexonelite.jetpack.study.viewmodels.ListViewModel
import java.util.logging.Level
import java.util.logging.Logger


@Composable
fun ListColumnDemo01(viewModel: ListViewModel = viewModel()) {
    LazyColumn() {
        items(
            items = viewModel.articles,
            key = { article -> article.id },
        ) { article ->
            Text(text = article.name)
        }
    }
}


@Composable
fun StableListColumn01(articleList: ArticleList, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(
            articleList.articles,
        ) { article ->
            Text(text = article.name)
        }
    }
}



@Composable
fun StableListColumn02(articleList: ArticleList, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(
            items = articleList.articles,
            key = { article -> article.id },
        ) { article ->
            Text(text = article.name)
        }
    }
}


@Composable
fun ArticleItem1(article: Article, itemClickCallback: HolderItemClickDelegate<Article>? = null) {
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


@Composable
fun ArticleItemDefault(article: Article, itemClickCallback: HolderItemClickDelegate<Article>? = null) {
    Box(
        modifier = Modifier
            .clickable { itemClickCallback?.onHolderItemClicked(article, "DEFAULT", -1) }
            .padding(8.dp)
    ) {
        Text(
            text = article.name,
            modifier = Modifier.padding(vertical = 6.dp)
        )
    }
}


@Composable
fun StableListColumn03(articleList: ArticleList, modifier: Modifier = Modifier, itemClickCallback: HolderItemClickDelegate<Article>? = null) {
    LazyColumn(modifier = modifier) {
        items(
            items = articleList.articles,
            key = { article -> article.id },
            contentType = { article -> article.contentType },
        ) { article ->
            when(article.contentType) {
                1 -> { ArticleItem1(article, itemClickCallback) }
                else -> { ArticleItemDefault(article, itemClickCallback) }
            }
        }
    }
}

@Composable
fun ListColumn01(modifier: Modifier = Modifier, viewModel: ListViewModel = viewModel()) {
    val articleList = ArticleList(viewModel.articles)
    StableListColumn01(articleList, modifier)
}

@Composable
fun ListColumn02(modifier: Modifier = Modifier, viewModel: ListViewModel = viewModel()) {
    val articleList = ArticleList(viewModel.articles)
    StableListColumn02(articleList, modifier)
}

@Composable
fun ListColumn03(modifier: Modifier = Modifier, itemClickCallback: HolderItemClickDelegate<Article>? = null, viewModel: ListViewModel = viewModel()) {
    val articleList = ArticleList(viewModel.articles)
    StableListColumn03(articleList, modifier, itemClickCallback)
}



fun interface HolderItemClickDelegate<T> {
    fun onHolderItemClicked(dataObject: T, action: String, position: Int)
}



@Composable
fun ListColumnDemo02() {
    val itemClickCallback = HolderItemClickDelegate<Article> { dataObject, action, position ->
        Logger.getLogger("ListColumnDemo02").log(Level.INFO, "itemClickCallback - delegate: [$dataObject], action: [$action], position: [$position]")
        when(action) {
            "TAP" -> {}
            "DEFAULT" -> {}
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp),
        //horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ListColumn03(modifier = Modifier.weight(1 / 2f), itemClickCallback = itemClickCallback)
        ListColumn03(modifier = Modifier.weight(1 / 2f), itemClickCallback = itemClickCallback)
    }
}













