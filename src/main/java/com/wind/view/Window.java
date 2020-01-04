package com.wind.view;

import javax.swing.*;

/**
 * @Title: Window
 * @Package com.wind.view
 * @Description: TODO
 * @author huanghy
 * @date 2019/10/21 13:30
 * @version V1.0
 */
public class Window {

    private JPanel panel;
    private JLabel label;

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        JFrame frame = new JFrame("Window");
        frame.setContentPane(new Window().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
        frame.pack();
        frame.setVisible(true);
    }
}
