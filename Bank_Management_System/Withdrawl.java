
package BankingSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.time.*;

public class Withdrawl extends JFrame implements ActionListener{
    
    JTextField t1,t2;
    JButton b1,b2,b3;
    JLabel l1,l2,l3,l4;
    String Account_number;

    Withdrawl(String Account_number){
        
	this.Account_number=Account_number;
        
        l1 = new JLabel("WELCOME  TO  OUR");
        l1.setFont(new Font("System", Font.BOLD, 40));
        
        l2 = new JLabel("WITHDRAWL SECTION");
        l2.setFont(new Font("System", Font.BOLD, 40));
        
        l3 = new JLabel("PLEASE ENTER YOUR AMOUNT");
        l3.setFont(new Font("System", Font.BOLD, 35));
        
        l4 = new JLabel("Enter Pin");
        l4.setFont(new Font("System", Font.BOLD, 14));
        
        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 22));
        
        t2 = new JTextField();
        t2.setFont(new Font("Raleway", Font.BOLD, 14));
        
        b1 = new JButton("WITHDRAW");
        b1.setFont(new Font("System", Font.BOLD, 18));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
    
        b2 = new JButton("BACK");
        b2.setFont(new Font("System", Font.BOLD, 18));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        
        b3 = new JButton("LOG OUT");
        b3.setFont(new Font("System", Font.BOLD, 18));
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        
        
        setLayout(null);
        
        l4.setBounds(620,10,80,30);
        add(l4);
        
        t2.setBounds(700,10,40,30);
        add(t2);
        
        l1.setBounds(90,100,800,60);
        add(l1);
        
        l2.setBounds(270,160,800,60);
        add(l2);
        
        l3.setBounds(120,260,800,60);
        add(l3);
        
        t1.setBounds(210,340,360,50);
        add(t1);
        
        b1.setBounds(220,400,160,50);
        add(b1);
        
        b2.setBounds(400,400,160,50);
        add(b2);
        
        b3.setBounds(300,550,200,50);
        add(b3);
        
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(800,800);
        setLocation(350,40);
	setUndecorated(true);
        setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent ae)
    {
       
        try
	{        
           
            String a = t1.getText();
            String b = t2.getText();
            
            
            
            
            if(ae.getSource()==b1)
	    {
                if(a.equals(""))
		{
                    
                    JOptionPane.showMessageDialog(null, "Please enter the Amount to you want to Withdraw");
                
                }
		else if(b.equals(""))
		{
			JOptionPane.showMessageDialog(null, "Please enter the Pin at top right corner of the page");
		}
		else
		{
                    jdbcConn c1 = new jdbcConn();
                    double amount=0,balance=0;                    
		    
		    ResultSet rs = c1.s.executeQuery("SELECT * FROM TransactionDetails \nhaving pin = '"+b+"' and Account_no ='"+Account_number+"'\norder by date_time DESC");
                    if(rs.next())
		    {
			
			balance=rs.getDouble("balance");

			amount=Double.parseDouble(a);
			if(balance<amount)
			{
				JOptionPane.showMessageDialog(null, "Insufficient amount");	
			}
			else
			{
				balance-=amount;
			
				LocalDateTime now = LocalDateTime.now();  
				String Date=now.toString();
			
				String q1= "insert into TransactionDetails values('"+Account_number+"','"+b+"','0','"+a+"','"+balance+"','"+Date+"')";
              			c1.s.executeUpdate(q1);
                   
				JOptionPane.showMessageDialog(null, "Rs. "+a+" Debited Successfully");    
                    
                    		new Transactions(Account_number).setVisible(true);
                   		setVisible(false);
			}
		    }
		    else
		    {		JOptionPane.showMessageDialog(null, "Incorrect pin");	}
                 }
                
            }
	    else if(ae.getSource()==b2)
	    {
                
                new Transactions(Account_number).setVisible(true);
                setVisible(false);
                
            }
	    else if(ae.getSource()==b3)
	    {
		new login().setVisible(true);
		setVisible(false); 
            }
        }catch(Exception e){
                e.printStackTrace();
                System.out.println("error: "+e);
        }
            
    }

}