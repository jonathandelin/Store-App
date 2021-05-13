package com.example.cafeapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Class that defines Order Object to contain details of each menu item being order
 * by the user. Methods used are:  add(),
 * remove(), toString(), describeContents(), setSubTotal(), setTax(), setFinalTotal(),
 * and writeToParcel().
 *
 * @author Jasmin Kaur, Jonathan Delin
 */

public class Order implements Customizable, Parcelable {

    //private static final double NJ_TAX = 0.06625;
    private static final String DOLLAR_FORMAT = "#,##0.00";

    private double subTotal;
    private double tax;
    private double finalTotal;

    private int orderID;
    private ArrayList<MenuItem> itemsList;

    /**
     * Constructor for order object
     * @param orderNumber order number
     */
    public Order(int orderNumber)
    {
        this.orderID = orderNumber;
        ArrayList<MenuItem> itemList = new ArrayList<>();
        this.itemsList = itemList;
        this.subTotal = 0;
        this.tax = 0;
        this.finalTotal = 0;
    }

    /**
     * Makes the Order object with Parcelable data
     * @param in, Parcel that retrieves the Order data
     */
    protected Order(Parcel in) {
        orderID = in.readInt();
        itemsList = in.createTypedArrayList(MenuItem.CREATOR);
        subTotal = in.readDouble();
        tax = in.readDouble();
        finalTotal = in.readDouble();
    }

    /**
     * Creator class allows for retrieval of ClassLoader of object by the system.
     */
    public static final Creator<Order> CREATOR = new Creator<Order>() {

        /**
         * Make new instance of Parcelable, using the info from writeToParcel()
         * @param in, Parcel which we read object data
         */
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        /**
         * Make new Parcelable array
         * @param size, array size
         */
        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    /**
     * getter method for items list
     * @return itemsList, the list of items
     */
    public ArrayList<MenuItem> getItemsList() {
        return itemsList;
    }

    /**
     * Determines object is an instance of menu item before added it to list
     * @param obj object being compared
     * @return true if identical and is added to list
     */
    public boolean add(Object obj)
    {
        if(obj instanceof MenuItem) {
            itemsList.add((MenuItem) obj);
            return true;
        } else {
            return false;
        }
    }


    /**
     * Determines object is an instance of menu item before removing it from list
     * @param obj object being compared
     * @return true if identical and is removed from list
     */
    public boolean remove(Object obj)
    {
        if(obj instanceof MenuItem)
        {
            itemsList.remove(obj);
            return true;
        }
        return false;
    }

    /**
     * setter method for array list of Order objects
     * @param orderedItems items ordered
     */
    public void setOrderedItems(ArrayList<MenuItem> orderedItems) {
        this.itemsList = orderedItems;
    }


    /**
     * toString method that returns the order as a string
     * @return string of the Order object
     */
    @Override
    public String toString()
    {
        DecimalFormat df = new DecimalFormat(DOLLAR_FORMAT);
        String orderString = "";
        for (MenuItem menuItem : itemsList)
        {
            orderString = orderString + menuItem.toString() + "\n";
        }
        return "Order ID" + this.orderID + "\n" +
                "Sub-Total: $" + df.format(this.subTotal) + "\n" +
                "Tax: $" + df.format(this.tax) + "\n" +
                "Total: $" + df.format(this.finalTotal) + "\n" +
                orderString + "\n";
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
     * setter method for subtotal
     * @param subTotal subtotal of menu items
     */
    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    /**
     * setter method for tax
     * @param tax tax
     */
    public void setTax(double tax) {
        this.tax = tax;
    }

    /**
     * setter method for final cost of ordered items
     * @param finalTotal final total with tax included
     */
    public void setFinalTotal(double finalTotal) {
        this.finalTotal = finalTotal;
    }

    /**
     * Makes the object into Parcel
     *
     * @param dest, the destination where the object is written
     * @param flags, indicator for the way the object is written
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(orderID);
        dest.writeTypedList(itemsList);
        dest.writeDouble(subTotal);
        dest.writeDouble(tax);
        dest.writeDouble(finalTotal);
    }
}
