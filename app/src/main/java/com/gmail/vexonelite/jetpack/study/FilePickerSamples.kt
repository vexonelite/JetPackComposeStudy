package com.gmail.vexonelite.jetpack.study


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.io.BufferedReader
import java.io.InputStreamReader


fun getPdfMimeType(): String = "application/pdf"

//fun getCsvMimeType(): String = "text/csv" // not work
fun getCsvMimeType(): String = "text/comma-separated-values"

fun getTxtMimeType(): String = "text/plain"


@Composable
fun FilePicker() {
    val context = LocalContext.current
    var fileContent by remember { mutableStateOf<String?>(null) }
    var fileType by remember { mutableStateOf<String?>(null) }

    val intentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { activityResult: ActivityResult ->
            val uri = activityResult.data?.data
            uri?.let {
                val mimeType = context.contentResolver.getType(it)
                fileType = mimeType
                fileContent = readFileFromUri(context, it, mimeType)
            }
        }
    )

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri: Uri? ->
            uri?.let {
                val mimeType = context.contentResolver.getType(it)
                fileType = mimeType
                fileContent = readFileFromUri(context, it, mimeType)
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Row(
          Modifier.fillMaxWidth()
              .wrapContentHeight()
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    filePickerLauncher.launch(
                        arrayOf(
                            getCsvMimeType(), getTxtMimeType(), getPdfMimeType(),
                        )
                    )
                },
            ) {
                Text("FilePicker1")
            }

            Spacer(modifier = Modifier.padding(horizontal = 10.dp))

            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                        addCategory(Intent.CATEGORY_OPENABLE)
                        type = getCsvMimeType()
                    }
                    intentLauncher.launch(intent)
                },
            ) {
                Text("FilePicker2")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        fileType?.let {
            Text("File Type: $it")
        }

        fileContent?.let {
            Text("File Content:")
            Text(it)
        }
    }
}

private fun readFileFromUri(context: Context, uri: Uri, mimeType: String?): String? {
    return when (mimeType) {
        getCsvMimeType(), getTxtMimeType() -> {
            context.contentResolver.openInputStream(uri)?.use { inputStream ->
                BufferedReader(InputStreamReader(inputStream)).use { reader ->
                    reader.readText()
                }
            }
        }
        getPdfMimeType() -> { "PDF files cannot be displayed as text."
        }
        else -> { "Unsupported file type." }
    }
}

