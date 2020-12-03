package com.yu.yuadscodelab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.huawei.hms.ads.AdListener;
import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.BannerAdSize;
import com.huawei.hms.ads.HwAds;
import com.huawei.hms.ads.InterstitialAd;
import com.huawei.hms.ads.banner.BannerView;

public class MainActivity extends AppCompatActivity {

    InterstitialAd interstitialAd;
    SharedPreferences myPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // HMS Ads Servinin tetikliyoruz.
        HwAds.init(this);

        // Banner Ad example
        loadBannerAd();

        myPreferences = getSharedPreferences("my_setting", MODE_PRIVATE);
        increaseCounterValue();
        Toast.makeText(this, getCounterValue() + " kez actınız...", Toast.LENGTH_SHORT).show();
        if(getCounterValue() == 3) {
            interstitialAd = new InterstitialAd(this);
            interstitialAd.setAdId("teste9ih9j0rc3");
            interstitialAd.setAdListener(adListener);

            AdParam adParam = new AdParam.Builder().build();
            interstitialAd.loadAd(adParam);
            setCounterValue(0);
        }
    }

    public int getCounterValue(){
        return myPreferences.getInt("counter", 0);
    }

    public void increaseCounterValue() {
        SharedPreferences.Editor editor = myPreferences.edit();
        editor.putInt("counter", getCounterValue() + 1);
        editor.commit();
    }

    public void setCounterValue(int value) {
        SharedPreferences.Editor editor = myPreferences.edit();
        editor.putInt("counter", value);
        editor.commit();
    }

    public void loadBannerAd(){

        // Kode uzerınden bır abnner ad view yaratıyoruz
        BannerView bannerView = new BannerView(this);
        // Ad Id: Sızın tekıl reklam idniz. Bu id sayesinde reklamın size ait oldugu anlasılıyor ve reklamla ılgılı ayarlar cekılıyor.
        bannerView.setAdId("testw6vs28auh3");
        // Reklamın Boyutunu ayarlıyoruz
        bannerView.setBannerAdSize(BannerAdSize.BANNER_SIZE_SMART);

        // Reklamın gosterileceği layouta erişiyoruz
        LinearLayout rootView = findViewById(R.id.root_view);
        // Reklam viewini bu layout içine ekliyoruz
        rootView.addView(bannerView);


        // Ads Parameter objesini olustur
        AdParam adParam = new AdParam.Builder().build();
        // Reklamın gosterılmesını tetıklıyoruz.
        bannerView.loadAd(adParam);
    }

    private AdListener adListener = new AdListener() {
        @Override
        public void onAdLoaded() {
            super.onAdLoaded();
            Toast.makeText(MainActivity.this, "Ad loaded", Toast.LENGTH_SHORT).show();
            // Display an interstitial ad.
            showInterstitial();
        }

        @Override
        public void onAdFailed(int errorCode) {
            Toast.makeText(MainActivity.this, "Ad load failed with error code: " + errorCode,
                    Toast.LENGTH_SHORT).show();
            Log.d("TAG", "Ad load failed with error code: " + errorCode);
        }

        @Override
        public void onAdClosed() {
            super.onAdClosed();
            Log.d("TAG", "onAdClosed");
        }

        @Override
        public void onAdClicked() {
            Log.d("TAG", "onAdClicked");
            super.onAdClicked();
        }

        @Override
        public void onAdOpened() {
            Log.d("TAG", "onAdOpened");
            super.onAdOpened();
        }
    };

    private void showInterstitial() {
        // Display an interstitial ad.
        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
        }
    }

}

