# INVOICE GENERATION AND INVENTORY MANAGEMENT SYSTEM


## Database Section-Tables

### Customer Table

| Column      | Type         |
|-------------|--------------|                  
| id          | INT PRIMARY KEY AUTO_INCREMENT |
| name        | VARCHAR(255) |
| phoneNo     | VARCHAR(20)  |
| membership  | BOOLEAN      |
| points      | INT          |

![Alt text](https://github.com/lokeshloki55/screenshots/blob/main/table_customer.png)

### Product Table

| Column            | Type                      |
|-------------------|---------------------------|
| id                | INT PRIMARY KEY AUTO_INCREMENT |
| name              | VARCHAR(255)              |
| price             | INT                       |
| availableQuantity | INT                       |
| tax               | DOUBLE                    |

![Alt text](https://github.com/lokeshloki55/screenshots/blob/main/table_product.png)

### Bill Table

| Column       | Type         |
|--------------|--------------|
| billNo       | INT PRIMARY KEY AUTO_INCREMENT |
| dates        | DATE         |
| customerId   | INT          |
| totalPrice   | INT          |
| totalTax     | DOUBLE       |
| discount     | DOUBLE       |
| customerId   | INT, FOREIGN KEY(Customer(id)) |

![Alt text](https://github.com/lokeshloki55/screenshots/blob/main/tablebill.png)

### BillProduct Table

| Column      | Type                     |
|-------------|--------------------------|
| billNo      | INT                      |
| productId   | INT                      |
| quantity    | INT                      |
| PRIMARY KEY | (billNo, productId)      |
| billNo      | INT, FOREIGN KEY(Bill(billNo)) |
| productId   | INT, FOREIGN KEY(Product(id)) |

### Seller Table

| Column         | Type         |
|----------------|--------------|
| name           | VARCHAR(255) |
| balanceAmount  | DOUBLE       |
| PRIMARY KEY    | (name)       |

![Alt text](https://github.com/lokeshloki55/screenshots/blob/main/table_seller.png)

## CODE SECTION
## File:App(Main Class)
![Alt text](https://github.com/lokeshloki55/screenshots/blob/main/app.png)
The App.java file contains the main class.The App class extends Frame and serves as the entry point of the application. It provides buttons for administrator and employee logins.

### Fields:

adminButton: A button for administrator login.<br/>
employeeButton: A button for employee login.

### CheckAdminFrame:*(username:admin ,password 123)
![Alt text](https://github.com/lokeshloki55/screenshots/blob/main/security_admin.png)

A frame that prompts the user to enter a username and password for administrator login.
Checks if the credentials match the hardcoded values (admin and 123).
Opens a new AdminFrame upon successful login or a popuperror frame if the login fails.

### CheckEmployeeFrame:*(username:employee1 , password:321)
![Alt text](https://github.com/lokeshloki55/screenshots/blob/main/security_employee.png)

Similar to checkAdminFrame, but for employee login.
Checks if the credentials match the hardcoded values (employee1 and 321).
Opens a new EmployeeFrame upon successful login or a popuperror frame if the login fails.

## File:EmployeeFrame:
![Alt text](https://github.com/lokeshloki55/screenshots/blob/main/employee_main.png)
This class creates the main frame of the application with three buttons: "NEW BILL", "PRINT BILL", and "STOCK LIST".
Each button is associated with an action listener that opens a new window when clicked.

### 1A).newbill:
![Alt text](https://github.com/lokeshloki55/screenshots/blob/main/nb.png)<br/>
This class facilitates the creation of a new bill.
Users can enter product IDs and quantities, and the application updates the invoice area with these details.
It also calculates the total value of the bill and allows the user to continue to the final billing process.
Additional features include changing the date, clearing the last item, and handling membership status for discounts and the employee can refer to stock availability while billing.

### 1B).printfunction:
![Alt text](https://github.com/lokeshloki55/screenshots/blob/main/newbill2.png)
<br/>
This class handles customer selection (existing or new) before finalizing the bill.
For existing customers, the bill is created and customer details are fetched.
For new customers, the application validates the input and creates a new customer entry in the database.
The final bill is generated and displayed in the finalprint class.

### 1C).finalprint:
![Alt text](https://github.com/lokeshloki55/screenshots/blob/main/new_bill2.png)
<br/>
This class generates the final bill and saves it to a file.
It writes customer details, item details, total amount, tax, discounts, net payable amount, and balance to a file.
The bill is also saved in the database using the Bill.addBill method.

### 2.billprint:
![Alt text](https://github.com/lokeshloki55/screenshots/blob/main/print_bill.png)
This class allows users to print a bill by entering the bill number.
It provides a text field to input the bill number, a submit button to retrieve the bill, and a text area to display the bill details.
The Bill.getBillFromDB method is used to fetch the bill details from the database.

### 3.stocklist:
![Alt text](https://github.com/lokeshloki55/screenshots/blob/main/Stock_status.png)
This class displays a list of products from the database.
It connects to a MySQL database, retrieves product details (ID, Name, Quantity), and shows them in a text area.
Products with a quantity less than or equal to 5 are marked as "Critical Level", and others as "Normal Level".

### LOCALLY SAVED BILL FILE:<br/> 
![Alt text](https://github.com/lokeshloki55/screenshots/blob/main/savedbill.png)

### 4.popuperror:

This class creates a popup window to display error messages.

### 5.popupsuccess:

This class creates a popup window to display success messages.


## File: AdminFrame
![Alt text](https://github.com/lokeshloki55/screenshots/blob/main/admin_main.png)<br/>
The AdminFrame class is the main class that extends Frame and serves as the user interface for administrative tasks such as adding products, sales analysis, customer payments, deleting options, purchase analysis, viewing inventory, updating products, paying sellers, product analysis, and customer analysis..

### 1.Class:AddProductFrame
![Alt text](https://github.com/lokeshloki55/screenshots/blob/main/new_product.png)<br/>
This class extends Frame and provides a user interface for adding a new product and a new seller.

### 2.Class: updateproductframe
![Alt text](https://github.com/lokeshloki55/screenshots/blob/main/update_product.png)<br/>
The updateproductframe class extends Frame and provides functionality to update product details(We can select Quantity or price).

### 3.Class: salesanalysisframe
![Alt text](https://github.com/lokeshloki55/screenshots/blob/main/sales_analysis_billwisereport.png)<br/>
The salesanalysisframe class extends Frame and provides functionality to analyze sales for the given date range.

### 4.Class: purchaseanalysisframe
![Alt text](https://github.com/lokeshloki55/screenshots/blob/main/purchase_analysis.png)<br/>
The purchaseanalysisframe class extends Frame and provides functionality to analyze purchases from sellers for the given date range.

### 5.Class:deleteoptions
![Alt text](https://github.com/lokeshloki55/screenshots/blob/main/delete_options.png)<br/>
This class extends Frame and is the main window for selecting delete operations. It contains three buttons:<br/>

~Product Delete<br/>
~Customer Delete<br/>
~Bill Delete<br/>
Each button opens a corresponding window for deleting a product, customer, or bill.

### 6.Class:CustomerDelete
![Alt text](https://github.com/lokeshloki55/screenshots/blob/main/customer_delete.png)<br/>
This class extends Frame and provides a user interface for deleting a customer by their ID.In the above image error message is shown beacause the customer with Id:19 was not available in the customer table.

### 7.Class:ProductDelete
![Alt text](https://github.com/lokeshloki55/screenshots/blob/main/product_delete.png)<br/>
This class extends Frame and provides a user interface for deleting a product by its ID.

### 8.Class:BillDelete
![Alt text](https://github.com/lokeshloki55/screenshots/blob/main/bill_delete.png)<br/>
This class extends Frame and provides a user interface for deleting a bill by its ID.


### 9.Class:productanalysisframe
![Alt text](https://github.com/lokeshloki55/screenshots/blob/main/product_report.png)<br/>
This class extends Frame and provides a user interface for generating product sales reports based on a product ID and a date range.

### 10.Class: paysellerframe
![Alt text](https://github.com/lokeshloki55/screenshots/blob/main/seller_payment.png)<br/>
The paysellerframe class extends Frame and provides functionality to pay a seller.

### 11.Class: paycustomerframe
![Alt text](https://github.com/lokeshloki55/screenshots/blob/main/customer_payment.png)<br/>
The paycustomerframe class extends Frame and provides functionality to get balance payments from customers by their Id.


## File: Bill 
The Bill class handles operations related to billing, including creating new bills, retrieving bill details, and performing sales analysis.

### Constructors:
Bill(int cid, String date): Initializes a new bill with the given customer ID and date.<br/>
Bill(int billNo, String date, int totalPrice, double totalTax, double discount, double rewardsdiscount, int netamount): Initializes a bill with all fields specified.

### Methods:
### 1.addBill 
Adds a new bill to the database. If the customer has membership, their points are updated(1% of net bill value), and points can be applied as a discount. Returns the generated bill number.

### 2.getBillFromDB
Retrieves the details of a bill from the database given a bill number. Returns a formatted string containing the bill details.

### 3. salesanalysis
Retrieves the list of bills within the given date range for sales analysis. Returns an ArrayList of Bill objects, including a summary bill with cumulative totals.

### 4.getLastBillNumber
Retrieves the last bill number from the database. Returns the last bill number.

### 5.customeranalysisframe
This class extends Frame and provides a user interface for generating customer purchase reports based on a customer ID and a date range.



## File:Customer
The Customer class manages customer-related operations such as creating new customers, updating customer information, and retrieving customer details.

### Constructors
Customer(String name, String phoneNo): Initializes a new customer with the given name and phone number.<br/>
Customer(int id, String name, String phoneNo, Boolean membership, int points): Initializes a customer with all fields specified.

### Methods
### 1. connect
Establishes a connection to the database. Returns the Connection object.

### 2. insertCustomer
Inserts a new customer into the database. Membership status is set based on the provided value. Returns the generated customer ID.

### 3..updateCustomer
Updates the membership status and adds points to the specified customer. Prints a success or failure message based on the outcome.

### 4.deleteCustomer
Deletes a customer from the database based on the provided customer ID. Prints a success or failure message based on the outcome.

### 5.getCustomerDetails
Retrieves customer details from the database based on the provided customer ID. Returns a Customer object if found, otherwise returns null.

### 6.customerbalance
Manages the customer's balance. Can either retrieve (get) or add (add) to the customer's balance based on the action specified. Returns a result message.

### 7.customeranalysis
Retrieves the list of bills for the specified customer within the given date range and calculates the total amount purchased. Also retrieves the customer's balance. Returns a formatted string containing the analysis.

### 8.paycustomer
Deducts a specified amount from the customer's balance if sufficient funds are available. Returns a result message indicating success or failure.

# File:Product
The Product class manages product-related operations such as creating new products, updating product information, and retrieving product details.

### Constructors
Product(String seller, String name, int price, int availableQuantity, double tax)<br/>
Initializes a new product with the given seller, name, price, available quantity, and tax rate.

### Methods
### 1.connect
Establishes a connection to the database. Returns the Connection object.

### 2.insertProduct
Inserts a new product into the database. Also updates the seller's balance amount based on the product's price and quantity.

### 3.insertseller
Inserts a new seller into the database with a balance amount of 0.

### 4.isSellerAvailable
Checks if a seller exists in the database. Returns true if the seller exists, otherwise false.

### 5.payseller
Deducts the specified amount from the seller's balance. Prints a success or error message based on the outcome.

### 6.purchaseanalysis
Retrieves the balance amount for each seller and calculates the total amount. Returns a formatted string containing the analysis.

### 7.updateProduct
Updates the specified field (price or quantity) of the product. If the quantity is updated, the seller's balance amount is also updated based on the new quantity.

### 8.deleteProduct
Deletes a product from the database based on the provided product ID. Prints a success or error message based on the outcome.

### 9.getProductDetailById
Retrieves the specified detail (name, price, tax, available quantity, seller) of the product. Returns the detail as a string.

### 10.getProductSales
Retrieves the sales data for the specified product within the given date range. Returns a formatted string containing the sales data.




