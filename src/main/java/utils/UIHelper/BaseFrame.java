package utils.UIHelper;

import com.formdev.flatlaf.FlatIntelliJLaf;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public abstract class BaseFrame extends JFrame {

    protected JPanel sidebar;
    protected JPanel mainPanel;
    protected JButton logoutBtn;
    protected JLabel logo;

    public BaseFrame(String title, String activePage) {
        // إعداد الشكل العام
        FlatIntelliJLaf.setup();
        setTitle("Car Rental System - " + title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(AppTheme.WINDOW_WIDTH, AppTheme.WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(true);

        // 🟡 إضافة أيقونة للنافذة
        try {
            Image icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png"))).getImage();
            setIconImage(icon);
        } catch (Exception e) {
            System.err.println("⚠ Icon not found: " + e.getMessage());
        }

        // استدعاء دوال بناء العناصر المشتركة
        buildSidebar(activePage);
        buildMainPanel();

        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
    }

    /** إنشاء الـSidebar المشترك **/
    private void buildSidebar(String activePage) {
        sidebar = new JPanel();
        sidebar.setBackground(AppTheme.SIDEBAR_BG_DARK);
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(AppTheme.SIDEBAR_WIDTH, 0));
        sidebar.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));

        // Logo
        logo = new JLabel("Car Rentals", SwingConstants.CENTER);
        logo.setFont(AppTheme.HEADER_FONT.deriveFont(26f));
        logo.setForeground(AppTheme.SIDEBAR_TEXT_ACTIVE);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
        sidebar.add(logo);

        // Navigation Items
        JPanel carsNavItem = StyleUtils.createNavItem("Vehicles", activePage.equals("Vehicles"));
        JPanel rentalsNavItem = StyleUtils.createNavItem("Rentals", activePage.equals("Rentals"));
        JPanel customersNavItem = StyleUtils.createNavItem("Customers", activePage.equals("Customers"));

        carsNavItem.setAlignmentX(Component.CENTER_ALIGNMENT);
        rentalsNavItem.setAlignmentX(Component.CENTER_ALIGNMENT);
        customersNavItem.setAlignmentX(Component.CENTER_ALIGNMENT);

        sidebar.add(carsNavItem);
        sidebar.add(rentalsNavItem);
        sidebar.add(customersNavItem);

        sidebar.add(Box.createVerticalGlue());

        // Logout Button
        logoutBtn = StyleUtils.createSidebarButton("Logout");
        logoutBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this, "Are you sure you want to logout?", "Confirm Logout",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE
            );
            if (confirm == JOptionPane.YES_OPTION) dispose();
        });

        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(logoutBtn);
        sidebar.add(Box.createVerticalStrut(30));
    }

    /** إنشاء البانل الرئيسي الفارغ ليتم ملؤه من الصفحات الأبناء **/
    private void buildMainPanel() {
        mainPanel = new JPanel(new BorderLayout(25, 25));
        mainPanel.setBackground(AppTheme.BACKGROUND_LIGHT);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
    }

    /**
     * دالة يقدر الكلاس الابن يستدعيها
     * لإضافة المحتوى الخاص به داخل الـmainPanel
     */
    protected void setContentPanel(JPanel content) {
        mainPanel.add(content, BorderLayout.CENTER);
    }
}