package com.cupidtsao.securitywatchdog;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

public class AlertActivity extends Activity {
	
	private MediaPlayer mMediaPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.alert);
		super.onCreate(savedInstanceState);
		
		mMediaPlayer = MediaPlayer.create(this, R.raw.siren);
		mMediaPlayer.setLooping(true);
	}

	@Override
	protected void onPause() {
		mMediaPlayer.release();
		super.onPause();
	}

	@Override
	protected void onResume() {
		doSiren();
		super.onResume();
	}
	
	private void doSiren() {
		// 發出警報聲響
		// 拍下照片
		// 開始錄影
		// 發出簡訊/E-mail/即時通
	}
}
