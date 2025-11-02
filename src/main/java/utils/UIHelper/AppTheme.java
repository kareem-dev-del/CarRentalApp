package utils.UIHelper;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

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
    // إضافة لون ذهبي أهدأ لاستخدامه مع الشريط الرفيع
    public static final Color ACCENT_GOLD_SUBTLE = new Color(255, 205, 90); // ألطف من 255,193,7

    // --- Fonts ---
    public static final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 28);
    public static final Font NAV_FONT = new Font("Segoe UI", Font.BOLD, 16);
    public static final Font LABEL_FONT = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font FIELD_FONT = new Font("Segoe UI", Font.PLAIN, 15);
    public static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 15);

    // --- Dimensions ---
    public static final Dimension FIELD_DIMENSION = new Dimension(180, 40);
    public static final int SIDEBAR_WIDTH = 250;
    public static final int WINDOW_WIDTH = 1100;
    public static final int WINDOW_HEIGHT = 720;
}