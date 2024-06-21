package com.shoppingcart.service;

import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService{
    @Override
    public int buyOneGetOneFree(int count, int price) {
        return (count / 2 + count % 2) * price;
    }

    @Override
    public int buyThreeForTwo(int count, int price) {
        return ((count / 3) * 2 + count % 3) * price;
    }
}
