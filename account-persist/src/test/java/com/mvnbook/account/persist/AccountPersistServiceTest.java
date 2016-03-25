package com.mvnbook.account.persist;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

import java.io.File;

/**
 * Created by niming on 6/11/15.
 */
public class AccountPersistServiceTest {

    private AccountPersistService service;

    @Before
    public void prepare() throws Exception{
        File persistDataFile = new File("target/test-classes/persist-data.xml");
        if (persistDataFile.exists()){
            persistDataFile.delete();
        }

        ApplicationContext ctx = new ClassPathXmlApplicationContext("account-persist.xml");

        service = (AccountPersistService)ctx.getBean("accountPersistService");

        Account account = new Account();
        account.setId("20375658");
        account.setName("NiMing");
        account.setEmail("ming.ni@hp.com");
        account.setPassword("123456");
        account.setActivated(true);

        service.createAccount(account);
    }

    @Test
    public void test_readAccount() throws Exception{
        Account account = service.readAccount("20375658");
        Assert.notNull(account);
    }
}
