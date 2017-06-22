/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.*;
import java.io.*;

/**
 *
 * @author chris_000
 */
public class ProductManager {
    
    private ArrayList<Product> product_list = new ArrayList<>();
    
    public boolean load_products() {
        
        FileInputStream in = null;
        try {
            
            in = new FileInputStream("./products.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            
            String strLine;

            //Read File Line By Line
            while ((strLine = br.readLine()) != null)   {
                
                String delims = ",";
                String[] entries = strLine.split(delims);
                
                // Did we reach the end?
                if (entries.length < 5) {
                    break;
                }
                
                Product new_product = new Product();
                
                // Load up a new product.
                new_product.setProduct(Integer.parseInt(entries[0]), Integer.parseInt(entries[4]), 
                        entries[2], Double.parseDouble(entries[3]), Integer.parseInt(entries[1]));
                
                // Add it to our list.
                this.product_list.add(new_product);
               
            }
        } 
        catch (FileNotFoundException e) {
            System.out.println("Could not find the Products file...\n");
            return false;
        }
        catch (IOException e) {
            System.out.println("File IO Exception...\n");
            return false;
        }
        finally {
            
            if (in != null) {
                try {
                    in.close();
                }
                catch (IOException e) {
                    
                }
            }
        }
        return true;
    }
    
    public void set_product_quantity(int product_id, int quantity) {
        for (int loop = 0 ; loop < this.product_list.size() ; loop++) {
            if (product_id == this.product_list.get(loop).getId()) {
                this.product_list.get(loop).setQuantity(quantity);
            }
        }
    }
    
    public Product get_product(int index) {
        if (index >= 0 && index < this.product_list.size()) {
            return new Product(this.product_list.get(index));
        }
        return null;
    }
    
    public Product get_product_by_id(int id) {
        for (int loop = 0 ; loop < this.product_list.size() ; loop++) {
            if (id == this.product_list.get(loop).getId()) {
                return new Product(this.product_list.get(loop));
            }
        }
        return null;
    }
    
    public int number_products() {
        return this.product_list.size();
    }
        
    public boolean new_product(int product_id, int product_account, String product_name, float product_cost, int product_quantity) {
        
        // Load up a new product.
        Product new_product = new Product();
        
        // Set values.
        new_product.setProduct(product_id, product_account, product_name, product_cost, product_quantity);
                
        // Add it to our list.
        this.product_list.add(new_product);
        
        return true;
    }
}

