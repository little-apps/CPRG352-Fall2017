package filters;

import dataaccess.UserDB;
import domainmodel.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 704199
 */
public class AdminFilter implements Filter {
    
     private FilterConfig filterConfig = null;
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        // this code executes before the servlet
        // ...
        
        // ensure user is authenticated
        HttpSession session = ((HttpServletRequest)request).getSession();
        String username = (String)session.getAttribute("username");
        
        if (username != null) {
            // Get user from username
            UserDB userDB = new UserDB();
            try {
                User user = userDB.getUser(username);
                
                if (user.getRole().getRoleName().equals("admin")) {
                    chain.doFilter(request, response);
                    return;
                }
            } catch (Exception ex) {
                // Exception occurred trying to get user or role
            }
        }
        
        // get out of here!
        ((HttpServletResponse)response).sendRedirect("login");
        
       // this code executes after the servlet
       // ...
            
    }

    @Override
    public void destroy() {        
    }

    @Override
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
    }

    
}
