# INVOICE GENERATION AND INVENTORY MANAGEMENT SYSTEM


## File:App(Main Class)
The App.java file contains the main class.The App class extends Frame and serves as the entry point of the application. It provides buttons for administrator and employee logins.

### Fields:

adminButton: A button for administrator login.<br/>
employeeButton: A button for employee login.

### CheckAdminFrame:

A frame that prompts the user to enter a username and password for administrator login.
Checks if the credentials match the hardcoded values (admin and 123).
Opens a new AdminFrame upon successful login or a popuperror frame if the login fails.

### CheckEmployeeFrame:

Similar to checkAdminFrame, but for employee login.
Checks if the credentials match the hardcoded values (employee1 and 321).
Opens a new EmployeeFrame upon successful login or a popuperror frame if the login fails.

## File:EmployeeFrame:

This class creates the main frame of the application with three buttons: "NEW BILL", "PRINT BILL", and "STOCK LIST".
Each button is associated with an action listener that opens a new window when clicked.

### 1.stocklist:

This class displays a list of products from the database.
It connects to a MySQL database, retrieves product details (ID, Name, Quantity), and shows them in a text area.
Products with a quantity less than or equal to 5 are marked as "Critical Level", and others as "Normal Level".

### 2.billprint:

This class allows users to print a bill by entering the bill number.
It provides a text field to input the bill number, a submit button to retrieve the bill, and a text area to display the bill details.
The Bill.getBillFromDB method is used to fetch the bill details from the database.

### 3.newbill:

This class facilitates the creation of a new bill.
Users can enter product IDs and quantities, and the application updates the invoice area with these details.
It also calculates the total value of the bill and allows the user to continue to the final billing process.
Additional features include changing the date, clearing the last item, and handling membership status for discounts.

### 4.printfunction:

This class handles customer selection (existing or new) before finalizing the bill.
For existing customers, the bill is created and customer details are fetched.
For new customers, the application validates the input and creates a new customer entry in the database.
The final bill is generated and displayed in the finalprint class.

### 5.finalprint:

This class generates the final bill and saves it to a file.
It writes customer details, item details, total amount, tax, discounts, net payable amount, and balance to a file.
The bill is also saved in the database using the Bill.addBill method.

### 6.popuperror:

This class creates a popup window to display error messages.

### 7.popupsuccess:

This class creates a popup window to display success messages.


## File: AdminFrame
The AdminFrame class is the main class that extends Frame and serves as the user interface for administrative tasks such as adding products, sales analysis, customer payments, deleting options, purchase analysis, viewing inventory, updating products, paying sellers, product analysis, and customer analysis..


### 1.Class: paysellerframe
The paysellerframe class extends Frame and provides functionality to pay a seller.

### 2.Class: paycustomerframe
The paycustomerframe class extends Frame and provides functionality to record customer payments.

### 3.Class: updateproductframe
The updateproductframe class extends Frame and provides functionality to update product details.

### 4.Class: salesanalysisframe
The salesanalysisframe class extends Frame and provides functionality to analyze sales.

### 5.Class: purchaseanalysisframe
The purchaseanalysisframe class extends Frame and provides functionality to analyze purchases.

### 6.Class:deleteoptions
This class extends Frame and is the main window for selecting delete operations. It contains three buttons:

~Product Delete
~Customer Delete
~Bill Delete
Each button opens a corresponding window for deleting a product, customer, or bill.

### 7.Class:CustomerDelete
This class extends Frame and provides a user interface for deleting a customer by their ID.

### 8.Class:ProductDelete
This class extends Frame and provides a user interface for deleting a product by its ID.

### 9.Class:BillDelete
This class extends Frame and provides a user interface for deleting a bill by its ID.

### 10.Class:AddProductFrame
This class extends Frame and provides a user interface for adding a new product and a new seller.

### 11.Class:productanalysisframe
This class extends Frame and provides a user interface for generating product sales reports based on a product ID and a date range.


## File: Bill 
The Bill class handles operations related to billing, including creating new bills, retrieving bill details, and performing sales analysis.


### Constructors:
Bill(int cid, String date): Initializes a new bill with the given customer ID and date.
Bill(int billNo, String date, int totalPrice, double totalTax, double discount, double rewardsdiscount, int netamount): Initializes a bill with all fields specified.

### Methods:
### 1.Class:addBill 
Adds a new bill to the database. If the customer has membership, their points are updated, and points can be applied as a discount. Returns the generated bill number.

### 2.Class:getBillFromDB
Retrieves the details of a bill from the database given a bill number. Returns a formatted string containing the bill details.

### 3.Class: salesanalysis
Retrieves the list of bills within the given date range for sales analysis. Returns an ArrayList of Bill objects, including a summary bill with cumulative totals.

### 4.Class:getLastBillNumber
Retrieves the last bill number from the database. Returns the last bill number.

### 5.customeranalysisframe
This class extends Frame and provides a user interface for generating customer purchase reports based on a customer ID and a date range.





