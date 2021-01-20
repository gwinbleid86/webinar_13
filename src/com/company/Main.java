package com.company;

public class Main {

    public static void main(String[] args) {

        Product[] products = {
                Product.make(ProductState.IN_STOCK),
                Product.make(ProductState.IN_STOCK),
                Product.make(ProductState.IN_STOCK),
                Product.make(ProductState.IN_STOCK),
                Product.make(ProductState.IN_STOCK)
        };

        for (Product p : products) {
            p.startSale();
            p.risePrice();
            p.giveToTheWinner();
            System.out.println(p.toString());
        }

    }
}
