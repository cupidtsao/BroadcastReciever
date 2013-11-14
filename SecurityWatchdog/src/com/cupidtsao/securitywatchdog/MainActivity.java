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
		// ���}App�ɡA���P������
		unregisterReceiver(mReceiver);
		super.onPause();
	}

	@Override
	protected void onResume() {
		// �Ұ�App�ɡA���U������
		
		// ��ް��վ��ɡAAndroid�t�Ϊ� AudioManager �|���� ACTION_AUDIO_BECOMING_NOISY �ƥ�s���C
		// �ڭ̫إߤ@�ӱ������A��b����ɭԦ���o�Ӽs���ƥ�A�õo�ͤ����C
		// �����]�ް��վ��^����Ұ����ơA�|�b BecomingNoistReceiver ���O�� onReceive ��k���M�w�C
        registerReceiver(mReceiver, new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY));
		super.onResume();
	}
}
