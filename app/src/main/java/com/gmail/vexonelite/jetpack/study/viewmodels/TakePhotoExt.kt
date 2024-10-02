package com.gmail.vexonelite.jetpack.study.viewmodels


import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.io.File
import java.util.logging.Level
import java.util.logging.Logger


/**
 * * [Jetpack Compose: Display a Photo Picker](https://medium.com/@jpmtech/jetpack-compose-display-a-photo-picker-6bcb9b357a3a)
 *
 * * [Jetpack Compose + Take Photo and/or Select Image: Simplifying Image Handling in Android Apps](https://medium.com/@jerry.cho.dev/jetpack-compose-take-photo-and-or-select-image-simplifying-image-handling-in-android-apps-21fca396481a)
 */
@Composable
fun photoSelectionAndTakingPhotoSample() {
    val context = LocalContext.current

    val takePhotoUri: MutableState<Uri?> = remember { mutableStateOf<Uri?>(null) }
    val takePhotoLauncher: ManagedActivityResultLauncher<Uri, Boolean> = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { isSaved: Boolean ->
            Logger.getLogger("photoSelectionAndTakingPhotoSample").log(Level.INFO, "photoSelectionAndTakingPhotoSample - takePhotoLauncher() - onResult - isSaved : [$isSaved]")
        }
    )

    val selectedImageUris: MutableState<List<Uri>> = remember {
        mutableStateOf<List<Uri>>(emptyList())
    }
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri: Uri? ->
            uri?.let { photoUri: Uri ->
                Logger.getLogger("photoSelectionAndTakingPhotoSample").log(Level.INFO, "photoSelectionAndTakingPhotoSample - singlePhotoPickerLauncher() - photoUri : [${photoUri.path}]")
                selectedImageUris.value = listOf(photoUri)
            }
        }
    )

    // I will start this off by saying that I am still learning Android development:
    // We are tricking the multiple photos picker here which is probably not the best way,
    // if you know of a better way to implement this feature drop a comment and let me know
    // how to improve this design
    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(maxItems = 4),
        onResult = { photoUris: List<Uri> ->
            Logger.getLogger("photoSelectionAndTakingPhotoSample").log(Level.INFO, "photoSelectionAndTakingPhotoSample - multiplePhotoPickerLauncher() - photoUris.size : [${photoUris.size}]")
            selectedImageUris.value = photoUris
            photoUris.forEach { photoUri: Uri ->
                Logger.getLogger("photoSelectionAndTakingPhotoSample").log(Level.INFO, "photoSelectionAndTakingPhotoSample - multiplePhotoPickerLauncher() - photoUri : [${photoUri.path}]")
            }
        }
    )

    // trigger take photo event
    takePhotoIfPossible(context, takePhotoUri, takePhotoLauncher)

    // trigger selecting multiple photos event
    multiplePhotoPickerLauncher.launch(
        PickVisualMediaRequest(
            ActivityResultContracts.PickVisualMedia.ImageOnly
        )
    )
}

fun takePhotoIfPossible(
    context: Context,
    takePhotoUri: MutableState<Uri?>,
    takePhotoLauncher: ManagedActivityResultLauncher<Uri, Boolean>
) {
    if (!context.hasTakePhotoPermissionBeenGranted()) { return }

    context.getOutputFileNameR("", "jpg", "NPTC_COURT", Environment.DIRECTORY_DCIM)
        .then { photoFileName: String ->
            Logger.getLogger("takePhotoIfPossible").log(Level.INFO, "takePhotoIfPossible() - photoFileName: [$photoFileName]")
            photoFileName.createFileWithCatch()
        }
        .then { photoFile: File ->
            context.getFileUriViaFileProvider(
                context.getFileProviderAuthority(), photoFile)
        }
        .then { photoUri: Uri ->
            takePhotoUri.value = photoUri
            takePhotoLauncher.launch(photoUri)
            FmApiResult.Success(0)
        }
}

