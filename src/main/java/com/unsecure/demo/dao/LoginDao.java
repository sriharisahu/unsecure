package com.unsecure.demo.dao;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDao {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    Context context;
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    public LoginDao() {
        if (connection == null) {
            try {
                context = new InitialContext();
                DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/mydatabase");
                connection = dataSource.getConnection();
            } catch (NamingException ne) {
                logger.error(ne.getExplanation());
            } catch (SQLException sqe) {
                logger.error(sqe.getMessage());
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            resultSet.close();
            statement.close();
            connection.close();
            context.close();
            super.finalize();
        } catch (SQLException sqe) {
            logger.error(sqe.getMessage());
        }
    }

    public boolean isUserNameExists(String username) throws SQLException {
        int number = 0;
        statement = connection.createStatement();
        resultSet = statement.executeQuery("select count(username) from users where username='" + username + "'");
        while (resultSet.next()) {
            number = resultSet.getInt(1);
        }
        resultSet.close();
        statement.close();
        return number > 0;
    }

    public boolean isPasswordExists(String password) throws SQLException {
        int number = 0;
        statement = connection.createStatement();
        resultSet = statement.executeQuery("select count(password) from users where password='" + password + "'");
        while (resultSet.next()) {
            number = resultSet.getInt(1);
        }
        resultSet.close();
        statement.close();
        return number > 0;
    }

    public boolean checkUserNameAndPassword(String username, String formPassword) throws Exception {
        String password = "";
        boolean returnValue;

        statement = connection.createStatement();
        resultSet = statement.executeQuery("select password from users where username='" + username + "'");
        while (resultSet.next()) {
            password = resultSet.getString("password");
        }

        logger.debug("username: " + username);
        logger.debug("password: " + password);
        logger.debug("Entered Password:" + formPassword);

        //logger.debug("username: {0}",username);
        //logger.debug(String.format("username: {0}",username));

        returnValue = !StringUtils.isEmpty(password) && password.equalsIgnoreCase(formPassword);

        if (returnValue) {
            logger.debug("Logged in");
        } else {
            logger.debug("Login Error");
            if (!password.equalsIgnoreCase(formPassword)) {
                logger.debug("password: " + password);
                logger.debug("Entered Password:" + formPassword);
                throw new Exception("Username and password do not match!");
            } else if (!isUserNameExists(username)) {
                logger.debug("username: " + username);
                throw new Exception("User Name does not exist!");
            } else if (!isPasswordExists(formPassword)) {
                logger.debug("password: " + password);
                throw new Exception("Password does not exist!");
            }
        }

        resultSet.close();
        statement.close();

        return returnValue;
    }
}