package com.shoppingcart.controller;

import com.shoppingcart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/calculate")
    public String calculateTotal(@RequestParam List<String> items) {
        int total = shoppingCartService.calculateTotal(items);
        return "Total cost is: " + convertToPoundsAndPence(total);
    }

    private String convertToPoundsAndPence(int totalPence) {
        int pounds = totalPence / 100;
        int pence = totalPence % 100;

        if (pounds > 0) {
            if (pence > 0) {
                return String.format("%d pounds %d pences.", pounds, pence);
            } else {
                return String.format("%d pounds.", pounds);
            }
        } else {
            return String.format("%d pences.", pence);
        }
    }
}