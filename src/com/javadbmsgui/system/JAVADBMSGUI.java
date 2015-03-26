/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javadbmsgui.system;

import com.javadbmsgui.utils.LayoutUtils;
import com.javadbmsgui.utils.MessageUtils;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 *
 * @author Raunak Shakya
 */
public class JAVADBMSGUI extends JFrame {

    public static final ResourceBundle messages = MessageUtils.MESSAGES;

    private final ConnectPanel connectPanel;
    private final QueryPanel queryPanel;
    private final JTabbedPane tabbedPane;

    public JAVADBMSGUI() {
        super(messages.getString("title.application.name"));

        connectPanel = new ConnectPanel();
        queryPanel = new QueryPanel();
        tabbedPane = new JTabbedPane();
        tabbedPane.add("                 " + messages.getString("title.connect.panel") + "                 ", connectPanel);
        tabbedPane.add("                 " + messages.getString("title.query.panel") + "                 ", queryPanel);
        add(tabbedPane);
    }

    public static void main(String[] args) {
        JAVADBMSGUI javadbmsgui = new JAVADBMSGUI();
        javadbmsgui.setSize(LayoutUtils.APPLICATION_WINDOW_WIDTH, LayoutUtils.APPLICATION_WINDOW_HEIGHT);
        javadbmsgui.setResizable(true);
        javadbmsgui.setLocationRelativeTo(null);
        javadbmsgui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        javadbmsgui.setVisible(true);
    }

}
