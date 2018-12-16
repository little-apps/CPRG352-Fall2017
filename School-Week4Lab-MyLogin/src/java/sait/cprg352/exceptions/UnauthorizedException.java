package sait.cprg352.exceptions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author 704199
 */
public class UnauthorizedException extends ServletException {
    public UnauthorizedException() {
        super("You are not authorized to access that page.");
    }
}
