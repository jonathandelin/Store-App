package com.example.cafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Activity class for current order details. User can view menu items they have
 * added to order thus far and can remove any they want to. Methods used are: onCreate(),
 * removeItem(), calculateTotal(), confirmOrder().
 *
 * @author Jasmin Kaur, Jonathan Delin
 */

public class OrderDetailsActivity extends AppCompatActivity {

    private ListView listView;
    private int itemPosition;
    private double subTotal = 0;
    private double tax_cost = 0;
    private double finalTotal = 0;
    private ArrayList<MenuItem> orderList;
    private ArrayAdapter<MenuItem> items;

    /**
     * sets view to current order activity
     * @param savedInstanceState instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Current Items in Order");
        setContentView(R.layout.activity_order_details);

        Toast.makeText(getApplicationContext(), "Press an item to select and use remove button to remove",
                Toast.LENGTH_SHORT).show();

        Intent intent = getIntent();
        orderList = intent.getParcelableArrayListExtra("Order");

        listView = findViewById(R.id.listview);
        items = new ArrayAdapter<MenuItem>(this, android.R.layout.simple_list_item_1, orderList);
        listView.setAdapter(items);

        calculateTotal(orderList);
        MainActivity.order.setTax(tax_cost);
        MainActivity.order.setSubTotal(subTotal);
        MainActivity.order.setFinalTotal(finalTotal);


        TextView taxView = (TextView) findViewById(R.id.textView12);
        taxView.setText(Double.toString(tax_cost));

        TextView subTotalView = (TextView) findViewById(R.id.textView13);
        subTotalView.setText(Double.toString(subTotal));

        TextView finalTotalView = (TextView) findViewById(R.id.textView14);
        finalTotalView.setText(Double.toString(finalTotal));




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemPosition = position;
            }
        });


    }

    /**
     * method to remove menu item from current order
     * @param view view
     */
    public void removeItem(View view){
        if(!orderList.isEmpty()) {
            orderList.remove(itemPosition);
            items.notifyDataSetChanged();

            calculateTotal(orderList);
            MainActivity.order.setTax(tax_cost);
            MainActivity.order.setSubTotal(subTotal);
            MainActivity.order.setFinalTotal(finalTotal);


            TextView taxView = (TextView) findViewById(R.id.textView12);
            taxView.setText(Double.toString(tax_cost));

            TextView subTotalView = (TextView) findViewById(R.id.textView13);
            subTotalView.setText(Double.toString(subTotal));

            TextView finalTotalView = (TextView) findViewById(R.id.textView14);
            finalTotalView.setText(Double.toString(finalTotal));

        } else {
            Toast.makeText(getApplicationContext(), "Please add menu items or press a menu item to delete",
                    Toast.LENGTH_SHORT).show();
        }


    }

    /**
     * method to calculate total of all items ordered
     * @param orderList array list
     */
    private void calculateTotal(ArrayList<MenuItem> orderList)
    {
        double sum = 0;
        for (MenuItem menuItem : orderList)
        {
            sum += menuItem.getItemCost();
        }
        this.subTotal = Math.floor((0 + sum)*100) / 100;

        this.tax_cost = Math.floor((this.subTotal * Prices.tax) * 100) / 100;

        this.finalTotal = Math.floor((this.subTotal + this.tax_cost) * 100) / 100;
    }

    /**
     * method to confirm and submit order to store.
     * @param view view
     */
    public void confirmOrder(View view) {
        if(orderList.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Your order is empty!",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Order placed!",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtra("placedOrder", orderList);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

}
