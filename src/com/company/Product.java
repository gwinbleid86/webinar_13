package com.company;

import java.util.Random;

public class Product {
    private String id;
    private String name;
    private int startPrice;
    private int finalPrice;
    private String honorary_code;
    private ProductState state;

    public static Product make(ProductState state){
        Random r = new Random();
        Product p = new Product();
        p.id = "ID_"+Util.makeName(10);
        p.name = Util.makeName(5);
        p.startPrice = (r.nextInt(1000)+2)*4;
        p.finalPrice = 0;
        p.state = state;
        return p;
    }

    public void setHonorary_code(String honorary_code) {
        this.honorary_code = honorary_code;
    }

    public int getStartPrice() {
        return startPrice;
    }

    public int getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(int finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getId() {
        return id;
    }

    public void setState(ProductState state) {
        this.state = state;
    }

    public void startSale(){
        try {
            state.startSale(this);
        } catch (Exception e) {
            printMessage(this.id, e.getMessage());
        }
    }
    public void risePrice(){
        try {
            state.risePrice(this);
        } catch (Exception e) {
            printMessage(this.id, e.getMessage());
        }
    }
    public void withdraw(){
        try {
            state.withdraw(this);
        } catch (Exception e) {
            printMessage(this.id, e.getMessage());
        }
    }
    public void giveToTheWinner(){
        try {
            state.giveToTheWinner(this);
        } catch (Exception e) {
            printMessage(this.id, e.getMessage());
        }
    }

    private void printMessage(String id, String e){
        System.out.printf("[%s] - %s\n", id, e);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", startPrice=" + startPrice +
                ", finalPrice=" + finalPrice +
                ", honorary_code='" + honorary_code + '\'' +
                ", state=" + state +
                '}';
    }
}
