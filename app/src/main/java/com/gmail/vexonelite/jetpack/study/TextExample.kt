package com.gmail.vexonelite.jetpack.study


import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.logging.Level
import java.util.logging.Logger


/**
 * [Ref](https://jetpackcompose.cn/docs/elements/text/)
 */
@Composable
fun TextDemo01() {
    Column{
        Text(
            text = "你好呀陌生人，这是一个标题",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text ="你好呀陌生人，我是内容",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun TextDemo02() {
    val style = TextStyle(
        fontWeight = FontWeight.W900,
        fontSize = 20.sp,
        letterSpacing = 7.sp
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "你好呀陌生人，这是一个标题",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text ="你好呀陌生人，我是内容",
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = "你好陌生人",
            style = style
        )
    }
}


@Composable
fun TextDemo03() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "两面包夹芝士".repeat(15),
        )
        Spacer(Modifier.padding(vertical = 15.dp))
        Text(
            text = "两面包夹芝士".repeat(15),
            lineHeight = 30.sp
        )
    }
}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TextDemo04() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "1 你好呀陌生人，这是一个标题，不是很长，因为我想不出其他什么比较好的标题了",
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "2 你好呀陌生人，这是一个标题，不是很长，因为我想不出其他什么比较好的标题了",
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
            overflow = TextOverflow.Clip
        )
        Text(
            text = "3 你好呀陌生人，这是一个标题，不是很长，因为我想不出其他什么比较好的标题了",
            modifier = Modifier.basicMarquee(),  // have the text to scroll automatically (跑馬燈)
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
            overflow = TextOverflow.Clip
        )
        Text(
            text ="你好呀陌生人，我是内容",
            style = MaterialTheme.typography.bodySmall
        )

    }
}

@Composable
fun TextDemo05() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "每天摸鱼",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )
        Text(
            text = "这好吗",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = "这非常的好",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Right
        )
    }
}


@Composable
fun TextDemo05_1() {
    SelectionContainer {
        TextDemo05()
    }
}


@Composable
fun TextDemo06() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text("Hello World", fontFamily = FontFamily.Serif)
        Text("Hello World", fontFamily = FontFamily.SansSerif)
        Text("Hello World", fontFamily = FontFamily.Cursive)
    }
}


@Composable
fun TextDemo07() {
    val context = LocalContext.current
    Text(
        text = "确认编辑",
        modifier = Modifier.clickable(
            onClick = {
                Toast.makeText(context, "你点击了此文本", Toast.LENGTH_LONG).show()
            },
        )
    )
}


@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun TextDemo07_1() {
    val context = LocalContext.current
    Text(
        text = "确认编辑",
        modifier = Modifier.clickable(
            onClick = {
                Toast.makeText(context, "你点击了此文本", Toast.LENGTH_LONG).show()
            },
            indication = null,
            interactionSource = MutableInteractionSource()
        )
    )
}


@Composable
fun TextDemo08() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            buildAnnotatedString {
                append("你现在观看的章节是 ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.W900)) {
                    append("Text")
                }
            }
        )
    }
}


@Composable
fun TextDemo08_1() {

    val text = buildAnnotatedString {
        append("勾选即代表同意")
        withStyle(
            style = SpanStyle(
                color = Color(0xFF0E9FF2),
                fontWeight = FontWeight.Bold
            )
        ) {
            append("用户协议")
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ClickableText(
            text = text,
            onClick = { offset ->
                Logger.getLogger("WTF").log(Level.INFO, "Hi，你按到了第 $offset 位的文字")
            }
        )
    }
}


@Composable
fun TextDemo08_2() {

    val text = buildAnnotatedString {
        append("勾选即代表同意")
        pushStringAnnotation(
            tag = "tag",
            annotation = "一个用户协议啦啦啦，内容内容"
        )
        withStyle(
            style = SpanStyle(
                color = Color(0xFF0E9FF2),
                fontWeight = FontWeight.Bold
            )
        ) {
            append("用户协议")
        }
        pop()
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ClickableText(
            text = text,
            onClick = { offset ->
                text.getStringAnnotations(
                    tag = "tag", start = offset, end = offset
                ).firstOrNull()?.let { annotation ->
                    Logger.getLogger("WTF").log(Level.INFO, "你已经点到 ${annotation.item} 啦")
                }
            }
        )
    }
}


@Composable
fun TextDemo09() {
    Column{
        Text("123")
        Text("456")
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text("789")
        }
    }
}


/**
 * [Ref](https://medium.com/@andyphiri92/using-text-in-jetpack-compose-0fb812118809)
 */
@Composable
fun TextSampleCompose(modifier: Modifier = Modifier) {
    Text(
        text = "Hello Android Compose!",
        //modifier = modifier,
        color = Color.Magenta,

        //fontSize = TextUnit(18f, TextUnitType.Sp),
        fontSize = 20.sp,
        //fontSize = 20.dp, // only accept sp here, dp will raise error

        //fontStyle = FontStyle.Italic,
        //fontStyle = FontStyle.Normal,

        fontWeight = FontWeight.ExtraBold,
        fontFamily = FontFamily.Cursive,
        textDecoration = TextDecoration.LineThrough,    // strikethrough text
        //textDecoration = TextDecoration.Underline,      // underline  text

        //style = MaterialTheme.typography.titleLarge,
        //style = MaterialTheme.typography.bodySmall
        style = TextStyle(
            //color = Color.Blue,
            fontWeight = FontWeight.W900,   // Bold
            fontSize = 20.sp,               // only accept sp here, dp will raise error
            letterSpacing = 7.sp            // only accept sp here, dp will raise error
        ),

        maxLines = 1,
        overflow = TextOverflow.Ellipsis,

        modifier = Modifier.fillMaxWidth(),
        //textAlign = TextAlign.Left,
        //textAlign = TextAlign.Center,
        textAlign = TextAlign.Right,

    )
}