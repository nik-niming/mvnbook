package com.mvnbook.account.email;

/**
 * Created by niming on 6/9/15.
 */
public interface AccountEmailService {

    void sendMail(String to, String subject, String htmltext) throws AccountEmailException;
}
