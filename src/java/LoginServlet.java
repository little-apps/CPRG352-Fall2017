
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sait.cprg352.business.User;
import sait.cprg352.business.UserService;

/**
 *
 * @author 704199
 */
public class LoginServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        Cookie usernameCookie = getCookie(request.getCookies(), "username");
        
        String action = (String) request.getParameter("action");
        
        if (action != null && action.equals("logout")) {
            request.getSession().invalidate();
        }
        
        if (usernameCookie != null)
            request.setAttribute("username", usernameCookie.getValue());
        
        // If an exception is thrown and that's why we're here, this takes the 
        // exceptions message and sets to 'message', causing it to be displayed
        // as the error message.
        if (request.getAttribute("javax.servlet.error.message") != null)
            request.setAttribute("message", request.getAttribute("javax.servlet.error.message"));
        
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
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
        UserService userService = new UserService();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (username == null || password == null) {
            request.setAttribute("message", "Missing username and/or password.");
        } else {
            User user = userService.login(request.getParameter("username"), request.getParameter("password"));
            
            if (user != null) {
                request.getSession().setAttribute("user", User.cloneNoPassword(user));

                if (request.getParameter("rememberMe") != null) {
                    Cookie cookie = new Cookie("username", request.getParameter("username"));

                    cookie.setMaxAge(60 * 60 * 24 * 365);                

                    response.addCookie(cookie);
                }

                response.sendRedirect("/home");
                return;
            } else {
                request.setAttribute("message", "Invalid username and/or password");
            }
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
    
    private static Cookie getCookie(Cookie[] cookies, String cookieName) {
        Cookie cookie = null;
        
        if (cookies == null) 
            return cookie;
        
        for (Cookie c : cookies) {
            if (c.getName().equals(cookieName))
                cookie = c;
        }
        
        return cookie;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
