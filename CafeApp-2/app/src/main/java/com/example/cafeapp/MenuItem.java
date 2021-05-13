package com.example.cafeapp;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.DecimalFormat;

/**
 * Defines menu item object which is extended by Donut and Coffee classes. It includes
 * the following methods: getItemCost(), setItemCost(), toString(), equals(),
 * itemPrice(), describeContents(), and writeToParcel().
 *
 * @author Jasmin Kaur, Jonathan Delin
 */

public class MenuItem implements Parcelable {
    private String itemType;
    private double itemCost;

    private static final String DOLLAR_FORMAT = "#,##0.00";


    /**
     * menu item constructor
     * @param itemType donut or coffee
     * @param itemCost cost of item
     */
    public MenuItem(String itemType, double itemCost)
    {
        this.itemType = itemType;
        this.itemCost = itemCost;
    }

    /**
     * Makes the MenuItem object with Parcelable data
     * @param in, Parcel that retrieves the MenuItem data
     */
    protected MenuItem(Parcel in) {
        itemType = in.readString();
        itemCost = in.readDouble();
    }

    /**
     * Creator class allows for retrieval of ClassLoader of object by the system.
     */
    public static final Creator<MenuItem> CREATOR = new Creator<MenuItem>() {

        /**
         * Make new instance of Parcelable, using the info from writeToParcel()
         * @param in, Parcel which we read object data
         */
        @Override
        public MenuItem createFromParcel(Parcel in) {
            return new MenuItem(in);
        }

        /**
         * Make new Parcelable array
         * @param size, array size
         */
        @Override
        public MenuItem[] newArray(int size) {
            return new MenuItem[size];
        }
    };

    /**
     * gets item cost
     * @return cost of menu item
     */
    public double getItemCost()
    {
        return itemCost;
    }


    /**
     * sets item cost
     * @param itemCost cost of menu item
     */
    public void setItemCost(double itemCost)
    {
        this.itemCost = itemCost;
    }


    /**
     * formats cost of item to two decimal places
     * @return String representation of Menu Item
     */
    @Override
    public String toString()
    {
        DecimalFormat df = new DecimalFormat(DOLLAR_FORMAT);
        return itemType + ": $" + df.format(itemCost);
    }


    /**
     * to be used in donut and coffee classes
     */
    public void itemPrice()
    {
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
        dest.writeString(itemType);
        dest.writeDouble(itemCost);
    }
}
