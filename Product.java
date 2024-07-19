import java.sql.*;

public class Product {
       static int i=0;
      int id;
      String seller;
      String name;
      int price ;
      int availableQuantity ;
      double tax ;
     
      Product(String seller,String name , int price, int availableQuantity,double tax ){
              this.seller=seller;
              this.name = name ;
              this.price = price ;
              this.availableQuantity = availableQuantity;
              this.tax=tax;
              this.id=++i;
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

    public static void insertProduct(Product product) {
        String sql = "INSERT INTO Product(seller,name, price, availableQuantity, tax) VALUES(?,?,?,?,?)";
        String sql1 = "UPDATE Seller SET balanceAmount = balanceAmount + ? WHERE name = ?";

        try (Connection conn = Product.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);PreparedStatement pstmt1 = conn.prepareStatement(sql1)) {
            pstmt.setString(1,product.seller);
            pstmt.setString(2, product.name);
            pstmt.setInt(3, product.price);
            pstmt.setInt(4, product.availableQuantity);
            pstmt.setDouble(5, product.tax);
            pstmt.executeUpdate();
            int payableamount=(product.price*product.availableQuantity);
            pstmt1.setInt(1,(int)(payableamount/1.3));   //assuming profit as 30%.
            pstmt1.setString(2,product.seller);
            pstmt1.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void insertseller(String sellername) {
        String sql = "INSERT INTO seller(name,balanceamount) VALUES(?,?)";
       

        try (Connection conn = Product.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,sellername);
            pstmt.setInt(2, 0);
            
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean isSellerAvailable(String sellerName) {
        String query = "SELECT COUNT(*) FROM Seller WHERE name = ?";

        try (Connection connection =Product.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, sellerName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void payseller(String sellerName, int amountToDeduct) {
        String updateQuery = "UPDATE Seller SET balanceAmount = balanceAmount - ? WHERE name = ?";

        try (Connection connection =Product.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                
            preparedStatement.setInt(1, amountToDeduct);
            preparedStatement.setString(2, sellerName);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                new popupsuccess("Balance Amount for the seller updated successfully!!!");
            } else {
                 new popuperror("Seller not found !!!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   

    public static String purchaseanalysis() {
            String query = "SELECT name, balanceAmount FROM Seller";
            String str="NAME     BALANCE AMOUNT";
            str+="\n";
            double amt=0;
            try (Connection connection = Product.connect();
                 PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                 
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    double balanceAmount = resultSet.getDouble("balanceAmount");
                    str+=name+"     "+String.valueOf(balanceAmount)+"\n";
                    amt+=balanceAmount;
                }
                str+="\n";
                str+="Total Amount:"+String.valueOf(amt);
    
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return str;
    }



    public static void updateProduct(int productId, String fieldToUpdate, int newValue) {
      String query = null;
      String query1=null;

      if ("price".equalsIgnoreCase(fieldToUpdate)) {
          query = "UPDATE product SET price = ? WHERE id = ?";
      } 
      else if ("quantity".equalsIgnoreCase(fieldToUpdate)) {
          query = "UPDATE product SET availableQuantity = availableQuantity+ ? WHERE id = ?";
          query1="UPDATE Seller SET balanceAmount = balanceAmount + ? WHERE name = ?";
      }
       else {
          System.out.println("Invalid field to update.");
          return;
      }

      try (Connection conn = Product.connect();
           PreparedStatement preparedStatement = conn.prepareStatement(query);
            ) {
          
          preparedStatement.setInt(1, newValue);
          preparedStatement.setInt(2, productId);

          if("quantity".equalsIgnoreCase(fieldToUpdate)){
          PreparedStatement preparedStatement1 = conn.prepareStatement(query1);
          int price=Integer.valueOf(getProductDetailById(productId,"price"));
          String seller=getProductDetailById(productId,"seller");
         // int availableQuantity=Integer.valueOf(getProductDetailById(productId,"availablequantity"));
          int payableamount=(price*newValue);
          preparedStatement1.setInt(1,(int)(payableamount/1.3));   //assuming profit as 30%.
          preparedStatement1.setString(2,seller);
          preparedStatement1.executeUpdate();
          }
          int rowsAffected = preparedStatement.executeUpdate();
      } catch (SQLException e) {
          e.printStackTrace();
      }
  }


  public static void tempupdateProductqty(int productId, int newValue) {
    String query = "UPDATE product SET availableQuantity = availableQuantity+ ? WHERE id = ?";
    try (Connection conn = Product.connect();
         PreparedStatement preparedStatement = conn.prepareStatement(query);
          ) {
        
        preparedStatement.setInt(1, newValue);
        preparedStatement.setInt(2, productId);    

        int rowsAffected = preparedStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

  public  static void deleteProduct(int productId) {
      String query = "DELETE FROM product WHERE id = ?";

      try (Connection conn = Product.connect();
      PreparedStatement preparedStatement = conn.prepareStatement(query)) {
          
          preparedStatement.setInt(1, productId);

          int rowsAffected = preparedStatement.executeUpdate();
          if (rowsAffected > 0) {
              System.out.println("Product deleted successfully.");
          } else {
              System.out.println("No product found with the given ID.");
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
  }
 public static String getProductDetailById(int productId, String detail) {
        String query = "";
        switch (detail.toLowerCase()) {
            case "name":
                query = "SELECT name FROM product WHERE id = ?";
                break;
            case "price":
                query = "SELECT price FROM product WHERE id = ?";
                break;
            case "tax":
                query = "SELECT tax FROM product WHERE id = ?";
                break;
            case "availablequantity":
                query="SELECT availablequantity FROM product WHERE id = ?";
            break;
            case "seller":
            query="SELECT seller FROM product WHERE id = ?";
            break;
            default:
                throw new IllegalArgumentException("Invalid detail requested: " + detail);
        }

        try (Connection conn = Product.connect();
        PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setInt(1, productId);
        ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                switch (detail.toLowerCase()) {
                    case "name":
                        return rs.getString("name");
                    case "price":
                        return String.valueOf(rs.getInt("price"));
                    case "tax":
                        return String.valueOf(rs.getDouble("tax"));
                    case "availablequantity":
                        return String.valueOf(rs.getInt("availablequantity"));
                    case "seller":
                        return String.valueOf(rs.getString("seller"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


public static String getProductSales(int productId, String startDate, String endDate) {
    String result = "";
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {

        connection = Product.connect();

        String query = "SELECT bp.billNo, bp.quantity FROM BillProduct bp JOIN Bill b ON bp.billNo = b.billNo WHERE bp.productId = ? AND b.dates BETWEEN ? AND ?";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, productId);
        preparedStatement.setString(2, startDate);
        preparedStatement.setString(3, endDate);

        resultSet = preparedStatement.executeQuery();

        int totalQuantity = 0;
        StringBuilder sb = new StringBuilder();

        sb.append("Bill Number | Quantity Sold\n");
        sb.append("---------------------------\n");

        while (resultSet.next()) {
            int billNo = resultSet.getInt("billNo");
            int quantity = resultSet.getInt("quantity");

            sb.append(billNo).append(" | ").append(quantity).append("\n");
            totalQuantity += quantity;
        }

        sb.append("---------------------------\n");
        sb.append("Total Quantity Sold: ").append(totalQuantity);

        result = sb.toString();

        resultSet.close();
        preparedStatement.close();
        connection.close();

    } catch (SQLException e) {
        e.printStackTrace();
        result = "Error fetching product sales data.";
    } 
    return result;
}

}
