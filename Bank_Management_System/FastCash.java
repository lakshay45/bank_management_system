package BankingSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.time.*;

public class FastCash extends JFrame implements ActionListener{

    JLabel l1,l2;
    JButton b1,b2,b3,b4,b5,b6,b7;
    JTextField t1;
    String Account_number;

    FastCash(String Account_number){
    
	this.Account_number=Account_number;
        
        l1 = new JLabel("SELECT WITHDRAWL AMOUNT");
        l1.setFont(new Font("System", Font.BOLD, 38));
        
        l2 = new JLabel("Enter PIN");
        l2.setFont(new Font("System", Font.BOLD, 13));
        
        t1 = new JTextField();
        t1.setFont(new Font("System", Font.BOLD, 13));
    
       
        b1 = new JButton("100");
        b1.setFont(new Font("System", Font.BOLD, 18));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
    
        b2 = new JButton("500");
        b2.setFont(new Font("System", Font.BOLD, 18));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        
        b3 = new JButton("1000");
        b3.setFont(new Font("System", Font.BOLD, 18));
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        
        b4 = new JButton("2000");
        b4.setFont(new Font("System", Font.BOLD, 18));
        b4.setBackground(Color.BLACK);
        b4.setForeground(Color.WHITE);
        
        b5 = new JButton("5000");
        b5.setFont(new Font("System", Font.BOLD, 18));
        b5.setBackground(Color.BLACK);
        b5.setForeground(Color.WHITE);
        
        b6 = new JButton("10000");
        b6.setFont(new Font("System", Font.BOLD, 18));
        b6.setBackground(Color.BLACK);
        b6.setForeground(Color.WHITE);
        
        b7 = new JButton("LOG OUT");
        b7.setFont(new Font("System", Font.BOLD, 18));
        b7.setBackground(Color.BLACK);
        b7.setForeground(Color.WHITE);
        
        
        setLayout(null);
        
        l2.setBounds(640,10,60,40);
        add(l2);
        
        t1.setBounds(710,10,60,40);
        add(t1);
        
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
	setUndecorated(true);
        setLocation(350,40);
        setVisible(true);
        
        
        
    }
    
    public void actionPerformed(ActionEvent ae){
       
            try{        
           
            String a = t1.getText();
            double balance = 0;
	    double amount=0;            
	    String b=null,pin=null;
            int flag=1;

	    if(a.equals(""))
	    {
		JOptionPane.showMessageDialog(null, "Enter pin at top right corner of the page");
	    }
	    else
	    {       
            	jdbcConn c1 = new jdbcConn();
            	ResultSet rs = c1.s.executeQuery(" select * from TransactionDetails having pin = '"+a+"' and Account_no='"+Account_number+"' order by date_time DESC");
            
            	if(rs.next()){
                        pin = rs.getString("pin");
                        balance = rs.getDouble("balance");
                      
	    		if(ae.getSource()==b1)
	    		{
             			b=b1.getText();   
	    		}
	    		else if(ae.getSource()==b2)
	    		{
               			b=b2.getText();   
	    		}
	    		else if(ae.getSource()==b3)
	    		{
               			b=b3.getText();   
	    		}
	    		else if(ae.getSource()==b4)
	    		{
               			b=b4.getText();   
	    		}
	    		else if(ae.getSource()==b5)
	    		{
               			b=b5.getText();   
	    		}
	    		else if(ae.getSource()==b6)
	    		{
               			b=b6.getText();   
	    		}
            		else if(ae.getSource()==b7){
                		flag=0;
				new login().setVisible(true);
				setVisible(false);
                	}
			if(flag==1)
			{
				amount=Double.parseDouble(b);
				LocalDateTime now = LocalDateTime.now();
				String Date=now.toString();
			
				if(balance<amount)
				{	JOptionPane.showMessageDialog(null, "Insufficient balance");	}
				else
				{
                	       		balance-=amount;
                	       		String q1= "insert into TransactionDetails values('"+Account_number+"','"+pin+"','0','"+amount+"','"+balance+"','"+Date+"')";
                	       		c1.s.executeUpdate(q1);
                		}
                    
                		JOptionPane.showMessageDialog(null, "Rs. "+amount+" Debited Successfully");
                    
				//showing Transaction page
                		new Transactions(Account_number).setVisible(true);

				//closing fastCash page
                		setVisible(false);
			}
		}
	    	else
	    	{
			JOptionPane.showMessageDialog(null, "Incorrect pin");
	    	}
	    }
            
        }catch(Exception e){
                e.printStackTrace();
                System.out.println("error: "+e);
        }
            
    }
}