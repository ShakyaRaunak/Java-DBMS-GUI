/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javadbmsgui.system;

import static com.javadbmsgui.system.ConnectPanel.messages;
import static com.javadbmsgui.system.JAVADBMSGUI.logger;
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
import static com.javadbmsgui.system.QueryPanel.resultSet;
import static com.javadbmsgui.system.QueryPanel.statement;
import com.javadbmsgui.utils.DatabaseUtils;
import com.javadbmsgui.utils.MessageUtils;
import java.util.ResourceBundle;
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

    public static final ResourceBundle messages = MessageUtils.MESSAGES;

    private static final String[] dbms = {
        messages.getString("label.select.dbms"),
        messages.getString("label.oracle.db"),
        messages.getString("label.postgres.db"),
        messages.getString("label.sql.server.db"),
        messages.getString("label.mysql.db")
    };

    private static final String[] drivers = {
        messages.getString("label.select.db.driver"),
        DatabaseUtils.ORACLE_DRIVER,
        DatabaseUtils.POSTGRES_DRIVER,
        DatabaseUtils.SQL_SERVER_DRIVER,
        DatabaseUtils.MYSQL_DRIVER
    };

    private static final String[] urls = {
        messages.getString("label.select.db.url"),
        DatabaseUtils.ORACLE_DB_URL,
        DatabaseUtils.POSTGRES_DB_URL,
        DatabaseUtils.SQL_SERVER_DB_URL,
        DatabaseUtils.MYSQL_DB_URL
    };

    private Box mainbox, hbox, hbox1, hbox2, hbox3, hbox4, hbox5, hbox6, hbox7, hbox8, hbox9;
    private JLabel title, lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7;
    private JTextField txt1, txt2;
    private JPasswordField txt3;
    private JTextArea txt4;
    private JScrollPane sp;
    private JComboBox com1, com2, com3;
    private JButton btn1, btn2, btn3, closeDBConnectionButton;

    String dbmsValue;
    String driverValue, urlValue;
    String databaseValue = null;
    String usernameValue = null;
    String passwordValue = null;
    static Connection connection = null;
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

        title = new JLabel(messages.getString("choose.db.connection.parameters"));
        Font font = new Font("Arial", Font.BOLD, 15);
        title.setFont(font);
        lbl1 = new JLabel(messages.getString("label.dbms") + ":                                    ");
        lbl2 = new JLabel(messages.getString("label.driver.name") + ":                        ");
        lbl3 = new JLabel(messages.getString("label.url.name") + ":                            ");
        lbl4 = new JLabel(messages.getString("label.database.name") + ":                 ");
        lbl5 = new JLabel(messages.getString("label.user.name") + ":                          ");
        lbl6 = new JLabel(messages.getString("label.password") + ":                            ");
        lbl7 = new JLabel(messages.getString("common.output") + ":                                                                  ");

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

        btn1 = new JButton(messages.getString("common.connect"));
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                databaseValue = txt1.getText().trim();
                usernameValue = txt2.getText().trim();
                passwordValue = txt3.getText().trim();

                if ((dbmsValue == null ? dbms[0] == null : dbmsValue.equals(dbms[0])) || dbmsValue == null) {
                    JOptionPane.showMessageDialog(null, messages.getString("dbms.select"), messages.getString("common.warning"), JOptionPane.ERROR_MESSAGE);
                } else if ((driverValue == null ? drivers[0] == null : driverValue.equals(drivers[0])) || driverValue == null) {
                    JOptionPane.showMessageDialog(null, messages.getString("driver.select"), messages.getString("common.warning"), JOptionPane.ERROR_MESSAGE);
                } else if ((urlValue == null ? urls[0] == null : urlValue.equals(urls[0])) || urlValue == null) {
                    JOptionPane.showMessageDialog(null, messages.getString("db.url.select"), messages.getString("common.warning"), JOptionPane.ERROR_MESSAGE);
                } else if (databaseValue == null || "".equals(databaseValue)) {
                    JOptionPane.showMessageDialog(null, messages.getString("database.enter"), messages.getString("common.warning"), JOptionPane.ERROR_MESSAGE);
                } else if (usernameValue == null || "".equals(usernameValue)) {
                    JOptionPane.showMessageDialog(null, messages.getString("username.enter"), messages.getString("common.warning"), JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        logger.debug("Opening database connection");
                        Class.forName(driverValue);
                        connection = DriverManager.getConnection(urlValue + databaseValue, usernameValue, passwordValue);
                        txt4.setText(messages.getString("connection.success"));
                        closeDBConnectionButton.setEnabled(true);
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
                        logger.error("Error with database connection");
                        txt4.setText(exc.getMessage());
                    }
                }
            }
        });

        btn2 = new JButton(messages.getString("common.reset"));
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

        btn3 = new JButton(messages.getString("clear.output.message"));
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                txt4.setText("");
            }
        });

        closeDBConnectionButton = new JButton(messages.getString("common.disconnect"));
        closeDBConnectionButton.setEnabled(false);
        closeDBConnectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                logger.debug("Closing database resources");
                closeResultSet();
                closeStatement();
                closeConnection();
            }
        });
    }

    private void closeResultSet() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void closeStatement() {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                logger.error("Error closing statement");
            }
        }
    }

    private void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                txt4.setText(messages.getString("connection.closed"));
                flag = 1;
                QueryPanel.dbmstxt.setText(messages.getString("common.none"));
                QueryPanel.databasetxt.setText(messages.getString("common.none"));
                QueryPanel.usernametxt.setText(messages.getString("common.none"));
                QueryPanel.txt1.setText(messages.getString("get.db.connection"));
                QueryPanel.txt1.setEditable(false);
                QueryPanel.btn1.setEnabled(false);
                QueryPanel.btn2.setEnabled(false);
                QueryPanel.btn3.setEnabled(false);
                closeDBConnectionButton.setEnabled(false);
            } catch (SQLException ex) {
                logger.error("Error closing connection");
                txt4.setText(ex.getMessage());
            }
        }
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
        hbox7.add(closeDBConnectionButton);

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
