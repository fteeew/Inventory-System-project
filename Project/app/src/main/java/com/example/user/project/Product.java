package com.example.user.project;

public class Product {
    private String name;  //holds the product name.
    private double standard_price; //holds the standard product price.
    private String unit; // holds the unit of product.
    //int voidInd;
    //int quantity;

    public Product() {
    }

    public Product(String name, double standard_price, String unit) {

        this.name = name;
        this.standard_price = standard_price;
        this.unit = unit;

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStandard_price() {
        return standard_price;
    }

    public void setStandard_price(double standard_price) {
        this.standard_price = standard_price;
    }



    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Product{" +
                ", name='" + name + '\'' +
                ", standard_price=" + standard_price +
                ", unit='" + unit + '\'' +
                '}';
    }


}
