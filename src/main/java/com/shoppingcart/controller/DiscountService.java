package com.shoppingcart.controller;

import org.springframework.stereotype.Service;

@Service
public class DiscountService {
    public int buyOneGetOneFree(int count, int price) {
        return (count / 2 + count % 2) * price;
    }

    public int buyThreeForTwo(int count, int price) {
        return ((count / 3) * 2 + count % 3) * price;
    }
}
