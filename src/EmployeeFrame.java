import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.sql.*;

public class EmployeeFrame extends Frame {
  
    Button button1, button2, button3;
    public EmployeeFrame() {
        Font buttonFont = new Font("Arial", Font.PLAIN, 32);
        int buttonWidth = 250;
        int buttonHeight = 110;
       
        Button button1 = new Button("NEW BILL");
        button1.setBounds(350, 200, buttonWidth, buttonHeight);
        button1.setFont(buttonFont);

        Button button2 = new Button("PRINT BILL");
        button2.setBounds(350, 350, buttonWidth, buttonHeight);
        button2.setFont(buttonFont); 

        Button button3 = new Button("STOCK LIST");
        button3.setBounds(350, 500, buttonWidth, buttonHeight);
        button3.setFont(buttonFont); 

       
        add(button1);
        add(button2);
        add(button3);
                    button1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new newbill();
                        }
                    });
            
                    button2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                           new billprint();
                        }
                    });
            
                    button3.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                          new stocklist();
                        }
                    });
            
                    setLayout(null);
                    setTitle("Employee-Main");
                    setSize(1000, 1000);
                    setVisible(true);
            
                    addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent e) {
                            dispose();
                        }
                    });
                }
        }


class stocklist extends Frame {
    TextArea productTextArea;
    Connection conn;

    stocklist() {
        productTextArea = new TextArea(10, 40);
        productTextArea.setEditable(false);
        Font labelFont = new Font("Arial", Font.PLAIN, 24);
        productTextArea.setFont(labelFont);
        productTextArea.setBounds(100, 100, 800, 800);
        add(productTextArea);

    
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/billingsystem", "root", "08669");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            StringBuilder sb = new StringBuilder();
            sb.append("ID\tName\tQuantity\t  Status\n");
            sb.append("----------------------------------------------------------------------------------\n");
    
            try (Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT id, name, availableQuantity FROM Product")) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int quantity = rs.getInt("availableQuantity");
                    if(quantity<=5)
                    sb.append(id).append("\t").append(name).append("\t").append(quantity).append("\t  ").append("Critical Level").append("\n");
                    else
                    sb.append(id).append("\t").append(name).append("\t").append(quantity).append("\t  ").append("Normal Level").append("\n");
                }
                productTextArea.setText(sb.toString());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        

        setLayout(null);
        setTitle("Product List");
        setSize(1000, 1000);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
            dispose();
        }
    });
    }
}
    
class billprint extends Frame {
            TextField textField;
            Button submitButton;
            TextArea textArea;
            Label l;
        
