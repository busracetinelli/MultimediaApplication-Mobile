package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CallActivity extends AppCompatActivity {

    EditText enterPhoneNumber;
    Button callButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        enterPhoneNumber = (EditText) findViewById(R.id.phone_number_et);
        callButton = (Button) findViewById(R.id.call_button);

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String receivedPhoneNumber = enterPhoneNumber.getText().toString();
                callPhoneNumber(receivedPhoneNumber);

            }
        });
    }

    public void callPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);//telefon etme işlemni gerçekleştirecek uygulamaya yönlendiren intent
        intent.setData(Uri.parse("tel:" + phoneNumber)); //telefon numarası intentle yollanacak
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
