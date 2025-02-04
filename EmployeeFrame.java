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
        setTitle("Stock Status");
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
    Button addButton, continueButton, clearButton,changedateButton,inventoryButton;

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

        Label ll = new Label("ProductId  Quantity");
        ll.setBounds(50, 150, 500, 40);
        ll.setFont(textFieldFont);

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

        inventoryButton = new Button("Stock Availability");
        inventoryButton.setBounds(600, 410, 200, 50);
        inventoryButton.setFont(buttonFont);

        Label member = new Label("Use Points(Y/N):");
        member.setBounds(600, 460, 200, 50);
        member.setFont(buttonFont);

        TextField memberField = new TextField("Y");
        memberField.setBounds(600, 520, 300, 40);
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

        Label msgLabel = new Label("Type *paid* if full amount is paid.");
        msgLabel.setBounds(50, 900, 400, 40);
        msgLabel.setFont(new Font("Arial", Font.PLAIN, 18));
    
        add(productLabel);
        add(productField);
        add(quantityLabel);
        add(quantityField);
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
        add(msgLabel);
        add(ll);
        add(inventoryButton);
        

      
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
                new printfunction(invoiceArea,paidField.getText(),memberField.getText());
                
            }
        });

        changedateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               App.datetemp= datefeild.getText();
               new popupsuccess("Date Changed Successfully!!!");
            }
        });

       inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new stocklist();
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
                    invoiceArea.setText(""); // If text has 2 or lesser words, clear it
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
    Label l,l1,l2,l3,l4,l5;
    public static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    public printfunction(TextArea tf3,String paid,String member){

        Font labelFont = new Font("Arial", Font.PLAIN, 22);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 24);
        Font buttonFont = new Font("Arial", Font.PLAIN, 24);
        
        tf1=new TextField();
        tf1.setBounds(200,150,220,50);
        tf1.setFont(textFieldFont);
        l1= new Label("EXISTING CUSTOMER");
        l1.setBounds(70, 80, 250, 30);
        add(l1);

        l= new Label("Enter Id:");
        l.setBounds(70, 160, 100, 30);
        add(l);
        l.setFont(labelFont);
        
        l1.setFont(labelFont);
        l2=new Label("NEW CUSTOMER:");
        l2.setBounds(460, 80, 250, 30);
        add(l2);
        l2.setFont(labelFont);
        tf21=new TextField();
        tf21.setBounds(630,150,200,50);
        tf21.setFont(textFieldFont);
        l3=new Label("Name:");
        l3.setBounds(460, 170, 100, 30);
        add(l3);
        l3.setFont(labelFont);
        tf22=new TextField();
        tf22.setBounds(630,200,200,50);
        tf22.setFont(textFieldFont);
        l4=new Label("Phone number:");
        l4.setBounds(460, 220, 150, 30);
        add(l4);
        l4.setFont(labelFont);
        tf23=new TextField();
        tf23.setBounds(630,250,200,50);
        tf23.setFont(textFieldFont);
        l5=new Label("Membership(Y/N):");
        l5.setBounds(460, 270, 180, 30);
        add(l5);
        l5.setFont(labelFont);
        
        b1=new Button("Submit existing");
        b1.setBounds(220, 220, 180,50);
        b1.setFont(buttonFont);
        b2=new Button("Submit new");
        b2.setBounds(600, 320, 170,50);
        b2.setFont(buttonFont);
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
    public finalprint(String name,String pno,Bill b,Customer c,String paid,String member){
        File file=new File(App.datetemp+"_"+name+"_"+b.billNo);
        FileWriter fw;
        try {
            fw = new FileWriter(file,true);
            fw.write("****************   GENERAL STORES   *****************");
            fw.write("\n");
            fw.write("Customer name :"+name+"  |   Customer phone:"+pno+"\n");
            fw.write("------------------------------------------------------");
            fw.write("\n");
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
       
        if(b.totalPrice>1000&&b.totalPrice<3000)
        b.disamt=(int)(b.totalPrice*0.05);
        else if(b.totalPrice>3000)
        b.disamt=(int)(b.totalPrice*0.1);
       
        if(member.equals("Y"))
        b.rewardsdiscount=c.points;
        else
        b.rewardsdiscount=0;


        b.netamount=b.totalPrice-(int)b.disamt-(int)b.rewardsdiscount;
        b.totalPrice=b.totalPrice-(int)b.totalTax;
        fw.write("\n");
        fw.write("TOTAL AMOUNT WITHOUT TAX:"+String.valueOf(b.totalPrice)+"\n");
        int lastbillno= Bill.addBill(b,member);
        
        if(!paid.equalsIgnoreCase("paid")){
        int remamt=b.netamount-Integer.valueOf(paid);
        Customer.customerbalance(c.id,"add", remamt);
        }
        
        fw.write("TOTAL TAX AMOUNT:"+String.valueOf(b.totalTax)+"\n");
        fw.write("TOTAL SAVINGS:"+String.valueOf(b.disamt)+"\n");
        fw.write("TOTAL SAVINGS BY REWARD POINTS:"+String.valueOf(b.rewardsdiscount)+"\n");
        fw.write("NET AMOUNT PAYABLE:"+String.valueOf(b.netamount+"\n"));
        fw.write("\n");
        if(paid.equalsIgnoreCase("paid"))
        fw.write("TOTAL AMOUNT PAID:"+String.valueOf(b.netamount+"\n"));
        else
        fw.write("TOTAL AMOUNT PAID:"+String.valueOf(paid+"\n"));
        fw.write("Total"+Customer.customerbalance(c.id,"get",0));
        fw.write("\n");
        fw.write("***Thank You for purchasing.Visit Again***");
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



