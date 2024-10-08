package com.alexonsn.tavolo.presentation.components.permissions



sealed class PermissionsScreenEvent {
    data object OnDismissRationalUiDialog: PermissionsScreenEvent()
    data object OnDismissSettingDialog: PermissionsScreenEvent()

}