# Compose-Permissions-Check 🎨
![Jitpack.io](https://img.shields.io/static/v1?label=Jitpack.io&message=1.0.0&color=blue)


## Defination
This is library is used to request and check permession(single or multible) With Rationale UI for only **Jetpack Compose**

## Features
- **Handling** request and check Permissions (single or multible) with results (if granted or not)
- **Easy and simple** for usage, only give it permission name
- Support only **Jetpack Compose**
- **Ability** to present Rationale UI that clearly explains to the user why is your app need this the permission?!
  (can you disable it) 


## Installation

To install and use Compose-Permissions-Check Library, follow these step-by-step instructions:

1. Add jitpack in **settings.gradle.kts**

```jsx

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://www.jitpack.io" ) }

    }
}
```

2. Add this dependency in **build.gradle** :

```jsx

dependencies {
    // Replace 'latest-version' with the actual latest version number
    implementation("com.github.elramadywork:ComposePermissionsCheck:latest-version")
}
```

3. **Setup** Dagger Hilt and make your activity annotated by @AndroidEntryPoint :

```jsx

@AndroidEntryPoint
class MainActivity : ComponentActivity() {}

//then create this class:

@HiltAndroidApp
class MyApplication :Application() {
}

//and add it in manifest

android:name=".MyApplication"

```


## Usage

```jsx
@Composable
fun CheckPermissions(titlePermission: String, modifier: Modifier = Modifier) {
    var showPermissionScreen by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier=modifier.fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            showPermissionScreen=true
        }) {
            Text(
                text = titlePermission,
                modifier = Modifier.padding(10.dp)
            )
        }
    }

    if (showPermissionScreen) {
        PermissionsCheckScreen(
            isRationalUiFirst = false,
            titleRationalUi = "Permission Needed",
            descriptionRationalUi = "Permission is needed for location services",
            permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
            onDismiss = { }
        ) { isGranted ->
            if (isGranted) {
                Log.e("locationPermissionCheck","true")
                showPermissionScreen = false
            } else {
                Log.e("locationPermissionCheck","false")
                showPermissionScreen = false
            }
        }


    }





}


```


| Parameter               | Description                                                                                                                                              |
|-------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------| 
| `isRationalUiFirst`     | Boolean Value (true or false) if you want to present Rationale UI.                                                                                       |
| `permissions`           | must be arrayOf permessions(single or multiple) like this arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION). |
| `titleRationalUi`       | for  title Rationale UI.                                                                                                                                 |
| `descriptionRationalUi` | for  Description Rationale UI.                                                                                                                           |
| `resultPermission`      | is high order function give you result permission (isGranted or not).                                                                                    |

## Don't Forget

Don't Forget to add your permissions in manifest


## Contact

Feel free to reach out to me on my email:
mahmoudelrmady@gmail.com





## License

[![License](https://img.shields.io/static/v1?label=Licence&message=MIT&color=blue)](https://opensource.org/license/MIT)

