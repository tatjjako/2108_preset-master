import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;

//import static sun.java2d.marlin.CollinearSimplifier.SimplifierState.Empty;

public class Auth implements ActionListener {
//    private JFrame frame = new JFrame();
//    private JPanel panel = new JPanel();
//    private JLabel username;
//    private JTextField userInput;
//
//    private JLabel password;
//    private JPasswordField userPass;
//    private JButton register;
//    private JButton login;
//
//    private JPanel dashboard;

    JFrameElements elements = new JFrameElements();

    public void authScreen() {
        elements.frame.setSize(400, 250);
        elements.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        elements.dashboard = new JPanel();
        elements.dashboard.setLayout(null);

        elements.frame.add(elements.panel);

        elements.panel.setLayout(null);

        elements.username = new JLabel("Username");
        elements.username.setBounds(10,20,80,25);
        elements.panel.add(elements.username);

        elements.userInput = new JTextField(20);
        elements.userInput.setBounds(100,20,165,25);
        elements.panel.add(elements.userInput);

        elements.password = new JLabel("Password");
        elements.password.setBounds(10,50,80,25);
        elements.panel.add(elements.password);

        elements.userPass = new JPasswordField(20);
        elements.userPass.setBounds(100,50,165,25);
        elements.panel.add(elements.userPass);

        elements.register = new JButton("Register");
        elements.register.setBounds(6, 80, 80, 25);
        elements.register.addActionListener(this);
        elements.panel.add(elements.register);

        elements.login = new JButton("Login");
        elements.login.setBounds(80, 80, 80, 25);
        elements.login.addActionListener(this);
        elements.panel.add(elements.login);

        elements.frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String userVal = elements.userInput.getText();
        String passVal = elements.userInput.getText();

        DBlogic db = new DBlogic();

        if(e.getSource() == elements.register) {
            if(userVal.length() == 0 || passVal.length() == 0) {
                System.out.println("Empty field");
            } else {
                boolean check = Boolean.parseBoolean(db.select(userVal));

                if(check){
                    System.out.println("User already exists");
                } else {
                    String md5 = null;
                    MD5 md5Hash = new MD5();

                    try {
                        md5 = md5Hash.getMd5(passVal);
                    } catch (Exception a) {
                        a.printStackTrace();
                    }

                    System.out.println(md5);
                    db.register(userVal, md5);
                }
            }
        } else if(e.getSource() == elements.login) {
            if(userVal.length() == 0 || passVal.length() == 0) {
                System.out.println("Empty field");
            } else {
                String md5 = null;
                MD5 md5Hash = new MD5();

                try {
                    md5 = md5Hash.getMd5(passVal);
                } catch (Exception a) {
                    a.printStackTrace();
                }

                boolean check = db.login(userVal, md5);

                if(check) {
                    elements.frame.remove(elements.panel);
                    elements.frame.add(elements.dashboard);
                    elements.frame.repaint();

                    elements.frame.setTitle("Welcome " + userVal);
                }
            }
        } else {
            System.out.println("Error");
        }

    }

}
