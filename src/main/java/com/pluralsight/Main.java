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

//
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
//        ResultSet resultSet = statement.executeQuery("SELECT ProductID, ProductName, UnitPrice, UnitsInStock  FROM Products;");
//
//        // Print results
//        while (resultSet.next()) {
//            int id = resultSet.getInt("ProductID");
//            String name = resultSet.getString("ProductName");
//            double price = resultSet.getDouble("UnitPrice");
//            int stock = resultSet.getInt("UnitsInStock");
//
//
//            System.out.println(("Product ID:"+id + "\n  "+"Name:" + name+" \n $" +"Price:" + price+" \n stock: " + stock));

//                  OR
//                System.out.println("Product ID:" + ProductID);
//                System.out.println("Product Name:" + ProductName);
//                System.out.println("Unit Price:" + UnitPrice);
//                System.out.println("Units in stack:" + UnitsInStack);

//        }
//
//        // Close connection
//
//        connection.close();
//    }
//}




// ================================= Exercise 3 =====================================


package com.pluralsight;

import java.sql.*;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Connection connection = null; // connection variable is available in finally because it declared outside of try{

        try{

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind","root","yearup24");

// HOME SCREEN MENU
            boolean running = true;

            while (running) {
                // HOME SCREEN
                System.out.println();
                System.out.println("What do you want to do?");
                System.out.println("1) Display all products");
                System.out.println("2) Display all customers");
                System.out.println("0) Exit");
                System.out.print("Select an option: ");

                int option = scanner.nextInt();
                scanner.nextLine(); // clear Enter

                if (option == 1) {
                    // Display all products
                    String sql = "SELECT ProductID, ProductName FROM Products";

                    try (PreparedStatement stmt = connection.prepareStatement(sql);
                         ResultSet rs = stmt.executeQuery()) {

                        while (rs.next()) {
                            int id = rs.getInt("ProductID");
                            String name = rs.getString("ProductName");
                            System.out.println(id + " - " + name);
                        }
                    }

                } else if (option == 2) {
                    // Display all customers ordered by country
                    String sql =
                            "SELECT ContactName, CompanyName, City, Country, Phone " +
                                    "FROM Customers ORDER BY Country";

                    try (PreparedStatement stmt = connection.prepareStatement(sql);
                         ResultSet result = stmt.executeQuery()) {

                        while (result.next()) {
                            System.out.println(
                                    result.getString("ContactName") + " | " +
                                            result.getString("CompanyName") + " | " +
                                            result.getString("City") + " | " +
                                            result.getString("Country") + " | " +
                                            result.getString("Phone")
                            );
                        }
                    }

                } else if (option == 0) {
                    System.out.println("Goodbye!");
                    running = false;   // exit loop
                } else {
                    System.out.println("Invalid option.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection.");
            }
        }
    }
}