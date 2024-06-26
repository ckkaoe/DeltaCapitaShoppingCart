package com.shoppingcart.service;

import com.shoppingcart.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{

    @Autowired
    private DiscountService discountService;

    //private final DiscountService discountService = new DiscountService();
    private static final Map<String, Item> ITEM_MAP = new HashMap<>();

    private static final int APPLE_PRICE = 35;
    private static final int BANANA_PRICE = 20;
    private static final int MELON_PRICE = 50;
    private static final int LIME_PRICE = 15;

    static {
        ITEM_MAP.put("Apple", new Item("Apple", APPLE_PRICE, Item.DiscountType.NONE));
        ITEM_MAP.put("Banana", new Item("Banana", BANANA_PRICE, Item.DiscountType.NONE));
        ITEM_MAP.put("Melon", new Item("Melon", MELON_PRICE, Item.DiscountType.BUY_ONE_GET_ONE));
        ITEM_MAP.put("Lime", new Item("Lime", LIME_PRICE, Item.DiscountType.BUY_THREE_FOR_TWO));
    }

    @Override
    public int calculateTotal(List<String> items) {
        Map<String, Integer> itemCount = new HashMap<>();

        for (String item : items) {
            itemCount.put(item, itemCount.getOrDefault(item, 0) + 1);
        }

        int total = 0;

        for (Map.Entry<String, Integer> entry : itemCount.entrySet()) {
            String itemName = entry.getKey();
            int count = entry.getValue();
            Item item = ITEM_MAP.get(itemName);

            if (item == null) {
                continue;
            }

            int price = item.getPrice();

            switch(item.getDiscountType()){
                case BUY_ONE_GET_ONE:
                    total += discountService.buyOneGetOneFree(count, price);
                    break;
                case BUY_THREE_FOR_TWO:
                    total += discountService.buyThreeForTwo(count, price);
                    break;
                default:
                    total += count * price;
                    break;
            }
        }

        return total;
    }
}
