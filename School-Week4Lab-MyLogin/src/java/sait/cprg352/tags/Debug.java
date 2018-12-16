package sait.cprg352.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author 704199
 */
public class Debug extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        String remoteHost = pageContext.getRequest().getRemoteHost();
        String debugParam = pageContext.getRequest().getParameter("debug");
        
        if ((!pageContext.getRequest().getServerName().equals("localhost") && !pageContext.getRequest().getServerName().equals("test.cprg352.com")) || pageContext.getRequest().getParameter("debug") == null)
            return SKIP_BODY;
        
        return EVAL_BODY_INCLUDE;
    }
    
}
