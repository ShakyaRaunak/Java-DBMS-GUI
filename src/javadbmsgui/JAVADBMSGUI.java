/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadbmsgui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 *
 * @author Raunak Shakya
 */
public class JAVADBMSGUI extends JFrame {

    private final ConnectPanel ap;
    private final QueryPanel qp;
    private final JTabbedPane tp;

    public JAVADBMSGUI() {
        super("My JAVA DBMS GUI Project");

        ap = new ConnectPanel();
        qp = new QueryPanel();
        tp = new JTabbedPane();
        tp.add("          Connect Panel          ", ap);
        tp.add("          Query Panel          ", qp);
        add(tp);
    }

    public static void main(String[] args) {
        JAVADBMSGUI javadbmsgui = new JAVADBMSGUI();
        javadbmsgui.setSize(600, 640);
        javadbmsgui.setResizable(true);
        javadbmsgui.setLocationRelativeTo(null);
        javadbmsgui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        javadbmsgui.setVisible(true);
    }

}
