package BankingSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Transactions extends JFrame implements ActionListener{

    JLabel l1;
    JButton b1,b2,b3,b4,b5,b6,b7;
    String Account_number;
    Transactions(String Account_number){
    	
	this.Account_number=Account_number;

        l1 = new JLabel("Please Select Your Transaction");
        l1.setFont(new Font("System", Font.BOLD, 38));
    
       
        b1 = new JButton("DEPOSIT");
        b1.setFont(new Font("System", Font.BOLD, 18));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
    
        b2 = new JButton("CASH WITHDRAWL");
        b2.setFont(new Font("System", Font.BOLD, 18));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        
        b3 = new JButton("FAST CASH");
        b3.setFont(new Font("System", Font.BOLD, 18));
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        
        b4 = new JButton("MINI STATEMENT");
        b4.setFont(new Font("System", Font.BOLD, 18));
        b4.setBackground(Color.BLACK);
        b4.setForeground(Color.WHITE);
        
        b5 = new JButton("PIN CHANGE");
        b5.setFont(new Font("System", Font.BOLD, 18));
        b5.setBackground(Color.BLACK);
        b5.setForeground(Color.WHITE);
        
        b6 = new JButton("BALANCE ENQUIRY");
        b6.setFont(new Font("System", Font.BOLD, 18));
        b6.setBackground(Color.BLACK);
        b6.setForeground(Color.WHITE);
        
        b7 = new JButton("LOG OUT");
        b7.setFont(new Font("System", Font.BOLD, 18));
        b7.setBackground(Color.BLACK);
        b7.setForeground(Color.WHITE);
        
        
        setLayout(null);
        
        l1.setBounds(100,100,700,40);
        add(l1);
        
        b1.setBounds(40,250,300,60);
        add(b1);
        
        b2.setBounds(440,250,300,60);
        add(b2);
        
        b3.setBounds(40,360,300,60);
        add(b3);
        
        b4.setBounds(440,360,300,60);
        add(b4);
        
        b5.setBounds(40,470,300,60);
        add(b5);
        
        b6.setBounds(440,470,300,60);
        add(b6);
        
        b7.setBounds(240,600,300,60);
        add(b7);
        
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(800,800);
        setLocation(350,40);
	setUndecorated(true);
        setVisible(true);
        
        
        
    }
    
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource()==b1){ 
            
            new deposit1(Account_number).setVisible(true);
            setVisible(false);
            
        }
        else if(ae.getSource()==b2){ 
            
            new Withdrawl(Account_number).setVisible(true);
            setVisible(false);
            
        }
        else if(ae.getSource()==b3){ 
            
            new FastCash(Account_number).setVisible(true);
            setVisible(false);
            
        }else if(ae.getSource()==b4){ 

		jdbcConn c1 = new jdbcConn();
	    	String pin=null;
	        try {
                	ResultSet rs = c1.s.executeQuery(" SELECT * FROM login where Account_no ='"+Account_number+"'");
			rs.next();
			pin=rs.getString("pin");
                }catch (Exception e) 
		{
                	e.printStackTrace();
			System.out.println("Error "+e);
           	}
            
           new MiniStatement(Account_number,pin).setVisible(true);
           setVisible(false);
            
        }else if(ae.getSource()==b5){ 
            
            String pinn = JOptionPane.showInputDialog("Enter PIN"); 
            jdbcConn c1 = new jdbcConn();
	    String pin=null;
	        try {
                	ResultSet rs = c1.s.executeQuery(" SELECT * FROM login where Account_no ='"+Account_number+"'");
			rs.next();
			pin=rs.getString("pin");
                }catch (Exception e) 
		{
                	e.printStackTrace();
			System.out.println("Error "+e);
           	}
	    if(pinn.equals(pin))
	    {
            	new Pin(Account_number,pin).setVisible(true);
            	setVisible(false);
	    }
	    else
	    {
		
                JOptionPane.showMessageDialog(null,"Incorrect pin");
	    }
            
        }else if(ae.getSource()==b6){ 

            String pinn = JOptionPane.showInputDialog("Enter PIN"); 
            jdbcConn c1 = new jdbcConn();
	    String pin=null;
	        try {
                	ResultSet rs = c1.s.executeQuery(" SELECT * FROM login where Account_no ='"+Account_number+"'");
			rs.next();
			pin=rs.getString("pin");
			if(pinn.equals(pin))
	    		{
                		rs = c1.s.executeQuery(" SELECT * FROM TransactionDetails having pin  = '"+pin+"' and Account_no ='"+Account_number+"' order by date_time DESC");
                		rs.next();
                
                		String balance = rs.getString("balance");
                
                		JOptionPane.showMessageDialog(null,"Your Account Balance is "+balance);
             
	    		}
	    		else
	    		{
				JOptionPane.showMessageDialog(null,"Incorrect pin");
	    		}	
             	}catch (Exception e) 
		{
                	e.printStackTrace();
			System.out.println("Error "+e);
           	}
	   
        
        }else if(ae.getSource()==b7){ 
                        
		new login().setVisible(true);
		setVisible(false);

        }
        
    }
}