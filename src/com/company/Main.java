package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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

//        try (FileWriter fw = new FileWriter("./products.txt")) {
//            fw.write(gson.toJson(products));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        String json = "";
        try (
                FileReader fr = new FileReader("./products.txt");
                Scanner scan = new Scanner(fr)
        ) {
            while (scan.hasNextLine()) {
                json += scan.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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
