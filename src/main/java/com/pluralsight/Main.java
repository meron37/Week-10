//
//// ============================= Exercise 1 =============================
//package com.pluralsight;
//
//import java.sql.*;
//
//public class Main {
//    public static void main(String[] args) throws SQLException {
//
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind","root","yearup24");
//
//        //  Prepare the statement
//        Statement statement = connection.createStatement();
//
//        // Execute query
//        ResultSet resultSet = statement.executeQuery("SELECT * FROM northwind.Products;");
//
//        // Print results
//        while (resultSet.next()) {
//            String name = resultSet.getString("ProductName");
//            System.out.println(name);
//        }
//
//        // Close connection
//
//        connection.close();
//    }
//}


// =========================================== Exercise 2====================


package com.pluralsight;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind","root","yearup24");

        //  Prepare the statement
        Statement statement = connection.createStatement();

        // Execute query
        ResultSet resultSet = statement.executeQuery("SELECT ProductID, ProductName, UnitPrice, UnitsInStock  FROM Products;");

        // Print results
        while (resultSet.next()) {
            int id = resultSet.getInt("ProductID");
            String name = resultSet.getString("ProductName");
            double price = resultSet.getDouble("UnitPrice");
            int stock = resultSet.getInt("UnitsInStock");


            System.out.println(("Product ID:"+id + "\n  "+"Name:" + name+" \n $" +"Price:" + price+" \n stock: " + stock));
        }

        // Close connection

        connection.close();
    }
}


