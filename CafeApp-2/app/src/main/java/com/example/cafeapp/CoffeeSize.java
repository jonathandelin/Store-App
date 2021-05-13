package com.example.cafeapp;

/**
 * enum class for the sizes available for coffees and the prices
 * @author Jasmin Kaur, Jonathan Delin
 */

public enum CoffeeSize {
    SHORT(1.99),
    TALL(2.49),
    GRANDE(2.99),
    VENTI(3.49);

    private double price;

    /**
     * Sets coffee size price
     *
     * @param price the price as a double
     */
    CoffeeSize(double price) {
        this.price = price;
    }

    /**
     * gets the price of the CoffeeSize
     *
     * @return price as a double value
     */
    public double getPrice() {
        return price;
    }
}
