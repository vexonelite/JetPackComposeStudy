package com.gmail.vexonelite.jetpack.study.screens


import android.content.Context
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey94
import com.gmail.vexonelite.jetpack.study.ui.theme.ImmutableObjectList
import com.gmail.vexonelite.jetpack.study.ui.theme.theBuiltInButtonColor01
import com.gmail.vexonelite.jetpack.study.viewmodels.AspectRatioReference
import com.gmail.vexonelite.jetpack.study.viewmodels.BuiltInTopAppBarScreenContent
import com.gmail.vexonelite.jetpack.study.viewmodels.aspectRatioReference
import com.gmail.vexonelite.jetpack.study.viewmodels.generateRandomStringViaUuid
import com.gmail.vexonelite.jetpack.study.viewmodels.takePhotoIfPossible
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import java.util.logging.Level
import java.util.logging.Logger


@Composable
fun TakePhotoOrImageSelectionScreen(
) {
    Logger.getLogger("TakePhotoOrImageSelectionScreen").log(Level.INFO, "TakePhotoOrImageSelectionScreen")

    val context: Context = LocalContext.current

    val takePhotoOrImageSelectionParameters: TakePhotoOrImageSelectionParameters = rememberTakePhotoOrImageSelectionParameters()

    BuiltInTopAppBarScreenContent(
        topAppBarTitle = "Image Demo",
//        navigationIcon = {
//            TopAppBarNavigation01(
//                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                onClick = {
//                    navController.popBackStack(
//                        //route = "", inclusive = true, saveState = true,
//                    )
//                },
//            )
//        },
        bodyContent = {
            TakePhotoOrImageSelectionScreenContent(
                theParameters = takePhotoOrImageSelectionParameters,

                )
        }
    )
}


@Preview
@Composable
fun TakePhotoOrImageSelectionScreenContent(
    theParameters: TakePhotoOrImageSelectionParameters = TakePhotoOrImageSelectionParameters(),
) {
    Logger.getLogger("TakePhotoOrImageSelectionScreenContent").log(Level.INFO, "TakePhotoOrImageSelectionScreenContent")

    val context = LocalContext.current

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp),
    ) {
        val (previewRowRef, bottomRowRef) = createRefs()

        LazyRow(
            modifier = Modifier
                .constrainAs(previewRowRef) {
                    linkTo(start = parent.start, end = parent.end, bias = 0f)
                    top.linkTo(parent.top, margin = 10.dp)
                    //linkTo(top = parent.top, bottom = bottomRowRef.top, bias = 0f, topMargin = 10.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.value(300.dp)
                }
        ) {
            val itemList: List<ImageUriModel> = theParameters.selectedImageUris
                .map { entry: Map.Entry<String, ImageUriModel> -> entry.value }
                .sortedBy { imageUri: ImageUriModel -> imageUri.theAddedTime }

            items(
                items = ImmutableObjectList<ImageUriModel>(itemList).objectList,
                key = { imageItem: ImageUriModel -> imageItem.theIdentifier },
            ) { imageItem: ImageUriModel ->
                Box( // Child
                    modifier = Modifier
                        .aspectRatioReference(
                            ratioWidth = 1f,
                            ratioHeight = 1f,
                            AspectRatioReference.MIN_PARENT_WIDTH_PARENT_HEIGHT
                        )
                        .background(Grey94),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        //model = imageItem.theUri,
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imageItem.theUri)
                            .crossfade(true)
                            .build(),
                        contentDescription = imageItem.theDescription,
                        modifier = Modifier.fillMaxSize(),
                        //.clip(CircleShape),
                        //contentScale = ContentScale.Fit // ContentScale.Crop,
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .constrainAs(bottomRowRef) {
                    linkTo(start = parent.start, end = parent.end, bias = 0f)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                    //linkTo(top = previewRowRef.bottom, bottom = parent.bottom, bias = 1f, topMargin = 10.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                }
        ) {
            Button(
                modifier = Modifier.weight(1 / 3f),
                onClick = theParameters.clearOnClick,
                enabled = true,
                shape = ButtonDefaults.shape, // | elevatedShape | outlinedShape | textShape
                colors = theBuiltInButtonColor01(),
                contentPadding = PaddingValues(horizontal = 4.dp, vertical = 12.dp),
            ) {
                Icon(imageVector = Icons.Filled.Clear, contentDescription = null, tint = Color.White)

                Spacer(modifier = Modifier.padding(horizontal = 4.dp))

                Text(
                    text = "清空",
                    fontSize = 20.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 0.dp, horizontal = 10.dp)
                )
            }

            Spacer(modifier = Modifier.padding(horizontal = 6.dp))

            Button(
                modifier = Modifier.weight(1 / 3f),
                onClick = theParameters.takePhotoOnClick,
                enabled = true,
                shape = ButtonDefaults.shape, // | elevatedShape | outlinedShape | textShape
                colors = theBuiltInButtonColor01(),
                contentPadding = PaddingValues(horizontal = 4.dp, vertical = 12.dp),
            ) {
                Icon(imageVector = Icons.Filled.AddAPhoto, contentDescription = null, tint = Color.White)

                Spacer(modifier = Modifier.padding(horizontal = 4.dp))

                Text(
                    text = "拍照",
                    fontSize = 20.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 0.dp, horizontal = 10.dp)
                )
            }

            Spacer(modifier = Modifier.padding(horizontal = 6.dp))

            Button(
                modifier = Modifier.weight(1 / 3f),
                onClick = theParameters.imageSelectionOnClick,
                enabled = true,
                shape = ButtonDefaults.shape, // | elevatedShape | outlinedShape | textShape
                colors = theBuiltInButtonColor01(),
                contentPadding = PaddingValues(horizontal = 4.dp, vertical = 12.dp),
            ) {
                Icon(imageVector = Icons.Filled.AddPhotoAlternate, contentDescription = null, tint = Color.White)

                Spacer(modifier = Modifier.padding(horizontal = 4.dp))

                Text(
                    text = "挑選圖片",
                    fontSize = 20.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 0.dp, horizontal = 10.dp)
                )
            }
        }
    }
}


