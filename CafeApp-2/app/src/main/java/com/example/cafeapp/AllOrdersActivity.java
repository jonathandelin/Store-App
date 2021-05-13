package com.example.cafeapp;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Activity class for all orders placed thus far. It allows user to view all orders
 * and remove any. Methods in this class are: onCreate(), and deleteOrder().
 * @author Jasmin Kaur, Jonathan Delin
 */

public class AllOrdersActivity extends AppCompatActivity {

    private ListView orderListView;
    private MainActivity mainActivity;
    private Order retrievedOrder;
    private ArrayAdapter<Order> orders;
    private ArrayList<Order> orderClone;
    private int itemPosition;


    /**
     * method to iniatlize view of class
     * @param savedInstanceState instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("All Orders");
        setContentView(R.layout.activity_store_orders);

        Toast.makeText(getApplicationContext(), "Press an order to select and use cancel button to cancel",
                Toast.LENGTH_SHORT).show();

        orderListView = findViewById(R.id.storeorderlistview);
        orders = new ArrayAdapter<Order>(this, android.R.layout.simple_list_item_1, MainActivity.allOrders.getListOfStoreOrders());
        orderListView.setAdapter(orders);

        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemPosition = position;
            }
        });

    }

    /**
     * method to delete order from all store orders
     * @param view view
     */
    public void deleteOrder(View view){
        if(!MainActivity.allOrders.getListOfStoreOrders().isEmpty() ) {
            MainActivity.allOrders.getListOfStoreOrders().remove(itemPosition);
            orders.notifyDataSetChanged();
        } else {
            Toast.makeText(getApplicationContext(), "Please add Orders or press an existing order to delete!",
                    Toast.LENGTH_SHORT).show();
        }
    }

}