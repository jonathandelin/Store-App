package com.example.cafeapp;

/**
 * Enum class for various addins available for ordering coffee
 * @author Jasmin Kaur, Jonathan Delin
 */

public enum Addins {
    CREAM(0.20),
    SYRUP(0.20),
    MILK(0.20),
    CARAMEL(0.20),
    WHIPPED_CREAM(0.20);

    private double addinsPrice;

    /**
     * Sets price of addins
     * @param addInCost
     */
    Addins(double addInCost)
    {
        this.addinsPrice = addinsPrice;
    }

    /**
     * retrieve the add ins price
     * @return addinsPrice, the price of the addins
     */
    public double addinsCost() {
        return addinsPrice;
    }
}
