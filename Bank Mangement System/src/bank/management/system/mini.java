package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class mini extends JFrame implements ActionListener {
    String pin;
    JButton button;
    mini(String pin) {

        // assign pin received from previous screen
        this.pin = pin;
        getContentPane().setBackground(new Color(235, 245, 245));

        setSize(400,600);
        setLocation(20,20);
        setLayout(null);

        JLabel label1 = new JLabel();
        label1.setBounds(20,140,400,200);
        add(label1);

        JLabel label2 = new JLabel("TechCoder A.V");
        label2.setFont(new Font("System", Font.BOLD,15));
        label2.setBounds(150,20,200,20);
        add(label2);

        // label to display masked card number
        JLabel label3 = new JLabel();
        label3.setBounds(20,80,300,20);
        add(label3);

        // label to display total balance
        JLabel label4 = new JLabel();
        label4.setBounds(20,400,300,20);
        add(label4);

        // fetch card number from login table and mask it
        try {
            Con c = new Con();
            ResultSet resultSet = c.statement.executeQuery("select * from login where pin = '"+pin+"'");

            while (resultSet.next()) {
                label3.setText("Card Number:  " + resultSet.getString("card_number").substring(0,4) + "XXXXXXXX" + resultSet.getString("card_number").substring(12));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // fetch transaction history and calculate balance
        try {
            int balance = 0;
            Con c = new Con();
            ResultSet resultSet = c.statement.executeQuery("select * from bank where pin = '"+pin+"'");

            while (resultSet.next()) {

                // append transaction details using html formatting
                label1.setText(label1.getText() + "<html>" + resultSet.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + resultSet.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + resultSet.getString("amount") + "<br><br><html>");

                // calculate balance based on transaction type
                if (resultSet.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(resultSet.getString("amount"));
                } else {
                    balance -= Integer.parseInt(resultSet.getString("amount"));
                }
            }

            // display total balance
            label4.setText("Your Total Balance is Rs " + balance);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // exit button to close mini statement window
        button = new JButton("Exit");
        button.setBounds(20,500,100,25);
        button.addActionListener(this);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        add(button);
        // make frame visible
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // close mini statement window
        setVisible(false);
    }

    public static void main(String[] args) {
        new mini("");
    }
}
