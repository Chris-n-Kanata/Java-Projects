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
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Store storefront = new Store();
        
        // Load our accounts.
        storefront.load_accounts();
        
        // Load our products.
        storefront.load_products();
        
        // Start the store.
        storefront.open();
    }
}
