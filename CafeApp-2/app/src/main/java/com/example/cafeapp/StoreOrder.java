package com.example.cafeapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Defines the object Store Orders which includes an array list to hold
 * all orders thus far. Methods used are:add(), remove(), toString(),
 * getStoreOrderList(), describeContents(), and writeToParcel().
 *
 * @author Jasmin Kaur, Jonathan Delin
 */

public class StoreOrder implements Parcelable {
    private ArrayList<Order> orders;

    /**
     * constructor for store order object
     */
    public StoreOrder()
    {
        ArrayList<Order> storeOrdersList = new ArrayList<>();
        this.orders = storeOrdersList;
    }

    /**
     * Makes the StoreOrder object with Parcelable data
     * @param in, Parcel that retrieves the StoreOrder data
     */
    protected StoreOrder(Parcel in) {
        orders = in.createTypedArrayList(Order.CREATOR);
    }

    /**
     * Creator class allows for retrieval of ClassLoader of object by the system.
     */
    public static final Creator<StoreOrder> CREATOR = new Creator<StoreOrder>() {
        /**
         * Make new instance of Parcelable, using the info from writeToParcel()
         * @param in, Parcel which we read object data
         */
        @Override
        public StoreOrder createFromParcel(Parcel in) {
            return new StoreOrder(in);
        }

        /**
         * Make new Parcelable array
         * @param size, array size
         */
        @Override
        public StoreOrder[] newArray(int size) {
            return new StoreOrder[size];
        }
    };

    /**
     * Gets the list of store orders
     *
     * @return orders, the array list of store orders
     */
    public ArrayList<Order> getListOfStoreOrders() {
        return orders;
    }

    /**
     * Add order to list of orders if it is an order
     *
     * @param obj the order to be verified and added
     * @return true if successful and false if not
     */
    public boolean add(Object obj)
    {
        if(obj instanceof Order)
        {
            orders.add((Order) obj);
            return true;
        }
        return false;
    }

    /**
     * Remove an Order from the list of orders if it is an order
     *
     * @param obj the Order that being removed
     * @return true if removed
     */
    public boolean remove(Object obj)
    {
        if(obj instanceof Order)
        {
            orders.remove(obj);
            return true;
        }
        return false;
    }

    /**
     * toString method that returns the StoreOrder as a string
     * @return string of the StoreOrder object
     */
    @Override
    public String toString()
    {
        String storeOrderString = "";
        for (Order order : orders)
        {
            storeOrderString = storeOrderString + order.toString();
        }
        return storeOrderString;
    }

    /**
     * Describes contents in the Parcelable instance
     *
     * @return 0, a default return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Makes the object into Parcel
     *
     * @param dest, the destination where the object is written
     * @param flags, indicator for the way the object is written
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(orders);
    }
}
