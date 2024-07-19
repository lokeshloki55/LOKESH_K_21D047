import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;


public class AdminFrame extends Frame {
  
    public AdminFrame() {
        
        Font buttonFont = new Font("Arial", Font.PLAIN, 32);

        int frameWidth = 1000;
        int buttonWidth = 270;
        int buttonHeight = 100;
        int verticalSpacing = 50;
        int horizontalSpacing = 50;

        
        int centerX = frameWidth / 2;
        int buttonX1 = centerX - buttonWidth - horizontalSpacing / 2;
        int buttonX2 = centerX + horizontalSpacing / 2;

        Button addProductButton = new Button("Add New Product/Seller");
        addProductButton.setBounds(buttonX1, 150, buttonWidth, buttonHeight);
        addProductButton.setFont(new Font("Arial", Font.PLAIN, 23)); 

        Button salesAnalysisButton = new Button("Sales Analysis");
        salesAnalysisButton.setBounds(buttonX2, 150, buttonWidth, buttonHeight);
        salesAnalysisButton.setFont(buttonFont); 

        Button addChangeDateButton = new Button("Customer Payment");
        addChangeDateButton.setBounds(buttonX1, 150 + buttonHeight + verticalSpacing, buttonWidth, buttonHeight);
        addChangeDateButton.setFont(buttonFont); 

        Button deleteoptionbutton = new Button("Delete Options");
        deleteoptionbutton.setBounds(buttonX2, 150 + buttonHeight + verticalSpacing, buttonWidth, buttonHeight);
        deleteoptionbutton.setFont(buttonFont); 

        Button purchaseanalysisbutton = new Button("Purchase Analysis");
        purchaseanalysisbutton.setBounds(buttonX1, 150 + 2 * (buttonHeight + verticalSpacing), buttonWidth, buttonHeight);
        purchaseanalysisbutton.setFont(buttonFont); 

        Button viewInventoryButton = new Button("View Inventory");
        viewInventoryButton.setBounds(buttonX2, 150 + 2 * (buttonHeight + verticalSpacing), buttonWidth, buttonHeight);
        viewInventoryButton.setFont(buttonFont); 

        Button updateproductbButton = new Button("Update Product");
        updateproductbButton.setBounds(buttonX1, 150 + 3 * (buttonHeight + verticalSpacing), buttonWidth, buttonHeight);
        updateproductbButton.setFont(buttonFont); 

        Button payselleButton = new Button("Pay Seller");
        payselleButton.setBounds(buttonX2, 150 + 3 * (buttonHeight + verticalSpacing), buttonWidth, buttonHeight);
        payselleButton.setFont(buttonFont); 

        Button newButton3 = new Button("Product Analysis");
        newButton3.setBounds(buttonX1, 150 + 4 * (buttonHeight + verticalSpacing), buttonWidth, buttonHeight);
        newButton3.setFont(buttonFont); 

        Button newButton4 = new Button("Customer Analysis");
        newButton4.setBounds(buttonX2, 150 + 4 * (buttonHeight + verticalSpacing), buttonWidth, buttonHeight);
        newButton4.setFont(buttonFont); 

       
        add(addProductButton);
        add(salesAnalysisButton);
        add(addChangeDateButton);
        add(deleteoptionbutton);
        add(purchaseanalysisbutton);
        add(viewInventoryButton);
        add(updateproductbButton);
        add(payselleButton);
        add(newButton3);
        add(newButton4);
       
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddProductFrame();
            }
        });

        salesAnalysisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new salesanalysisframe();
            }
        });

        addChangeDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new paycustomerframe();
            }
        });

        deleteoptionbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new deleteoptions();
            }
        });

        purchaseanalysisbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new purchaseanalysisframe();
            }
        });

        viewInventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new stocklist();
            }
        });

        updateproductbButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new updateproductframe();
            }
        });

        payselleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new paysellerframe();
            }
        });

        newButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new productanalysisframe();
            }
        });

        newButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new customeranalysisframe();
            }
        });

        setLayout(null);
        setSize(frameWidth, 1000); 
        setTitle("Administrator");
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}


class paysellerframe extends Frame {

    public paysellerframe() {
       
        Font buttonFont = new Font("Arial", Font.PLAIN, 32);
        Font labelFont = new Font("Arial", Font.PLAIN, 24);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 24);

        
        int frameWidth = 1000;
        int buttonWidth = 270;
        int buttonHeight = 100;
        int fieldWidth = 300;
        int fieldHeight = 50;

        
        int centerX = frameWidth / 2;


