import java.util.*;
import java.sql.*;

public class Customer {

    static int i=0;
    int id ;
    String name ;
    String phoneNo ;
    Boolean membership ;
    int points ;
    Map<Bill,String> billwithdate ;

    Customer (String name ,String phoneNo ){
        this.id=i++;
        this.name = name;
        this.phoneNo = phoneNo;
        this.membership = false;
        this.points =0;
        this.billwithdate = new HashMap<>();
    }

    public Customer(int id, String name, String phoneNo, Boolean membership, int points) {
      this.id = id;
      this.name = name;
      this.phoneNo = phoneNo;
      this.membership = membership;
      this.points = points;
  
  }

    private static Connection connect() {
        
      String url = "jdbc:mysql://localhost:3306/billingsystem";
      String username = "root";
      String password = "08669";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static int insertCustomer(String name, String phone,String membershipstatus) {
      String sql = "INSERT INTO Customer(name, phoneNo, membership, points) VALUES(?, ?, ?, ?)";
      int lastid = -1;

      try (Connection conn = Customer.connect(); 
           PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

          pstmt.setString(1, name);
          pstmt.setString(2, phone);
          if(membershipstatus.equals("Y"))
          pstmt.setBoolean(3, true);
          else
          pstmt.setBoolean(3, false);
          pstmt.setInt(4, 0);
          pstmt.executeUpdate();

          ResultSet rs = pstmt.getGeneratedKeys();
          if (rs.next()) {
              lastid = rs.getInt(1);
          } else {
              throw new SQLException("Failed to retrieve customer ID.");
          }
      } catch (SQLException e) {
          System.out.println(e.getMessage());
      }

      return lastid;
  }



    
    public static void updateCustomer(int customerId, boolean newMembershipStatus, int additionalPoints){
     
        String updateQuery = "UPDATE Customer SET membership = ?, points = points + ? WHERE id = ?";

        try (Connection connection = Customer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            
            preparedStatement.setBoolean(1, newMembershipStatus);
            preparedStatement.setInt(2, additionalPoints);
            preparedStatement.setInt(3, customerId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Customer updated successfully.");
            } else {
                System.out.println("Customer not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  public static Customer getCustomerDetails(int customerId) {
    String customerQuery = "SELECT id, name, phoneNo, membership, points FROM Customer WHERE id = ?";
    Customer customer = null;
    try (Connection connection = Customer.connect();PreparedStatement preparedStatement = connection.prepareStatement(customerQuery)){
        preparedStatement .setInt(1, customerId);
        ResultSet rs = preparedStatement .executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String phoneNo = rs.getString("phoneNo");
            boolean membership = rs.getBoolean("membership");
            int points = rs.getInt("points");
            customer = new Customer(id, name, phoneNo, membership, points);  
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return customer;
}

public static String customerbalance(int customerId, String action, int amount) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String result = "";
    try {
        connection = Customer.connect();

        if (action.equalsIgnoreCase("get")) {
         
            String query = "SELECT balance FROM Customer WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, customerId);
            resultSet = preparedStatement.executeQuery();
          

            if (resultSet.next()) {
                int balance = resultSet.getInt("balance");
                result = "Customer balance: " + balance;
              
            } else {
                result = "Customer not found.";
            }

        } else if (action.equalsIgnoreCase("add")) {
            
            String query = "UPDATE Customer SET balance = balance + ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, amount);
            preparedStatement.setInt(2, customerId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                result = "Customer balance updated successfully.";
               
            } else {
                result = "Customer not found.";
            }
        } else {
            result = "Invalid action. Please use 'get' or 'add'.";
        }

    } catch (SQLException e) {
        e.printStackTrace();
        result = "Error managing customer balance.";
    }
    return result;
}



public static String customeranalysis(int customerId, String startDate, String endDate) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuilder result = new StringBuilder();

    try {
        connection = Customer.connect();

        // Retrieve bills within the date range for the customer
        String query = "SELECT billNo, netamount FROM Bill WHERE customerId = ? AND dates BETWEEN ? AND ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, customerId);
        preparedStatement.setString(2, startDate);
        preparedStatement.setString(3, endDate);
        resultSet = preparedStatement.executeQuery();

        int totalAmountPurchased = 0;

        result.append("Bill Details:\n");
        while (resultSet.next()) {
            int billNo = resultSet.getInt("billNo");
            int totalPrice = resultSet.getInt("netamount");
            totalAmountPurchased += totalPrice;
            result.append("Bill No: ").append(billNo).append(", Amount: ").append(totalPrice).append("\n");
        }

        result.append("Total Amount Purchased: ").append(totalAmountPurchased).append("\n");

        // Retrieve the customer's balance
        String balanceQuery = "SELECT balance FROM Customer WHERE id = ?";
        preparedStatement = connection.prepareStatement(balanceQuery);
        preparedStatement.setInt(1, customerId);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int balanceAmount = resultSet.getInt("balance");
            result.append("Customer Balance: ").append(balanceAmount).append("\n");
        } else {
            result.append("Customer not found.\n");
        }

    } catch (SQLException e) {
        e.printStackTrace();
        result.append("Error retrieving bill details.\n");
    } finally {
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return result.toString();
}


public static String paycustomer(int customerId, int amountToDeduct) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String result = "";

    try {
        connection = connect();

        // Retrieve the current balance
        String getBalanceQuery = "SELECT balance FROM Customer WHERE id = ?";
        preparedStatement = connection.prepareStatement(getBalanceQuery);
        preparedStatement.setInt(1, customerId);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int currentBalance = resultSet.getInt("balance");

            // Check if there is enough balance
            if (currentBalance >= amountToDeduct) {
                // Deduct the specified amount
                int newBalance = currentBalance - amountToDeduct;

                // Update the customer's balance
                String updateBalanceQuery = "UPDATE Customer SET balance = ? WHERE id = ?";
                preparedStatement = connection.prepareStatement(updateBalanceQuery);
                preparedStatement.setInt(1, newBalance);
                preparedStatement.setInt(2, customerId);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    result = "Customer balance updated successfully. New balance: " + newBalance;
                } else {
                    result = "Customer not found.";
                }
            } else {
                result = "Insufficient balance.";
            }
        } else {
            result = "Customer not found.";
        }

    } catch (SQLException e) {
        e.printStackTrace();
        result = "Error managing customer balance.";
    } finally {
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return result;
}


}
