package com.suns;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.sql.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        try {
            Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://10.50.10.201:3306/hhn_user1","develop","");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user");
            System.out.println(resultSet);
            while (resultSet.next()){
                System.out.println(resultSet.getString("id"));
                System.out.println(resultSet.getString("userName"));
                System.out.println(resultSet.getString("pwd"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
