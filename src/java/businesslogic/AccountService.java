/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import dataaccess.UserDB;
import domainmodel.User;
import util.HashUtil;

/**
 *
 * @author awarsyle
 */
public class AccountService {
    
    public User loginHandler(String username, String password) {
        
        UserDB userDB = new UserDB();
        try {
            User user = userDB.getUser(username);
            
            String expectedPasswordHashed = HashUtil.bytesToHex(HashUtil.hash(password));
            
            if (user.getPassword().equals(expectedPasswordHashed)) {
                return user;
            }
            
        } catch (Exception e) {
        }
        
        return null;
    }
    
}
