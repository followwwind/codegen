package com.wind.ui;

import javax.swing.*;

public class UiMain {

    private JTabbedPane tab;
    private JPanel mainPanel;
    private JPanel home;
    private JPanel table;
    private JPanel settings;
    private JPanel about;

    public static void main(String[] args) {
        JFrame frame = new JFrame("UiMain");
        frame.setContentPane(new UiMain().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
