/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.*;
import java.util.Scanner;

/**
 *
 * @author chris_000
 */
public class Store {
    
    private final AccountManager account_manager = new AccountManager();
    private final ProductManager product_manager = new ProductManager();
    
    private ArrayList<Product> my_cart = new ArrayList<>();
    
    public boolean load_accounts() {
    
        // Load the accounts.
        this.account_manager.load_accounts();
        return true;
    }
    
    public boolean load_products() {
    
        // Load the products.
        this.product_manager.load_products();
        return true;
    }
    
    private void clear_screen() {
                
        // Clear the console window.
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
      
    }
    
    private boolean display_items() {
        
        clear_screen();
        
        for (int loop = 0; loop < this.product_manager.number_products() ; loop++) {
          Product product = this.product_manager.get_product(loop);
          if (product != null) {
              
              String name = new String(product.getName());
              System.out.println("ID (" + product.getId() + ")  " + name + "  Price: " + product.getCost() + "  Quantity: " + product.getQuantity());
          }
        }
        return true;
    }
    
    private boolean payment_section() {
        
        Scanner keyboard = new Scanner(System.in);
        double total = 0.0;
        int option = 0;
       
        System.out.println("Your Cart\n\n");

        // Add the items to the cart or update the cart.
        Iterator<Product> cart_iterator = this.my_cart.iterator();

        while (cart_iterator.hasNext()) {
            
            Product current_item = cart_iterator.next();
            String name = new String(current_item.getName());
            System.out.println("ID (" + current_item.getId() + ")  " + "Name: " + name + "  Price: " + current_item.getCost() + "  Quantity: " + current_item.getQuantity());
        
            total = total + current_item.getCost() * current_item.getQuantity();
        }
        
        System.out.println("You owe: $" + total + "  - 1 to pay 0 to abort");

        option = keyboard.nextInt();
        if (option == 0) {
            return false;
        }
        
        Iterator<Product> cart_iterator2 = this.my_cart.iterator();

        while (cart_iterator2.hasNext()) {
         
            // Get the current cart item.
            Product current_item = cart_iterator2.next();
            
            // Get the current item on the shelf
            Product shelf_item = this.product_manager.get_product_by_id(current_item.getId());
            
            // Update the shelf item reducing its quantity.
            this.product_manager.set_product_quantity(current_item.getId(), (shelf_item.getQuantity() - current_item.getQuantity()));
        }
        return true;
    }
    
    private boolean buyer_section() {
        
        int quantity = 0;
        boolean valid_quantity = false;
        boolean finished = false;
        
        while (finished == false) {
        
            display_items();
            
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Select a product to buy or press 0 to checkout: ");
       
            int option = keyboard.nextInt();
            if (option == 0) {
                if (this.payment_section() == false) {
                    continue;
                }
                return true;
            }
            
            valid_quantity = false;
            
            // Enter a valid quantity.
            while (valid_quantity == false) {
                
                System.out.println("Select a quantity: ");
                quantity = keyboard.nextInt();
                
                // Grab the product.
                Product product = this.product_manager.get_product_by_id(option);
                if (product == null) {
                    return false;
                }
                
                // Make sure we have a valid quantity and its available.
                if (quantity <= 0 || quantity > product.getQuantity()) {
                    System.out.println("You entered an invalid quantity");
                }
                else {
                    valid_quantity = true;
                }
            }
            
            // Add the items to the cart or update the cart.
            Iterator<Product> cart_iterator = this.my_cart.iterator();
            
            boolean found = false;
            
            while (cart_iterator.hasNext()) {
                Product current_item = cart_iterator.next();
                if (current_item.getId() == option) {
                    current_item.setQuantity(current_item.getQuantity() + quantity);
                    found = true;
                    break;
                }
            }
            
            // Did we find the item already
            if (!found) {
                
                // Grab the product.
                Product selected_product = this.product_manager.get_product_by_id(option);
            
                // Update the quantitiy in the product.
                selected_product.setQuantity(quantity);
            
                this.my_cart.add(selected_product);
            }
        }
        return true;
    }
    
    private boolean vendor_section() {
        return true;
    }
    
    private boolean admin_section() {
        return true;
    }
    
    private boolean show_storefront() {
        
        boolean quit = false;
        Scanner keyboard = new Scanner(System.in);
        
        while (quit == false) {
                
            System.out.println("Welcome to the market - Are you a (1) Buyer  (2) Vendor  (3) Admin  (4) Quitter");
       
            int option = keyboard.nextInt();
        
            switch (option) {
                case 1:
                    buyer_section();
                    break;
                case 2:
                    vendor_section();
                    break;
                case 3:
                    admin_section();
                    break;
                case 4:
                    quit = true;
                    break;
            }
        }
        return true;
    }
    
    public void open() {
        
        boolean done = false;
        
        while (!done) {
            done = show_storefront();
        }
    }
}
