package com.cupidtsao.securitywatchdog;

import android.media.AudioManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.IntentFilter;

public class MainActivity extends Activity {
	
	// Broadcast Receiver
	private BecomingNoisyReceiver mReceiver;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		mReceiver = new BecomingNoisyReceiver();
	}
	
	@Override
	protected void onPause() {
		// 離開App時，註銷接收器
		unregisterReceiver(mReceiver);
		super.onPause();
	}

	@Override
	protected void onResume() {
		// 啟動App時，註冊接收器
		
		// 當拔除耳機時，Android系統的 AudioManager 會產生 ACTION_AUDIO_BECOMING_NOISY 事件廣播。
		// 我們建立一個接收器，能在任何時候收到這個廣播事件，並發生反應。
		// 反應（拔除耳機）之後所做的事，會在 BecomingNoistReceiver 類別的 onReceive 方法中決定。
        registerReceiver(mReceiver, new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY));
		super.onResume();
	}
}
