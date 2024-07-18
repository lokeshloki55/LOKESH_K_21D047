import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class App  extends Frame{

     Map<String,List<Bill>> dateBill ;
     static Map<Integer,Customer>clist=new HashMap<>();
     static Map<String,Product>plist=new HashMap<>();
     Button adminButton, employeeButton;
     static LocalDate currentDate = LocalDate.now();
     static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
     static String datetemp = currentDate.format(formatter);
    
     public App() {
        Font buttonFont = new Font("Arial", Font.PLAIN, 32);

        Button adminButton = new Button("Administrator");
        adminButton.setBounds(400, 350, 220, 100); 
        adminButton.setFont(buttonFont); 

        Button employeeButton = new Button("Employee");
        employeeButton.setBounds(400, 475, 220, 100); 
        employeeButton.setFont(buttonFont); 
         add(adminButton);
         add(employeeButton);
 
         adminButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 new checkAdminFrame();
             }
         });
 
         employeeButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 new checkEmployeeFrame();
             }
         });
 
         setLayout(null);
         setSize(1000, 1000);
         setTitle("Supermarket Application");
         setVisible(true);
         addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    class checkAdminFrame extends Frame{
        TextField tf1,tf2;
         Button b1;
         checkAdminFrame(){
            
            Font buttonFont = new Font("Arial", Font.PLAIN, 32);
            Font labelFont = new Font("Arial", Font.PLAIN, 24);
            Font textFieldFont = new Font("Arial", Font.PLAIN, 24);
    
            int frameWidth = 1000;
            int buttonWidth = 220;
            int buttonHeight = 100;
            int textFieldWidth = 300;
            int textFieldHeight = 50;
            int labelWidth = 200;
            int labelHeight = 50;
            
            int centerX = frameWidth / 2;

            Label userLabel = new Label("Enter User name:");
            userLabel.setBounds(centerX - (labelWidth + textFieldWidth) / 2, 100, labelWidth, labelHeight);
            userLabel.setFont(labelFont); 
    
            Label passwordLabel = new Label("Enter Password:");
            passwordLabel.setBounds(centerX - (labelWidth + textFieldWidth) / 2, 200, labelWidth, labelHeight);
            passwordLabel.setFont(labelFont); 
    
            TextField tf1 = new TextField();
            tf1.setBounds(centerX - (labelWidth + textFieldWidth) / 2 + labelWidth, 100, textFieldWidth, textFieldHeight);
            tf1.setFont(textFieldFont); 
    
            TextField tf2 = new TextField();
            tf2.setBounds(centerX - (labelWidth + textFieldWidth) / 2 + labelWidth, 200, textFieldWidth, textFieldHeight);
            tf2.setFont(textFieldFont); 
    
            Button b1 = new Button("Submit");
            b1.setBounds(centerX - buttonWidth / 2, 300, buttonWidth, buttonHeight);
            b1.setFont(buttonFont);  
            add(tf1);
            add(tf2);
            add(b1);
            add(userLabel);
             add(passwordLabel);
            
             
             b1.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                    if(tf2.getText().equals("123")&&tf1.getText().equals("admin")){
                    new AdminFrame();
                    
                }
                else
                  new popuperror();
                   tf1.setText("");
                   tf2.setText("");
                  
                 }
             });
             setLayout(null);
             setSize(1000, 1000);
             setTitle("Security Check Admin");
             setVisible(true);
     
             addWindowListener(new WindowAdapter() {
                 public void windowClosing(WindowEvent e) {
                     dispose();
                 }
             });
    
         }
    
    
     }  

     class checkEmployeeFrame extends Frame{
        TextField tf1,tf2;
         Button b1;
         checkEmployeeFrame(){
            
            Font buttonFont = new Font("Arial", Font.PLAIN, 32);
            Font labelFont = new Font("Arial", Font.PLAIN, 24);
            Font textFieldFont = new Font("Arial", Font.PLAIN, 24);
    
            int frameWidth = 1000;
            int buttonWidth = 220;
            int buttonHeight = 100;
            int textFieldWidth = 300;
            int textFieldHeight = 50;
            int labelWidth = 200;
            int labelHeight = 50;
            
            int centerX = frameWidth / 2;
    
            Label userLabel = new Label("Enter User name:");
            userLabel.setBounds(centerX - (labelWidth + textFieldWidth) / 2, 100, labelWidth, labelHeight);
            userLabel.setFont(labelFont); 
    
            Label passwordLabel = new Label("Enter Password:");
            passwordLabel.setBounds(centerX - (labelWidth + textFieldWidth) / 2, 200, labelWidth, labelHeight);
            passwordLabel.setFont(labelFont); 
    
            TextField tf1 = new TextField();
            tf1.setBounds(centerX - (labelWidth + textFieldWidth) / 2 + labelWidth, 100, textFieldWidth, textFieldHeight);
            tf1.setFont(textFieldFont); 
    
            TextField tf2 = new TextField();
            tf2.setBounds(centerX - (labelWidth + textFieldWidth) / 2 + labelWidth, 200, textFieldWidth, textFieldHeight);
            tf2.setFont(textFieldFont); 
    
            Button b1 = new Button("Submit");
            b1.setBounds(centerX - buttonWidth / 2, 300, buttonWidth, buttonHeight);
            b1.setFont(buttonFont);  
            add(tf1);
            add(tf2);
            add(b1);
            add(userLabel);
            add(passwordLabel);
            
             
             b1.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                    if(tf2.getText().equals("321")&&tf1.getText().equalsIgnoreCase("employee1")){
                        new EmployeeFrame();
                        
                    }
                    else
                      new popuperror();
                       tf1.setText("");
                       tf2.setText("");
                   
                 }
             });
             setLayout(null);
             setSize(1000, 1000);
             setTitle("Security check Employee");
             setVisible(true);
     
             addWindowListener(new WindowAdapter() {
                 public void windowClosing(WindowEvent e) {
                     dispose();
                 }
             });
    
         }
    
    
     }  
     class popuperror extends Frame{
        popuperror(){
            
            Label warningLabel = new Label("Enter valid Username and password!!!");
        
            Font labelFont = new Font("Arial", Font.PLAIN, 24);
            warningLabel.setFont(labelFont);
        
            warningLabel.setBounds(50, 100, 400, 50);
            add(warningLabel);

            setLayout(null);
             setSize(550, 200);
             setTitle("Security check Employee");
             setVisible(true);
     
             addWindowListener(new WindowAdapter() {
                 public void windowClosing(WindowEvent e) {
                     dispose();
                 }
             });
        }
     }

    public static void main(String[] args) {
        new App();
    }
}



