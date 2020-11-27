package com.ylv.yalovapushdemo;

import android.content.Intent;

import com.huawei.hms.push.HmsMessageService;
import com.huawei.hms.push.RemoteMessage;

public class PushService extends HmsMessageService {
    String ACTION = "com.ylv.yalovapushdemo.ON_NEW_TOKEN";
    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        sendTokenToDisplay(token);

    }

    @Override
    public void onTokenError(Exception e) {
        super.onTokenError(e);

    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);


    }

    @Override
    public void onMessageSent(String s) {
        super.onMessageSent(s);
    }


    @Override
    public void onSendError(String s, Exception e) {
        super.onSendError(s, e);

    }

    private void sendTokenToDisplay(String token){
        Intent intent = new Intent(ACTION);
        intent.putExtra("token",token);
        sendBroadcast(intent);
    }
}
