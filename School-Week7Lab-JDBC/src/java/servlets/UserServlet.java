package servlets;

import businesslogic.UserService;
import domainmodel.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UserService us = new UserService();
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "view": {
                    String selectedUsername = request.getParameter("selectedUsername");
                    try {
                        User user = us.get(selectedUsername);
                        request.setAttribute("selectedUser", user);
                    } catch (Exception ex) {
                        Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    break;
                }
                
                case "delete": {
                    String selectedUsername = request.getParameter("selectedUsername");
                
                    try {
                        if (us.delete(selectedUsername) == 0)
                            throw new Exception();
                    } catch (Exception ex) {
                        request.setAttribute("errorMessage", "Unable to delete user.");
                    }
                }
            }
            
            
        }
        
        ArrayList<User> users = null;        
        try {
            users = (ArrayList<User>) us.getAll();
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        boolean active = request.getParameter("active") != null;

        UserService us = new UserService();

        try {
            switch (action) {
                case "add": {
                    if (us.insert(username, password, email, active, firstname, lastname) == 0)
                        request.setAttribute("errorMessage", "Unable to insert user.");
                    
                    break;
                }
                
                case "edit": {
                    if (us.update(username, password, email, active, firstname, lastname) == 0)
                        request.setAttribute("errorMessage", "Unable to update user.");
                    
                    break;
                }
            }
        } catch (Exception ex) {
            request.setAttribute("errorMessage", "Whoops.  Could not perform that action.");
        }
        
        ArrayList<User> users = null;
        try {
            users = (ArrayList<User>) us.getAll();
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }
}
