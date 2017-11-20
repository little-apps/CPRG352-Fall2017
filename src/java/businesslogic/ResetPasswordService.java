package businesslogic;

import dataaccess.PasswordChangeRequestDB;
import dataaccess.UserDB;
import domainmodel.PasswordChangeRequest;
import domainmodel.User;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import util.HashUtil;

/**
 *
 * @author 704199
 */
public class ResetPasswordService {
    public static final int EXPIRES_AFTER_HOURS = 24;
    
    public ResetPasswordService() {
        
    }
    
    public void generate(User user, ServletContext servletContext, String baseUrl) throws Exception {
        // Generate random UUID
        UUID randomUuid = UUID.randomUUID();
        
        // Get SHA256 hash (bytes) of UUID
        byte[] hash = HashUtil.hash(randomUuid.toString());

        // SHA256 is in bytes so convert to hex (0123456789ABCDEF)
        String hashHex = HashUtil.bytesToHex(hash);

        // Set date created
        Date created = new Date();

        // Insert hash into database
        UserDB userDB = new UserDB();
        PasswordChangeRequestDB passwordChangeRequestDB = new PasswordChangeRequestDB();
        PasswordChangeRequest passwordChangeRequest = new PasswordChangeRequest();

        passwordChangeRequest.setCreated(created);
        passwordChangeRequest.setId(hashHex);
        passwordChangeRequest.setUsername(user);

        passwordChangeRequestDB.insert(passwordChangeRequest);
        
        user.getPasswordchangerequestCollection().add(passwordChangeRequest);
        userDB.update(user);

        // Send link to user with UUID (before it's hashed)
        String resetLink = baseUrl + "/reset-password?id=" + randomUuid.toString();
        String path = servletContext.getRealPath("/WEB-INF");
        String email = user.getEmail();
        
        try {
            HashMap<String, String> contents = new HashMap<>();
            contents.put("username", user.getEmail());
            contents.put("date", created.toString());
            contents.put("resetLink", resetLink);

            try {
                EmailService.sendMail(email, "NotesKeepr Reset Password", path + "/emailtemplates/forgotpassword.html", contents);
            } catch (IOException ex) {
                Logger.getLogger(ResetPasswordService.class.getName()).log(Level.SEVERE, null, ex);
                
                throw new Exception("An error occurred sending the e-mail.");
            }
        } catch (MessagingException ex) {
            Logger.getLogger(ResetPasswordService.class.getName()).log(Level.SEVERE, null, ex);
            
            throw new Exception("An error occurred sending the e-mail.");
        } catch (NamingException ex) {
            Logger.getLogger(ResetPasswordService.class.getName()).log(Level.SEVERE, null, ex);
            
            throw new Exception("An error occurred sending the e-mail.");
        }
    }
    
    public boolean isValid(PasswordChangeRequest passwordChangeRequest) {
        long expires = passwordChangeRequest.getCreated().getTime() + (60 * 60 * EXPIRES_AFTER_HOURS);
        
        return (passwordChangeRequest.getCreated().getTime() <= expires);
    }
    
    public PasswordChangeRequest getRequestFromId(String id) throws Exception {
        // Convert UUID to hash
        byte[] hash = HashUtil.hash(id);

        // SHA256 is in bytes so convert to hex (0123456789ABCDEF)
        String hashHex = HashUtil.bytesToHex(hash);

        // Find matching hashed UUID in password resets table
        PasswordChangeRequestDB passwordChangeRequestDB = new PasswordChangeRequestDB();
        return passwordChangeRequestDB.getId(hashHex);
    }
}
