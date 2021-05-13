package com.example.cafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Activity class for ordering coffee. Allows user to select size and quantity of coffee and apply any addins
 * such as caramel, milk, syrup, cream or whipped cream. Once done, user can submit
 * order to current orders. Methods used are: onCreate(), setSubTotal(), submitOrder(),
 * onItemSelected(), onNothingSelected().
 *
 * @author Jasmin Kaur, Jonathan Delin
 */

public class OrderingCoffeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner size;
    private Spinner quantity;

    private CheckBox milk, cream, syrup, caramel, whippedCream;
    private int addinAmount;

    private double addinCost;
    private ArrayList<Addins> addins = new ArrayList<Addins>();


    DecimalFormat df = new DecimalFormat("#,###,##0.00");

    private Coffee coffee = new Coffee("Coffee", CoffeeSize.SHORT.getPrice(), "Short", addins, addinAmount);

    /**
     * sets view to ordering coffee activity
     * @param savedInstanceState instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Ordering Coffee");
        setContentView(R.layout.activity_ordering_coffee);

        Spinner spinner_quantity = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.quantity, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_quantity.setAdapter(adapter3);
        spinner_quantity.setOnItemSelectedListener(this);

        milk=(CheckBox)findViewById(R.id.checkBox);
        cream=(CheckBox)findViewById(R.id.checkBox2);
        syrup=(CheckBox)findViewById(R.id.checkBox3);
        caramel=(CheckBox)findViewById(R.id.checkBox4);
        whippedCream=(CheckBox)findViewById(R.id.checkBox5);

        Spinner spinner_size = findViewById(R.id.spinner4);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.coffee_sizes, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_size.setAdapter(adapter4);
        spinner_size.setOnItemSelectedListener(this);

        TextView textView2 = (TextView) findViewById(R.id.textView9);
        textView2.setText(Double.toString(addinCost));
    }

    /**
     * calculates subtotal based off size and quantity of coffee, and if any addins are
     * selected.
     * @param view view
     */
    public void setSubTotal(View view){
        TextView textView3 = (TextView) findViewById(R.id.textView9);
        size = findViewById(R.id.spinner4);

        String selectedSize = size.getSelectedItem().toString();
        quantity = findViewById(R.id.spinner);
        int quan = Integer.parseInt(quantity.getSelectedItem().toString());
        coffee.setQuantity(quan);

        addinCost = 0;

        if(cream.isChecked()){
            addinCost += Prices.addin;
            if(!coffee.getAddins().contains(Addins.CREAM))
                coffee.add(Addins.CREAM);
        } else {
            coffee.remove(Addins.CREAM);
        }

        if(syrup.isChecked()){
            addinCost += Prices.addin;
            if(!coffee.getAddins().contains(Addins.SYRUP))
                coffee.add(Addins.SYRUP);
        } else {
            coffee.remove(Addins.SYRUP);
        }

        if(milk.isChecked()){
            addinCost += Prices.addin;
            if(!coffee.getAddins().contains(Addins.MILK))
                coffee.add(Addins.MILK);
        } else {
            coffee.remove(Addins.MILK);
        }

        if(caramel.isChecked()){
            addinCost += Prices.addin;
            if(!coffee.getAddins().contains(Addins.CARAMEL))
                coffee.add(Addins.CARAMEL);
        } else {
            coffee.remove(Addins.CARAMEL);
        }

        if(whippedCream.isChecked()){
            addinCost += Prices.addin;
            if(!coffee.getAddins().contains(Addins.WHIPPED_CREAM))
                coffee.add(Addins.WHIPPED_CREAM);
        } else {
            coffee.remove(Addins.WHIPPED_CREAM);
        }

        switch(selectedSize) {
            case "short":

                coffee.setSize(selectedSize);
                coffee.itemPrice(Prices.shortCoffee);
                break;

            case "tall":


                coffee.setSize(selectedSize);
                coffee.itemPrice(Prices.tallCoffee);
                break;

            case "grande":


                coffee.setSize(selectedSize);
                coffee.itemPrice(Prices.grandeCoffee);
                break;

            case "venti":


                coffee.setSize(selectedSize);
                coffee.itemPrice(Prices.ventiCoffee);
                break;
        }

        textView3.setText(df.format((coffee.getItemCost() + addinCost) * quan));
        coffee.itemPrice((coffee.getItemCost() + addinCost)*quan);

    }



    /**
     * Submits order to current coffee order.
     * @param view view
     */
    public void submitOrder(View view) {
        Toast.makeText(getApplicationContext(), "Coffee added to order!",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra("Coffee", coffee);
        setResult(RESULT_OK, intent);
        finish();

    }

    /**
     *sets text for subtotal based on coffee size and quantity
     * @param parent parent
     * @param view view
     * @param position position
     * @param id id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

        quantity = findViewById(R.id.spinner);
        int quan = Integer.parseInt(quantity.getSelectedItem().toString());
        coffee.setQuantity(quan);

        size = findViewById(R.id.spinner4);
        String selectedSize = size.getSelectedItem().toString();

        switch(selectedSize) {
            case "short":
                coffee.setSize(selectedSize);
                coffee.itemPrice(Prices.shortCoffee);
                break;

            case "tall":
                coffee.setSize(selectedSize);
                coffee.itemPrice(Prices.tallCoffee);
                break;

            case "grande":
                coffee.setSize(selectedSize);
                coffee.itemPrice(Prices.grandeCoffee);
                break;

            case "venti":
                coffee.setSize(selectedSize);
                coffee.itemPrice(Prices.ventiCoffee);
                break;
        }

        TextView textView4 = (TextView) findViewById(R.id.textView9);
        textView4.setText(df.format((coffee.getItemCost() + addinCost) * quan));
        coffee.itemPrice((coffee.getItemCost() + addinCost)*quan);
    }

    /**
     * method to handle nothing selected for checkboxes
     * @param parent
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        return;
    }


}