            public billprint() {
                
                textField = new TextField(20);
                submitButton = new Button("Submit");
                textArea = new TextArea(10, 40);
                l=new Label("Bill no.");
                Font labelFont = new Font("Arial", Font.PLAIN, 24);
               
                l.setBounds(100, 100, 100, 20); 
                textField.setBounds(200, 100, 200, 30);
                submitButton.setBounds(420, 90, 100, 50);
                textArea.setBounds(50, 150, 700, 500);
                textArea.setFont(labelFont);
                l.setFont(labelFont);
                add(textField);
                add(submitButton);
                add(textArea);
                add(l);
        
                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        textArea.setText("");
                        String str= Bill.getBillFromDB(Integer.parseInt(textField.getText()));
                        textArea.setText(str);
                    }
                });
        
                setLayout(null);
                setTitle("Bill Print");
                setSize(1000, 1000);
                setVisible(true);
        
      
                addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        dispose();
                    }
                });
    }
}


 class newbill extends Frame {
    TextField productField, quantityField, textField,totField,datefeild;
    TextArea invoiceArea;
    Button addButton, continueButton, clearButton,changedateButton;

    public newbill() {
       
        Font labelFont = new Font("Arial", Font.PLAIN, 24);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 24);
        Font buttonFont = new Font("Arial", Font.PLAIN, 24);
        Font textAreaFont = new Font("Arial", Font.PLAIN, 24);

      
        productField = new TextField();
        productField.setBounds(250, 60, 300, 40);
        productField.setFont(textFieldFont);

        datefeild = new TextField(App.datetemp);
        datefeild.setBounds(720, 100, 200, 40);
        datefeild.setFont(textFieldFont);

        quantityField = new TextField();
        quantityField.setBounds(250, 100, 300, 40);
        quantityField.setFont(textFieldFont);

        textField = new TextField("ProductId  Quantity");
        textField.setBounds(50, 150, 500, 40);
        textField.setFont(textFieldFont);

        invoiceArea = new TextArea();
        invoiceArea.setBounds(50, 200, 500, 400);
        invoiceArea.setFont(textAreaFont);

        totField = new TextField("0");
        totField.setBounds(50, 750, 300, 40);
        totField.setFont(textFieldFont);

        TextField paidField = new TextField("0");
        paidField.setBounds(50, 850, 300, 40);
        paidField.setFont(textFieldFont);

        addButton = new Button("Add");
        addButton.setBounds(600, 200, 200, 50);
        addButton.setFont(buttonFont);

        clearButton = new Button("Clear Last Item");
        clearButton.setBounds(600, 270, 200, 50);
        clearButton.setFont(buttonFont);

        continueButton = new Button("Continue to Bill");
        continueButton.setBounds(600, 700, 250, 80);
        continueButton.setFont(buttonFont);

        
        changedateButton = new Button("Change Date");
        changedateButton.setBounds(600, 340, 200, 50);
        changedateButton.setFont(buttonFont);

        Label member = new Label("Use Points(Y/N):");
        member.setBounds(600, 400, 200, 50);
        member.setFont(buttonFont);

        TextField memberField = new TextField("Y");
        memberField.setBounds(600, 470, 300, 40);
        memberField.setFont(textFieldFont);
       
        Label productLabel = new Label("Product Id:");
        productLabel.setBounds(50, 60, 200, 40);
        productLabel.setFont(labelFont);

        Label dateLabel = new Label("Date:");
        dateLabel.setBounds(600, 100, 100, 40);
        dateLabel.setFont(labelFont);

        Label quantityLabel = new Label("Quantity:");
        quantityLabel.setBounds(50, 100, 200, 40);
        quantityLabel.setFont(labelFont);

        Label totLabel = new Label("TOTAL VALUE:");
        totLabel.setBounds(50, 700, 200, 40);
        totLabel.setFont(labelFont);

        
        Label paidLabel = new Label("PAID AMOUNT:");
        paidLabel.setBounds(50, 800, 200, 40);
        paidLabel.setFont(labelFont);
    
        add(productLabel);
        add(productField);
        add(quantityLabel);
        add(quantityField);
        add(textField);
        add(invoiceArea);
        add(addButton);
        add(continueButton);
        add(clearButton);
        add(totField);
        add(totLabel);
        add(dateLabel);
        add(changedateButton);
        add(datefeild);
        add(paidLabel);
        add(paidField);
        add(member);
        add(memberField);

        

      
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int total=Integer.parseInt(totField.getText());
                String product = productField.getText();
                String quantity = quantityField.getText();
                int numQuantity = Integer.valueOf(quantity);
                int avaQty = Integer.valueOf(Product.getProductDetailById(Integer.valueOf(product), "availablequantity"));
                total=total+(Integer.valueOf(Product.getProductDetailById(Integer.valueOf(product), "price"))*numQuantity);
                if (avaQty >= numQuantity) {
                    Product.tempupdateProductqty(Integer.valueOf(product), -numQuantity);
                    invoiceArea.append(product + "              " + quantity + "\n");
                    productField.setText("");
                    quantityField.setText("");
                    totField.setText(String.valueOf(total));
                } else {
                    new popuperror("Insufficient Quantity!!!");
                    productField.setText("");
                    quantityField.setText("");
                }
            }
        });

 
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(Integer.valueOf(paidField.getText()));
                new printfunction(invoiceArea,Integer.valueOf(paidField.getText()),memberField.getText());
                
            }
        });

        changedateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               App.datetemp= datefeild.getText();
               new popupsuccess("Date Changed Successfully!!!");
            }
        });
        

      
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = invoiceArea.getText();
                String[] words = text.trim().split("\\s+");
                int lastId = Integer.parseInt(words[words.length - 2]);
                int lastQty = Integer.parseInt(words[words.length - 1]);
                int total=Integer.parseInt(totField.getText());

                total=total-(Integer.valueOf(Product.getProductDetailById(Integer.valueOf(lastId), "price"))*lastQty);
                totField.setText(String.valueOf(total));
     
                Product.tempupdateProductqty(lastId, lastQty);

                if (words.length > 2) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < words.length - 2; i+=2) {
                        sb.append(words[i]).append("              ").append(words[i+1]).append("\n");
                    }
                    invoiceArea.setText(sb.toString().trim()); // Set the updated text
                } else {
                    invoiceArea.setText(""); // If text has 2 or fewer words, clear it
                }
            }
        });

        setLayout(null);
        setSize(1000, 1000);
        setTitle("Employee - Invoice Printing");
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}

