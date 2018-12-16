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
public class User {
    private String username = "admin";
    private String password = "password";
    
    public User() {
        
    }
    
    public boolean login(String username, String password) {
        return username.equals(this.username) && password.equals(this.password);
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        return password;
    }
}
