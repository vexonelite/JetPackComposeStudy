package com.gmail.vexonelite.jetpack.study.viewmodels


import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle

import java.util.logging.Level
import java.util.logging.Logger


/**
 * [Ref1](https://betterprogramming.pub/how-to-get-activity-from-jetpack-compose-d0af406d534f)
 * [Ref2](https://stackoverflow.com/questions/76989222/is-there-a-safer-way-to-get-the-activity-in-jetpack-compose)
 */
fun Context.findActivityExt(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) { return context }
        context = context.baseContext
    }
    //throw IllegalStateException("no activity")
    return null
}


fun Context.hasCameraPermissionBeenGranted(): Boolean =
    ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED


fun Context.hasWriteStoragePermissionBeenGranted(): Boolean =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        //Android is 11 (R) or above
        val manageExternalStoragePermission1 = Environment.isExternalStorageManager()
        val manageExternalStoragePermission2 = ContextCompat.checkSelfPermission(this, Manifest.permission.MANAGE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        Logger.getLogger("Context ktx").log(Level.INFO, "hasWriteStoragePermissionBeenGranted() - manageExternalStoragePermission1: [$manageExternalStoragePermission1], manageExternalStoragePermission2: [$manageExternalStoragePermission2]")
        manageExternalStoragePermission1
    }
    else {
        //Below android 11
        val writeExternalStoragePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        val readExternalStoragePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        Logger.getLogger("Context ktx").log(Level.INFO, "hasWriteStoragePermissionBeenGranted() - writeExternalStoragePermission: [$writeExternalStoragePermission], readExternalStoragePermission: [$readExternalStoragePermission]")
        writeExternalStoragePermission
    }


fun Context.hasTakePhotoPermissionBeenGranted(): Boolean {
    Logger.getLogger("Context ktx").log(Level.INFO, "hasTakePhotoPermissionBeenGranted() - Build.VERSION.SDK_INT: [${Build.VERSION.SDK_INT}]")
    val cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
        val imagePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED
        val userSelectedPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED) == PackageManager.PERMISSION_GRANTED
        Logger.getLogger("Context ktx").log(Level.INFO, "hasTakePhotoPermissionBeenGranted() - cameraPermission: [$cameraPermission], imagePermission: [$imagePermission], userSelectedPermission: [$userSelectedPermission]")
        cameraPermission && imagePermission && userSelectedPermission
    }
    else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val imagePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED
        //val audioPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_AUDIO) == PackageManager.PERMISSION_GRANTED
        //val videoPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_VIDEO) == PackageManager.PERMISSION_GRANTED
        //Logger.getLogger(getLogTag()).log(Level.INFO, "cameraPermission: [$cameraPermission], imagePermission: [$imagePermission], audioPermission: [$audioPermission], videoPermission: [$videoPermission]")
        //cameraPermission && imagePermission && audioPermission && videoPermission
        Logger.getLogger("Context ktx").log(Level.INFO, "hasTakePhotoPermissionBeenGranted() - cameraPermission: [$cameraPermission], imagePermission: [$imagePermission]")
        cameraPermission && imagePermission
    }
    else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        //Android is 11 (R) or above
        val manageExternalStoragePermission1 = Environment.isExternalStorageManager()
        val manageExternalStoragePermission2 = ContextCompat.checkSelfPermission(this, Manifest.permission.MANAGE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        Logger.getLogger("Context ktx").log(Level.INFO, "hasTakePhotoPermissionBeenGranted() - cameraPermission: [$cameraPermission], manageExternalStoragePermission1: [$manageExternalStoragePermission1], manageExternalStoragePermission2: [$manageExternalStoragePermission2]")
        cameraPermission && manageExternalStoragePermission1
    }
    else {
        //Below android 11
        val writeExternalStoragePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        val readExternalStoragePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        Logger.getLogger("Context ktx").log(Level.INFO, "hasTakePhotoPermissionBeenGranted() - cameraPermission: [$cameraPermission], writeExternalStoragePermission: [$writeExternalStoragePermission]")
        cameraPermission && writeExternalStoragePermission
    }
}


