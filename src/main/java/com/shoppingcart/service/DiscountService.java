package com.shoppingcart.service;

public interface DiscountService {
    public int buyOneGetOneFree(int count, int price);

    public int buyThreeForTwo(int count, int price);
}
