package sait.cprg352.business;

/**
 *
 * @author 704199
 */
public class User {
    private String username;
    private String password;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    /**
     * Clones User object with password set to null
     * @param user Existing user object.
     * @return User object with password as null.
     */
    public static User cloneNoPassword(User user) {
        return new User(user.username, null);
    }
}
