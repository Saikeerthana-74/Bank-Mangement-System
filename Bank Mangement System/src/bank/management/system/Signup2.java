package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Signup2 extends JFrame implements ActionListener {

    JComboBox comboBox, comboBox2, comboBox3, comboBox4, comboBox5;
    JTextField textPan, textAadhar;
    JRadioButton r1, r2, e1, e2;
    ButtonGroup seniorGroup, accountGroup;
    JButton next;
    String formno;

    Font headingFont = new Font("Raleway", Font.BOLD, 22);
    Font labelFont = new Font("Raleway", Font.BOLD, 18);
    Font fieldFont = new Font("Raleway", Font.PLAIN, 14);

    Color bgColor = new Color(255, 243, 200);
    Color fieldBg = Color.WHITE;

    Signup2(String formno) {
        super("APPLICATION FORM - PAGE 2");

        this.formno = formno;

        setLayout(null);
        getContentPane().setBackground(bgColor);

        // Bank logo
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(150, 10, 100, 100);
        add(image);

        JLabel l1 = new JLabel("Page 2 :-");
        l1.setFont(headingFont);
        l1.setBounds(300, 30, 300, 30);
        add(l1);

        JLabel l2 = new JLabel("Additional Details");
        l2.setFont(headingFont);
        l2.setBounds(300, 60, 400, 30);
        add(l2);

        // Religion
        addLabel("Religion :", 100, 120);
        comboBox = createCombo(new String[]{"Hindu", "Muslim", "Sikh", "Christian", "Other"}, 350, 120);

        // Category
        addLabel("Category :", 100, 170);
        comboBox2 = createCombo(new String[]{"General", "OBC", "SC", "ST", "Other"}, 350, 170);

        // Income
        addLabel("Income :", 100, 220);
        comboBox3 = createCombo(new String[]{"Null", "<1,50,000", "<2,50,000", "5,00,000", "Upto 10,00,000", "Above 10,00,000"}, 350, 220);

        // Education
        addLabel("Educational :", 100, 270);
        comboBox4 = createCombo(new String[]{"Non-Graduate", "Graduate", "Post-Graduate", "Doctorate", "Others"}, 350, 270);

        // Occupation
        addLabel("Occupation :", 100, 340);
        comboBox5 = createCombo(new String[]{"Salaried", "Self-Employed", "Business", "Student", "Retired", "Other"}, 350, 340);

        // PAN
        addLabel("PAN Number :", 100, 390);
        textPan = createTextField(350, 390);

        // Aadhar
        addLabel("Aadhar Number :", 100, 440);
        textAadhar = createTextField(350, 440);

        // Senior Citizen
        addLabel("Senior Citizen :", 100, 490);

        r1 = createRadio("Yes", 350, 490);
        r2 = createRadio("No", 460, 490);

        seniorGroup = new ButtonGroup();
        seniorGroup.add(r1);
        seniorGroup.add(r2);

        // Existing Account
        addLabel("Existing Account :", 100, 540);

        e1 = createRadio("Yes", 350, 540);
        e2 = createRadio("No", 460, 540);

        accountGroup = new ButtonGroup();
        accountGroup.add(e1);
        accountGroup.add(e2);



        // Form number
        JLabel l12 = new JLabel("Form No :");
        l12.setFont(fieldFont);
        l12.setBounds(700, 10, 80, 20);
        add(l12);

        JLabel l13 = new JLabel(formno);
        l13.setFont(fieldFont);
        l13.setBounds(780, 10, 80, 20);
        add(l13);

        // Next button
        next = new JButton("Next");
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBounds(570, 640, 120, 35);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        add(next);

        setSize(850, 750);
        setLocation(450, 80);
        setVisible(true);
    }

    private void addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(labelFont);
        label.setBounds(x, y, 200, 30);
        add(label);
    }

    private JComboBox createCombo(String[] items, int x, int y) {
        JComboBox box = new JComboBox(items);
        box.setFont(fieldFont);
        box.setBackground(fieldBg);
        box.setBounds(x, y, 320, 30);
        add(box);
        return box;
    }

    private JTextField createTextField(int x, int y) {
        JTextField tf = new JTextField();
        tf.setFont(fieldFont);
        tf.setBounds(x, y, 320, 30);
        add(tf);
        return tf;
    }

    private JRadioButton createRadio(String text, int x, int y) {
        JRadioButton rb = new JRadioButton(text);
        rb.setFont(fieldFont);
        rb.setBackground(bgColor);
        rb.setBounds(x, y, 80, 30);
        add(rb);
        return rb;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String rel = (String) comboBox.getSelectedItem();
        String cate = (String) comboBox2.getSelectedItem();
        String inc = (String) comboBox3.getSelectedItem();
        String edu = (String) comboBox4.getSelectedItem();
        String occ = (String) comboBox5.getSelectedItem();

        String pan = textPan.getText();
        String addhar = textAadhar.getText();

        String scitizen = " ";
        if (r1.isSelected()) scitizen = "Yes";
        else if (r2.isSelected()) scitizen = "No";

        String eAccount = " ";
        if (e1.isSelected()) eAccount = "Yes";
        else if (e2.isSelected()) eAccount = "No";

        try {
            if (pan.equals("") || addhar.equals("")) {
                JOptionPane.showMessageDialog(null, "Fill all the fields");
            } else {
                Con c = new Con();
                String q = "insert into Signuptwo values('" + formno + "','" + rel + "','" + cate + "','" + inc + "','" + edu + "','" + occ + "','" + pan + "','" + addhar + "','" + scitizen + "','" + eAccount + "')";
                c.statement.executeUpdate(q);
                setVisible(false);
                new Signup3(formno);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Signup2("");
    }
}
