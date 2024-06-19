package com.gmail.vexonelite.jetpack.study.viewmodels


import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Color as GraphicsColor
import android.graphics.Paint
import androidx.annotation.ColorInt
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.util.logging.Level
import java.util.logging.Logger


// Complete Dark image
fun ImageBitmap.toAndroidBitmapExt(): FmApiResult<Bitmap> =
    try {
        val bitmap: Bitmap = this.asAndroidBitmap()
        FmApiResult.Success<Bitmap>(bitmap)
    }
    catch (cause: Exception) {
        Logger.getLogger("ImageBitmap Ktx").log(Level.SEVERE, "toAndroidBitmapExt() - error on ImageBitmap.asAndroidBitmap()", cause)
        FmApiResult.Error<Bitmap>(FmRuntimeException(cause, "5487"))
    }


fun ImageBitmap.toProcessedAndroidBitmapExt(
    @ColorInt signatureColor: Int = GraphicsColor.BLACK,
    @ColorInt backgroundColor: Int = GraphicsColor.WHITE,
): FmApiResult<Bitmap> =
    try {
        val rawBitmap: Bitmap = this.asAndroidBitmap()
        // Create a bitmap with white background
        val outputBitmap: Bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(outputBitmap)
        canvas.drawColor(backgroundColor)

        // Draw the signature onto the white background
        val paint = Paint().apply {
            color = signatureColor
        }
        canvas.drawBitmap(rawBitmap, 0f, 0f, paint)
        FmApiResult.Success<Bitmap>(outputBitmap)
    }
    catch (cause: Exception) {
        Logger.getLogger("ImageBitmap Ktx").log(Level.SEVERE, "toProcessedAndroidBitmapExt() - error on ImageBitmap.toProcessedAndroidBitmapExt()", cause)
        FmApiResult.Error<Bitmap>(FmRuntimeException(cause, "5487"))
    }


fun ImageBitmap.toInputStreamExt(
    @ColorInt signatureColor: Int = GraphicsColor.BLACK,
    @ColorInt backgroundColor: Int = GraphicsColor.WHITE,
): FmApiResult<InputStream> =
    toProcessedAndroidBitmapExt(signatureColor = signatureColor, backgroundColor = backgroundColor)
        .then { bitmap: Bitmap ->
            bitmap.toInputStreamExt()
        }


fun Bitmap.toInputStreamExt(
    compressFormat: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG, // Bitmap.CompressFormat.PNG
    quality: Int = 100
): FmApiResult<InputStream> =
    try {
        ByteArrayOutputStream().use { outputStream: ByteArrayOutputStream ->
            this.compress(compressFormat, quality, outputStream)
            val byteArray: ByteArray = outputStream.toByteArray()
            val byteArrayInputStream = ByteArrayInputStream(byteArray)
            FmApiResult.Success<InputStream>(byteArrayInputStream)
        }
    }
    catch (cause: Exception) {
        Logger.getLogger("Bitmap Ktx").log(Level.SEVERE, "toInputStreamExt() - error!!", cause)
        FmApiResult.Error<InputStream>(FmRuntimeException(cause, "5487"))
    }


fun Bitmap.resizeRefToWidthExt(newWidth: Int): Bitmap {
    val scaleFactor = newWidth.toFloat() / this.width
    return resizeExt(
        newWidth,
        (this.height * scaleFactor).toInt()
    )
}


fun Bitmap.resizeRefToWidthExtR(newWidth: Int): FmApiResult<Bitmap> =
    try {
        val scaleFactor = newWidth.toFloat() / this.width
        val scaledBitmap = resizeExt(
            newWidth,
            (this.height * scaleFactor).toInt()
        )
        FmApiResult.Success<Bitmap>(scaledBitmap)
    }
    catch (cause: Exception) {
        Logger.getLogger("Bitmap Ktx").log(Level.SEVERE, "resizeRefToWidthExtR() - error!!", cause)
        FmApiResult.Error<Bitmap>(FmRuntimeException(cause, "5487"))
    }


fun Bitmap.resizeExt(newWidth: Int, newHeight: Int): Bitmap {
    val matrix = Matrix()
    matrix.postScale(
        (newWidth.toFloat() / this.width.toFloat()),
        (newHeight.toFloat() / this.height.toFloat())
    )
    return Bitmap.createBitmap(this, 0, 0, this.width, this.height, matrix, true)
}


fun Bitmap.resizeExtR(newWidth: Int, newHeight: Int): FmApiResult<Bitmap> =
    try {
        val scaledBitmap = resizeExt(newWidth, newHeight)
        FmApiResult.Success<Bitmap>(scaledBitmap)
    }
    catch (cause: Exception) {
        Logger.getLogger("Bitmap Ktx").log(Level.SEVERE, "resizeExtR() - error!!", cause)
        FmApiResult.Error<Bitmap>(FmRuntimeException(cause, "5487"))
    }


fun Bitmap.saveToFileViaOutputStreamExt(
    outputStream: OutputStream,
    compressFormat: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG, // Bitmap.CompressFormat.PNG
    quality: Int = 100
): FmApiResult<Int> =
    try {
        Logger.getLogger("OutputStream Ktx").log(Level.INFO, "saveToFileViaOutputStreamExt() - Bitmap - w: [${this.width}], h: [${this.height}]")
        outputStream.use { self: OutputStream ->
            // Save the new Bitmap as a file
            this.compress(compressFormat, quality, self)
            FmApiResult.Success(0)
        }
    }
    catch (cause: Exception) {
        Logger.getLogger("OutputStream Ktx").log(Level.SEVERE, "error on saveToFileViaOutputStreamExt()", cause)
        FmApiResult.Error(FmRuntimeException(cause, "54088"))
    }


