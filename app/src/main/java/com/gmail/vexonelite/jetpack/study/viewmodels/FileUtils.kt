package com.gmail.vexonelite.jetpack.study.viewmodels


import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.logging.Level
import java.util.logging.Logger


fun Context.getFileProviderAuthority(): String = this.applicationContext.packageName + ".fileprovider"

/**
 * You should declare and setup your own FileProvider properly before involving the method!
 */
fun Context.getFileUriViaFileProvider(fileProviderAuthority: String, file: File): FmApiResult<Uri> =
    try {
        val uri = FileProvider.getUriForFile(this.applicationContext, fileProviderAuthority, file)
        FmApiResult.Success(uri)
    } catch (cause: Throwable) {
        Logger.getLogger("Context Ktx").log(Level.SEVERE, "getFileUriViaFileProvider() - error on FileProvider.getUriForFile()", cause)
        FmApiResult.Error(FmRuntimeException(cause, IoErrorCodes.FILE_PROVIDER_GET_URI_FAILURE))
    }


fun String.createFileWithCatch(): FmApiResult<File> =
    try {
        val file = File(this)
        Logger.getLogger("String Ktx").log(Level.INFO, "createFileWithCatch() - File: [$file]")
        FmApiResult.Success(file)
    } catch (cause: Throwable) {
        Logger.getLogger("String Ktx").log(Level.SEVERE, "createFileWithCatch() - error on File(fileName)", cause)
        FmApiResult.Error(FmRuntimeException(cause, "89982"))
    }


fun Context.getOutputFolderR(
    folderName: String = "",
    environmentFolder: String = "", // Environment.DIRECTORY_DOWNLOADS / Environment.DIRECTORY_DOCUMENTS
): FmApiResult<File> =
    if (Build.VERSION.SDK_INT >= 29) {
        FmApiResult.Success(
            File(this.getExternalFilesDir(environmentFolder), folderName)
        )
    }
    else {
        // To be safe, you should check if the SDCard is mounted
        // by using Environment.getExternalStorageState() before doing this.
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED, ignoreCase = true)) {
            FmApiResult.Success(
                File(Environment.getExternalStoragePublicDirectory(environmentFolder), folderName)
            )
        }
        else {
            FmApiResult.Error(
                FmRuntimeException("Environment.getExternalStorageState() != [Environment.MEDIA_MOUNTED]!!", BaseErrorCodes.ILLEGAL_ARGUMENT_ERROR)
            )
        }
    }


fun Context.getOutputFileNameR(
    fileName: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date()),
    fileExtension: String = "",
    folderName: String = "",
    environmentFolder: String = "", // Environment.DIRECTORY_DOWNLOADS / Environment.DIRECTORY_DOCUMENTS
): FmApiResult<String> =
    this.getOutputFolderR(folderName, environmentFolder)
        .then { mediaStorageDir: File ->
            // Create the storage directory if it does not exist
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    Logger.getLogger("Context Ktx").log(Level.WARNING, "getOutputFileName() - failed to create the directory!")
                    FmApiResult.Error<String>(
                        FmRuntimeException("getOutputFileName() - failed to create the directory!!", BaseErrorCodes.INTERNAL_GENERATION_ERROR)
                    )
                }
            }
            FmApiResult.Success(mediaStorageDir)
        }
        .then { mediaStorageDir: File ->
            // Create a media file name
            val fileNameX = fileName.ifEmpty {
                SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            }

            if (fileExtension.isEmpty()) {
                FmApiResult.Success(mediaStorageDir.path + File.separator + fileNameX)
            }
            else {
                FmApiResult.Success(mediaStorageDir.path + File.separator + fileNameX + "." + fileExtension)
            }
        }