@Composable
fun rememberTakePhotoOrImageSelectionParameters(
): TakePhotoOrImageSelectionParameters {
    val context: Context = LocalContext.current
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    // <editor-fold desc="selectedImageUris">
    val selectedImageUris: MutableState<Map<String, ImageUriModel>> = remember {
        mutableStateOf<Map<String, ImageUriModel>>(emptyMap())
    }
    // </editor-fold desc="selectedImageUris">

    // <editor-fold desc="takePhotoUri">
    val takePhotoUri: MutableState<Uri?> = remember { mutableStateOf<Uri?>(null) }
    // </editor-fold desc="takePhotoUri">

    // <editor-fold desc="takePhotoLauncher">
    val takePhotoLauncher: ManagedActivityResultLauncher<Uri, Boolean> = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { isSaved: Boolean ->
            Logger.getLogger("rememberTakePhotoOrImageSelectionParameters").log(Level.INFO, "rememberTakePhotoOrImageSelectionParameters - takePhotoLauncher() - onResult - isSaved : [$isSaved]")
            val photoUri: Uri? = takePhotoUri.value
            if (isSaved && (null != photoUri) && (null != photoUri.path) && (selectedImageUris.value.size < 4)) {
                val updatedMap = mutableMapOf<String, ImageUriModel>()
                updatedMap.putAll(selectedImageUris.value)
                val photoPath: String = photoUri.path!!
                Logger.getLogger("rememberTakePhotoOrImageSelectionParameters").log(Level.INFO, "rememberTakePhotoOrImageSelectionParameters - takePhotoLauncher() - photoPath : [$photoPath]")
                updatedMap[photoPath] = ImageUriModel(photoUri)
                selectedImageUris.value = updatedMap
            }
        }
    )
    // </editor-fold desc="takePhotoLauncher">

    // <editor-fold desc="multiplePhotoPickerLauncher">
    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
        //contract = ActivityResultContracts.PickMultipleVisualMedia(maxItems = 4 - selectedImageUris.value.size),
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { photoUris: List<Uri> ->
            Logger.getLogger("rememberTakePhotoOrImageSelectionParameters").log(Level.INFO, "rememberTakePhotoOrImageSelectionParameters - multiplePhotoPickerLauncher() - photoUris.size : [${photoUris.size}]")

            val updatedMap = mutableMapOf<String, ImageUriModel>()
            updatedMap.putAll(selectedImageUris.value)

            photoUris.forEach { photoUri: Uri ->
                photoUri.path?.let { uriPath: String ->
                    if (updatedMap.size < 4) {
                        Logger.getLogger("rememberTakePhotoOrImageSelectionParameters").log(Level.INFO, "rememberTakePhotoOrImageSelectionParameters - multiplePhotoPickerLauncher() - photoUri : [${photoUri.path}]")
                        updatedMap[uriPath] = ImageUriModel(photoUri)
                    }
                }
            }

            selectedImageUris.value = updatedMap
        }
    )
    // </editor-fold desc="multiplePhotoPickerLauncher">

    return TakePhotoOrImageSelectionParameters(
        // <editor-fold desc="TakePhotoOrImageSelectionParameters - selectedImageUris">
        selectedImageUris = selectedImageUris.value,
        // </editor-fold desc="TakePhotoOrImageSelectionParameters - selectedImageUris">

        // <editor-fold desc="TakePhotoOrImageSelectionParameters - clearOnClick">
        clearOnClick = {
            coroutineScope.launch { selectedImageUris.value = emptyMap() }
        },
        // </editor-fold desc="TakePhotoOrImageSelectionParameters - clearOnClick">

        // <editor-fold desc="TakePhotoOrImageSelectionParameters - takePhotoOnClick">
        takePhotoOnClick = {
            if (selectedImageUris.value.size >= 4) { return@TakePhotoOrImageSelectionParameters }
            coroutineScope.launch {
                takePhotoIfPossible(context, takePhotoUri, takePhotoLauncher)
            }
        },
        // </editor-fold desc="TakePhotoOrImageSelectionParameters - takePhotoOnClick">

        // <editor-fold desc="TakePhotoOrImageSelectionParameters - imageSelectionOnClick">
        imageSelectionOnClick = {
            if (selectedImageUris.value.size >= 4) { return@TakePhotoOrImageSelectionParameters }

            coroutineScope.launch {
                multiplePhotoPickerLauncher.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            }
        },
        // </editor-fold desc="TakePhotoOrImageSelectionParameters - imageSelectionOnClick">
    )
}


data class TakePhotoOrImageSelectionParameters(
    val selectedImageUris: Map<String, ImageUriModel> = emptyMap(),
    val clearOnClick: () -> Unit = {},
    val takePhotoOnClick: () -> Unit = {},
    val imageSelectionOnClick: () -> Unit = {},
)


data class ImageUriModel(
    val theUri: Uri,
    val theAddedTime: Date = Calendar.getInstance().time,
    val theIdentifier: String = theUri.path?: generateRandomStringViaUuid(),
    val theCellType: Int = 301,
    val theDescription: String = theUri.path?: "",
    val theAction: String = "actiion_TAP"
)

