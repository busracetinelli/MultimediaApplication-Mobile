package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SmsActivity extends AppCompatActivity {

    EditText senderMessage;
    EditText receiverNumber;
    Button sendSmsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        senderMessage = (EditText) findViewById(R.id.sender_message);
        receiverNumber = (EditText) findViewById(R.id.receiver_phone_number);
        sendSmsButton = (Button) findViewById(R.id.send_sms_button);
        sendSmsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = senderMessage.getText().toString(); //yazılan mesajı alır
                String phoneNumber = receiverNumber.getText().toString(); //telefon numarasını alır
                composeMmsMessage(message,phoneNumber);

            }
        });


    }

    public void composeMmsMessage(String message, String phoneNumber) {

        Uri uri = Uri.parse("smsto:" + phoneNumber); //bilgileri uri tipine çevirdi
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri); //mesaj atmak için gerekli olan uygulamaya yönlendirecek intent
        intent.putExtra("sms_body", message); //mesaj içeri bilgisi intent ile yollandı
        startActivity(intent);
    }
}
