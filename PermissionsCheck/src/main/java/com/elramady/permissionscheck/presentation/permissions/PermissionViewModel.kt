package com.elramady.permissionscheck.presentation.permissions

import android.app.Activity
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.alexonsn.tavolo.presentation.components.permissions.PermissionsScreenEvent
import com.alexonsn.tavolo.presentation.components.permissions.PermissionsStates
import com.elramady.permissionscheck.data.permissions.PermissionsManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PermissionViewModel @Inject constructor(
    private val permissionsManager: PermissionsManager
) : ViewModel() {

    private val _permissionState = mutableStateOf(PermissionsStates())
    val permissionState: State<PermissionsStates> = _permissionState



    private fun hasPermission(permission: String): Boolean {
        return permissionsManager.hasPermission(permission)
    }

    fun setUserGrantedPermission(isGranted:Boolean){
        _permissionState.value = _permissionState.value.copy(
            permissionGranted = isGranted
        )
    }

 fun onEvent(event: PermissionsScreenEvent){

     when(event){
         is PermissionsScreenEvent.OnDismissRationalUiDialog ->{
             _permissionState.value = _permissionState.value.copy(
                 permissionShowRationale =false
             )
         }
         is PermissionsScreenEvent.OnDismissSettingDialog ->{
             _permissionState.value = _permissionState.value.copy(
                 permissionShowSettingsDialog = false
             )
         }
         else -> Unit
     }

 }

    // with xml
//    fun requestPermissions(activity: Activity, permissions: Array<String>, requestCode: Int,) {
//        permissionsManager.requestPermissions(activity, permissions, requestCode)
//    }


//    fun shouldShowRequestPermissionRationale(activity: Activity, permission: String): Boolean {
//        return permissionsManager.shouldShowRequestPermissionRationale(activity, permission)
//    }

    private fun willShowPermissionRequestDialog(activity: Activity, permission: String): Boolean {
        return permissionsManager.willShowPermissionRequestDialog(activity, permission)
    }


//    // with jetpack compose
//    fun checkRequestPermissions(
//        activity: Activity,
//        permissions: Array<String>,
//        requestCode: Int,
//        launcher: ActivityResultLauncher<String>,
//        isRationalUiFirst:Boolean=false,
//     //   resultsRequestPermission:(hasPermission:Boolean,showRationalUi:Boolean,showDialogSetting:Boolean)->Unit
//    ) {
//        _permissionState.value = _permissionState.value.copy(
//            permissionsNames = permissions,
//            launcherPermission = launcher,
//        )
//        permissions.forEach { permission ->
//
//            if (hasPermission(permission)) {
//                // Permission already granted
//
//                Log.e("doPermissionScreenRegisterScreen","viewModelNotGranted")
//                _permissionState.value = _permissionState.value.copy(
//                 permissionGranted = true
//                )
//
//            }  else if (!willShowPermissionRequestDialog(activity,permission)) {
//
//                // Request permission is case of if user denied permission before show dialog with setting
//                //   resultsRequestPermission(false, false,true)
//
//                _permissionState.value = _permissionState.value.copy(
//                    permissionShowSettingsDialog = true
//                )
//
//
//            }else{
//
//                //Request permission is case of if user first time ask permission
//                // And check if you want to show RationalUi First or not
//
//
//                if (isRationalUiFirst){
//                    _permissionState.value = _permissionState.value.copy(
//                         permissionShowRationale =true
//                    )
//                }else{
//                    _permissionState.value = _permissionState.value.copy(
//                        permissionShowRationale =false
//                    )
//                    launcher.launch(permission)
//
//
//                }
//
//
//
//            }
//        }
//    }
//
//
//        fun requestPermission(){
//        _permissionState.value.permissionsNames.forEach {
//            _permissionState.value.launcherPermission?.launch(it)
//        }
//    }

    fun checkRequestMultiplePermissions(
        activity: Activity,
        permissions: Array<String>,
        requestCode: Int,
        launcher: ActivityResultLauncher<Array<String>>,
        isRationalUiFirst: Boolean = false
    ) {
        _permissionState.value = _permissionState.value.copy(
            permissionsNames = permissions,
            launcherListPermission = launcher
        )
        val permissionsToRequest = mutableListOf<String>()

        val permissionsToRequestGrantedList = mutableListOf<Boolean>()

        permissions.forEach { permission ->
            if (hasPermission(permission)) {

                // Permission already granted
                permissionsToRequestGrantedList.add(true)

            } else if (!willShowPermissionRequestDialog(activity, permission)) {
                // Show settings dialog if permission denied before
                _permissionState.value = _permissionState.value.copy(
                    permissionShowSettingsDialog = true
                )
            } else {
                // Add permission to request list
                permissionsToRequest.add(permission)
            }
        }

        if (permissionsToRequestGrantedList.isNotEmpty()) {
            if (permissionsToRequestGrantedList.size==permissions.size){
                _permissionState.value = _permissionState.value.copy(
                    permissionGranted = true
                )
            }

        }

        if (permissionsToRequest.isNotEmpty()) {
            if (isRationalUiFirst) {
                _permissionState.value = _permissionState.value.copy(
                    permissionShowRationale = true
                )
            } else {
                launcher.launch(permissionsToRequest.toTypedArray())
            }
        }
    }




    fun requestMultiplePermission(){
        val permissionsToRequest = mutableListOf<String>()

        _permissionState.value.permissionsNames.forEach {
            permissionsToRequest.add(it)
        }
        _permissionState.value.launcherListPermission?.launch(permissionsToRequest.toTypedArray())

    }
}