        Label typeLabel = new Label("Seller name:");
        typeLabel.setFont(labelFont);
        typeLabel.setBounds(centerX - fieldWidth / 2, 150, fieldWidth, fieldHeight);

        Label newValueLabel = new Label("Amount to be paid:");
        newValueLabel.setFont(labelFont);
        newValueLabel.setBounds(centerX - fieldWidth / 2, 250, fieldWidth, fieldHeight);

        TextField typeField = new TextField();
        typeField.setFont(textFieldFont);
        typeField.setBounds(centerX - fieldWidth / 2, 200, fieldWidth, fieldHeight);

        TextField newValueField = new TextField();
        newValueField.setFont(textFieldFont);
        newValueField.setBounds(centerX - fieldWidth / 2, 300, fieldWidth, fieldHeight);

        Button submitButton = new Button("Pay");
        submitButton.setFont(buttonFont);
        submitButton.setBounds(centerX - buttonWidth / 2, 400, buttonWidth, buttonHeight);

        add(typeLabel);
        add(typeField);
        add(newValueLabel);
        add(newValueField);
        add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Product.payseller(typeField.getText(),Integer.parseInt(newValueField.getText()));
            }
        });

        setLayout(null);
        setSize(frameWidth, 1000);
        setTitle("Seller Payment");
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}


class paycustomerframe extends Frame {

    public paycustomerframe() {
       
        Font buttonFont = new Font("Arial", Font.PLAIN, 32);
        Font labelFont = new Font("Arial", Font.PLAIN, 24);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 24);

        
        int frameWidth = 1000;
        int buttonWidth = 270;
        int buttonHeight = 100;
        int fieldWidth = 300;
        int fieldHeight = 50;

        
        int centerX = frameWidth / 2;


        Label typeLabel = new Label("Customer Id");
        typeLabel.setFont(labelFont);
        typeLabel.setBounds(centerX - fieldWidth / 2, 150, fieldWidth, fieldHeight);

        Label newValueLabel = new Label("Amount paid:");
        newValueLabel.setFont(labelFont);
        newValueLabel.setBounds(centerX - fieldWidth / 2, 250, fieldWidth, fieldHeight);

        TextField typeField = new TextField();
        typeField.setFont(textFieldFont);
        typeField.setBounds(centerX - fieldWidth / 2, 200, fieldWidth, fieldHeight);

        TextField newValueField = new TextField();
        newValueField.setFont(textFieldFont);
        newValueField.setBounds(centerX - fieldWidth / 2, 300, fieldWidth, fieldHeight);

        Button submitButton = new Button("Accept");
        submitButton.setFont(buttonFont);
        submitButton.setBounds(centerX - buttonWidth / 2, 400, buttonWidth, buttonHeight);

        add(typeLabel);
        add(typeField);
        add(newValueLabel);
        add(newValueField);
        add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //Product.payseller(typeField.getText(),Integer.parseInt(newValueField.getText()));
               Customer.paycustomer(Integer.parseInt(typeField.getText()),Integer.parseInt(newValueField.getText()));
               typeField.setText("");
               newValueField.setText("");
               new popupsuccess("Payment Updated Successfully!!!");
            }
        });

        setLayout(null);
        setSize(frameWidth, 1000);
        setTitle("Customer Payment");
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}

class updateproductframe extends Frame {

    public updateproductframe() {
       
        Font buttonFont = new Font("Arial", Font.PLAIN, 32);
        Font labelFont = new Font("Arial", Font.PLAIN, 24);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 24);

        int frameWidth = 1000;
        int labelWidth = 200;
        int fieldWidth = 300;
        int fieldHeight = 50;
        int buttonWidth = 270;
        int buttonHeight = 100;

        int centerX = frameWidth / 2;

        Label idLabel = new Label("Product Id:");
        idLabel.setFont(labelFont);
        idLabel.setBounds(centerX - fieldWidth / 2 - labelWidth / 2, 150, labelWidth, fieldHeight);

        Label typeLabel = new Label("Quantity/Price:");
        typeLabel.setFont(labelFont);
        typeLabel.setBounds(centerX - fieldWidth / 2 - labelWidth / 2, 250, labelWidth, fieldHeight);

