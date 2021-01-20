package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

    public static void main(String[] args) {

        Product[] products = {
                Product.make(ProductState.IN_STOCK),
                Product.make(ProductState.IN_STOCK),
                Product.make(ProductState.IN_STOCK),
                Product.make(ProductState.IN_STOCK),
                Product.make(ProductState.SOLD)
        };


        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(products);
        System.out.println(json);

        Product[] p = gson.fromJson(json, Product[].class);

        for (Product product : p) {
            product.startSale();
            product.risePrice();
            product.giveToTheWinner();
            System.out.println(product.toString());
        }
    }
}
