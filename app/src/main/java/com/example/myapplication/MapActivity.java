package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MapActivity extends AppCompatActivity {

    Button openMapPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        openMapPage = (Button) findViewById(R.id.open_map_page);


        openMapPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //İstanbul enlem ve boylam
                Uri geoLocation = Uri.parse("geo:41.0138400,28.9496600"); //lokasyonu alır
                showMap(geoLocation);
            }
        });
    }


    public void showMap(Uri geoLocation) { //lokasyon parameteresi
  /*
        Uri gmmIntentUri = Uri.parse("google.streetview:cbll=46.414382,10.013988");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
  */

        Intent intent = new Intent(Intent.ACTION_VIEW); //uygulamadayken başka bir uygulamayı gösterebilmek için
        intent.setData(geoLocation); //intentle beraber lokasyonumuzu gönderiyoruz
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent); //haritayı açar
        }
    }
}
