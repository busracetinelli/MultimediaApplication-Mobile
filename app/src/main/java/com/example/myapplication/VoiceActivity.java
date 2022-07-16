package com.example.myapplication;

import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;



import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class VoiceActivity extends AppCompatActivity implements View.OnClickListener {

    Button recordVoiceButton;
    Button stopVoiceButton;
    Button playVoiceButton;
    private MediaRecorder recorder;
    private MediaPlayer player;
    private final String filepath = Environment.getExternalStorageDirectory().getPath() + "/record.3gp";

    private static final int REQUEST_AUDIO_PERMISSION_CODE = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);

        recordVoiceButton = (Button) findViewById(R.id.record_voice_button);
        recordVoiceButton.setOnClickListener(this);
        stopVoiceButton = (Button) findViewById(R.id.stop_voice_button);
        stopVoiceButton.setOnClickListener(this);
        playVoiceButton = (Button) findViewById(R.id.play_voice_button);
        playVoiceButton.setOnClickListener(this);


    }


    //buttonları dinler
    @Override
    public void onClick(View view) {

        if (view == recordVoiceButton) {
           if(checkPermissions()){
               startRecording();
           } else {
               requestPermissions();
               startRecording();
           }
        } else if (view == stopVoiceButton) {
            stopRecording();
        } else if (view == playVoiceButton) {
            startPlaying();
        }

    }

    //kayıt etmek için gerekli fonksiyon
    private void startRecording() {

        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(filepath);


        try {
            recorder.prepare();
            recorder.start();
            Toast.makeText(getApplicationContext(),"Kayıt Yapılıyor", Toast.LENGTH_LONG).show();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //videoyu durdurmak için gerekli fonksiyon
    private void stopRecording() {
        if (recorder != null) {
            Toast.makeText(getApplicationContext(),"Kayıt Durduruldu", Toast.LENGTH_LONG).show();
            recorder.stop();
            recorder.reset();
            recorder.release();
            recorder = null;
        }
    }

    //kaydı oynatmak için gerekli fonksyion
    private void startPlaying() {
        player = new MediaPlayer();
        player.setVolume(1.0f, 1.0f);
        try {
            player.setDataSource(filepath);
            player.prepare();
            player.start();
            Toast.makeText(getApplicationContext(),"Kayıt Çalıyor", Toast.LENGTH_LONG).show();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer arg0) {
                    player.stop();
                    player.release();
                    player = null;
                }
            });
        } catch (Exception e) {
        }
    }

  //izinleri kontrol eder
    public boolean checkPermissions() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }
    //izinleri alır
    private void requestPermissions() {
        ActivityCompat.requestPermissions(VoiceActivity.this, new String[]{RECORD_AUDIO, WRITE_EXTERNAL_STORAGE}, REQUEST_AUDIO_PERMISSION_CODE);
    }





}
