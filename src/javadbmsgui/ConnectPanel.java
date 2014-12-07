/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadbmsgui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javadbmsgui.QueryPanel.rs;
import static javadbmsgui.QueryPanel.stmt;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Raunak Shakya
 */
public class ConnectPanel extends JPanel {

    private static final String[] dbms = {
        "----------------------Select DBMS----------------------",
        "Oracle DB 12c",
        "PostGre SQL",
        "SQL Server 2012",
        "MySQL DB"
    };

    private static final String[] drivers = {
        "----------------Select Database Driver----------------",
        "oracle.jdbc.driver.OracleDriver",
        "org.postgresql.Driver",
        "com.microsoft.sqlserver.jdbc.SQLServerDriver",
        "com.mysql.jdbc.Driver"
    };

    private static final String[] urls = {
        "-----------------Select Database URL-----------------",
        "jdbc:oracle:thin:@localhost:1521:",
        "jdbc:postgresql://localhost:5432/",
        "jdbc:sqlserver://localhost:1433;databaseName=",
        "jdbc:mysql://localhost/"
    };

    private Box mainbox, hbox, hbox1, hbox2, hbox3, hbox4, hbox5, hbox6, hbox7, hbox8, hbox9;
    private JLabel title, lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7;
    private JTextField txt1, txt2;
    private JPasswordField txt3;
    private JTextArea txt4;
    private JScrollPane sp;
    private JComboBox com1, com2, com3;
    private JButton btn1, btn2, btn3, conCloseBtn;

    String dbmsValue;
    String driverValue, urlValue;
    String databaseValue = null;
    String usernameValue = null;
    String passwordValue = null;
    static Connection conn = null;
    int flag = 0;

    public ConnectPanel() {
        super(new FlowLayout(FlowLayout.LEFT));
        initWidgets();
        addWidgets();
        setBackground(new Color(192, 230, 248));
    }

