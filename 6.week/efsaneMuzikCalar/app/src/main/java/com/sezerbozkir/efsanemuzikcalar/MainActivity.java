package com.sezerbozkir.efsanemuzikcalar;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.push.HmsMessageService;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton ibPlay, ibPause, ibNext, ibList;
    MediaPlayer mediaPlayer;
    ArrayList<String> musicList;
    TextView mTitle;
    int currentMusic = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ibPlay = findViewById(R.id.ibPlay);
        ibPause = findViewById(R.id.ibPause);
        ibNext = findViewById(R.id.ibNext);
        ibList = findViewById(R.id.ibMusicList);
        mTitle = findViewById(R.id.tvMusicTitle);
        musicList = new ArrayList<>();
        ListRaw();
        ibPlay.setOnClickListener(this);
        ibPause.setOnClickListener(this);
        ibNext.setOnClickListener(this);
        ibList.setOnClickListener(this);
        mTitle.setText(musicList.get(0));
        mediaPlayer = MediaPlayer.create(this, R.raw.cemre_solmaz_bir_dilek_official_video_mp3_26963);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ibPlay: {
                mediaPlayer.start();
                break;
            }
            case R.id.ibNext: {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                if (currentMusic < musicList.size() - 1) {
                    currentMusic += 1;
                } else {
                    currentMusic = 0;
                }
                String musicUri = "android.resource://" + getPackageName() + "/raw/" + musicList.get(currentMusic);
                mediaPlayer = MediaPlayer.create(this, Uri.parse(musicUri));
                mediaPlayer.start();
                mTitle.setText(musicList.get(currentMusic));
                Log.d("butonKesfet", "onClick: Next ");
                break;
            }
            case R.id.ibPause: {
                mediaPlayer.pause();
                break;
            }
            case R.id.ibMusicList: {
                // # TODO Music list feature will be available in the future.
                Log.d("butonKesfet", "onClick: MusicList ");
                break;
            }
            default: {
                Log.d("butonKesfet", "onClick: Unkown ");
                break;
            }
        }

    }

    private void getToken() {
        new Thread() {
            private void getToken() {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            // read from agconnect-services.json
                            String appId = AGConnectServicesConfig.fromContext(this).getString("client/app_id");
                            String token = HmsInstanceId.getInstance(this).getToken(appId, "HCM");
                            Log.i(TAG, "get token:" + token);
                            if (!TextUtils.isEmpty(token)) {
                                sendRegTokenToServer(token);
                            }
                        } catch (ApiException e) {
                            Log.e(TAG, "get token failed, " + e);
                        }
                    }
                }.start();
            }

            private void sendRegTokenToServer(String token) {
                Log.i(TAG, "sending token to server. token:" + token);
            }

            @Override
            public void run() {
                try {
                    // read from agconnect-services.json
                    String appId = AGConnectServicesConfig.fromContext(this).getString("client/app_id");
                    String token = HmsInstanceId.getInstance(this).getToken(appId, "HCM");
                    Log.i(TAG, "get token:" + token);
                    if (!TextUtils.isEmpty(token)) {
                        sendRegTokenToServer(token);
                    }
                } catch (ApiException e) {
                    Log.e(TAG, "get token failed, " + e);
                }
            }
        }.start();
    }

    private void sendRegTokenToServer(String token) {
        Log.i(TAG, "sending token to server. token:" + token);
    }

    public void ListRaw() {
        // TODO improve this loop
        Field[] fields = R.raw.class.getFields();
        for (int i = 0; i < fields.length; i++) {
            musicList.add(fields[i].getName());
        }
//        for (Field f: fields){
//            musicList.add(f.getName());
//        }
    }
}