package com.cit.medi_screen;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class CallActivity extends AppCompatActivity {
    private Button callGP;
    private Button callInsurer;
    private DatabaseAccess dbAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
       // dbAccess = getDBAccess();
        dbAccess = new DatabaseAccess();
        callGP = findViewById(R.id.callGPButton);
        callGP = findViewById(R.id.callGPButton);
        callInsurer = findViewById(R.id.callInsurerButton);
        callGP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phoneIntent = new Intent(Intent.ACTION_CALL);
                String phoneNum = "tel:" + getGPPhone();
                phoneIntent.setData(Uri.parse(phoneNum));
                if (ActivityCompat.checkSelfPermission(CallActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CallActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    startActivity(phoneIntent);
                }
            }
        });
        callInsurer.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Intent phoneIntent = new Intent(Intent.ACTION_CALL);
                String phoneNum = "tel:" + getInsurePhone();

                phoneIntent.setData(Uri.parse(phoneNum));
                if (ActivityCompat.checkSelfPermission(CallActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CallActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    startActivity(phoneIntent);
                }
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String getInsurePhone() {
       // dbAccess.open();
        return dbAccess.getInsurePhone();
    }

    public String getGPPhone() {
       // dbAccess.open();
        return dbAccess.getGPPhone();
    }

/*
    private DatabaseAccess getDBAccess() {
        return DatabaseAccess.getInstance(getApplicationContext());
    }

 */
}