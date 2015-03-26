/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javadbmsgui.system;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static com.javadbmsgui.system.ConnectPanel.conn;
import com.javadbmsgui.utils.MessageUtils;
import java.util.ResourceBundle;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Raunak Shakya
 */
public class QueryPanel extends JPanel {

    public static final ResourceBundle messages = MessageUtils.MESSAGES;

    private Box mainbox, hbox, hbox1, hbox4, hbox5, hbox6, hbox7, hbox8, hbox9, hbox10;
    private JLabel title, lbl1, lbl4, lbl5, lbl6, lbl7;
    static JTextArea txt1, txt2;
    private JScrollPane sp1, sp2;
    static JButton btn1, btn2, btn3;

    static JTextField dbmstxt, databasetxt, usernametxt;
    static PreparedStatement stmt = null;
    static ResultSet rs = null;

    public QueryPanel() {
        super(new FlowLayout(FlowLayout.LEFT));
        initWidgets();
        addWidgets();
        setBackground(new Color(192, 230, 248));
    }

    private void initWidgets() {
        mainbox = Box.createVerticalBox();

        hbox = Box.createHorizontalBox();
        hbox1 = Box.createHorizontalBox();
        hbox4 = Box.createHorizontalBox();
        hbox5 = Box.createHorizontalBox();
        hbox6 = Box.createHorizontalBox();
        hbox7 = Box.createHorizontalBox();
        hbox8 = Box.createHorizontalBox();
        hbox9 = Box.createHorizontalBox();
        hbox10 = Box.createHorizontalBox();

        title = new JLabel(messages.getString("db.connection.parameters.chosen"));
        Font font = new Font("Arial", Font.BOLD, 15);
        title.setFont(font);
        lbl1 = new JLabel(messages.getString("label.dbms") + ":                                    ");
        lbl4 = new JLabel(messages.getString("label.database.name") + ":                 ");
        lbl5 = new JLabel(messages.getString("label.user.name") + ":                          ");
        lbl6 = new JLabel(messages.getString("insert.query.below") + ":                                                                                               ");
        lbl7 = new JLabel(messages.getString("common.output") + ":                                                                  ");

        dbmstxt = new JTextField(messages.getString("common.none"), 33);
        dbmstxt.setEditable(false);
        databasetxt = new JTextField(messages.getString("common.none"), 33);
        databasetxt.setEditable(false);
        usernametxt = new JTextField(messages.getString("common.none"), 33);
        usernametxt.setEditable(false);

        txt1 = new JTextArea(10, 33);
        font = new Font("Arial", Font.PLAIN, 12);
        txt1.setFont(font);
        txt1.setText(messages.getString("get.db.connection"));
        txt1.setEditable(false);
        txt1.setLineWrap(true);
        txt1.setWrapStyleWord(true);
        sp1 = new JScrollPane(txt1);
        sp1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        txt2 = new JTextArea(8, 33);
        txt2.setEditable(false);
        txt2.setLineWrap(true);
        txt2.setWrapStyleWord(true);
        sp2 = new JScrollPane(txt2);
        sp2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        btn1 = new JButton(messages.getString("query.execute"));
        btn1.setEnabled(false);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String queryString;
                queryString = txt1.getSelectedText();
                if (queryString == null) {
                    queryString = txt1.getText().trim();

                    if (queryString.isEmpty()) {
                        JOptionPane.showMessageDialog(new JFrame(), messages.getString("query.write.then.execute"));
                    } else if (queryString.contains("select")) {
                        try {
                            stmt = conn.prepareStatement(queryString);
                        } catch (SQLException ex) {
                            txt2.setText(ex.getMessage());
                        }
                        try {
                            rs = stmt.executeQuery();
                            txt2.setText(messages.getString("query.executed.success"));
                        } catch (SQLException ex) {
                            txt2.setText(ex.getMessage());
                        }
                    } else {
                        try {
                            stmt = conn.prepareStatement(queryString);
                        } catch (SQLException ex) {
                            txt2.setText(ex.getMessage());
                        }
                        try {
                            int affectedRows = stmt.executeUpdate();
                            txt2.setText(messages.getString("query.executed.success") + "\n\n" + affectedRows + " rows affected successfully!");
                        } catch (SQLException ex) {
                            txt2.setText(ex.getMessage());
                        }
                    }
                } else {
                    if (queryString.contains("select")) {
                        try {
                            stmt = conn.prepareStatement(queryString);
                        } catch (SQLException ex) {
                            txt2.setText(ex.getMessage());
                        }
                        try {
                            rs = stmt.executeQuery();
                            txt2.setText(messages.getString("query.executed.success"));
                        } catch (SQLException ex) {
                            txt2.setText(ex.getMessage());
                        }
                    } else {
                        try {
                            stmt = conn.prepareStatement(queryString);
                        } catch (SQLException ex) {
                            txt2.setText(ex.getMessage());
                        }
                        try {
                            int affectedRows = stmt.executeUpdate();
                            txt2.setText(messages.getString("query.executed.success") + "\n\n" + affectedRows + " rows affected successfully!");
                        } catch (SQLException ex) {
                            txt2.setText(ex.getMessage());
                        }
                    }
                }
            }
        });

        btn2 = new JButton(messages.getString("common.clear"));
        btn2.setEnabled(false);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                txt1.setText("");
            }
        }
        );

        btn3 = new JButton(messages.getString("clear.output.message"));
        btn3.setEnabled(false);
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                txt2.setText("");
            }
        }
        );
    }

    private void addWidgets() {
        hbox.add(title);

        hbox1.add(Box.createHorizontalStrut(15));
        hbox1.add(lbl1);
        hbox1.add(dbmstxt);

        hbox4.add(Box.createHorizontalStrut(15));
        hbox4.add(lbl4);
        hbox4.add(databasetxt);

        hbox5.add(Box.createHorizontalStrut(15));
        hbox5.add(lbl5);
        hbox5.add(usernametxt);

        hbox6.add(Box.createHorizontalStrut(15));
        hbox6.add(lbl6);

        hbox7.add(Box.createHorizontalStrut(15));
        hbox7.add(sp1);

        hbox8.add(Box.createHorizontalStrut(260));
        hbox8.add(btn1);
        hbox8.add(Box.createHorizontalStrut(10));
        hbox8.add(btn2);

        hbox9.add(Box.createHorizontalStrut(15));
        hbox9.add(lbl7);
        hbox9.add(Box.createHorizontalStrut(40));
        hbox9.add(btn3);

        hbox10.add(Box.createHorizontalStrut(15));
        hbox10.add(sp2);

        mainbox.add(Box.createVerticalStrut(15));
        mainbox.add(hbox);
        mainbox.add(Box.createVerticalStrut(25));
        mainbox.add(hbox1);
        mainbox.add(Box.createVerticalStrut(15));
        mainbox.add(hbox4);
        mainbox.add(Box.createVerticalStrut(15));
        mainbox.add(hbox5);
        mainbox.add(Box.createVerticalStrut(15));
        mainbox.add(hbox6);
        mainbox.add(Box.createVerticalStrut(15));
        mainbox.add(hbox7);
        mainbox.add(Box.createVerticalStrut(10));
        mainbox.add(hbox8);
        mainbox.add(Box.createVerticalStrut(15));
        mainbox.add(hbox9);
        mainbox.add(Box.createVerticalStrut(10));
        mainbox.add(hbox10);
        add(mainbox);
    }
}
