package com.example.radardisplayapp;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    float tracker = 0;

    private BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    private BluetoothLeScanner bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();
    private boolean scanning;
    private Handler handler = new Handler();

    private static final long SCAN_PERIOD = 10000;

    private void ScanLeDevice() {
        leDeviceListAdapter.reset();
        leDeviceListAdapter.invalidate();
        if(bluetoothLeScanner != null){
            if (!scanning) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        scanning = false;
                        bluetoothLeScanner.stopScan(leScanCallback);
                    }
                }, SCAN_PERIOD);

                scanning = true;
                bluetoothLeScanner.startScan(leScanCallback);
            } else {
                scanning = false;
                bluetoothLeScanner.stopScan(leScanCallback);
            }
        }
    }

    private LeDeviceListAdapter leDeviceListAdapter;

    private ScanCallback leScanCallback =
            new ScanCallback() {
                @Override
                public void onScanResult(int callbackType, ScanResult result) {
                    super.onScanResult(callbackType, result);
                    leDeviceListAdapter.add(result.getDevice());
                    leDeviceListAdapter.inc();
                    leDeviceListAdapter.invalidate();
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        leDeviceListAdapter = findViewById(R.id.leList);

        ScanLeDevice();

    }

    public void scan(){
        ScanLeDevice();
    }

    public void resizeBox(View view)
    {
        // Do something in response to button

        tracker += 10;
        if (tracker > 100){
            tracker = 0;
        }

        DisplayPaperLevel box = findViewById(R.id.box);
        box.setLevel(tracker);
    }

}
