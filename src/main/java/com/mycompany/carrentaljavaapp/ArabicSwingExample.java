package com.mycompany.carrentaljavaapp;

import javax.swing.*;
import java.awt.*;
import java.awt.ComponentOrientation;

public class ArabicSwingExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("تجربة العربية");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        JLabel label = new JLabel("مرحبا بك يا إبراهيم!");
        label.setFont(new Font("Tahoma", Font.BOLD, 18));

        panel.add(label);
        frame.add(panel);
        frame.setVisible(true);
    }
}