        Label newValueLabel = new Label("Value:");
        newValueLabel.setFont(labelFont);
        newValueLabel.setBounds(centerX - fieldWidth / 2 - labelWidth / 2, 350, labelWidth, fieldHeight);

        TextField idField = new TextField();
        idField.setFont(textFieldFont);
        idField.setBounds(centerX - fieldWidth / 2 + labelWidth / 2, 150, fieldWidth, fieldHeight);

        TextField typeField = new TextField();
        typeField.setFont(textFieldFont);
        typeField.setBounds(centerX - fieldWidth / 2 + labelWidth / 2, 250, fieldWidth, fieldHeight);

        TextField newValueField = new TextField();
        newValueField.setFont(textFieldFont);
        newValueField.setBounds(centerX - fieldWidth / 2 + labelWidth / 2, 350, fieldWidth, fieldHeight);

        Button submitButton = new Button("Update");
        submitButton.setFont(buttonFont);
        submitButton.setBounds(centerX - buttonWidth / 2, 450, buttonWidth, buttonHeight);

        add(idLabel);
        add(idField);
        add(typeLabel);
        add(typeField);
        add(newValueLabel);
        add(newValueField);
        add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int productId = Integer.parseInt(idField.getText());
                String type = typeField.getText();
                int newValue = Integer.parseInt(newValueField.getText());
                if (type.equalsIgnoreCase("price") || type.equalsIgnoreCase("quantity")) {
                    Product.updateProduct(productId, type, newValue);
                    new popupsuccess("Product updated successfully!!!");
                } else {
                    new popuperror("Please type only Quantity or Price in the first column!!!");
                }
            }
        });

        setLayout(null);
        setSize(frameWidth, 1000);
        setTitle("Update Product Details");
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

   
}

class salesanalysisframe extends Frame {

    public salesanalysisframe() {
        int frameWidth = 1000;
        int frameHeight = 1000;
        
        Font labelFont = new Font("Arial", Font.PLAIN, 24);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 24);
        Font buttonFont = new Font("Arial", Font.PLAIN, 24);
        Font textAreaFont = new Font("Arial", Font.PLAIN, 24);

        int centerX = frameWidth / 2;

        Label startDateLabel = new Label("Start Date:");
        startDateLabel.setFont(labelFont);
        startDateLabel.setBounds(centerX - 200, 50, 150, 40);

        TextField startDateField = new TextField();
        startDateField.setFont(textFieldFont);
        startDateField.setBounds(centerX - 50, 50, 200, 40);

        Label endDateLabel = new Label("End Date:");
        endDateLabel.setFont(labelFont);
        endDateLabel.setBounds(centerX - 200, 120, 150, 40);

        TextField endDateField = new TextField();
        endDateField.setFont(textFieldFont);
        endDateField.setBounds(centerX - 50, 120, 200, 40);

        Button submitButton = new Button("Submit");
        submitButton.setFont(buttonFont);
        submitButton.setBounds(centerX + 170, 85, 120, 40);

        TextArea resultArea = new TextArea();
        resultArea.setFont(textAreaFont);
        resultArea.setBounds(50, 200, 900, 750);

        add(startDateLabel);
        add(startDateField);
        add(endDateLabel);
        add(endDateField);
        add(submitButton);
        add(resultArea);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String startDate = startDateField.getText();
                String endDate = endDateField.getText();
                ArrayList<Bill>arr=Bill.salesanalysis(startDate,endDate);
                String str="BILL NO.    DATE    BILL-VALUE    TAX    DISCOUNT    ROYALTY    NET-VALUE \n\n";
                for(int i=0;i<=arr.size()-2;i++){
                    Bill b=arr.get(i);
                    str+=String.format("%-10s",String.valueOf(b.billNo))+"   "+b.date+"   "+String.format("%-10s",String.valueOf(b.totalPrice))+"    "+String.format("%-10s",String.valueOf(b.totalTax))+"   "+String.format("%-10s",String.valueOf(b.discount))+"      "+String.format("%-10s",String.valueOf(b.rewardsdiscount))+"       "+String.format("%-10s",String.valueOf(b.netamount))+"\n";
                }
                Bill tot=arr.get(arr.size()-1);
                str+="\n";
                str+="TOTAL:"+"                        "+String.valueOf(tot.totalPrice)+"        "+String.valueOf(tot.totalTax)+"        "+String.valueOf(tot.discount)+"          "+String.valueOf(tot.rewardsdiscount)+"           "+String.valueOf(tot.netamount)+"\n";
                str+="Total number of bills:"+tot.billNo;
                resultArea.setText(str);
            }
        });

        setLayout(null);
        setSize(frameWidth, frameHeight);
        setTitle("Date wise Bill Report");
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}

