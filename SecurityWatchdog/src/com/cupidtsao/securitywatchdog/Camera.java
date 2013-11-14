package com.cupidtsao.securitywatchdog;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class Camera extends Activity {
	private static final String TAG = "CameraDemo";
	Camera camera;
	CameraPreview preview;
	Button buttonClick;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera);

		preview = new CameraPreview(this);
		((FrameLayout) findViewById(R.id.preview)).addView(preview);

		buttonClick = (Button) findViewById(R.id.buttonClick);
		buttonClick.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				preview.camera.takePicture(shutterCallback, rawCallback, jpegCallback);
			}
		});

		//preview.camera.takePicture(shutterCallback, rawCallback, jpegCallback);
		Log.d(TAG, "onCreate'd");
		finish();
	}

	ShutterCallback shutterCallback = new ShutterCallback() {
		public void onShutter() {
			Log.d(TAG, "onShutter'd");
		}
	};

	/** Handles data for raw picture */
	PictureCallback rawCallback = new PictureCallback() {
		public void onPictureTaken(byte[] data, android.hardware.Camera camera) {
			Log.d(TAG, "onPictureTaken - raw");		
		}
	};

	/** Handles data for jpeg picture */
	PictureCallback jpegCallback = new PictureCallback() {
		public void onPictureTaken(byte[] data, android.hardware.Camera camera) {
			FileOutputStream outStream = null;
			try {
				// write to local sandbox file system
				// outStream =
				// CameraDemo.this.openFileOutput(String.format("%d.jpg",
				// System.currentTimeMillis()), 0);
				// Or write to sdcard
				String externalStoragePath = Environment.getExternalStorageDirectory().getPath();
				outStream = new FileOutputStream(String.format(externalStoragePath + "/%d.jpg", System.currentTimeMillis()));
				outStream.write(data);
				outStream.close();
				Log.d(TAG, "onPictureTaken - wrote bytes: " + data.length);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
			}
			Log.d(TAG, "onPictureTaken - jpeg");
		}
	};

}