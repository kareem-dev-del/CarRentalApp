package utils.UIHelper;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.border.Border; // يجب استيرادها إذا كنت تستخدمها
import javax.swing.BorderFactory; // يجب استيرادها إذا كنت تستخدمها

/**
 * AppTheme: Contains all application colors, fonts, and dimensions
 * as static final constants for easy access and modification.
 */
@SuppressWarnings("unused")
public class AppTheme {

    // --- Global Colors ---
    public static final Color PRIMARY_BLUE = new Color(0, 102, 204);
    public static final Color BACKGROUND_LIGHT = new Color(248, 249, 250);
    public static final Color DANGER_RED = new Color(220, 53, 69);

    // 💡 الألوان المضافة حديثاً لحل الأخطاء وتماشيًا مع التنسيقات الجديدة:
    public static final Color SUCCESS_GREEN = new Color(40, 167, 69);       // مضاف لصفحة Rents
    public static final Color TEXT_DARK = new Color(50, 50, 50);            // لون نص داكن عام
    public static final Color TEXT_LIGHT = Color.WHITE;                     // لون نص فاتح عام
    public static final Color BACKGROUND_DARK = new Color(30, 30, 30);      // لون خلفية داكن عام

    // --- Field and Border Colors ---
    public static final Color FIELD_BORDER_GRAY = new Color(200, 204, 209);
    public static final Color FIELD_BACKGROUND_WHITE = Color.WHITE;
    public static final Color HOVER_LIGHT_BLUE = new Color(220, 235, 255);

    // --- Sidebar Colors ---
    public static final Color SIDEBAR_BG_DARK = new Color(24, 28, 36);
    public static final Color SIDEBAR_ACTIVE_BLUE = new Color(0, 80, 160);
    public static final Color SIDEBAR_HOVER_BG = new Color(40, 48, 60);
    public static final Color SIDEBAR_TEXT_DEFAULT = new Color(180, 190, 200);
    public static final Color SIDEBAR_TEXT_ACTIVE = Color.WHITE;
    public static final Color ACCENT_GOLD = new Color(255, 193, 7);
    public static final Color ACCENT_GOLD_SUBTLE = new Color(255, 205, 90);

    // --- Fonts ---
    public static final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 28);
    public static final Font NAV_FONT = new Font("Segoe UI", Font.BOLD, 16);
    public static final Font LABEL_FONT = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font FIELD_FONT = new Font("Segoe UI", Font.PLAIN, 15);
    public static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 15);

    // 💡 الخطوط المضافة حديثاً لحل الأخطاء
    public static final Font BASE_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font SIDEBAR_FONT = new Font("Segoe UI", Font.BOLD, 18);

    // --- Dimensions ---
    public static final Dimension FIELD_DIMENSION = new Dimension(180, 40);
    public static final int SIDEBAR_WIDTH = 250;
    public static final int WINDOW_WIDTH = 1100;
    public static final int WINDOW_HEIGHT = 720;

    // --- الألوان والخطوط التي كانت مضافة في نهاية الكود السابق لديك:
    public static final Color PRIMARY_RED = new Color(220, 53, 69);
    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 14);

    // --- Borders & Styles (مضاف لحل الأخطاء إن وجدت) ---
    public static final Border ROUNDED_BORDER = BorderFactory.createLineBorder(FIELD_BORDER_GRAY, 1, true);

}