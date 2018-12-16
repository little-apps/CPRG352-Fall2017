package sait.cprg352.tags;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author 704199
 */
public class LoginTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        
        String username = (String) (pageContext.getAttribute("username") != null ? pageContext.getAttribute("username") : "");
        String message = (String) pageContext.getAttribute("message");
        String info = (String) pageContext.getAttribute("info");
        
        String messages = "";
        
        if (message != null)
            messages += "<div class=\"row\"><div class=\"col-md-12\"><div class=\"alert alert-danger\" role=\"alert\">" + message + "</div></div></div>";
        
        if (info != null)
            messages += "<div class=\"row\"><div class=\"col-md-12\"><div class=\"alert alert-info\" role=\"alert\">" + info + "</div></div></div>";
        
        String loginForm = 
                    "<form class=\"form-horizontal\" action='/login' method='post'>" +
"                <div class=\"form-group\">\n" +
"                  <label for=\"username\" class=\"col-sm-2 control-label\">Username</label>\n" +
"                  <div class=\"col-sm-10\">\n" +
"                    <input type=\"text\" class=\"form-control\" name=\"username\" id=\"username\" value=\"" + username + "\">\n" +
"                  </div>\n" +
"                </div>\n" +
"                <div class=\"form-group\">\n" +
"                  <label for=\"password\" class=\"col-sm-2 control-label\">Password</label>\n" +
"                  <div class=\"col-sm-10\">\n" +
"                    <input type=\"password\" class=\"form-control\" name=\"password\" id=\"password\">\n" +
"                  </div>\n" +
"                </div>\n" +
"                <div class=\"form-group\">\n" +
"                    <div class=\"col-sm-offset-2 col-sm-10\">\n" +
"                        <div class=\"checkbox\">\n" +
"                            <label>\n" +
"                                <input type=\"checkbox\" name=\"rememberMe\" " + (!username.isEmpty() ? "checked" : "") + "> Remember me\n" +
"                            </label>\n" +
"                        </div>\n" +
"                    </div>\n" +
"                </div>\n" +
"                <div class=\"form-group\">\n" +
"                  <div class=\"col-sm-offset-2 col-sm-10\">\n" +
"                    <button type=\"submit\" class=\"btn btn-default\">Login</button>\n" +
"                  </div>\n" +
"                </div>\n" +
"            </form>";
        
        try {
            JspWriter out = pageContext.getOut();
            out.print(loginForm);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        return SKIP_BODY;
    }
}