    private void initWidgets() {
        mainbox = Box.createVerticalBox();

        hbox = Box.createHorizontalBox();
        hbox1 = Box.createHorizontalBox();
        hbox2 = Box.createHorizontalBox();
        hbox3 = Box.createHorizontalBox();
        hbox4 = Box.createHorizontalBox();
        hbox5 = Box.createHorizontalBox();
        hbox6 = Box.createHorizontalBox();
        hbox7 = Box.createHorizontalBox();
        hbox8 = Box.createHorizontalBox();
        hbox9 = Box.createHorizontalBox();

        title = new JLabel("Choose the parameters for Database Connection");
        Font font = new Font("Arial", Font.BOLD, 15);
        title.setFont(font);
        lbl1 = new JLabel("DBMS:                                    ");
        lbl2 = new JLabel("Driver Name:                        ");
        lbl3 = new JLabel("URL Name:                            ");
        lbl4 = new JLabel("Database Name:                 ");
        lbl5 = new JLabel("User Name:                          ");
        lbl6 = new JLabel("Password:                            ");
        lbl7 = new JLabel("Output:                                                      ");

        com1 = new JComboBox(dbms);
        com1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dbmsValue = (String) com1.getSelectedItem();
            }
        });
        com2 = new JComboBox(drivers);
        com2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                driverValue = (String) com2.getSelectedItem();
            }
        });
        com3 = new JComboBox(urls);
        com3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                urlValue = (String) com3.getSelectedItem();
            }
        });

        txt1 = new JTextField(20);
        txt2 = new JTextField(20);
        txt3 = new JPasswordField(20);
        txt4 = new JTextArea(13, 10);
        txt4.setEditable(false);
        txt4.setLineWrap(true);
        txt4.setWrapStyleWord(true);
        sp = new JScrollPane(txt4);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        btn1 = new JButton("Connect");
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                databaseValue = txt1.getText().trim();
                usernameValue = txt2.getText().trim();
                passwordValue = txt3.getText().trim();

                if ((dbmsValue == null ? dbms[0] == null : dbmsValue.equals(dbms[0])) || dbmsValue == null) {
                    JOptionPane.showMessageDialog(null, "Please Select DBMS", "Warning", JOptionPane.ERROR_MESSAGE);
                } else if ((driverValue == null ? drivers[0] == null : driverValue.equals(drivers[0])) || driverValue == null) {
                    JOptionPane.showMessageDialog(null, "Please Select Driver", "Warning", JOptionPane.ERROR_MESSAGE);
                } else if ((urlValue == null ? urls[0] == null : urlValue.equals(urls[0])) || urlValue == null) {
                    JOptionPane.showMessageDialog(null, "Please Select URL", "Warning", JOptionPane.ERROR_MESSAGE);
                } else if (databaseValue == null || "".equals(databaseValue)) {
                    JOptionPane.showMessageDialog(null, "Please Enter Database", "Warning", JOptionPane.ERROR_MESSAGE);
                } else if (usernameValue == null || "".equals(usernameValue)) {
                    JOptionPane.showMessageDialog(null, "Please Enter Username", "Warning", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        Class.forName(driverValue);
                        conn = DriverManager.getConnection(urlValue + databaseValue, usernameValue, passwordValue);
                        txt4.setText("Connection Successful!");
                        conCloseBtn.setEnabled(true);
                        flag = 1;
                        QueryPanel.dbmstxt.setText(dbmsValue);
                        QueryPanel.databasetxt.setText(databaseValue);
                        QueryPanel.usernametxt.setText(usernameValue);
                        QueryPanel.txt1.setText("");
                        QueryPanel.txt1.setEditable(true);
                        QueryPanel.btn1.setEnabled(true);
                        QueryPanel.btn2.setEnabled(true);
                        QueryPanel.btn3.setEnabled(true);
                    } catch (ClassNotFoundException | SQLException exc) {
                        txt4.setText(exc.getMessage());//exc.getMessage());
                    }
                }
            }
        });

        btn2 = new JButton("Reset");
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                com1.setSelectedIndex(0);
                com2.setSelectedIndex(0);
                com3.setSelectedIndex(0);
                txt1.setText("");
                txt2.setText("");
                txt3.setText("");
            }
        });

        btn3 = new JButton("Clear Output Message");
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                txt4.setText("");
            }
        });

        conCloseBtn = new JButton("Disconnect");
        conCloseBtn.setEnabled(false);
        conCloseBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ConnectPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ConnectPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if (conn != null) {
                    try {
                        conn.close();
                        txt4.setText("Connection is now Closed!");
                        flag = 1;
                        QueryPanel.dbmstxt.setText("none");
                        QueryPanel.databasetxt.setText("none");
                        QueryPanel.usernametxt.setText("none");
                        QueryPanel.txt1.setText("Get your database connection first and foremost.....");
                        QueryPanel.txt1.setEditable(false);
                        QueryPanel.btn1.setEnabled(false);
                        QueryPanel.btn2.setEnabled(false);
                        QueryPanel.btn3.setEnabled(false);
                        conCloseBtn.setEnabled(false);

                    } catch (SQLException ex) {
                        txt4.setText(ex.getMessage());
                    }
                }
            }
        });
    }

    private void addWidgets() {
        hbox.add(title);

        hbox1.add(Box.createHorizontalStrut(15));
        hbox1.add(lbl1);
        hbox1.add(com1);

        hbox2.add(Box.createHorizontalStrut(15));
        hbox2.add(lbl2);
        hbox2.add(com2);

        hbox3.add(Box.createHorizontalStrut(15));
        hbox3.add(lbl3);
        hbox3.add(com3);

        hbox4.add(Box.createHorizontalStrut(15));
        hbox4.add(lbl4);
        hbox4.add(txt1);

        hbox5.add(Box.createHorizontalStrut(15));
        hbox5.add(lbl5);
        hbox5.add(txt2);

        hbox6.add(Box.createHorizontalStrut(15));
        hbox6.add(lbl6);
        hbox6.add(txt3);

        hbox7.add(Box.createHorizontalStrut(180));
        hbox7.add(btn1);
        hbox7.add(Box.createHorizontalStrut(10));
        hbox7.add(btn2);
        hbox7.add(Box.createHorizontalStrut(10));
        hbox7.add(conCloseBtn);

        hbox8.add(Box.createHorizontalStrut(15));
        hbox8.add(lbl7);
        hbox8.add(Box.createHorizontalStrut(40));
        hbox8.add(btn3);

        hbox9.add(Box.createHorizontalStrut(15));
        hbox9.add(sp);

        mainbox.add(Box.createVerticalStrut(15));
        mainbox.add(hbox);
        mainbox.add(Box.createVerticalStrut(25));
        mainbox.add(hbox1);
        mainbox.add(Box.createVerticalStrut(15));
        mainbox.add(hbox2);
        mainbox.add(Box.createVerticalStrut(15));
        mainbox.add(hbox3);
        mainbox.add(Box.createVerticalStrut(15));
        mainbox.add(hbox4);
        mainbox.add(Box.createVerticalStrut(15));
        mainbox.add(hbox5);
        mainbox.add(Box.createVerticalStrut(15));
        mainbox.add(hbox6);
        mainbox.add(Box.createVerticalStrut(15));
        mainbox.add(hbox7);
        mainbox.add(Box.createVerticalStrut(15));
        mainbox.add(hbox8);
        mainbox.add(Box.createVerticalStrut(10));
        mainbox.add(hbox9);

        add(mainbox);

    }
}
