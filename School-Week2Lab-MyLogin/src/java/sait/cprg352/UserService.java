/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sait.cprg352;

/**
 *
 * @author 704199
 */
public class UserService {
    public UserService() {
        
    }
    
    public boolean login(String username, String password) {
        if (username == null || username.isEmpty())
            return false;
                    
        if (password == null || password.isEmpty())
            return false;
        
        if (username.compareTo("adam") == 0 && password.compareTo("password") == 0)
            return true;
        
        if (username.compareTo("betty") == 0 && password.compareTo("password") == 0)
            return true;
        
        return false;
    }
}
