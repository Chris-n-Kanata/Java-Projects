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
public class Account {

    private static final int ACCOUNT_NAME_LENGTH = 32;
    private static final int ACCOUNT_PASSWORD_LENGTH = 32;
    
    private int account_id;
    private int account_type;
    private char[] name = new char[ACCOUNT_NAME_LENGTH];
    private char[] password = new char[ACCOUNT_PASSWORD_LENGTH];
    
    Account() {
        
        this.account_id = -1;
        this.account_type = -1;
        this.password = "none".toCharArray();
        this.name = "none".toCharArray();
    }
    
    Account(Account account) {
        
        this.account_id = account.account_id;
        this.account_type = account.account_type;
        this.password = account.password;
        this.name = account.name; 
    }
    
    public boolean setAccount(int account_id, String account_name, String account_password, int account_type) {
        
        this.account_id = account_id;
        this.account_type = account_type;
        this.password = account_password.toCharArray();
        this.name = account_name.toCharArray();
        
        return true;
    }
    
    public char[] getName() {
        return this.name;
    }
    
    public int getId() {
        return this.account_id;
    }
}
