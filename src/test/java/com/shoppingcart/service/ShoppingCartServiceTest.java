package com.shoppingcart.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ShoppingCartServiceTest {

    @Autowired
    private ShoppingCartService shoppingCartService;

    private static final int APPLE_PRICE = 35;
    private static final int BANANA_PRICE = 20;
    private static final int MELON_PRICE = 50;
    private static final int LIME_PRICE = 15;

    @Test
    public void testCalculateTotal() {
        List<String> items1 = Arrays.asList("Apple", "Apple", "Banana");
        assertEquals(APPLE_PRICE + APPLE_PRICE + BANANA_PRICE, shoppingCartService.calculateTotal(items1));

        List<String> items2 = Arrays.asList("Melon", "Melon", "Melon");
        assertEquals(2 * MELON_PRICE, shoppingCartService.calculateTotal(items2));

        List<String> items3 = Arrays.asList("Lime", "Lime", "Lime", "Lime");
        assertEquals(3 * LIME_PRICE, shoppingCartService.calculateTotal(items3));

        List<String> items4 = Arrays.asList("Apple", "Banana", "Melon", "Lime");
        assertEquals(APPLE_PRICE + BANANA_PRICE + MELON_PRICE + LIME_PRICE, shoppingCartService.calculateTotal(items4));
    }

    @Test
    public void testCalculateTotal_NoItems() {
        List<String> items = Arrays.asList();
        assertEquals(0, shoppingCartService.calculateTotal(items));
    }

    @Test
    public void testCalculateTotal_SingleItem() {
        List<String> items = Arrays.asList("Apple");
        assertEquals(APPLE_PRICE, shoppingCartService.calculateTotal(items));
    }

    @Test
    public void testCalculateTotal_MultipleDifferentItems() {
        List<String> items = Arrays.asList("Apple", "Banana", "Lime");
        assertEquals(APPLE_PRICE + BANANA_PRICE + LIME_PRICE, shoppingCartService.calculateTotal(items));
    }

    @Test
    public void testCalculateTotal_MultipleItemsWithOffers() {
        List<String> items = Arrays.asList("Melon", "Melon", "Lime", "Lime", "Lime");
        assertEquals(80, shoppingCartService.calculateTotal(items));
    }

    @Test
    public void testCalculateTotal_ComplexBasket() {
        List<String> items = Arrays.asList(
                "Apple", "Apple", "Banana",
                "Melon", "Melon", "Melon",
                "Lime", "Lime", "Lime",
                "Lime", "Lime", "Lime"
        );
        assertEquals(250, shoppingCartService.calculateTotal(items));
    }

    @Test
    public void testCalculateTotal_LargeQuantity() {
        String[] apples = new String[1000];
        Arrays.fill(apples, "Apple");
        List<String> items = Arrays.asList(apples);
        assertEquals(1000 * APPLE_PRICE, shoppingCartService.calculateTotal(items));
    }

    @Test
    public void testCalculateTotal_MultipleSpecialOffers() {
        List<String> items = Arrays.asList("Melon", "Melon", "Melon", "Melon", "Lime", "Lime", "Lime", "Lime", "Lime", "Lime", "Lime");
        assertEquals(175, shoppingCartService.calculateTotal(items));
    }
}