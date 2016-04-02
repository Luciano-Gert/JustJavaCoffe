package com.example.android.justjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {


    //Global View Variables
    public EditText nameEditText;
    public CheckBox wCreamBox;
    public CheckBox chocolate;

    // Global quantity variable
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wCreamBox = (CheckBox) findViewById(R.id.tp1_checkBox);
        chocolate = (CheckBox) findViewById(R.id.tp2_checkBox);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
    }

    // Method for order button
    public void submitOrder(View view) {
        int price = calculatePrice();
        String priceMessage = createOrderSummary(price);
        String nameText = nameEditText.getText().toString();
        Intent intent = new Intent(this, InvoiceOrder.class);
        intent.putExtra("message", priceMessage);
        intent.putExtra("name", nameText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    // Method for + button
    public void increment(View view) {
        if (quantity < 100)
            quantity = quantity + 1;
        else
            Toast.makeText(MainActivity.this, getResources().getString(R.string.Toast100), Toast.LENGTH_SHORT).show();
        display(quantity);
    }

    // Method for - button
    public void decrement(View view) {
        if (quantity > 1)
            quantity = quantity - 1;
        else
            Toast.makeText(MainActivity.this, getResources().getString(R.string.Toast1), Toast.LENGTH_SHORT).show();
        display(quantity);
    }

    //Method that calculate the coffe price
    private int calculatePrice() {
        int price;
        int priceFor1 = 5;
        if (wCreamBox.isChecked())
            priceFor1 = priceFor1 + 1;
        if (chocolate.isChecked())
            priceFor1 = priceFor1 + 2;

        price = priceFor1 * quantity;
        return price;
    }

    //Method to create a order summary string
    public String createOrderSummary(int totalPrice) {
        String nameText = nameEditText.getText().toString();
        String message = getResources().getString(R.string.orderSummary1) + " " + nameText;
        if (wCreamBox.isChecked()) {
            message = message + getResources().getString(R.string.orderSummary2);
        } else
            message = message + getResources().getString(R.string.orderSummary3);

        if (chocolate.isChecked()) {
            message = message + getResources().getString(R.string.orderSummary4);
        } else
            message = message + getResources().getString(R.string.orderSummary5);

        message = message + getResources().getString(R.string.orderSummary6) + " " + quantity + getResources().getString(R.string.orderSummary7) + totalPrice + getResources().getString(R.string.orderSummary8);
        return message;
    }

    // Method to display the quantity on the TextView
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

}