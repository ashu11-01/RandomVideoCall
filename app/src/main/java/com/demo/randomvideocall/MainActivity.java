package com.demo.randomvideocall;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.sinch.android.rtc.ClientRegistration;
import com.sinch.android.rtc.Sinch;
import com.sinch.android.rtc.SinchClient;
import com.sinch.android.rtc.SinchClientListener;
import com.sinch.android.rtc.SinchError;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    SinchClient sinchClient;
    Button btnGetStarted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inintviews();
        initializeSinch();
    }

    private void inintviews() {
        btnGetStarted = findViewById(R.id.btn_get_started);
    }

    private void initializeSinch() {
        sinchClient = Sinch.getSinchClientBuilder().context(getApplicationContext())
                .applicationKey(getString(R.string.sinch_app_key))
                .applicationSecret(getString(R.string.app_secret))
                .environmentHost("clientapi.sinch.com")
                .userId("123456")
                .build();
        sinchClient.setSupportCalling(true);
//        sinchClient.setSupportManagedPush(true);
        sinchClient.addSinchClientListener(new SinchClientListener() {
            @Override
            public void onClientStarted(SinchClient sinchClient) {
                Log.d(TAG,"onClientStarted");
            }

            @Override
            public void onClientStopped(SinchClient sinchClient) {
                Log.d(TAG,"onClientStopped");
            }

            @Override
            public void onClientFailed(SinchClient sinchClient, SinchError sinchError) {
                Log.d(TAG,"onClientFailed");
            }

            @Override
            public void onRegistrationCredentialsRequired(SinchClient sinchClient, ClientRegistration clientRegistration) {
                Log.d(TAG,"onRegistrationCredentialsRequired");
            }

            @Override
            public void onLogMessage(int i, String s, String s1) {

            }
        });
        sinchClient.start();
    }
}
