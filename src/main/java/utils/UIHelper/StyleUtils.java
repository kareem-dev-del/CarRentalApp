package utils.UIHelper;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.Border;

/**
 * StyleUtils: Contains reusable static methods for creating styled components.
 */
@SuppressWarnings("unused")
public class StyleUtils {

    /**
     * Creates a standard styled JTextField with rounded border.
     */
    public static JTextField createStyledTextField() {
        JTextField f = new JTextField();
        f.setBackground(AppTheme.FIELD_BACKGROUND_WHITE);
        f.setForeground(new Color(40, 40, 40));
        f.setFont(AppTheme.FIELD_FONT);
        f.setPreferredSize(AppTheme.FIELD_DIMENSION);

        // Compound Border: Line Border (with rounding hint) + Padding
        Border lineBorder = BorderFactory.createLineBorder(AppTheme.FIELD_BORDER_GRAY, 1, true);
        Border padding = BorderFactory.createEmptyBorder(6, 10, 6, 10);
        f.setBorder(BorderFactory.createCompoundBorder(lineBorder, padding));
        return f;
    }

    /**
     * Creates a JPanel containing a label and a field stacked vertically.
     */
    public static JPanel createLabelFieldPanel(String labelText, JTextField field) {
        JPanel panel = new JPanel(new BorderLayout(0, 5));
        panel.setBackground(AppTheme.FIELD_BACKGROUND_WHITE);

        JLabel label = new JLabel(labelText);
        label.setFont(AppTheme.LABEL_FONT);
        label.setForeground(AppTheme.SIDEBAR_BG_DARK); // Using dark sidebar color for form labels

        panel.add(label, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    /**
     * Creates a fully styled JButton with hover effect.
     */
    public static JButton createActionStyledButton(String text, Color base, Color hover) {
        JButton btn = new JButton(text);
        btn.setBackground(base);
        btn.setForeground(Color.WHITE);
        btn.setFont(AppTheme.BUTTON_FONT);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Padding Border
        btn.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        btn.setRolloverEnabled(true);

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(hover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(base);
            }
        });
        return btn;
    }

    /**
     * Creates a styled sidebar navigation item with active/hover states.
     * @param text The item text.
     * @param isActive Boolean flag to determine the active state.
     */
    public static JPanel createNavItem(String text, boolean isActive) {
        Color bgColor = isActive ? AppTheme.SIDEBAR_ACTIVE_BLUE : AppTheme.SIDEBAR_BG_DARK;
        Color textColor = isActive ? AppTheme.SIDEBAR_TEXT_ACTIVE : AppTheme.SIDEBAR_TEXT_DEFAULT;
        Color hoverColor = AppTheme.SIDEBAR_HOVER_BG;

        JPanel item = new JPanel();
        item.setLayout(new BoxLayout(item, BoxLayout.X_AXIS));
        item.setBackground(bgColor);
        item.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
        item.setMaximumSize(new Dimension(220, 45)); // 👈 تحديد عرض العنصر

        if (isActive) {
            JPanel activeBar = new JPanel();
            activeBar.setPreferredSize(new Dimension(3, 0));
            activeBar.setMaximumSize(new Dimension(3, Integer.MAX_VALUE));
            activeBar.setBackground(AppTheme.ACCENT_GOLD_SUBTLE);
            item.add(activeBar);
            item.add(Box.createHorizontalStrut(10));
        } else {
            item.add(Box.createHorizontalStrut(15));
        }

        // حط النص داخل panel تاني بحيث hover ما يتمدش على المسافة الفاضية
        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        JLabel label = new JLabel(text);
        label.setFont(AppTheme.NAV_FONT);
        label.setForeground(textColor);
        textPanel.add(label);

        item.add(textPanel);

        // Hover محسّن
        item.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!isActive) {
                    item.setBackground(hoverColor);
                    item.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!isActive) item.setBackground(bgColor);
            }
        });

        return item;
    }



    /**
     * Creates a styled sidebar button (e.g., Logout) with hover effect.
     */
    public static JButton createSidebarButton(String text) {
        JButton btn = new JButton(text);
        Color fg = AppTheme.SIDEBAR_TEXT_ACTIVE;
        Color base = AppTheme.SIDEBAR_BG_DARK;
        Color hover = AppTheme.SIDEBAR_HOVER_BG;

        btn.setBackground(base);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setFont(AppTheme.BUTTON_FONT);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Compound border for professional look
        Border line = BorderFactory.createLineBorder(fg.darker(), 1, true);
        Border padding = BorderFactory.createEmptyBorder(10, 20, 10, 20);
        btn.setBorder(BorderFactory.createCompoundBorder(line, padding));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(hover);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(base);
            }
        });
        return btn;
    }

    public static void applyTextFieldStyle(JTextField field) {
        field.setFont(AppTheme.FIELD_FONT);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppTheme.FIELD_BORDER_GRAY, 1, true),
                BorderFactory.createEmptyBorder(5, 12, 5, 12)
        ));
        field.setBackground(AppTheme.FIELD_BACKGROUND_WHITE);
    }

}