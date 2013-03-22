package com.example.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final int REQUEST_ENABLE_BT = 1;

	/** Called when the activity is first created. */

	TextView stateBluetooth;
	BluetoothAdapter bluetoothAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		stateBluetooth = (TextView) findViewById(R.id.bluetoothstate);
		bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

		CheckBlueToothState();
	}

	private void CheckBlueToothState() {
		if (bluetoothAdapter == null) {
			stateBluetooth.setText("Bluetooth NOT support");
		} else {
			if (bluetoothAdapter.isEnabled()) {
				if (bluetoothAdapter.isDiscovering()) {
					stateBluetooth
							.setText("Bluetooth is currently in device discovery process.");
				} else {
					stateBluetooth.setText("Bluetooth is Enabled.");
				}
			} else {
				stateBluetooth.setText("Bluetooth is NOT Enabled!");
				Intent enableBtIntent = new Intent(
						BluetoothAdapter.ACTION_REQUEST_ENABLE);
				startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode == REQUEST_ENABLE_BT) {
			CheckBlueToothState();
		}

	}

}