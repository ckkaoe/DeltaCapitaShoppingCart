package com.shoppingcart.model;

import lombok.Data;

@Data
public class Item {
    public enum DiscountType
    {
        NONE, BUY_ONE_GET_ONE, BUY_THREE_FOR_TWO
    }

    private String name;
    private int price;
    private DiscountType discountType;

    public Item(String name, int price, DiscountType discountType) {
        this.name = name;
        this.price = price;
        this.discountType = discountType;
    }

}