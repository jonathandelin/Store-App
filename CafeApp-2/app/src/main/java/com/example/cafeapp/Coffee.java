package com.example.cafeapp;
/**
 * Class that outlines the Coffee object. Describes the fields of a Coffee object and its constructor.
 * Methods used in this class are: writeToParcel(), describeContents(), setSize(),
 * allAddInsCost(), toString(), equals(), itemPrice(), add(), and remove().
 *
 * @author Jasmin Kaur, Jonathan Delin
 */


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Coffee extends MenuItem implements Customizable, Parcelable {
    private String size;
    private ArrayList<Addins> addins;
    private int quantity;

    /**
     * Coffee constructor
     * @param itemName menu item name: coffee
     * @param itemCost cost of coffee
     * @param size size of coffee
     * @param addins addins applied to coffee
     * @param quantity the amount that coffee ordered
     */
    public Coffee(String itemName, double itemCost, String size, ArrayList<Addins> addins, int quantity)
    {
        super(itemName, itemCost);
        this.size = size;
        this.addins = addins;
        this.quantity = quantity;
    }

    /**
     * Makes the Coffee object with Parcelable data
     * @param in, Parcel that retrieves the Coffee data
     */
    protected Coffee(Parcel in) {
        super(in);
        size = in.readString();
        addins = in.readArrayList(null);
        quantity = in.readInt();
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
        dest.writeString(size);
        dest.writeList(addins);
        dest.writeInt(quantity);
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
     * Creator class allows for retrieval of ClassLoader of object by the system.
     */
    public static final Creator<Coffee> CREATOR = new Creator<Coffee>() {

        /**
         * Make new instance of Parcelable, using the info from writeToParcel()
         * @param in, Parcel which we read object data
         */
        @Override
        public Coffee createFromParcel(Parcel in) {
            return new Coffee(in);
        }

        /**
         * Make new Parcelable array
         * @param size, array size
         */
        @Override
        public Coffee[] newArray(int size) {
            return new Coffee[size];
        }
    };

    /**
     * Sets size of the Coffee
     * @param size CoffeeSize
     */
    public void setSize(String size)
    {
        this.size = size;
    }

    /**
     * Sets quantity ordered
     * @param quantity, amount of coffee ordered
     */
    public void setQuantity(int quantity) { this.quantity = quantity; }

    /**
     * Getter method for retrieving the addins of a Coffee object
     *
     * @return this.addins, the addins
     */
    public ArrayList<Addins> getAddins(){return this.addins;}

    /**
     * Method calculates the cost of all add ins in the Coffee
     *
     * @return cost, double value of the cost of all add ins
     */
    private double allAddInsCost()
    {
        double cost = 0;
        for (Addins addins : addins)
        {
            cost += addins.addinsCost();
        }
        return cost;
    }

    /**
     * toString method that displays the Coffee objects fields
     *
     * @return String of Coffee object with all relevant fields
     */
    @Override
    public String toString()
    {
        return super.toString() + " Quantity: "+ this.quantity + "\n" + "Size: " + size + "\n Add-ins: " + addins.toString();
    }

    /**
     * Method calculates Coffee object price and also sets it
     */
    //@Override
    public void itemPrice(double price)
    {
        this.setItemCost(price + allAddInsCost());
    }


    /**
     * Adds an addin to the Coffee.
     *
     * @param obj the addin that is being attempted to be added
     * @return true if correct addin object is added
     */
    @Override
    public boolean add(Object obj)
    {
        if(obj instanceof Addins) {
            addins.add((Addins) obj);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Removes an addin from the Coffee
     *
     * @param obj the addin that being removed
     * @return true if the addin object is removed
     */
    @Override
    public boolean remove(Object obj)
    {
        if(obj instanceof Addins) {
            addins.remove(obj);
            return true;
        } else {
            return false;
        }
    }
}
