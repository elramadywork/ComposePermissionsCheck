package com.alexonsn.tavolo.presentation.components.permissions

import androidx.activity.result.ActivityResultLauncher

data class PermissionsStates(
    val permissionGranted: Boolean = false,
    val permissionShowRationale: Boolean = false,
    val permissionShowSettingsDialog: Boolean = false,
    val permissionFirstTimeAsk: Boolean = false,
    val permissionsNames: Array<String> = emptyArray(),
    val launcherPermission: ActivityResultLauncher<String>?=null,
    val launcherListPermission: ActivityResultLauncher<Array<String>>?=null
)
