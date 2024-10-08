package com.elramady.permissionscheck.presentation.permissions

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.elramady.permissionscheck.presentation.components.ButtonAppWithoutBackground

@Composable
fun SettingPermissionDialogScreen(
    onDismiss: () -> Unit,
    onSettingBack:()->Unit,
    descriptionSettingDialog:String,
) {
    val context = LocalContext.current as Activity
    // Create a launcher for opening settings
    val settingsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        onSettingBack()
        // This is called when returning from the settings
        // Handle logic here (e.g., re-check permissions)

    }

    AlertDialog(

        shape = RoundedCornerShape(20.dp),
        onDismissRequest = { onDismiss()},
        title = { Text(text = "Permission Needed") },
        text = { Text(text = descriptionSettingDialog) },
        confirmButton = {
            ButtonAppWithoutBackground(textButton = "Open Settings", modifier = Modifier.padding(horizontal = 20.dp).padding(bottom = 18.dp)) {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", context.packageName, null)
                }
                settingsLauncher.launch(intent)
               // context.startActivity(intent)
              //  onDismiss()
            }
        },
        dismissButton = {
            ButtonAppWithoutBackground(textButton = "Cancel", enabled = false, modifier = Modifier.padding(horizontal = 20.dp)) {
                onDismiss()
            }
        }


    )




}