class purchaseanalysisframe extends Frame {
    public purchaseanalysisframe() {
        
        setSize(1000, 1000);
        setLayout(null);
        setTitle("PAYMENTS BENDING");

        TextArea textArea = new TextArea(Product.purchaseanalysis());
        textArea.setBounds(100, 100, 800, 800);

        Font textFont = new Font("Arial", Font.PLAIN, 24);
        textArea.setFont(textFont);

        add(textArea);

        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}
    class deleteoptions extends Frame {
        Button button1, button2, button3;
    
        public deleteoptions() {
            Font buttonFont = new Font("Arial", Font.PLAIN, 28);
            int frameWidth = 1000;
            int buttonWidth = 220;
            int buttonHeight = 100;
            
            int centerX = frameWidth / 2;
    
            Button button1 = new Button("Product Delete");
            button1.setBounds(centerX - buttonWidth / 2, 300, buttonWidth, buttonHeight);
            button1.setFont(buttonFont); 
    
            Button button2 = new Button("Customer Delete");
            button2.setBounds(centerX - buttonWidth / 2, 425, buttonWidth, buttonHeight);
            button2.setFont(buttonFont); 
    
            Button button3 = new Button("Bill Delete");
            button3.setBounds(centerX - buttonWidth / 2, 550, buttonWidth, buttonHeight);
            button3.setFont(buttonFont); 
            add(button1);
            add(button2);
            add(button3);
      
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ProductDelete();
                }
            });
    
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new CustomerDelete();
                }
            });
    
            button3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   new BillDelete();
                }
            });

            setLayout(null);
            setTitle("DeleteOptions");
            setSize(1000, 1000);
            setVisible(true);
    
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    dispose();
                }
            });
        }
}

class CustomerDelete extends Frame {
        TextField idField;
        Button submitButton;
        TextArea resultArea;
    
        public CustomerDelete() {
           
        Font buttonFont = new Font("Arial", Font.PLAIN, 32);
        Font labelFont = new Font("Arial", Font.PLAIN, 24);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 24);
        Font textAreaFont = new Font("Arial", Font.PLAIN, 24);

        
        int frameWidth = 1000;
       
        int labelWidth = 200;
        int labelHeight = 50;
        int textFieldWidth = 300;
        int textFieldHeight = 50;
        int buttonWidth = 300;
        int buttonHeight = 100;
        int textAreaWidth = 500;
        int textAreaHeight = 300;
        
        int centerX = frameWidth / 2;

        Label idLabel = new Label("Customer ID:");
        idLabel.setBounds(centerX - (labelWidth + textFieldWidth + buttonWidth) / 2, 150, labelWidth, labelHeight);
        idLabel.setFont(labelFont); 

        TextField idField = new TextField();
        idField.setBounds(centerX - (labelWidth + textFieldWidth + buttonWidth) / 2 + labelWidth, 150, textFieldWidth, textFieldHeight);
        idField.setFont(textFieldFont); 

        Button submitButton = new Button("Delete Customer");
        submitButton.setBounds(centerX - (labelWidth + textFieldWidth + buttonWidth) / 2 + labelWidth , 575, buttonWidth, buttonHeight);
        submitButton.setFont(buttonFont); 

        TextArea resultArea = new TextArea();
        resultArea.setBounds(centerX - textAreaWidth / 2, 250, textAreaWidth, textAreaHeight);
        resultArea.setFont(textAreaFont); 

     
            add(idLabel);
            add(idField);
            add(submitButton);
            add(resultArea);
    
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int customerId = Integer.parseInt(idField.getText());
                  
                    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/billingsystem", "root", "08669");
                         PreparedStatement stmt = connection.prepareStatement("DELETE FROM Customer WHERE id = ?")) {
                        stmt.setInt(1, customerId);
                        int rowsAffected = stmt.executeUpdate();
                        if (rowsAffected > 0) {
                            resultArea.setText("Customer deleted successfully.");
                        } else {
                            resultArea.setText("Customer ID not found.");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        resultArea.setText("Error deleting customer.");
                    }
                }
            });
    
            setLayout(null);
            setSize(1000, 1000);
            setTitle("Delete Customer");
            setVisible(true);

            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    dispose();
                }
            });
        }
}

