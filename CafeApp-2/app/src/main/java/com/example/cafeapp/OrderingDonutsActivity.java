package com.example.cafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.text.DecimalFormat;

/**
 * Activity class for ordering Donuts. Allows users to select which donut they would like to
 * order and the quantity for each. User can then submit order to current order.
 * Methods used are: onCreate(), setSubTotal(), submitOrder(), onItemSelected(),
 * onNothingSelected()
 *
 *
 * @author Jasmin Kaur, Jonathan Delin
 */

public class OrderingDonutsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner donutFlavor, quantity;
    private static final String DECIMAL_FORMAT_STR = "#,##0.00";
    private DecimalFormat df = new DecimalFormat(DECIMAL_FORMAT_STR);
    private ArrayList<Donut> donutBox = new ArrayList<>();
    private double runningTotal = 0;
    private double orderTotal = 0;

    /**
     * sets view for ordering donuts activity
     * @param savedInstanceState instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Ordering Donuts");
        setContentView(R.layout.activity_ordering_donuts);

        Spinner spinner = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.quantity, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Spinner spinner_flavor = findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.donut_flavor, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_flavor.setAdapter(adapter2);
        spinner_flavor.setOnItemSelectedListener(this);

        TextView textView = (TextView) findViewById(R.id.textView5);
        textView.setText(Double.toString(runningTotal));

        TextView textView_Order = (TextView) findViewById(R.id.textView10);
        textView_Order.setText(df.format(orderTotal));


    }

    /**
     * calculates subtotal based off of how many donuts are selected
     * @param view view
     */
    public void setSubTotal(View view){
        donutFlavor = findViewById(R.id.spinner3);
        String flavor = donutFlavor.getSelectedItem().toString();
        quantity = findViewById(R.id.spinner2);
        String num = quantity.getSelectedItem().toString();
        int totalNum = Integer.parseInt(num);
        double runningTotal=0;
        switch(flavor) {
            case "Chocolate":
                Donut donut1 = new Donut("Donut", Prices.donut, totalNum, "Chocolate");
                runningTotal += Prices.donut*totalNum;
                donut1.itemPrice();
                donutBox.add(donut1);
                break;

            case "Vanilla":
                Donut donut2 = new Donut("Donut", Prices.donut, totalNum, "Vanilla");
                runningTotal += Prices.donut*totalNum;
                donut2.itemPrice();
                donutBox.add(donut2);
                break;

            case "Blueberry":
                Donut donut3 = new Donut("Donut", Prices.donut, totalNum, "Blueberry");
                runningTotal += Prices.donut*totalNum;
                donut3.itemPrice();
                donutBox.add(donut3);
                break;

            case "Strawberry":
                Donut donut4 = new Donut("Donut", Prices.donut, totalNum, "Strawberry");
                runningTotal += Prices.donut*totalNum;
                donut4.itemPrice();
                donutBox.add(donut4);
                break;

            case "Glazed":
                Donut donut5 = new Donut("Donut", Prices.donut, totalNum, "Glazed");
                runningTotal += Prices.donut*totalNum;
                donut5.itemPrice();
                donutBox.add(donut5);
                break;
        }

        orderTotal += runningTotal;
        TextView textView_Order = (TextView) findViewById(R.id.textView10);
        textView_Order.setText(df.format(orderTotal));
    }

    /**
     * method to submit order to current order
     * @param view view
     */
    public void submitOrder(View view)
    {
        if(!donutBox.isEmpty()){
            Toast.makeText(getApplicationContext(), "Donut added to order!",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putParcelableArrayListExtra("donutBox", (ArrayList<? extends Parcelable>) donutBox);
            setResult(RESULT_OK, intent);
            finish();


        }
        else {
            Toast.makeText(getApplicationContext(), "No donut selected!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * method that sets price when quantity is selected
     * @param parent parent
     * @param view view
     * @param position position
     * @param id id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

        quantity = findViewById(R.id.spinner2);

        String num = quantity.getSelectedItem().toString();
        int totalNum = Integer.parseInt(num);

        TextView textView = (TextView) findViewById(R.id.textView5);
        textView.setText(df.format(Prices.donut * totalNum));
    }

    /**
     * method to handle when no quantity is selected
     *
     * @param parent
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        return;
    }
}