class printfunction extends Frame {
    TextField tf1,tf21,tf22,tf23;
    Button b1,b2;
    public static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    public printfunction(TextArea tf3,int paid,String member){
        
        tf1=new TextField();
        tf1.setBounds(200,150,200,50);
        add(new Label("Existing Customer(Enter id):")).setBounds(200, 50, 150, 30);
        add(new Label("New Customer:")).setBounds(500, 50, 150, 30);
        tf21=new TextField();
        tf21.setBounds(500,150,200,50);
        add(new Label("Name:")).setBounds(400, 170, 100, 30);
        tf22=new TextField();
        tf22.setBounds(500,200,200,50);
        add(new Label("Phone number:")).setBounds(400, 220, 100, 30);
        tf23=new TextField();
        tf23.setBounds(500,250,200,50);
        add(new Label("Membership(Y/N):")).setBounds(400, 270, 100, 30);
        
        b1=new Button("Submit existing");
        b1.setBounds(250, 220, 100,30);
        b2=new Button("Submit new");
        b2.setBounds(550, 320, 100,30);
        add(tf1);
        add(tf21);
        add(tf22);
        add(tf23);
        add(b1);
        add(b2);
        
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
              int id=Integer.parseInt(tf1.getText());
                Bill b=new Bill(id, App.datetemp);
                String[] str5=(tf3.getText()).split("\\s+");
                for(int i=0;i<str5.length;i+=2){
                    b.productidQuantity.put(Integer.valueOf(str5[i]),Integer.valueOf(str5[i+1]));
                }

                Customer c=Customer.getCustomerDetails(id);
                String name =c.name;
                String pno=c.phoneNo;
                tf1.setText("");
                new finalprint(name,pno,b,c,paid,member);  
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String name=tf21.getText();
                String pno=tf22.getText();
                String membershipstatus=tf23.getText();
            if(pno.length()==10&&isNumeric(pno)&&(membershipstatus.equals("N")||membershipstatus.equals("Y"))){
                int lastid= Customer.insertCustomer(name,pno,membershipstatus);
                Customer c=Customer.getCustomerDetails(lastid);

                Bill b=new Bill(lastid, App.datetemp);
                String[] str5=(tf3.getText()).split("\\s+");
                for(int i=0;i<str5.length;i+=2){
                   
                    b.productidQuantity.put(Integer.valueOf(str5[i]),Integer.valueOf(str5[i+1]));
                   
                }
                tf21.setText("");
                tf22.setText("");
                tf23.setText("");
             
                new finalprint(name,pno,b,c,paid,member);
            }
            else
            {
                new popuperror("Enter valid phone number / membership choice!!!");
                tf21.setText("");
                tf22.setText("");
                tf23.setText("");

            }
            }
        });
        setLayout(null);
        setSize(1000, 1000);
        setTitle("Select Customer");
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

    }
}




class finalprint extends Frame {
    public finalprint(String name,String pno,Bill b,Customer c,int paid,String member){
        File file=new File(App.datetemp+"_"+name);
        FileWriter fw;
        try {
            fw = new FileWriter(file,true);
            fw.write("GENERAL STORES\n");
            fw.write("Customer name :"+name+"     Customer phone:"+pno+"\n");
            fw.write("Item     Quantity     Amount"+"\n");
            LinkedHashMap<Integer , Integer> pq=b.productidQuantity;
              for(Map.Entry<Integer,Integer> e:pq.entrySet()){
                Integer pp=e.getKey();
                int qty=e.getValue();
               
            int sum=Integer.valueOf(Product.getProductDetailById(pp,"price"))*qty;
            double tax=sum*(Double.valueOf(Product.getProductDetailById(pp,"tax"))/100.0);
            fw.write(Product.getProductDetailById(pp,"name")+"              "+qty+"              "+sum+"\n");
            b.totalPrice=b.totalPrice+sum;
            b.totalTax=b.totalTax+tax;
        }
        System.out.println("Total price "+b.totalPrice);
        if(b.totalPrice>1000&&b.totalPrice<3000)
        b.disamt=(int)(b.totalPrice*0.05);
        else if(b.totalPrice>3000)
        b.disamt=(int)(b.totalPrice*0.1);
        fw.write("\n");
        fw.write("TOTAL AMOUNT WITHOUT TAX:"+String.valueOf(b.totalPrice)+"\n");
       
      // if(member.equals("Y"))
        b.rewardsdiscount=c.points;

        b.netamount=b.totalPrice-(int)b.disamt-(int)b.rewardsdiscount;
        int lastbillno= Bill.addBill(b,member);

        b.totalPrice=b.totalPrice-(int)b.totalTax;

        int remamt=b.netamount-paid;
        Customer.customerbalance(c.id,"add", remamt);
        
        fw.write("TOTAL TAX AMOUNT:"+String.valueOf(b.totalTax)+"\n");
        fw.write("TOTAL SAVINGS:"+String.valueOf(b.disamt)+"\n");
        fw.write("TOTAL SAVINGS BY REWARD POINTS:"+String.valueOf(b.rewardsdiscount)+"\n");
        fw.write("NET AMOUNT PAYABLE:"+String.valueOf(b.netamount+"\n"));
        fw.write("TOTAL AMOUNT PAID:"+String.valueOf(paid+"\n"));
        fw.write(Customer.customerbalance(c.id,"get",0));
        fw.close();

        } catch (IOException e1) {
            
            e1.printStackTrace();
        }
        
    Label l=new Label("Bill saved in file Sucessfully!!!");
    add(l);
    setLayout(new FlowLayout());
    setSize(1000, 1000);
    setTitle("Final Message");
    setVisible(true);

    addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
            dispose();
        }
    });
    }
}



