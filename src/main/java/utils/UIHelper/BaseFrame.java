package utils.UIHelper;

import com.formdev.flatlaf.FlatIntelliJLaf;
//import com.mycompany.carrentaljavaapp.Cars;
import com.mycompany.carrentaljavaapp.Login;
import com.mycompany.carrentaljavaapp.Rents;

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
            // ملاحظة: تم تغيير مسار الصورة بناءً على مثالك، يجب التأكد من وجودها
            Image icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/logo.png"))).getImage();
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
        logo = new JLabel("RENT MASTER", SwingConstants.CENTER); // تغيير الاسم ليكون أوضح
        logo.setFont(AppTheme.HEADER_FONT.deriveFont(26f));
        logo.setForeground(AppTheme.ACCENT_GOLD_SUBTLE); // استخدام لون accent مناسب
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
        sidebar.add(logo);

        // --- Navigation Links (باستخدام الدالة الجديدة) ---

        // 1. Vehicles/Cars Link
        sidebar.add(StyleUtils.createSidebarLink("Vehicles 🚗", () -> {
           // new Cars().setVisible(true);
            this.dispose();
        }, activePage.equals("Vehicles")));
        sidebar.add(Box.createVerticalStrut(5));

        // 2. Rentals Link
        sidebar.add(StyleUtils.createSidebarLink("Rentals 📝", () -> {
            new Rents().setVisible(true);
            this.dispose();
        }, activePage.equals("Rentals")));
        sidebar.add(Box.createVerticalStrut(5));

        // 3. Customers Link (يفترض وجود صفحة Customers)
        sidebar.add(StyleUtils.createSidebarLink("Customers 🧑‍🤝‍🧑", () -> {
            // new Customers().setVisible(true); // إذا كانت موجودة
            JOptionPane.showMessageDialog(this, "Customers page not implemented yet!", "Info", JOptionPane.INFORMATION_MESSAGE);
        }, activePage.equals("Customers")));
        sidebar.add(Box.createVerticalStrut(5));


        sidebar.add(Box.createVerticalGlue());

        // --- Logout Link (باستخدام نفس دالة الروابط) ---

        // يجب أن تكون الدالة التي تدعم الـ action هي المستخدمة
        Component logoutLink = StyleUtils.createSidebarLink("Logout 🚪", () -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this, "Are you sure you want to logout?", "Confirm Logout",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE
            );
            if (confirm == JOptionPane.YES_OPTION) {
                new Login().setVisible(true);
                this.dispose();
            }
        }, false); // دائماً false لعدم تفعيل لون النشط

        // نغير طريقة إضافة زر الـ Logout ليتناسب مع تصميم الروابط الجديدة
        logoutLink.getAlignmentX(); // تأكد من المحاذاة

        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(logoutLink);
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