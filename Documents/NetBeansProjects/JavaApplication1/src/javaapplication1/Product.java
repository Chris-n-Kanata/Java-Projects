/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author chris_000
 */
public class Product {

    private static final int PRODUCT_NAME_LENGTH = 32;
   
    private int account_id;
    private int product_id;
    private double product_cost;
    private int product_quantity;
    private char[] name = new char[PRODUCT_NAME_LENGTH];
    
    Product() {
        
        this.product_id = -1;
        this.account_id = -1;
        this.product_cost = 0.0;
        this.product_quantity = 0;
        this.name = "none".toCharArray();
    }
    
    Product (Product product) {
        
        this.product_id = product.product_id;
        this.account_id = product.account_id;
        this.product_cost = product.product_cost;
        this.name = product.name;
        this.product_quantity = product.product_quantity;
    }
    
    public boolean setProduct(int product_id, int account_id, String product_name, double product_cost, int product_quantity) {
        
        this.product_id = product_id;
        this.account_id = account_id;
        this.product_cost = product_cost;
        this.name = product_name.toCharArray();
        this.product_quantity = product_quantity;
        
        return true;
    }
    
    public boolean setQuantity(int product_quantity) {
        
        this.product_quantity = product_quantity;
        return true;
    }
    
    public char[] getName() {
        return this.name;
    }
    
    public int getId() {
        return this.product_id;
    }
    
    public double getCost() {
        return this.product_cost;
    }
    
    public int getQuantity() {
        return this.product_quantity;
    }
}
