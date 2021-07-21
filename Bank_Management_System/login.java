package BankingSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class login extends JFrame implements ActionListener{

    JLabel l1,l2,l3;
    JTextField tf1;
    JPasswordField pf2;
    JButton b1,b2,b3,b4;
  
    login(){  
               
        l1 = new JLabel("WELCOME TO OUR BANK");
        l1.setFont(new Font("Osward", Font.BOLD, 38));
        
        l2 = new JLabel("Account No:");
        l2.setFont(new Font("Raleway", Font.BOLD, 28));
        
        l3 = new JLabel("PIN:");
        l3.setFont(new Font("Raleway", Font.BOLD, 28));
        
        tf1 = new JTextField(15);
        pf2 = new JPasswordField(15);
        
        b1 = new JButton("SIGN IN");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        
        b2 = new JButton("CLEAR");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        
        b3 = new JButton("SIGN UP");
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);

	b4=new JButton("EXIT");
        b4.setBackground(Color.BLACK);
        b4.setForeground(Color.WHITE);
        
        setLayout(null);
        
        l1.setBounds(145,50,550,200);
        add(l1);
        
        l2.setBounds(125,150,375,200);
        add(l2);
        
        tf1.setBounds(300,235,230,30);
        add(tf1);
        
        l3.setBounds(125,225,375,200);
        add(l3);
        
        tf1.setFont(new Font("Arial", Font.BOLD, 14));
       
        
        pf2.setFont(new Font("Arial", Font.BOLD, 14));
        pf2.setBounds(300,310,230,30);
        add(pf2);
        
        b1.setFont(new Font("Arial", Font.BOLD, 14));
        b1.setBounds(300,400,100,30);
        add(b1);
        
        b2.setFont(new Font("Arial", Font.BOLD, 14));
        b2.setBounds(430,400,100,30);
        add(b2);
        
        b3.setFont(new Font("Arial", Font.BOLD, 14));
        b3.setBounds(300,450,230,30);
        add(b3);

        b4.setFont(new Font("Arial", Font.BOLD, 14));
        b4.setBounds(300,550,230,30);
        add(b4);
        
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(750,750);
        setLocation(400,70);
	setUndecorated(true);
        setVisible(true);
        

        
    }
    public void actionPerformed(ActionEvent ae){
       
        try{        
       	    jdbcConn c1 = new jdbcConn();
            String a  = tf1.getText();
            String b  = String.valueOf(pf2.getPassword());
            String q  = "select * from login having Account_no = '"+a+"' and pin = '"+b+"'";
            ResultSet rs = c1.s.executeQuery(q);         
            if(ae.getSource()==b1){
		
		//rs.next will jump to next row in database table
                if(rs.next()){

		//transaction frame will be visible
                    new Transactions(a).setVisible(true);

		//current frame will be closed
                    setVisible(false);
                
                }else{

                    JOptionPane.showMessageDialog(null, "Incorrect Account Number or PIN");
                    
                }
            }else if(ae.getSource()==b2){

		//clear the text field and password field
                tf1.setText("");
                pf2.setText("");

            }else if(ae.getSource()==b3){

		//show signup page
                new Signup().setVisible(true);
                setVisible(false);				
            }else if(ae.getSource()==b4){
		System.exit(0);
	    }
        }catch(Exception e){
                    e.printStackTrace();
		    System.out.println("Error "+e);
        }
            
    }
    public static void main(String[] args)
    {
	new login().setVisible(true);
    }
}