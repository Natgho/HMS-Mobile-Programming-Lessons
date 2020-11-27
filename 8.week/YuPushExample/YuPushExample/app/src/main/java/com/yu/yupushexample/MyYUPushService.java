package com.yu.yupushexample;

import android.content.Intent;
import android.util.Log;

import com.huawei.hms.push.HmsMessageService;

public class MyYUPushService extends HmsMessageService {

    private static final String TAG = "MyYUPushService";

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.i(TAG, "receive token:" + token);

        sendTokenToDisplay(token);
    }

    private void sendTokenToDisplay(String token) {
        Intent intent = new Intent("com.yu.yupushexample.onNewToken");
        intent.putExtra("token", token);
        sendBroadcast(intent);
    }
}