fun Context.hasBluetoothPermissionBeenGranted(): Boolean {
    val fineLocationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val btScanPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_GRANTED
        val btConnectPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED
        Logger.getLogger("Context ktx").log(Level.INFO, "hasBluetoothPermissionBeenGranted() - [>= API 31], btScanPermission: [$btScanPermission], btConnectPermission: [$btConnectPermission], fineLocationPermission: [$fineLocationPermission]")
        btScanPermission && btConnectPermission && fineLocationPermission
    }
    else {
        Logger.getLogger("Context ktx").log(Level.INFO, "hasBluetoothPermissionBeenGranted() - [< API 31], fineLocationPermission: [$fineLocationPermission]")
        fineLocationPermission
    }
}


fun getCameraPermissionArray(): Array<String> =
    arrayOf(
        Manifest.permission.CAMERA,
    )


fun getWriteExternalStoragePermissionArray(): Array<String> =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.MANAGE_EXTERNAL_STORAGE,
        )
    }
    else {
        arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
        )
    }


fun getTakePhotoPermissionsArray(): Array<String> =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
        arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED,
        )
    }
    else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_MEDIA_IMAGES,
        )
    }
    else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.MANAGE_EXTERNAL_STORAGE,
        )
    }
    else {
        arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
        )
    }


fun getBluetoothPermissionsArray(): Array<String> =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        arrayOf(
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.ACCESS_FINE_LOCATION,
        )
    } else {
        arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
        )
    }


@Composable
fun rememberMultiplePermissionsLauncher01(
    permissionsCheck: Boolean,
): Pair<Boolean, ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>> {

    var permissionsGranted: Boolean by remember { mutableStateOf(permissionsCheck) }

    val multiplePermissionsLauncher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>> = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap: Map<String, Boolean> ->
        permissionsGranted = permissionsMap.values.reduce { acc, next -> acc && next }
    }

    return Pair<Boolean, ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>>(permissionsGranted, multiplePermissionsLauncher)
}


@Composable
fun RequestCameraPermissionIfNeeded01() {
    Logger.getLogger("RequestCameraPermissionIfNeeded01").log(Level.INFO, "RequestCameraPermissionIfNeeded01 - [0]")

    val context: Context = LocalContext.current

    val permissionPair: Pair<Boolean, ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>> =
        rememberMultiplePermissionsLauncher01(permissionsCheck = context.hasCameraPermissionBeenGranted())

    Logger.getLogger("RequestCameraPermissionIfNeeded01").log(Level.INFO, "RequestCameraPermissionIfNeeded01 - PermissionsGranted: [${permissionPair.first}]")

    LifecycleEventWatcher01(
        lifecycleEventWatcher = { lifeCycleEvent: Lifecycle.Event ->
            if (lifeCycleEvent == Lifecycle.Event.ON_START && !permissionPair.first) {
                Logger.getLogger("RequestCameraPermissionIfNeeded01").log(Level.INFO, "RequestCameraPermissionIfNeeded01 - launch()")
                val permissionArray = getCameraPermissionArray()
                permissionPair.second.launch(permissionArray)
            }
        }
    )
}


@Composable
fun RequestWriteStoragePermissionIfNeeded01() {
    Logger.getLogger("RequestWriteStoragePermissionIfNeeded01").log(Level.INFO, "RequestWriteStoragePermissionIfNeeded01 - [0]")

    val context: Context = LocalContext.current

    val permissionPair: Pair<Boolean, ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>> =
        rememberMultiplePermissionsLauncher01(permissionsCheck = context.hasWriteStoragePermissionBeenGranted())

    Logger.getLogger("RequestWriteStoragePermissionIfNeeded01").log(Level.INFO, "RequestWriteStoragePermissionIfNeeded01 - PermissionsGranted: [${permissionPair.first}]")

    LifecycleEventWatcher01(
        lifecycleEventWatcher = { lifeCycleEvent: Lifecycle.Event ->
            if (lifeCycleEvent == Lifecycle.Event.ON_START ) {
                if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) &&
                    (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) &&
                    (!Environment.isExternalStorageManager())) {
                    val activity: Activity? = context.findActivityExt()
                    if (null != activity) {
                        Logger.getLogger("RequestWriteStoragePermissionIfNeeded01").log(Level.INFO, "RequestWriteStoragePermissionIfNeeded01 - [ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION] - go")

                        val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                        intent.data = Uri.fromParts("package", activity.packageName, null)
                        activity.startActivity(intent)
                    }
                    else {
                        Logger.getLogger("RequestWriteStoragePermissionIfNeeded01").log(Level.INFO, "RequestWriteStoragePermissionIfNeeded01 - [ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION] - activity is null!!")
                    }
                }
                else {
                    val permissions: Array<String> = getWriteExternalStoragePermissionArray()
                    Logger.getLogger("RequestWriteStoragePermissionIfNeeded01").log(Level.INFO, "RequestWriteStoragePermissionIfNeeded01 - permissions.size: [${permissions.size}]")

                    if (!permissionPair.first) {
                        Logger.getLogger("RequestWriteStoragePermissionIfNeeded01").log(Level.INFO, "RequestWriteStoragePermissionIfNeeded01 - launch()")
                        permissionPair.second.launch(permissions)
                    }
                }
            }
        }
    )
}


