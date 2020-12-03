package com.tfkb.pushdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.huawei.hms.ads.AdListener;
import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.BannerAdSize;
import com.huawei.hms.ads.InterstitialAd;
import com.huawei.hms.ads.banner.BannerView;

public class MainActivity extends AppCompatActivity {

    private BannerView bannerAd;
    private InterstitialAd interAd;
    AdListener bannerListener, interAdListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bannerAd =findViewById(R.id.bannerAd);
        bannerAd.setAdId("testw6vs28auh3");
        bannerAd.setBannerAdSize(BannerAdSize.BANNER_SIZE_300_250);
        bannerListener = new AdListener() {
            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }

            @Override
            public void onAdFailed(int i) {
                super.onAdFailed(i);

            }
        };

        interAdListener = new AdListener(){
            @Override
            public void onAdClicked() {
                super.onAdClicked();

            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();

            }

            @Override
            public void onAdFailed(int i) {
                super.onAdFailed(i);

            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                interAd.show();

            }
        };

        bannerAd.setAdListener(bannerListener);

        if(LocalStorage.isAvailableForInters()){
         interAd = new InterstitialAd(this);
         interAd.setAdId("testw6vs28auh3");
         interAd.setAdListener(interAdListener);
         loadInterAd();
        }
        else {
            LocalStorage.increaseLunch();
         }
        }

        private void loadInterAd(){
            AdParam param = new AdParam.Builder().build();
            interAd.loadAd(param);
        }

    }






















    class PushReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if ("com.tfkb.pushdemo.ON_NEW_TOKEN".equals(intent.getAction())) {
                String token = intent.getStringExtra("token");

            }
        }
    }


