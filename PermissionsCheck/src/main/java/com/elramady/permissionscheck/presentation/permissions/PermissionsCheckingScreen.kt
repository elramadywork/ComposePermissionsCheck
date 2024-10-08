package com.alexonsn.tavolo.presentation.components.permissions

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.elramady.permissionscheck.presentation.permissions.PermissionViewModel
import com.elramady.permissionscheck.presentation.permissions.RationalPermissionScreen
import com.elramady.permissionscheck.presentation.permissions.SettingPermissionDialogScreen

@Composable
fun PermissionsCheckingScreen(
    viewModel: PermissionViewModel = hiltViewModel(),
    isRationalUiFirst:Boolean=false,
    titleRationalUi:String="Permission Needed",
    descriptionRationalUi:String="permission is needed to take the action",
    descriptionSettingDialog:String="The app requires this permission to function properly. Please enable it in the app settings.",
    permissions:Array<String>,
    permissionCode:Int=202,
    onDismiss:()->Unit,
    resultPermission:(isGranted:Boolean)->Unit

     ) {


    val viewModelStates by viewModel.permissionState

    val context = LocalContext.current as Activity


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsResult: Map<String, Boolean> ->
        val allPermissionsGranted = permissionsResult.all { it.value }

        Log.e("doPermissionScreenRegisterScreen", "myLaunch $allPermissionsGranted")
        viewModel.setUserGrantedPermission(allPermissionsGranted)

        if (!allPermissionsGranted) {
            resultPermission(false)
            onDismiss()
        }

    }


    fun checkAndRequestPermission(){
        viewModel.checkRequestMultiplePermissions(context, permissions, permissionCode, launcher, isRationalUiFirst = isRationalUiFirst)
    }


    if (viewModelStates.permissionGranted){
      //  Toast.makeText(LocalContext.current,"Permission Granted" ,Toast.LENGTH_SHORT).show()
        viewModel.onEvent(PermissionsScreenEvent.OnDismissSettingDialog)

        resultPermission(true)

        onDismiss()

    }

    Column {

        LaunchedEffect(Unit) {
            checkAndRequestPermission()
        }
        if (viewModelStates.permissionShowRationale){
            Log.e("permissionShowRationale", "Rational")

            RationalPermissionScreen(
                title = titleRationalUi,
                description = descriptionRationalUi,
                onDismiss = {

                    Log.e("onDismiss","OnDismissRationalUiDialog")
                    viewModel.onEvent(PermissionsScreenEvent.OnDismissRationalUiDialog)

                    resultPermission(false)

                    onDismiss()

                },
                okButton = {
                    viewModel.onEvent(PermissionsScreenEvent.OnDismissRationalUiDialog)
                    viewModel.requestMultiplePermission()

                }
            )
        }

        if (viewModelStates.permissionShowSettingsDialog) {
            Log.e("permissionShowRationale", "Setting")

            SettingPermissionDialogScreen(
                descriptionSettingDialog = descriptionSettingDialog,
                onSettingBack = {
                    checkAndRequestPermission()
//                    viewModel.onEvent(PermissionsScreenEvent.OnDismissSettingDialog)
//                    resultPermission(false)
//                    onDismiss()
                },
                onDismiss = {
                    viewModel.onEvent(PermissionsScreenEvent.OnDismissSettingDialog)
                    resultPermission(false)
                    onDismiss()

            })
        }

    }


//




}