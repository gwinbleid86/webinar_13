package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Product[] products = {
                Product.make(ProductState.IN_STOCK),
                Product.make(ProductState.FOR_SALE),
                Product.make(ProductState.IN_STOCK),
                Product.make(ProductState.SOLD),
                Product.make(ProductState.IN_STOCK)
        };

    }
}