@Composable
fun RequestTakePhotoPermissionIfNeeded01() {
    Logger.getLogger("RequestTakePhotoPermissionIfNeeded01").log(Level.INFO, "RequestTakePhotoPermissionIfNeeded01 - [0]")

    val context: Context = LocalContext.current

    val permissionPair: Pair<Boolean, ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>> =
        rememberMultiplePermissionsLauncher01(permissionsCheck = context.hasTakePhotoPermissionBeenGranted())

    Logger.getLogger("RequestTakePhotoPermissionIfNeeded01").log(Level.INFO, "RequestTakePhotoPermissionIfNeeded01 - PermissionsGranted: [${permissionPair.first}]")

    LifecycleEventWatcher01(
        lifecycleEventWatcher = { lifeCycleEvent: Lifecycle.Event ->
            if (lifeCycleEvent == Lifecycle.Event.ON_START ) {
                if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) &&
                    (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) &&
                    (!Environment.isExternalStorageManager())) {
                    val activity: Activity? = context.findActivityExt()
                    if (null != activity) {
                        Logger.getLogger("RequestTakePhotoPermissionIfNeeded01").log(Level.INFO, "RequestTakePhotoPermissionIfNeeded01 - [ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION] - go")

                        val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                        intent.data = Uri.fromParts("package", activity.packageName, null)
                        activity.startActivity(intent)
                    }
                    else {
                        Logger.getLogger("RequestTakePhotoPermissionIfNeeded01").log(Level.INFO, "RequestTakePhotoPermissionIfNeeded01 - [ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION] - activity is null!!")
                    }
                }
                else {
                    val permissions: Array<String> = getTakePhotoPermissionsArray()
                    if (!permissionPair.first) {
                        Logger.getLogger("RequestTakePhotoPermissionIfNeeded01").log(Level.INFO, "RequestTakePhotoPermissionIfNeeded01 - launch()")
                        permissionPair.second.launch(permissions)
                    }
                }
            }
        }
    )
}


@Composable
fun RequestBluetoothPermissionIfNeeded01() {
    Logger.getLogger("RequestBluetoothPermissionIfNeeded01").log(Level.INFO, "RequestBluetoothPermissionIfNeeded01 - [0]")

    val context: Context = LocalContext.current

    val permissionPair: Pair<Boolean, ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>> =
        rememberMultiplePermissionsLauncher01(permissionsCheck = context.hasBluetoothPermissionBeenGranted())

    Logger.getLogger("RequestBluetoothPermissionIfNeeded01").log(Level.INFO, "RequestBluetoothPermissionIfNeeded01 - PermissionsGranted: [${permissionPair.first}]")

    LifecycleEventWatcher01(
        lifecycleEventWatcher = { lifeCycleEvent: Lifecycle.Event ->
            if (lifeCycleEvent == Lifecycle.Event.ON_START && !permissionPair.first) {
                Logger.getLogger("RequestBluetoothPermissionIfNeeded01").log(Level.INFO, "RequestBluetoothPermissionIfNeeded01 - launch()")
                val permissionArray = getBluetoothPermissionsArray()
                permissionPair.second.launch(permissionArray)
            }
        }
    )
}

