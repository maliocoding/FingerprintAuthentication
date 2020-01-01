package com.example.fingerprint;

import android.content.DialogInterface;
import android.hardware.biometrics.BiometricPrompt;
import android.os.CancellationSignal;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Executor executor= Executors.newSingleThreadExecutor();

        final BiometricPrompt biometricpromt= new BiometricPrompt.Builder(this)
                .setTitle("Fingerprint")
                .setSubtitle("subtitle")
                .setNegativeButton("cancel", executor, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                    }).build();

        Button authenticate =findViewById((R.id.authenticate));
        final MainActivity activity=this;
        authenticate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                biometricpromt.authenticate(new CancellationSignal(), executor, new BiometricPrompt.AuthenticationCallback(){
                public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result){
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText( getApplicationContext(),"Authentitated",Toast.LENGTH_LONG).show();
                        }
                    });
                }

                });
            }
        });


    }
}
