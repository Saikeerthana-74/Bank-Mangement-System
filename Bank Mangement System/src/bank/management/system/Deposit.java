package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {

    // Stores logged-in user's PIN (used to link transactions)
    String pin;

    // Input field for deposit amount
    TextField textField;

    // Buttons for deposit and navigation
    JButton b1, b2;

    Deposit(String pin) {

        // Assign pin passed from previous screen
        this.pin = pin;


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 1550, 830);
        add(l3);


        JLabel label1 = new JLabel("ENETR AMOUNT YOU WANT TO DEPOSIT");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(460, 180, 400, 35);
        l3.add(label1);

        //amount input field
        textField = new TextField();
        textField.setBackground(new Color(65, 125, 128));
        textField.setForeground(Color.WHITE);
        textField.setBounds(460, 230, 320, 25);
        textField.setFont(new Font("Raleway", Font.BOLD, 22));
        l3.add(textField);

        // DEPOSIT BUTTON
        b1 = new JButton("DEPOSIT");
        b1.setBounds(700, 362, 150, 35);
        b1.setBackground(new Color(65, 125, 128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(700, 406, 150, 35);
        b2.setBackground(new Color(65, 125, 128));
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        l3.add(b2);
        setLayout(null);
        setSize(1550, 1080);     // Full-screen ATM view
        setLocation(0, 0);
        setVisible(true);
    }

    /**
     * Handles button click events
     * DEPOSIT -> inserts transaction into bank table
     * BACK    -> returns to previous screen
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Read entered amount
            String amount = textField.getText();

            // Capture current date & time of transaction
            Date date = new Date();

            if (e.getSource() == b1) { // DEPOSIT button

                // Validate empty input
                if (textField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "Please enter the Amount you want to Deposit");
                } else {
                    // Insert deposit transaction into database
                    Con c = new Con();
                    c.statement.executeUpdate(
                            "insert into bank values('" + pin + "', '" + date + "','Deposit', '" + amount + "')"
                    );

                    // Success message
                    JOptionPane.showMessageDialog(null,
                            "Rs. " + amount + " Deposited Successfully");

                    setVisible(false);
                    new main_Class(pin);  // Navigate back to main menu
                }

            } else if (e.getSource() == b2) { // BACK button
                setVisible(false);
                new main_Class(pin);//it will direct to main screen
            }

        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Deposit("");
    }
}
