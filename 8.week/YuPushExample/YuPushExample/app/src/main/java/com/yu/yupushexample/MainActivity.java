package com.yu.yupushexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView myTokenView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTokenView = findViewById(R.id.myTokenText);

        MyReceiver receiver = new MyReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction("com.yu.yupushexample.onNewToken");

        this.registerReceiver(receiver,filter);
    }

    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            if ("com.yu.yupushexample.onNewToken".equals(intent.getAction())) {

                String token = intent.getStringExtra("token");
                myTokenView.setText(token);

            }
        }
    }
}