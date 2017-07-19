/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * 
 * Adding the Account Manager
 */

package javaapplication1;

import java.util.*;
import java.io.*;

/**
 *
 * @author chris_000
 */
public class AccountManager {
    
    private ArrayList<Account> account_list = new ArrayList<>();
    
    public boolean load_accounts() {
        
        int loop = 0;
        FileInputStream in = null;
        try {
            
            in = new FileInputStream("./accounts.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            
            String strLine;

            //Read File Line By Line
            while ((strLine = br.readLine()) != null)   {
                
                String delims = ",";
                String[] entries = strLine.split(delims);
                
                // Did we reach the end?
                if (entries.length < 4) {
                    break;
                }
                
                Account new_account = new Account();
                
                // Build the new account.
                new_account.setAccount(Integer.parseInt(entries[0]),entries[1],entries[2],Integer.parseInt(entries[3]));
                    
                // Add it to the list.
                account_list.add(new_account);
            }
        } 
        catch (FileNotFoundException e) {
            System.out.println("Could not find the Accounts file...\n");
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
        System.out.println("Loaded " + loop + " Accounts.");
        return true;
    }
        
    public boolean new_account(int account_id, String account_name, String account_password, int account_type) {
        
        Account new_account = new Account();

        // Build the new account.
        new_account.setAccount(account_id, account_name, account_password, account_type);

        // Add it to the list.
        account_list.add(new_account);
        
        return true;
    }
}
