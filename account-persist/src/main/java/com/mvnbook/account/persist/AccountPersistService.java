package com.mvnbook.account.persist;

/**
 * Created by niming on 6/11/15.
 */
public interface AccountPersistService {

    void createAccount(Account account) throws AccountPersistException;
    Account readAccount(String id) throws AccountPersistException;
    Account updateAccount(Account account) throws AccountPersistException;
    Account deleteAccount(String id) throws AccountPersistException;

}
