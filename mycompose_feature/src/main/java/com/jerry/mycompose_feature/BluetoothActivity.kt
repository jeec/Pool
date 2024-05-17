package com.jerry.mycompose_feature

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class BluetoothActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetooth)

        Handler().postDelayed({
            startBluetooth()
        }, 3000)
//        startBluetooth()


    }

    fun startScanDevices(bleAdapter: BluetoothAdapter): Unit {
        val bleScanner = bleAdapter.bluetoothLeScanner
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.BLUETOOTH_SCAN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                Log.d(">>>", "startScanDevices: not permission scan")
                ActivityCompat.requestPermissions(this@BluetoothActivity, arrayOf(Manifest.permission.BLUETOOTH_SCAN), 110)
            }
            return
        }
        Log.d(">>>", "startScanDevices: scanning")
        bleScanner.startScan(object : ScanCallback() {
            override fun onBatchScanResults(results: MutableList<ScanResult>?) {
                super.onBatchScanResults(results)
                Log.d(">>>", "onBatchScanResults: $results")

            }

            override fun onScanFailed(errorCode: Int) {
                super.onScanFailed(errorCode)
                Log.d(">>>", "onScanFailed: $errorCode")
            }

            override fun onScanResult(callbackType: Int, result: ScanResult?) {
                super.onScanResult(callbackType, result)
                Log.d(">>>", "onScanResult: ")
            }
        })
    }

    private fun startBluetooth() {
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        //通过isEnable() 判断蓝牙是否打开
        if (!bluetoothAdapter.isEnabled()) {
            //如果没有打开，我们可以通过 Intent 达到不离开app就可以打开蓝牙的效果。
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.BLUETOOTH_CONNECT
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            startActivityForResult(enableBtIntent, 111)
        } else {
            startScanDevices(bluetoothAdapter)
        }
    }

    @Deprecated("")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(">>>", "onActivityResult: $requestCode")
    }
}