class ProductDelete extends Frame {
    TextField idField;
    Button submitButton;
    TextArea resultArea;

    public ProductDelete() {
    
        Font buttonFont = new Font("Arial", Font.PLAIN, 32);
        Font labelFont = new Font("Arial", Font.PLAIN, 24);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 24);
        Font textAreaFont = new Font("Arial", Font.PLAIN, 24);

        
        int frameWidth = 1000;
        int labelWidth = 150;
        int labelHeight = 30;
        int textFieldWidth = 300;
        int textFieldHeight = 50;
        int buttonWidth = 300;
        int buttonHeight = 50;
        int textAreaWidth = 500;
        int textAreaHeight = 300;

        int centerX = frameWidth / 2;

        Label idLabel = new Label("Product ID:");
        idLabel.setBounds(centerX - (labelWidth + textFieldWidth + buttonWidth) / 2, 150, labelWidth, labelHeight);
        idLabel.setFont(labelFont); 

        TextField idField = new TextField();
        idField.setBounds(centerX - (labelWidth + textFieldWidth + buttonWidth) / 2 + labelWidth, 150, textFieldWidth, textFieldHeight);
        idField.setFont(textFieldFont); 

        Button submitButton = new Button("Delete Product");
        submitButton.setBounds(centerX - (labelWidth + textFieldWidth + buttonWidth) / 2 + labelWidth , 575, buttonWidth, buttonHeight);
        submitButton.setFont(buttonFont); 

        TextArea resultArea = new TextArea();
        resultArea.setBounds(centerX - textAreaWidth / 2, 250, textAreaWidth, textAreaHeight);
        resultArea.setFont(textAreaFont); 

        add(idLabel);
        add(idField);
        add(submitButton);
        add(resultArea);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int productId = Integer.parseInt(idField.getText());

                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/billingsystem", "root", "08669");
                     PreparedStatement stmt = connection.prepareStatement("DELETE FROM Product WHERE id = ?")) {
                    stmt.setInt(1, productId);
                    int rowsAffected = stmt.executeUpdate();
                    if (rowsAffected > 0) {
                        resultArea.setText("Product deleted successfully.");
                    } else {
                        resultArea.setText("Product ID not found.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    resultArea.setText("Error deleting product.");
                }
            }
        });

        setLayout(null);
        setSize(1000, 1000);
        setTitle("Delete Product");
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}


class BillDelete extends Frame {
    TextField idField;
    Button submitButton;
    TextArea resultArea;

    public BillDelete() {
    
        Font buttonFont = new Font("Arial", Font.PLAIN, 32);
        Font labelFont = new Font("Arial", Font.PLAIN, 24);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 24);
        Font textAreaFont = new Font("Arial", Font.PLAIN, 24);
        
        int frameWidth = 1000;
        int labelWidth = 150;
        int labelHeight = 30;
        int textFieldWidth = 300;
        int textFieldHeight = 50;
        int buttonWidth = 300;
        int buttonHeight = 50;
        int textAreaWidth = 500;
        int textAreaHeight = 300;

        int centerX = frameWidth / 2;

        Label idLabel = new Label("Bill ID:");
        idLabel.setBounds(centerX - (labelWidth + textFieldWidth + buttonWidth) / 2, 150, labelWidth, labelHeight);
        idLabel.setFont(labelFont); 

        TextField idField = new TextField();
        idField.setBounds(centerX - (labelWidth + textFieldWidth + buttonWidth) / 2 + labelWidth, 150, textFieldWidth, textFieldHeight);
        idField.setFont(textFieldFont); 

        Button submitButton = new Button("Delete Bill");
        submitButton.setBounds(centerX - (labelWidth + textFieldWidth + buttonWidth) / 2 + labelWidth , 575, buttonWidth, buttonHeight);
        submitButton.setFont(buttonFont); 

        TextArea resultArea = new TextArea();
        resultArea.setBounds(centerX - textAreaWidth / 2, 250, textAreaWidth, textAreaHeight);
        resultArea.setFont(textAreaFont); 

