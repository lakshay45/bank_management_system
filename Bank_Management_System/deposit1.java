package BankingSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.time.*;

//deposit 1 extending deposit2 ,therefore inheriting its members
public class deposit1 extends deposit2{
    
    //declaring text field and buttons
    JTextField t2;
    JButton b2,b3;
    JLabel l3;
    String Account_number;
    
    //Constructor taking account number after signing from login
    deposit1(String Account_number){
	
	//sending empty string to its parent class since it requires account number and pin
	super("","");        

	this.Account_number=Account_number;

        l3 = new JLabel("Enter Pin");
        l3.setFont(new Font("System", Font.BOLD, 14));
        
        t2 = new JTextField();
        t2.setFont(new Font("Raleway", Font.BOLD, 14));
        
    
        b2 = new JButton("BACK");
        b2.setFont(new Font("System", Font.BOLD, 18));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        
        b3 = new JButton("LOG OUT");
        b3.setFont(new Font("System", Font.BOLD, 18));
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        
        
        setLayout(null);
        
        l3.setBounds(620,10,80,30);
        add(l3);
        
        t2.setBounds(700,10,40,30);
        add(t2);
        
        b2.setBounds(100,450,125,50);
        add(b2);
        
        b3.setBounds(555,458,135,50);
        add(b3);
        
        b2.addActionListener(this);
        b3.addActionListener(this);
        
    }
    
    public void actionPerformed(ActionEvent ae){
       
        try{        
           
            String a = t1.getText();		//t1 has been inherited from the super class deposit2
            String b = t2.getText();
            
            
            
            
            if(ae.getSource()==b1){
                if(a.equals("")||a.equals("0")){
                    
                    JOptionPane.showMessageDialog(null, "Please enter the Amount to you want to Deposit");
                
                }else if(b.equals(""))
		{

			JOptionPane.showMessageDialog(null, "Please enter the pin at top right corner of the page");
                			
		}else{
                    
                    jdbcConn c1 = new jdbcConn();
		    double amount=0,balance=0;                    
		    
		    ResultSet rs = c1.s.executeQuery("SELECT * FROM TransactionDetails \nhaving pin = '"+b+"' and Account_no='"+Account_number+"'\norder by date_time DESC");
                    if(rs.next())
		    {
			
			balance=rs.getDouble("balance");

			amount=Double.parseDouble(a);
	
			balance+=amount;
			
			LocalDateTime now = LocalDateTime.now();  
			String Date=now.toString();
			
			//inserting data into TrsactionDetails
			String q1= "insert into TransactionDetails values('"+Account_number+"','"+b+"','"+a+"','0','"+balance+"','"+Date+"')";
              		c1.s.executeUpdate(q1);
                   
                    	JOptionPane.showMessageDialog(null, "Rs. "+a+" Deposited Successfully");
                    
                    	new Transactions(Account_number).setVisible(true);
                    	setVisible(false);
		    }
                    
		    else
		    {

			JOptionPane.showMessageDialog(null, "Incorrect pin");
                	
		    }
                 }
                
            }else if(ae.getSource()==b2){
                
                new Transactions(Account_number).setVisible(true);
                setVisible(false);
                
            }else if(ae.getSource()==b3){
                
		new login().setVisible(true);
		setVisible(false);
                
            }
        }catch(Exception e){
                e.printStackTrace();
                System.out.println("error: "+e);
        }
    }
}