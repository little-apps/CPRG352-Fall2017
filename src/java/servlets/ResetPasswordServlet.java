package servlets;

import businesslogic.ResetPasswordService;
import dataaccess.PasswordChangeRequestDB;
import dataaccess.UserDB;
import domainmodel.PasswordChangeRequest;
import domainmodel.User;
import java.io.IOException;
import java.security.MessageDigest;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handles password resets
 * @author 704199
 */
public class ResetPasswordServlet extends HttpServlet {
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uuid = request.getParameter("id");
        
        ResetPasswordService resetPasswordService = new ResetPasswordService();
        
        try {
            // Do we have a UUID?
            if (uuid == null)
                throw new Exception("Missing password reset token.");
            
            // Try to get password change reset from database using token
            PasswordChangeRequest passwordChangeRequest = resetPasswordService.getRequestFromId(uuid);
            
            // Throw exception if not found.
            if (passwordChangeRequest == null)
                throw new Exception("Password reset token is invalid.");
            
            // Make sure the password change request has not expired.
            if (!resetPasswordService.isValid(passwordChangeRequest))
                throw new Exception("Password reset token is no longer valid.");
            
            // It's valid -> set the UUID as a session variable
            request.getSession().setAttribute("resetToken", uuid);
        } catch (Exception ex) {
            request.setAttribute("errorMessage", ex.getMessage());
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/resetpassword.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ResetPasswordService resetPasswordService = new ResetPasswordService();
        
        try {
            // Is the reset token for the session set?
            String uuid = (String) request.getSession().getAttribute("resetToken");
            
            // If not, throw an exception
            if (uuid == null)
                throw new Exception("Password reset token is invalid.");
            
            // Try to get password reset from database
            PasswordChangeRequest passwordChangeRequest = resetPasswordService.getRequestFromId(uuid);
            
            // Throw exception if it's not found or is invalid.
            if (passwordChangeRequest == null)
                throw new Exception("Password reset token is invalid.");
            
            if (!resetPasswordService.isValid(passwordChangeRequest))
                throw new Exception("Password reset token is no longer valid.");
            
            // Get new password
            String password = request.getParameter("password");
            String passwordConfirm = request.getParameter("passwordConfirm");
            
            // Throw exception if password is missing.
            if (password == null)
                throw new Exception("Password is missing.");
            
            // Make sure password matches confirm password
            if (!password.equals(passwordConfirm))
                throw new Exception("Passwords do not match.");
            
            // Can update password
            
            // Remove password change request (so the token can't be used to reset again)
            UserDB userDB = new UserDB();
            PasswordChangeRequestDB passwordChangeRequestDB = new PasswordChangeRequestDB();

            // Update users password
            User user = passwordChangeRequest.getUsername();
            
            // User shouldn't use the old password if they forgot it
            if (user.getPassword().equals(password))
                throw new Exception("Password is not strong enough.");
            
            // Make sure password doesn't have whitespace
            if (!password.matches("[^\\s]+"))
                throw new Exception("Password cannot contain whitespaces (spaces, tabs, etc.)");
            
            // Password should be at least 4 characters long
            if (password.length() < 4)
                throw new Exception("Password must be at least 4 characters.");
            
            user.setPassword(password);
            
            userDB.update(user);
            
            // Remove password change request (so it can't be reset again)
            passwordChangeRequestDB.delete(passwordChangeRequest);
            
            // Clear UUID from session
            request.getSession().setAttribute("resetToken", null);
            
            // TODO: Send email confirming password was changed.
            
            request.setAttribute("info", "Password updated successfully.");
            
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/resetpassword.jsp").forward(request, response);
        }
        
        
    }

}
