package com.example.android.justjava;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int coffeeq=0;
    int coffeep=5;
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
    String msg = "Order Successfull.";
        displaySuccess(msg);
    }
    //Increment order quantity
    public void increment(View view) {
        coffeeq++;
        display(coffeeq);
        displayPrice(coffeeq * coffeep);
    }
    //Decrement order quantity
    public void decrement(View view) {
        if(coffeeq > 0)
        coffeeq--;
        display(coffeeq);
        displayPrice(coffeeq * coffeep);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    // order successfull message
    private void displaySuccess(String ord) {
        TextView quantityTextView = (TextView) findViewById(R.id.order_text_view);
        quantityTextView.setText(ord);
    }
}