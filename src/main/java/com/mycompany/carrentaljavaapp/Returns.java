package com.mycompany.carrentaljavaapp;

import utils.UIHelper.StyleUtils;
import utils.UIHelper.AppTheme;

import javax.swing.*;
import java.awt.*;

/**
 * Returns Page – Car Rental App
 */
public class Returns extends javax.swing.JFrame {

    private JPanel sidebar;
    private JPanel mainPanel;

    public Returns() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Car Rental - Returns");
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLayout(new BorderLayout());

        // 🎨 Sidebar
        sidebar = new JPanel();
        sidebar.setBackground(AppTheme.SIDEBAR_BG_DARK);
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(220, getHeight()));

        // روابط الـ Sidebar باستخدام StyleUtils
        sidebar.add(Box.createVerticalStrut(30)); // مسافة فوق
        sidebar.add(StyleUtils.createSidebarLink("🏠 Dashboard", () -> openDashboard(), false));
        sidebar.add(StyleUtils.createSidebarLink("🚗 Cars", () -> openCars(), false));
        sidebar.add(StyleUtils.createSidebarLink("🧾 Rents", () -> openRents(), false));
        sidebar.add(StyleUtils.createSidebarLink("📦 Returns", () -> {}, true)); // الصفحة الحالية
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(StyleUtils.createSidebarLink("🔒 Logout", this::logout, false));
        sidebar.add(Box.createVerticalStrut(20));

        // 🎯 Main Panel (المحتوى الأساسي)
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(AppTheme.FIELD_BACKGROUND_WHITE);

        JLabel titleLabel = new JLabel("Return Management", SwingConstants.CENTER);
        titleLabel.setFont(AppTheme.TITLE_FONT);
        titleLabel.setForeground(AppTheme.SIDEBAR_BG_DARK);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // ممكن تضيف هنا جدول أو محتوى صفحة الـ Returns
        JLabel content = new JLabel("🧾 Here you can manage car returns.", SwingConstants.CENTER);
        content.setFont(AppTheme.LABEL_FONT);
        mainPanel.add(content, BorderLayout.CENTER);

        // دمج الجانبين في الإطار الرئيسي
        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        pack();
    }

    private void openCars() {

    }

    private void openDashboard() {
    }

    // 🌟 أكشنات الـ Sidebar
//    private void openDashboard() {
//        new Dashboard().setVisible(true);
//        this.dispose();
//    }

//    private void openCars() {
//        new Cars().setVisible(true);
//        this.dispose();
//    }

    private void openRents() {
        new Rents().setVisible(true);
        this.dispose();
    }

    private void logout() {
        JOptionPane.showMessageDialog(this, "You have been logged out.");
        System.exit(0);
    }

    // 🚀 Main method
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.themes.FlatMacDarkLaf");
        } catch (Exception ignored) {}
        java.awt.EventQueue.invokeLater(() -> new Returns().setVisible(true));
    }
}
