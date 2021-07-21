package BankingSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class MiniStatement extends JFrame implements ActionListener
{

	JLabel l1,l2,l3,l4,l5;
	JTextArea ta1,ta2,ta3,ta4,ta5;
	String s1="",s2="",s3="",s4="",s5="";
	String Account_number,pin;
	JButton b1,b2;

	MiniStatement(String Account_number, String pin)
	{
		this.Account_number=Account_number;
		this.pin=pin;
	
		l1 = new JLabel("Account Number");
        	l1.setFont(new Font("Osward", Font.BOLD, 18));
        
		l2 = new JLabel("Deposit");
        	l2.setFont(new Font("Osward", Font.BOLD, 18));
        
		l3 = new JLabel("Withdraw");
        	l3.setFont(new Font("Osward", Font.BOLD, 18));
        
		l4 = new JLabel("Balance");
        	l4.setFont(new Font("Osward", Font.BOLD, 18));
        
		l5 = new JLabel("Dates");
        	l5.setFont(new Font("Osward", Font.BOLD, 18));

		ta1=new JTextArea();
        	ta1.setFont(new Font("Osward", Font.PLAIN, 13));
		ta1.setLineWrap(true);
		ta1.setWrapStyleWord(true);
		
		ta2=new JTextArea();
        	ta2.setFont(new Font("Osward", Font.PLAIN, 13));
		ta2.setLineWrap(true);
		ta2.setWrapStyleWord(true);
		
		ta3=new JTextArea();
        	ta3.setFont(new Font("Osward", Font.PLAIN, 13));
		ta3.setLineWrap(true);
		ta3.setWrapStyleWord(true);
		
		ta4=new JTextArea();
        	ta4.setFont(new Font("Osward", Font.PLAIN, 13));
		ta4.setLineWrap(true);
		ta4.setWrapStyleWord(true);
		
		ta5=new JTextArea();
        	ta5.setFont(new Font("Osward", Font.PLAIN, 13));
		ta5.setLineWrap(true);
		ta5.setWrapStyleWord(true);
		
		b1 = new JButton("Back");
        	b1.setFont(new Font("Osward", Font.BOLD, 18));
        	b1.setBackground(Color.BLACK);
        	b1.setForeground(Color.WHITE);
	
		b2 = new JButton("LOG OUT");
        	b2.setFont(new Font("Osward", Font.BOLD, 18));
        	b2.setBackground(Color.BLACK);
        	b2.setForeground(Color.WHITE);
        
		setLayout(null);

		l1.setBounds(70,50,200,70);
		add(l1);

		ta1.setBounds(70,120,150,550);
		add(ta1);

		l2.setBounds(270,50,100,70);
		add(l2);

		ta2.setBounds(270,120,85,550);
		add(ta2);

		l3.setBounds(390,50,100,70);
		add(l3);

		ta3.setBounds(390,120,100,550);
		add(ta3);

		l4.setBounds(520,50,100,70);
		add(l4);

		ta4.setBounds(520,120,90,550);
		add(ta4);

		l5.setBounds(635,50,100,70);
		add(l5);

		ta5.setBounds(635,120,130,550);
		add(ta5);

		b1.setBounds(200,700,100,40);
		add(b1);

		b2.setBounds(500,700,150,40);
		add(b2);

		b1.addActionListener(this);
		b2.addActionListener(this);

		int c=0;
		String temp;
		jdbcConn c1 = new jdbcConn();
	    	try {
                	ResultSet rs = c1.s.executeQuery(" SELECT * FROM TransactionDetails having Account_no ='"+Account_number+"' and pin ='"+pin+"' order by date_time DESC");
			while(rs.next() && ++c<=28)
			{
				temp=rs.getString("Account_no")+"\n";
				s1+=temp;
				temp=rs.getString("deposit")+"\n";
				s2+=temp;
				temp=rs.getString("withdraw")+"\n";
				s3+=temp;
				temp=rs.getString("balance")+"\n";
				s4+=temp;
				temp=rs.getString("date_time")+"\n";
				s5+=temp;
			}
			ta1.setText(s1);
			ta1.setEditable(false);
			ta2.setText(s2);
			ta1.setEditable(false);
			ta3.setText(s3);
			ta1.setEditable(false);
			ta4.setText(s4);
			ta1.setEditable(false);
			ta5.setText(s5);
			ta1.setEditable(false);
			
                }catch (Exception e) 
		{
               		e.printStackTrace();
			System.out.println("Error "+e);
           	}
		
		getContentPane().setBackground(Color.WHITE);
	        

        	setSize(800,800);
        	setLocation(350,40);
		setUndecorated(true);
        	setVisible(true);

	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			new Transactions(Account_number).setVisible(true);
			setVisible(false);
		}
		else if(ae.getSource()==b2)
		{
			new login().setVisible(true);
			setVisible(false);
		}
	}
}