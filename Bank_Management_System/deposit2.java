package BankingSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.time.*;


public class deposit2 extends JFrame implements ActionListener{
 
	JTextField t1;
    	JButton b1;
    	JLabel l1,l2;
    
	String Account_number,pin;	

	deposit2(String Account_number,String pin)
	{
		this.Account_number=Account_number;
		this.pin=pin;

        l1 = new JLabel("ENTER AMOUNT YOU WANT");
        l1.setFont(new Font("System", Font.BOLD, 35));
        
        l2 = new JLabel("TO DEPOSIT");
        l2.setFont(new Font("System", Font.BOLD, 35));
        
        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 22));
        
        
        b1 = new JButton("DEPOSIT");
        b1.setFont(new Font("System", Font.BOLD, 18));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
    
        
        setLayout(null);
        
        
        l1.setBounds(150,150,800,60);
        add(l1);
        
        l2.setBounds(290,210,800,60);
        add(l2);
        
        t1.setBounds(250,300,300,50);
        add(t1);
        
        b1.setBounds(330,380,125,50);
        add(b1);
        
        b1.addActionListener(this);
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(800,800);
        setLocation(350,40);
	setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
       
       try{        
           
         	String a = t1.getText();
            
            
           	 if(ae.getSource()==b1){
           	     if(a.equals("")){
               	     
               	     		JOptionPane.showMessageDialog(null, "Please enter the Amount to you want to Deposit");
               	 
               	 	}else{
				double amount=Double.parseDouble(a);
            
				if(amount<3000){
			
					JOptionPane.showMessageDialog(null, "To start your account minimum amount to be deposited should be 3000");
               	 
				}else
                    		{
					// making connection to jdbc using jdbcConn object
               			     	     jdbcConn c1 = new jdbcConn();

					     LocalDateTime now = LocalDateTime.now();  
					     String Date=now.toString();                 

                   			     String q1= "insert into TransactionDetails values('"+Account_number+"','"+pin+"','"+a+"','0','"+a+"','"+Date+"')";
              				     c1.s.executeUpdate(q1);
                    
						//showing message on screen after depositing money
                  		 	     JOptionPane.showMessageDialog(null, "Rs. "+a+" Deposited Successfully");
               		 
        	        	   	     JOptionPane.showMessageDialog(null, "Your Account Number: "+Account_number+"\nPin :"+pin);
   	
					     new login().setVisible(true);
   		                 	     setVisible(false);
		   }
             }
                
        }
        }catch(Exception e){
                e.printStackTrace();
                System.out.println("error: "+e);
        }
            
    }
}