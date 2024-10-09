package com.alexons.myfirstlibrary

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexons.myfirstlibrary.ui.theme.MyFirstLibraryTheme
import com.elramady.permissionscheck.presentation.permissions.PermissionsCheckScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyFirstLibraryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CheckPermissions(
                        titlePermission = "check location Permission",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CheckPermissions(titlePermission: String, modifier: Modifier = Modifier) {
    var doPermissionScreen by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier=modifier.fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            doPermissionScreen=true
        }) {
            Text(
                text = titlePermission,
                modifier = Modifier.padding(10.dp)
            )
        }
    }

    if (doPermissionScreen) {
        PermissionsCheckScreen(
            isRationalUiFirst = false,
            titleRationalUi = "Permission Needed",
            descriptionRationalUi = "Permission is needed for location services",
            permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
            onDismiss = { }
        ) { isGranted ->
            if (isGranted) {
                Log.e("locationPermissionCheck","true")
                doPermissionScreen = false
            } else {
                Log.e("locationPermissionCheck","false")
                doPermissionScreen = false
            }
        }


    }





}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyFirstLibraryTheme {
        CheckPermissions("Android")
    }
}