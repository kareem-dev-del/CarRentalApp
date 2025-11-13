package utils.UIHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 * StyleUtils: Contains reusable static methods for creating styled components.
 */
@SuppressWarnings("unused")
public class StyleUtils {

    // 1. ✅ دالة إنشاء رابط جانبي احترافي (Sidebar Link)
    public static Component createSidebarLink(String text, Runnable action, boolean isActive) {
        // نستخدم JLabel لتكون رابطًا (Link)
        JLabel linkLabel = new JLabel(text);

        // استخدام خط الشريط الجانبي (يفضل استخدامه هنا بدلًا من BUTTON_FONT)
        linkLabel.setFont(AppTheme.SIDEBAR_FONT);

        // الألوان والخلفية
        Color bgColor = isActive ? AppTheme.SIDEBAR_ACTIVE_BLUE : AppTheme.SIDEBAR_BG_DARK;
        Color fgColor = isActive ? AppTheme.SIDEBAR_TEXT_ACTIVE : AppTheme.SIDEBAR_TEXT_DEFAULT;
        Color hoverColor = AppTheme.SIDEBAR_HOVER_BG;

        linkLabel.setForeground(fgColor);
        linkLabel.setOpaque(true);
        linkLabel.setBackground(bgColor);
        linkLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        linkLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        linkLabel.setHorizontalAlignment(SwingConstants.LEFT);

        // Hover effect
        linkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!isActive) {
                    linkLabel.setBackground(hoverColor);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!isActive) {
                    linkLabel.setBackground(bgColor);
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (action != null) action.run();
            }
        });

        // لتنسيق BoxLayout بشكل أفضل
        linkLabel.setMaximumSize(new Dimension(220, 45));
        linkLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // مهم للمحاذاة في BaseFrame

        return linkLabel;
    }

    // 2. ✅ دالة تطبيق تنسيق موحد لحقول الإدخال (المستخدمة في Rents و Cars)
    // نعتمد على هذه الدالة بدلاً من createStyledTextField القديمة
    public static void applyTextFieldStyle(JTextField field) {
        field.setFont(AppTheme.FIELD_FONT);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppTheme.FIELD_BORDER_GRAY, 1, true),
                BorderFactory.createEmptyBorder(5, 12, 5, 12)
        ));
        field.setBackground(AppTheme.FIELD_BACKGROUND_WHITE);
    }

    // 3. ✅ دالة إنشاء لوحة التسمية والحقل (مستخدمة في Rents و Cars)
    public static JPanel createLabelFieldPanel(String labelText, JTextField field) {
        JPanel panel = new JPanel(new BorderLayout(0, 5));
        panel.setBackground(AppTheme.FIELD_BACKGROUND_WHITE);

        JLabel label = new JLabel(labelText);
        label.setFont(AppTheme.LABEL_FONT);
        // نستخدم لون نص داكن عام بدلاً من SIDEBAR_BG_DARK
        label.setForeground(AppTheme.TEXT_DARK);
        panel.add(label, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    // 4. ✅ دالة إنشاء زر الأكشن (مستخدمة في Rents و Cars)
    public static JButton createActionStyledButton(String text, Color base, Color hover) {
        JButton btn = new JButton(text);
        btn.setBackground(base);
        btn.setForeground(Color.WHITE);
        btn.setFont(AppTheme.BUTTON_FONT);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        btn.setRolloverEnabled(true);
        btn.setPreferredSize(new Dimension(150, 45)); // تحديد حجم ثابت للأزرار

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { btn.setBackground(hover); }
            @Override
            public void mouseExited(MouseEvent e) { btn.setBackground(base); }
        });
        return btn;
    }

    // ⛔ الدوال التالية محذوفة لأنها مكررة أو غير مستخدمة:
    // 1. createStyledTextField: تم دمج وظيفته في applyTextFieldStyle
    // 2. createNavItem: تم استبدالها بدالة createSidebarLink
    // 3. createSidebarButton: تم استبدالها بدالة createSidebarLink
}