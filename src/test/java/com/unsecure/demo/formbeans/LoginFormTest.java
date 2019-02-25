package com.unsecure.demo.formbeans;

import org.junit.Assert;
import org.junit.Test;

public class LoginFormTest {

    LoginForm loginForm = new LoginForm ();

    @Test
    public void testSetUserName(){
        loginForm.setUserName ( "Something" );
        Assert.assertEquals("Something",loginForm.getUserName ());
    }

    @Test
    public void testSetPassword(){
        loginForm.setPassword ("password");
        Assert.assertEquals ( "password", loginForm.getPassword () );
    }
}
