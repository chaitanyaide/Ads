package com.example.ads;

import static com.adsmodule.api.adsModule.retrofit.APICallHandler.callAdsApi;
import static com.example.ads.singleton.AppOpenAds.activity;
import static com.example.ads.singleton.MyApplication.getConnectionStatus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.adsmodule.api.adsModule.AdUtils;
import com.adsmodule.api.adsModule.retrofit.AdsDataRequestModel;
import com.adsmodule.api.adsModule.utils.Constants;
import com.example.ads.databinding.ActivitySplashsrcBinding;

public class SplashScreen_Activity extends AppCompatActivity {

    ActivitySplashsrcBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivitySplashsrcBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());




        if (getConnectionStatus().isConnectingToInternet()) {
            callAdsApi(activity, Constants.MAIN_BASE_URL, new AdsDataRequestModel(this.getPackageName(), ""), adsResponseModel -> {
                if (adsResponseModel != null) {
                    AdUtils.showAppOpenAds(Constants.adsResponseModel.getApp_open_ads().getAdx(), activity, state_load -> {
                      Intent intent=new Intent(getApplicationContext(),AlladsActivity.class);
                      startActivity(intent);
                      finish();

                    });
                } else{
                    Intent intent=new Intent(getApplicationContext(),AlladsActivity.class);
                    startActivity(intent);
                    finish();
                }

            });
        } else {
            Intent intent=new Intent(getApplicationContext(),AlladsActivity.class);
            startActivity(intent);
            finish();
        }

    }
}