        add(idLabel);
        add(idField);
        add(submitButton);
        add(resultArea);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int billId = Integer.parseInt(idField.getText());
                try (Connection connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/billingsystem", "root", "08669");
                PreparedStatement stmt1 = connection.prepareStatement("DELETE FROM Billproduct WHERE billNo = ?");PreparedStatement stmt2 = connection.prepareStatement("DELETE FROM Bill WHERE billNo = ?")) {
                    stmt1.setInt(1, billId);
                    stmt2.setInt(1, billId);
                    int rowsAffected1 = stmt1.executeUpdate();
                    int rowsAffected2 = stmt2.executeUpdate();
                    if (rowsAffected1 > 0&&rowsAffected2>0) {
                        resultArea.setText("Bill deleted successfully.");
                    } else {
                        resultArea.setText("Bill ID not found.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    resultArea.setText("Error deleting bill.");
                }
            }
        });

        setLayout(null);
        setSize(1000, 1000);
        setTitle("Delete Bill");
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}

    class AddProductFrame extends Frame {
        TextField idField, nameField, priceField, quantityField, taxField,sellerField;
        Button submitButton,submitseller;
        

        public AddProductFrame() {
           
        Font buttonFont = new Font("Arial", Font.PLAIN, 32);
        Font labelFont = new Font("Arial", Font.PLAIN, 24);
        Font labelFont1 = new Font("Arial", Font.BOLD, 28);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 24);

        int frameWidth = 1000;
        int labelWidth = 275;
        int labelHeight = 50;
        int textFieldWidth = 300;
        int textFieldHeight = 50;
        int buttonWidth = 200;
        int buttonHeight = 80;

        int centerX = frameWidth / 2;

        Label productLabel = new Label("ADD NEW PRODUCT:");
        productLabel.setBounds(centerX - (labelWidth + textFieldWidth) / 2, 100, labelWidth+50, labelHeight);
        productLabel.setFont(labelFont1); 

        Label sellerLabel = new Label("ADD NEW SELLER:");
        sellerLabel.setBounds(centerX - (labelWidth + textFieldWidth) / 2, 550, labelWidth, labelHeight);
        sellerLabel.setFont(labelFont1); 

        Label sellerNameLabel = new Label("Name:");
        sellerNameLabel.setBounds(centerX - (labelWidth + textFieldWidth) / 2, 600, labelWidth, labelHeight);
        sellerNameLabel.setFont(labelFont); 

        Label idLabel = new Label("Seller Name:");
        idLabel.setBounds(centerX - (labelWidth + textFieldWidth) / 2, 150, labelWidth, labelHeight);
        idLabel.setFont(labelFont); 

        Label nameLabel = new Label("Name:");
        nameLabel.setBounds(centerX - (labelWidth + textFieldWidth) / 2, 200, labelWidth, labelHeight);
        nameLabel.setFont(labelFont); 

        Label priceLabel = new Label("Price:");
        priceLabel.setBounds(centerX - (labelWidth + textFieldWidth) / 2, 250, labelWidth, labelHeight);
        priceLabel.setFont(labelFont); 

        Label quantityLabel = new Label("Quantity:");
        quantityLabel.setBounds(centerX - (labelWidth + textFieldWidth) / 2, 300, labelWidth, labelHeight);
        quantityLabel.setFont(labelFont); 

        Label taxLabel = new Label("Tax:");
        taxLabel.setBounds(centerX - (labelWidth + textFieldWidth) / 2, 350, labelWidth, labelHeight);
        taxLabel.setFont(labelFont); 

        TextField idField = new TextField();
        idField.setBounds(centerX - (labelWidth + textFieldWidth) / 2 + labelWidth, 150, textFieldWidth, textFieldHeight);
        idField.setFont(textFieldFont); 

        TextField nameField = new TextField();
        nameField.setBounds(centerX - (labelWidth + textFieldWidth) / 2 + labelWidth, 200, textFieldWidth, textFieldHeight);
        nameField.setFont(textFieldFont); 

        TextField priceField = new TextField();
        priceField.setBounds(centerX - (labelWidth + textFieldWidth) / 2 + labelWidth, 250, textFieldWidth, textFieldHeight);
        priceField.setFont(textFieldFont); 

        TextField quantityField = new TextField();
        quantityField.setBounds(centerX - (labelWidth + textFieldWidth) / 2 + labelWidth, 300, textFieldWidth, textFieldHeight);
        quantityField.setFont(textFieldFont); 

        TextField taxField = new TextField();
        taxField.setBounds(centerX - (labelWidth + textFieldWidth) / 2 + labelWidth, 350, textFieldWidth, textFieldHeight);
        taxField.setFont(textFieldFont); 

        TextField sellerField = new TextField();
        sellerField.setBounds(centerX - (labelWidth + textFieldWidth) / 2 + labelWidth, 600, textFieldWidth, textFieldHeight);
        sellerField.setFont(textFieldFont); 

        Button submitButton = new Button("Add Product");
        submitButton.setBounds(centerX - buttonWidth / 2, 425, buttonWidth, buttonHeight);
        submitButton.setFont(buttonFont); 

        Button submitseller = new Button("Add Seller");
        submitseller.setBounds(centerX - buttonWidth / 2, 675, buttonWidth, buttonHeight);
        submitseller.setFont(buttonFont); 

        add(productLabel);
        add(sellerLabel);
        add(sellerNameLabel);
        add(idLabel);
        add(nameLabel);
        add(priceLabel);
        add(quantityLabel);
        add(taxLabel);
        add(idField);
        add(nameField);
        add(priceField);
        add(quantityField);
        add(taxField);
        add(sellerField);
        add(submitButton);
        add(submitseller);

    
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String sellername=idField.getText();
                    Product p=new Product(sellername,nameField.getText(),Integer.valueOf(priceField.getText()),Integer.valueOf(quantityField.getText()),Integer.valueOf(taxField.getText()));
                    idField.setText("");
                    nameField.setText("");
                    priceField.setText("");
                    quantityField.setText("");
                    taxField.setText("");

                    if(!Product.isSellerAvailable(sellername))
                    new popuperror("Seller is Unavailable in database.\nPlease add a new Seller in the below Column !!!");
                    else{
                    Product.insertProduct(p);
                    new popupsuccess("Product Added Successfully!!!");
                    }
                }
            });

            submitseller.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Product.insertseller(sellerField.getText());
                    sellerField.setText("");
                    new popupsuccess("Seller Added Successfully!!!");
                }
            });
    
    
            setLayout(null);
            setSize(1000, 1000);
            setTitle("Add New Product");
            setVisible(true);
    
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    dispose();
                }
            });
        }
}

