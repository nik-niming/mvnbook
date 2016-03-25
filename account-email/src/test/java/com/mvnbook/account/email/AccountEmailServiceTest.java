package com.mvnbook.account.email;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.mail.Message;

/**
 * Created by niming on 6/9/15.
 */
public class AccountEmailServiceTest {

    private GreenMail greenMail;

    @Before
    public void startMailServer() throws Exception{
        greenMail = new GreenMail(ServerSetup.SMTP);
        greenMail.setUser("admin@mvnbook.com", "123456");
        greenMail.setUser("test@mvnbook.com", "123456");
        greenMail.start();
    }

    @Test
    public void test_sendEmail() throws Exception{
        ApplicationContext ctx = new ClassPathXmlApplicationContext("account-email.xml");
        AccountEmailService accountEmailService = (AccountEmailService)ctx.getBean("accountEmailService");

        String subject = "Test Subject";
        String htmlText = "<b3>Test Mail</b3>";

        accountEmailService.sendMail("test@mvnbook.com",subject,htmlText);


        greenMail.waitForIncomingEmail(2000, 1);
        Message[] msgs =  greenMail.getReceivedMessages();
        Assert.assertEquals(1, msgs.length);
        Assert.assertEquals("Test Subject",msgs[0].getSubject());
    }

    public void stopMailServer() throws Exception{
        greenMail.stop();
    }

}
