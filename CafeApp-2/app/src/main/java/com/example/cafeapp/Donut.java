package com.example.cafeapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class that defines Donut object. It includes all identifiable fields and the constructor
 * for the Donut object. The methods used in this class are: getQuantity(), toString(),
 * itemPrice(), describeContents(), writeToParcel().
 *
 * @author Jasmin Kaur, Jonathan Delin
 */

public class Donut extends MenuItem implements Parcelable {
    private int quantity;
    private String flavor;

    /**
     * constructor for Donut object
     * @param itemName menu item: donut
     * @param itemCost donut cost
     * @param quantity donut quantity
     * @param flavor donut flavor
     */
    public Donut(String itemName, double itemCost, int quantity, String flavor)
    {
        super(itemName, itemCost);
        this.quantity = quantity;
        this.flavor = flavor;
    }

    /**
     * Makes the Donut object with Parcelable data
     * @param in, Parcel that retrieves the Donut data
     */
    protected Donut(Parcel in) {
        super(in);
        quantity = in.readInt();
        flavor = in.readString();
    }

    /**
     * Creator class allows for retrieval of ClassLoader of object by the system.
     */
    public static final Creator<Donut> CREATOR = new Creator<Donut>() {
        /**
         * Make new instance of Parcelable, using the info from writeToParcel()
         * @param in, Parcel which we read object data
         */
        @Override
        public Donut createFromParcel(Parcel in) {
            return new Donut(in);
        }

        /**
         * Make new Parcelable array
         * @param size, array size
         */
        @Override
        public Donut[] newArray(int size) {
            return new Donut[size];
        }
    };

    /**
     * gets quantity of donuts
     * @return quantity, the quantity ordered
     */
    public int getQuantity()
    {
        return quantity;
    }


    /**
     * toString method that displays the Donut objects fields
     *
     * @return String of Donut object with all relevant fields
     */
    @Override
    public String toString()
    {
        return(super.toString() + " Quantity: " + quantity  + "\nFlavor: " + flavor);
    }

    /**
     * sets price based on quantity of donuts
     */
    @Override
    public void itemPrice()
    {
        this.setItemCost(this.getQuantity() * Prices.donut);
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
        super.writeToParcel(dest, flags);
        dest.writeInt(quantity);
        dest.writeString(flavor);
    }
}
