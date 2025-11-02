package com.mycompany.carrentaljavaapp;

import com.formdev.flatlaf.FlatDarculaLaf;
import utils.UIHelper.BaseFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.net.URL;

public class Login extends BaseFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel titleLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton createAccountButton;
    private JButton forgotPasswordButton;
    private JPanel contentPanel;

    private Image backgroundImage;

    // الألوان المُحسنة:
    private static final Color ACCENT_COLOR = new Color(0, 150, 255); // الأزرق الأساسي للزر
    // تم تغيير هذا اللون ليتناسب مع LINK_COLOR للمظهر المتوهج
    private static final Color TITLE_COLOR = new Color(77, 195, 255);
    private static final Color FIELD_BG = new Color(65, 68, 70); // خلفية أغمق قليلاً للحقول
    private static final Color LINK_COLOR = new Color(77, 195, 255); // اللون السماوي الفاتح

    // دالة تحميل الصورة
    private void loadBackgroundImage() {
        try {
            URL imageUrl = getClass().getResource("/resources/car_background.jpg");
            if (imageUrl == null) {
                imageUrl = getClass().getClassLoader().getResource("car_background.jpg");
            }

            if (imageUrl != null) {
                backgroundImage = new ImageIcon(imageUrl).getImage();
            } else {
                System.err.println("❌ ERROR: Background image not found. Using solid dark color.");
            }
        } catch (Exception e) {
            System.err.println("⚠ WARNING: Exception during background image loading: " + e.getMessage());
        }
    }


    public Login() {
        super("Car Rental Solutions","- Login");
        setTitle("Car Rental Solutions - Login");
        setSize(450, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        loadBackgroundImage();
        setLayout(new BorderLayout());

        // ** 1. لوحة الخلفية (تحمل صورة السيارة) **
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } else {
                    setBackground(new Color(45, 45, 45));
                }
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());
        add(backgroundPanel, BorderLayout.CENTER);

        // ** 2. لوحة المحتوى (تأثير الزجاج الداكن) **
        contentPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // رسم خلفية شبه شفافة مع زوايا مستديرة
                g2.setColor(new Color(0, 0, 0, 160));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        contentPanel.setLayout(null);
        contentPanel.setOpaque(false);
        contentPanel.setPreferredSize(new Dimension(350, 490));

        // ** إضافة حدود خفيفة لتعزيز تأثير الإطار **
        contentPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 30)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(contentPanel, gbc);


        // العنوان "CAR RENTAL SOLUTIONS" - تم تصحيح الخطأ هنا
        titleLabel = new JLabel("CAR RENTAL SOLUTIONS");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 25)); // تم إزالة علامات النجمة
        titleLabel.setForeground(TITLE_COLOR); // تم إزالة علامات النجمة
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(0, 30, 350, 40);
        contentPanel.add(titleLabel);

        // أيقونة السيارة
        try {
            ImageIcon carIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/car_icon.png")));
            Image scaledImage = carIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            JLabel iconLabel = new JLabel(new ImageIcon(scaledImage));
            iconLabel.setBounds(135, 70, 80, 80);
            contentPanel.add(iconLabel);
        } catch (Exception e) {
            System.err.println("Could not load car icon: " + e.getMessage());
        }

        // اسم المستخدم والحقول
        usernameLabel = new JLabel("USERNAME:");
        usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setBounds(50, 175, 150, 25);
        contentPanel.add(usernameLabel);

        usernameField = new JTextField(20);
        usernameField.putClientProperty("JComponent.roundRect", true);
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        usernameField.setBounds(50, 205, 250, 45);
        usernameField.setBackground(FIELD_BG);
        usernameField.setForeground(Color.WHITE);
        usernameField.setCaretColor(Color.WHITE);
        contentPanel.add(usernameField);

        // كلمة المرور
        passwordLabel = new JLabel("PASSWORD:");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(50, 270, 150, 25);
        contentPanel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.putClientProperty("JComponent.roundRect", true);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passwordField.setBounds(50, 300, 250, 45);
        passwordField.setBackground(FIELD_BG);
        passwordField.setForeground(Color.WHITE);
        passwordField.setCaretColor(Color.WHITE);
        contentPanel.add(passwordField);

        // زر اللوجن
        loginButton = new JButton("LOGIN");
        loginButton.putClientProperty("JButton.buttonType", "roundRect");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        loginButton.setBackground(ACCENT_COLOR);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBounds(50, 380, 250, 55);
        loginButton.setFocusable(false);
        contentPanel.add(loginButton);

        // الأزرار السفلية
        forgotPasswordButton = new JButton("Forgot Password?");
        forgotPasswordButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        forgotPasswordButton.setForeground(LINK_COLOR);
        forgotPasswordButton.setBorderPainted(false);
        forgotPasswordButton.setContentAreaFilled(false);
        forgotPasswordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotPasswordButton.setBounds(20, 450, 150, 20);
        contentPanel.add(forgotPasswordButton);

        createAccountButton = new JButton("Create New Account");
        createAccountButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        createAccountButton.setForeground(LINK_COLOR);
        createAccountButton.setBorderPainted(false);
        createAccountButton.setContentAreaFilled(false);
        createAccountButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createAccountButton.setBounds(175, 450, 150, 20);
        contentPanel.add(createAccountButton);

        // إضافة Action Listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals("kareem") && password.equals("12345")) {
                    JOptionPane.showMessageDialog(Login.this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    new Cars().setVisible(true);
                    new Login().dispose();
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Invalid Username or Password", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }


        });

        forgotPasswordButton.addActionListener(e -> JOptionPane.showMessageDialog(Login.this, "Redirecting to Forgot Password page...", "Info", JOptionPane.INFORMATION_MESSAGE));
        createAccountButton.addActionListener(e -> JOptionPane.showMessageDialog(Login.this, "Redirecting to Create New Account page...", "Info", JOptionPane.INFORMATION_MESSAGE));
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
            UIManager.put("TextComponent.arc", 15);
            UIManager.put("Button.arc", 15);
        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Failed to initialize FlatLaf: " + ex);
        }

        SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    }
}