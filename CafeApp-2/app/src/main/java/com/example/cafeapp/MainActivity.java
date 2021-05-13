package com.example.cafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * This is the running class for the Cafe App. It begins with the main menu that gives
 * an option to either order coffee, order donuts, view the current order, and view all
 * orders. This class then opens a new window for whichever option is selected.
 * The methods in this class are: onCreate(), sendNextActivity_1(), sendNextActivity_2()
 * sendNextActivity_3(), sendNextActivity_4(), addCoffeeInCart(), addOrderInStoreOrder(),
 * getOrderInfo(), addDonutInCart(), onActivityResult().
 *
 * @author Jasmin Kaur, Jonathan Delin
 */

public class MainActivity extends AppCompatActivity {

    private static final int START_OF_ORDER_ID = 1;
    private static int orderID = START_OF_ORDER_ID;
    public static Order order = new Order(orderID);
    public static StoreOrder allOrders = new StoreOrder();
    private static final int ID3 = 3;


    /**
     * initializes class
     * @param savedInstanceState creates window
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Main Menu");
        setContentView(R.layout.activity_main);
    }

    /**
     * sendNextActivity_1 method is activated when ordering donuts is selected and
     * passes it to the next activity.
     *
     * @param view
     */
    public void sendNextActivity_1(View view) {
        Intent intent = new Intent(this, OrderingDonutsActivity.class);
        startActivityForResult(intent, 0);
    }

    /**
     * sendNextActivity_2 method is activated when ordering coffee is selected and
     * passes it to the next activity.
     *
     * @param view
     */
    public void sendNextActivity_2(View view) {
        Intent intent = new Intent(this, OrderingCoffeeActivity.class);
        startActivityForResult(intent, 1);
    }

    /**
     * sendNextActivity_3 method is activated when order details is selected and
     * passes it to the next activity.
     *
     * @param view
     */
    public void sendNextActivity_3(View view) {
        Intent intent = new Intent(this, OrderDetailsActivity.class);
        intent.putExtra("Order", order.getItemsList());
        startActivityForResult(intent, ID3);
    }

    /**
     * sendNextActivity_4 method is activated when store orders is selected and
     * passes it to the next activity.
     *
     * @param view
     */
    public void sendNextActivity_4(View view) {
        Intent intent = new Intent(this, AllOrdersActivity.class);
        startActivity(intent);
    }

    /**
     * A method to add a coffee order into the cart
     *
     * @param coffee the new coffee item that the user wants to add to the cart
     */
    public void addCoffeeInCart(Coffee coffee)
    {
        order.add(coffee);
    }

    /**
     * A method to add an order into store orders
     *
     * @param oldOrder the order which will be stored as a store order
     */
    public void addOrderInStoreOrder(Order oldOrder)
    {
        allOrders.add(oldOrder);
        order = createNewOrder();
    }

    /**
     * A helper method that gives the Order object a ID or number.
     *
     * @return int Order ID/number
     */
    private int giveOrderID()
    {
        return orderID += 1;
    }

    /**
     * A helper method that create a new Order object once the current order is ordered.
     *
     * @return a new Order object
     */
    private Order createNewOrder()
    {
        Order newOrder = new Order(giveOrderID());
        return newOrder;
    }

    /**
     * This method adds the donuts that the user ordered into the cart
     *
     * @param donutBox the collection of donuts that the user ordered
     */
    public void addDonutInCart(ArrayList<Donut> donutBox)
    {
        for (Donut donuts : donutBox)
        {
            order.add(donuts);
        }
    }

    /**
     * function to receive data from donut and coffee activity classes
     * @param requestCode code being requested from other classes
     * @param resultCode code that is received
     * @param data data received from other activities
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {

                ArrayList<Donut> myList = data.getParcelableArrayListExtra("donutBox");
                addDonutInCart(myList);
            }
        }

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Coffee myCoffee = data.getParcelableExtra("Coffee");
                addCoffeeInCart(myCoffee);

            }
        }

        if (requestCode == ID3) {
            if (resultCode == RESULT_OK) {
                ArrayList<MenuItem> newOrderList = data.getParcelableArrayListExtra("placedOrder");
                order.setOrderedItems(newOrderList);
                addOrderInStoreOrder(order);

            }

        }

    }

}