class productanalysisframe extends Frame {
    TextField productIdField, startDateField, endDateField;
    TextArea resultArea;
    Button submitButton;

    public productanalysisframe() {

        Font labelFont = new Font("Arial", Font.PLAIN, 24);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 24);
        Font buttonFont = new Font("Arial", Font.PLAIN, 24);
        Font textAreaFont = new Font("Arial", Font.PLAIN, 24);

        int frameWidth = 1000;
        int frameHeight = 1000;
        
        int labelWidth = 150;
        int labelHeight = 30;
        int fieldWidth = 200;
        int fieldHeight = 30;
        int buttonWidth = 150;
        int buttonHeight = 50;
        int textAreaWidth = 800;
        int textAreaHeight = 800;

        int labelX = 50;
        int fieldX = labelX + labelWidth + 10;
        int buttonX = labelX + 2 * (labelWidth + 10 + fieldWidth) / 2 - buttonWidth / 2;
        int textAreaX = (frameWidth - textAreaWidth) / 2;
        int textAreaY = 200;

        Label productIdLabel = new Label("Product ID:");
        productIdLabel.setFont(labelFont);
        productIdLabel.setBounds(labelX, 50, labelWidth, labelHeight);

        Label startDateLabel = new Label("Start Date:");
        startDateLabel.setFont(labelFont);
        startDateLabel.setBounds(labelX, 100, labelWidth, labelHeight);

        Label endDateLabel = new Label("End Date:");
        endDateLabel.setFont(labelFont);
        endDateLabel.setBounds(labelX, 150, labelWidth, labelHeight);

        productIdField = new TextField();
        productIdField.setFont(textFieldFont);
        productIdField.setBounds(fieldX, 50, fieldWidth, fieldHeight);

        startDateField = new TextField();
        startDateField.setFont(textFieldFont);
        startDateField.setBounds(fieldX, 100, fieldWidth, fieldHeight);

        endDateField = new TextField();
        endDateField.setFont(textFieldFont);
        endDateField.setBounds(fieldX, 150, fieldWidth, fieldHeight);

        submitButton = new Button("Submit");
        submitButton.setFont(buttonFont);
        submitButton.setBounds(buttonX+100, 100, buttonWidth, buttonHeight);

