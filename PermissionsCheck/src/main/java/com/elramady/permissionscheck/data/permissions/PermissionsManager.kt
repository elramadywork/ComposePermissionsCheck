package com.elramady.permissionscheck.data.permissions

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PermissionsManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun hasPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }


    fun requestPermissions(
        activity: Activity,
        permissions: Array<String>,
        requestCode: Int,
    ) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode)
    }




    fun shouldShowRequestPermissionRationale(activity: Activity, permission: String): Boolean {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)
    }

    fun willShowPermissionRequestDialog(activity: Activity, permission: String): Boolean {
        return !hasPermission(permission) && !shouldShowRequestPermissionRationale(activity, permission)
    }
}