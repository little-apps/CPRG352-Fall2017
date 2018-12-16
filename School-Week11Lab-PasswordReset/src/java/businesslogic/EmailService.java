package businesslogic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EmailService {
    /**
     * The e-mail that reset password emails will come from.
     */
    public static final String EMAIL_FROM = "webmaster@cprg352.com";
    
    public static void sendMail(String to, String subject, String template, HashMap<String, String> contents) throws FileNotFoundException, IOException, MessagingException, NamingException {
        BufferedReader br = new BufferedReader(new FileReader(new File(template)));
        
        StringBuilder body = new StringBuilder();
        String line = br.readLine();
        while(line != null) {
            body.append(line);
            line = br.readLine();
        }
        
        String bodyString = body.toString();
        
        for(String key : contents.keySet()) {
            bodyString = bodyString.replace("{{" + key + "}}", contents.get(key));
        }
        
        sendMail(to, subject, bodyString, true);
    }
    
    
    public static void sendMail(String to, String subject, String body, boolean bodyIsHTML) throws MessagingException, NamingException {
        Context env = (Context)new InitialContext().lookup("java:comp/env");
        String username = (String)env.lookup("webmail-username");
        String password = (String)env.lookup("webmail-password");
        
        Properties props = new Properties();
        props.put("mail.transport.protocol", (String)env.lookup("webmail-protocol"));
        props.put("mail.smtps.host", (String)env.lookup("webmail-hostname"));
        props.put("mail.smtps.port", (Integer)env.lookup("webmail-port"));
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);
        
        // create a message
        Message message = new MimeMessage(session);
        message.setSubject(subject);
        if (bodyIsHTML) {
            message.setContent(body, "text/html");
        } else {
            message.setText(body);
        }
        
        // address the message
        Address fromAddress = new InternetAddress(EMAIL_FROM);
        Address toAddress = new InternetAddress(to);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);
        
        // send the message
        Transport transport = session.getTransport();
        transport.connect(username, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}