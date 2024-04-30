package com.gmail.vexonelite.jetpack.study.viewmodels


import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.vexonelite.jetpack.study.ui.theme.DarkerGray
import com.gmail.vexonelite.jetpack.study.ui.theme.HoloBlueLight
import com.gmail.vexonelite.jetpack.study.ui.theme.HoloGreenLight
import com.gmail.vexonelite.jetpack.study.ui.theme.HoloOrangeLight
import com.gmail.vexonelite.jetpack.study.ui.theme.HoloPurple
import com.gmail.vexonelite.jetpack.study.ui.theme.HoloRedLight
//import kotlinx.collections.immutable.ImmutableList
//import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import java.util.logging.Level
import java.util.logging.Logger


class ListViewModel : ViewModel() {
    val articles = listOf<Article>(
        Article(name = "Article 1"),
        Article(name = "Article 2", contentType = 2),
        Article(name = "Article 3"),
        Article(name = "Article 4", contentType = 2),
        Article(name = "Article 5"),
        Article(name = "Article 6", contentType = 2),
        Article(name = "Article 7"),
        Article(name = "Article 8", contentType = 2),
        Article(name = "Article 9"),
        Article(name = "Article 10", contentType = 2),
        Article(name = "Article 11"),
        Article(name = "Article 12", contentType = 2),
        Article(name = "Article 13"),
        Article(name = "Article 14", contentType = 2),
        Article(name = "Article 15"),
        Article(name = "Article 16", contentType = 2),
        Article(name = "Article 17"),
        Article(name = "Article 18", contentType = 2),
        Article(name = "Article 19"),
        Article(name = "Article 20", contentType = 2)
    )

    private val _dynamicArticles = MutableStateFlow<List<Article>>(emptyList())
    val dynamicArticles = _dynamicArticles.asStateFlow()

    private val _number = MutableStateFlow("")
    val number: StateFlow<String> = _number.asStateFlow()

    init {
        viewModelScope.launch {
            _dynamicArticles.value = articles.subList(0, 10)
        }
        Logger.getLogger("ListViewModel").log(Level.INFO, "init()")
    }

    fun addArticleToDynamicList() {
        viewModelScope.launch {
            _dynamicArticles.value = _dynamicArticles.value + articles[_dynamicArticles.value.size]
        }
    }

    fun numberChanged(newNumber: String) {
        viewModelScope.launch {
            _number.update { newNumber }
        }
    }



}


// Stable
data class Article(
    val id: String = UUID.randomUUID().toString(),
    val contentType: Int = 1,
    val name: String,
)


@Immutable
data class ArticleList(
    val articles: List<Article> // List = Unstable or Article = Unstable
)


// Generic Immutable Object List
@Immutable
data class ImmutableObjectList<T>(
    val objectList: List<T> // List = Unstable or Article = Unstable
)



