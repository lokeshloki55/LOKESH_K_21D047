import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Bill {

       static int i = 0;
       int billNo ;
       int totalPrice ;
       double totalTax ;
       double discount ;
       double rewardsdiscount=0;
       int disamt;
       int netamount;
       String date ;
       int cid ;
       LinkedHashMap<Integer , Integer> productidQuantity ;


       Bill (int cid ,String date ){
              this.cid=cid ;
              this.date = date ;
              this.totalPrice =0;
              this.discount = 0;
              this.rewardsdiscount=0;
              this.netamount=0;
              this.totalTax =0;
              this.disamt=0;
              this.productidQuantity = new LinkedHashMap<>();
        
              this.billNo = ++i;
             }

        Bill(int billNo,String date,int totalPrice,double totalTax,double discount,double rewardsdiscount,int netamount){
            this.billNo=billNo;
            this.date=date;
            this.totalPrice=totalPrice;
            this.totalTax=totalTax;
            this.discount=discount;
            this.rewardsdiscount=rewardsdiscount;
            this.netamount=netamount;
        }
    private static final String URL =  "jdbc:mysql://localhost:3306/billingsystem";
    private static final String USER = "root";
    private static final String PASSWORD = "08669";

public static int addBill(Bill b,String member) {
      String insertBillQuery = "INSERT INTO Bill (dates, customerId, totalPrice, totalTax, discount,rewardsdiscount,netamount) VALUES (?, ?, ?, ?, ?,?,?)";
      String insertBillProductQuery = "INSERT INTO BillProduct (billNo, productId, quantity) VALUES (?, ?, ?)";
      String updateCustomerPointsQuery = "UPDATE Customer SET points = ? WHERE id = ?";
      int lastid = -1;

  
      try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
           PreparedStatement billStmt = connection.prepareStatement(insertBillQuery, Statement.RETURN_GENERATED_KEYS);
           PreparedStatement billProductStmt = connection.prepareStatement(insertBillProductQuery);
           PreparedStatement updateCustomerPointsStmt = connection.prepareStatement(updateCustomerPointsQuery)) {
  
          connection.setAutoCommit(false);
          Customer customer = Customer.getCustomerDetails(b.cid);
        if (customer == null) {
            throw new SQLException("Customer not found.");
        }

        // Apply points as discount if available and customer says yes
        int temp_pts=0;
       if(member=="N"){
        temp_pts=customer.points;
        customer.points=0;
       }

    //  if(member=="Y"){
        if (customer.points > 0) {
            b.rewardsdiscount= customer.points;
            customer.points=0; // reset points after applying as discount
        }
  //  }

    
          billStmt.setString(1, b.date);
          billStmt.setInt(2, b.cid);
          billStmt.setInt(3, b.totalPrice);
          billStmt.setDouble(4, b.totalTax);
          billStmt.setDouble(5, b.disamt);
          billStmt.setInt(6,(int) b.rewardsdiscount);
          billStmt.setInt(7,b.netamount);

          billStmt.executeUpdate();
          ResultSet rs = billStmt.getGeneratedKeys();
          if (rs.next()) {
              lastid = rs.getInt(1);
          } else {
              throw new SQLException("Failed to retrieve billNo.");
          }
  
         
          for (Map.Entry<Integer, Integer> entry : b.productidQuantity.entrySet()) {
            int key=entry.getKey();
            int value=entry.getValue();
              billProductStmt.setInt(1, lastid);
              billProductStmt.setInt(2, key);
              billProductStmt.setInt(3, value);
              billProductStmt.addBatch();
          }
          billProductStmt.executeBatch();

          if (customer.membership==true) {
            int newPoints = (int) (b.totalPrice * 0.1); // adding 10% of bill value as points
            customer.points=temp_pts+newPoints;
        }

       
        updateCustomerPointsStmt.setInt(1, customer.points);
        updateCustomerPointsStmt.setInt(2, customer.id);
        updateCustomerPointsStmt.executeUpdate();
  
          connection.commit();
          System.out.println("Bill added successfully.");
  
      } catch (SQLException e) {
          e.printStackTrace();
          
      }
      return lastid;
  }

  public static String getBillFromDB(int billNo) {
    String billQuery = "SELECT * FROM bill WHERE billNo = ?";
    String billProductQuery = "SELECT bp.*, p.name, p.price, p.tax FROM billproduct bp JOIN product p ON bp.productId = p.id WHERE bp.billNo = ?";
    StringBuilder billDetails = new StringBuilder();

    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement billStmt = connection.prepareStatement(billQuery);
         PreparedStatement billProductStmt = connection.prepareStatement(billProductQuery)) {

        billStmt.setInt(1, billNo);
        ResultSet billRs = billStmt.executeQuery();

        if (billRs.next()) {
            String date = billRs.getString("dates");
            int customerId = billRs.getInt("customerId");
            int totalPrice = billRs.getInt("totalPrice");
            double totalTax = billRs.getDouble("totalTax");
            double discount = billRs.getDouble("discount");
            double rewardsdiscount=billRs.getDouble("rewardsdiscount");
            int netamount=billRs.getInt("netamount");

            billDetails.append("GENERAL STORES").append("\n\n")
                       .append("Date: ").append(date).append("   Customer ID: ").append(customerId).append("\n\n").append("Billno:"+billNo).append("\n\n")
                       .append("Item           Quantity            Amount\n");

            billProductStmt.setInt(1, billNo);
            ResultSet billProductRs = billProductStmt.executeQuery();

            while (billProductRs.next()) {
                String productName = billProductRs.getString("name");
                int quantity = billProductRs.getInt("quantity");
                int productPrice = billProductRs.getInt("price");
                double productTax = billProductRs.getDouble("tax");
                double amount = productPrice * quantity;

                billDetails.append(String.format("%-15s %-15d %.2f\n", productName, quantity, amount));
            }

            billDetails.append("\n")
                       .append("Total Amount(Excl.Tax): ").append(totalPrice-totalTax).append("\n")
                       .append("Total Tax: ").append(totalTax).append("\n")
                       .append("Discount: ").append(discount).append("\n")
                       .append("Royalty: ").append(rewardsdiscount).append("\n")
                       .append("Net Bill Value:").append(netamount);
        } else {
            billDetails.append("No bill found with the given ID.\n");
        }

    } catch (SQLException e) {
        e.printStackTrace();
        return "Error occurred while fetching the bill details.";
    }

    return billDetails.toString();
}
   
  public static ArrayList<Bill> salesanalysis(String startDate, String endDate) {
        ArrayList<Bill> billDetailsList = new ArrayList<>();
    
        String sql = "SELECT billNo,dates,totalPrice, totalTax, discount, rewardSdiscount, netamount FROM Bill WHERE dates BETWEEN ? AND ?";

        try (Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, startDate);
            pstmt.setString(2, endDate);
            int cnt=0;
            Bill totalbill = new Bill(999,"2019-01-01",0,0,0,0,0);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                   
                    int billNo = rs.getInt("billNo");
                    String dates=rs.getString("dates");
                    int totalPrice = rs.getInt("totalPrice");
                    double totalTax = rs.getDouble("totalTax");
                    double discount = rs.getDouble("discount");
                    double rewardDiscount = rs.getDouble("rewardsdiscount");
                    int netAmount = rs.getInt("netamount");

                    cnt++;
                    totalbill.totalPrice += totalPrice;
                    totalbill.totalTax += totalTax;
                    totalbill.discount += discount;
                    totalbill.rewardsdiscount += rewardDiscount;
                    totalbill.netamount += netAmount;

                    Bill billDetails = new Bill(billNo,dates,totalPrice, totalTax, discount, rewardDiscount, netAmount);
                    billDetailsList.add(billDetails);
                }
                totalbill.billNo=cnt;
                billDetailsList.add(totalbill);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return billDetailsList;
    }





    public static int getLastBillNumber() {
      String query = "SELECT MAX(billNo) AS lastBillNo FROM bill";
      int lastBillNo = -1;
  
      try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
           PreparedStatement stmt = connection.prepareStatement(query)) {
  
          ResultSet rs = stmt.executeQuery();
          if (rs.next()) {
              lastBillNo = rs.getInt("lastBillNo");
          }
  
      } catch (SQLException e) {
          e.printStackTrace();
      }
  
      return lastBillNo;
  }

}
