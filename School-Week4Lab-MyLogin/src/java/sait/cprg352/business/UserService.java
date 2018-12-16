package sait.cprg352.business;

/**
 *
 * @author 704199
 */
public class UserService {
    public UserService() {
        
    }
    
    public User login(String username, String password) {
        User user = null;
        
        if (username == null || password == null)
            return user;
        
        
        if ((username.equals("adam") && password.equals("password")) || (username.equals("betty") && password.equals("password"))) {
            user = new User(username, password);
        }
        
        return user;
    }
}
