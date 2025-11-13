package utils.UIHelper;

import javax.swing.*;
import java.awt.*;

public class UIMessage {

    public static void showError(Component parent, String message) {
        showCustomDialog(parent, message, "Error", AppTheme.DANGER_RED);
    }

    public static void showInfo(Component parent, String message) {
        showCustomDialog(parent, message, "Information", AppTheme.PRIMARY_BLUE);
    }

    public static void showSuccess(Component parent, String message) {
        showCustomDialog(parent, message, "Success", new Color(46, 204, 113)); // أخضر جميل
    }

    private static void showCustomDialog(Component parent, String message, String title, Color color) {
        JLabel msgLabel = new JLabel(message, SwingConstants.CENTER);
        msgLabel.setFont(AppTheme.FIELD_FONT);
        msgLabel.setForeground(color);

        JOptionPane optionPane = new JOptionPane(msgLabel, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION);
        JDialog dialog = optionPane.createDialog(parent, title);

        dialog.getContentPane().setBackground(Color.WHITE);
        dialog.setModal(true);
        dialog.setSize(400, 150);
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
}