        resultArea = new TextArea();
        resultArea.setFont(textAreaFont);
        resultArea.setBounds(textAreaX, textAreaY, textAreaWidth-10, textAreaHeight-50);

        add(productIdLabel);
        add(productIdField);
        add(startDateLabel);
        add(startDateField);
        add(endDateLabel);
        add(endDateField);
        add(submitButton);
        add(resultArea);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productId = productIdField.getText();
                String startDate = startDateField.getText();
                String endDate = endDateField.getText();
                String report = Product.getProductSales(Integer.parseInt(productId), startDate, endDate);
                resultArea.setText(report);
            }
        });

        setLayout(null);
        setSize(frameWidth, frameHeight);
        setTitle("Product report");
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}


class customeranalysisframe extends Frame {
    TextField productIdField, startDateField, endDateField;
    TextArea resultArea;
    Button submitButton;

    public customeranalysisframe() {
       
        Font labelFont = new Font("Arial", Font.PLAIN, 24);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 24);
        Font buttonFont = new Font("Arial", Font.PLAIN, 24);
        Font textAreaFont = new Font("Arial", Font.PLAIN, 24);

        int frameWidth = 1000;
        int frameHeight = 1000;
        
        int labelWidth = 150;
        int labelHeight = 30;
        int fieldWidth = 200;
        int fieldHeight = 30;
        int buttonWidth = 150;
        int buttonHeight = 50;
        int textAreaWidth = 800;
        int textAreaHeight = 800;

        int labelX = 50;
        int fieldX = labelX + labelWidth + 10;
        int buttonX = labelX + 2 * (labelWidth + 10 + fieldWidth) / 2 - buttonWidth / 2;
        int textAreaX = (frameWidth - textAreaWidth) / 2;
        int textAreaY = 200;

        Label productIdLabel = new Label("Customer ID:");
        productIdLabel.setFont(labelFont);
        productIdLabel.setBounds(labelX, 50, labelWidth, labelHeight);

        Label startDateLabel = new Label("Start Date:");
        startDateLabel.setFont(labelFont);
        startDateLabel.setBounds(labelX, 100, labelWidth, labelHeight);

        Label endDateLabel = new Label("End Date:");
        endDateLabel.setFont(labelFont);
        endDateLabel.setBounds(labelX, 150, labelWidth, labelHeight);

        productIdField = new TextField();
        productIdField.setFont(textFieldFont);
        productIdField.setBounds(fieldX, 50, fieldWidth, fieldHeight);

        startDateField = new TextField();
        startDateField.setFont(textFieldFont);
        startDateField.setBounds(fieldX, 100, fieldWidth, fieldHeight);

        endDateField = new TextField();
        endDateField.setFont(textFieldFont);
        endDateField.setBounds(fieldX, 150, fieldWidth, fieldHeight);

        submitButton = new Button("Submit");
        submitButton.setFont(buttonFont);
        submitButton.setBounds(buttonX+100, 100, buttonWidth, buttonHeight);

        resultArea = new TextArea();
        resultArea.setFont(textAreaFont);
        resultArea.setBounds(textAreaX, textAreaY, textAreaWidth-10, textAreaHeight-50);

        add(productIdLabel);
        add(productIdField);
        add(startDateLabel);
        add(startDateField);
        add(endDateLabel);
        add(endDateField);
        add(submitButton);
        add(resultArea);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cid = productIdField.getText();
                String startDate = startDateField.getText();
                String endDate = endDateField.getText();
                String report = Customer.customeranalysis(Integer.parseInt(cid), startDate, endDate);
                resultArea.setText(report);
            }
        });

        setLayout(null);
        setSize(frameWidth, frameHeight);
        setTitle("Customer report");
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}



class popupsuccess extends Frame {
    Label l;
    public popupsuccess(String str){
        l=new Label(str);
        Font labelFont = new Font("Arial", Font.PLAIN, 24);
        l.setFont(labelFont);
        add(l);
        setLayout(new FlowLayout());
        setSize(700, 200);
        setTitle("SUCCESS MESSAGE");
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}

class popuperror extends Frame {
    Label l;
    public popuperror(String str){
        l=new Label(str);
        Font labelFont = new Font("Arial", Font.PLAIN, 24);
        l.setFont(labelFont);
        add(l);
        setLayout(new FlowLayout());
        setSize(550, 200);
        setTitle("Error MESSAGE");
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}


