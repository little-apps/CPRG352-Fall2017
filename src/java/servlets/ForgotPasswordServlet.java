package servlets;

import businesslogic.ResetPasswordService;
import dataaccess.UserDB;
import domainmodel.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handles users request to initiate password request
 * @author 704199
 */
public class ForgotPasswordServlet extends HttpServlet {

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
        getServletContext().getRequestDispatcher("/WEB-INF/forgotpassword.jsp").forward(request, response);
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
        String email = request.getParameter("email");
        
        try {
            if (email == null)
                throw new Exception("E-mail not specified");
            
            UserDB userDB = new UserDB();
            
            // Lookup user using username
            User user = userDB.getUserByEmail(email);
            
            if (user == null)
                throw new Exception("E-mail could not be found.");
            
            ResetPasswordService resetPasswordService = new ResetPasswordService();
            
            // Generate password reset token and send email with link to reset.
            resetPasswordService.generate(user, getServletContext(), request.getRequestURL().toString().replace("/forgot-password", ""));
            
            request.setAttribute("infoMessage", "Check your email for instructions on resetting your password.");
        } catch (Exception ex) {
            request.setAttribute("errorMessage", ex.getMessage());
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/forgotpassword.jsp").forward(request, response);
    }

}
