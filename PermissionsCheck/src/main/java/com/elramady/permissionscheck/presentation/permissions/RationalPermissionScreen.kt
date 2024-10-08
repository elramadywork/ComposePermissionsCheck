package com.elramady.permissionscheck.presentation.permissions

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.elramady.permissionscheck.presentation.components.ButtonAppWithoutBackground

@Composable
fun RationalPermissionScreen(
    modifier: Modifier = Modifier,
    title: String,
    description: String ,
    onDismiss: () -> Unit,
    okButton:()->Unit,
) {


    AlertDialog(
        shape = RoundedCornerShape(20.dp),
        onDismissRequest = {
            onDismiss()
                           },
        title = { Text(text = title) },
        text = { Text(text =description) },
        confirmButton = {
            ButtonAppWithoutBackground(textButton = "Ok", modifier = Modifier.padding(horizontal = 20.dp).padding(bottom = 18.dp)) {
                okButton.invoke()
            }
        },
        dismissButton = {
            ButtonAppWithoutBackground(textButton = "Cancel", enabled = false, modifier = Modifier.padding(horizontal = 20.dp)) {
                onDismiss()
            }
        },
    )




}