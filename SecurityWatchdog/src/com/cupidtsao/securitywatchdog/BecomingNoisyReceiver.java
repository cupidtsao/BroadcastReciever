package com.cupidtsao.securitywatchdog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

public class BecomingNoisyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (AudioManager.ACTION_AUDIO_BECOMING_NOISY.equals(intent.getAction())) {
			Intent i = new Intent();
			i.setClass(context, AlertActivity.class);
			context.startActivity(i);
		}
	}
}