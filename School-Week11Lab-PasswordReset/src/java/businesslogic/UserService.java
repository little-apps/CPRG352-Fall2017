package businesslogic;

import dataaccess.UserDB;
import domainmodel.Role;
import domainmodel.User;
import java.util.List;
import util.HashUtil;

public class UserService {

    private UserDB userDB;

    public UserService() {
        userDB = new UserDB();
    }

    public User get(String username) throws Exception {
        return userDB.getUser(username);
    }

    public List<User> getAll() throws Exception {
        return userDB.getAll();
    }

    public int update(String username, String password, String email, boolean active, String firstname, String lastname) throws Exception {
        String hashedPassword = HashUtil.bytesToHex(HashUtil.hash(password));
        
        User user = userDB.getUser(username);
        
        if (password != null && !password.isEmpty())
            user.setPassword(hashedPassword);
        
        user.setActive(active);
        user.setEmail(email);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        return userDB.update(user);
    }

    public int delete(String username) throws Exception {
        User deletedUser = userDB.getUser(username);
        return userDB.delete(deletedUser);
    }

    public int insert(String username, String password, String email, boolean active, String firstname, String lastname) throws Exception {
        String hashedPassword = HashUtil.bytesToHex(HashUtil.hash(password));
        
        User user = new User(username, hashedPassword, email, active, firstname, lastname);
        Role role = new Role(2);  // default regular user role
        user.setRole(role);
        return userDB.insert(user);
    }
}
