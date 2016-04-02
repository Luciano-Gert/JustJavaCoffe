package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Luciano on 02-Apr-16 Brazil - Minas Gerais
 */
public class InvoiceOrder extends AppCompatActivity {

    public TextView messageTextView;
    public Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invoice_order);

        //Getting the TextView by ID
        messageTextView = (TextView) findViewById(R.id.message);

        //Calling the intent to get the message string
        Intent intent = getIntent();
        String message = intent.getExtras().getString("message");

        //Placing the message string in the message TextView for the invoice
        messageTextView.setText(message);

    }

    //Method to send the invoice via Gmail
    public void sendG(View view) {
        send = (Button) findViewById(R.id.sendToButton);
        String priceMessage = messageTextView.getText().toString();
        Intent intent = getIntent();
        String nameText = intent.getExtras().getString("name");

        Intent sendG = new Intent(Intent.ACTION_SENDTO);
        sendG.setType("plain/text");
        sendG.setData(Uri.parse("JustJava@gmail.com"));
        sendG.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
        sendG.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.Intent) + " " + nameText);
        sendG.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (sendG.resolveActivity(getPackageManager()) != null) {
            startActivity(sendG);
        }
    }
}