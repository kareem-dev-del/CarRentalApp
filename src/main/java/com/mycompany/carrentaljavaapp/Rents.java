package com.mycompany.carrentaljavaapp;

import utils.UIHelper.AppTheme;
import utils.UIHelper.StyleUtils;
import utils.UIHelper.BaseFrame;
import utils.UIHelper.HintTextField;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import java.awt.*;
import java.util.Objects;

public class Rents extends BaseFrame {

    private static final Color RENT_ACCENT_COLOR = new Color(52, 152, 219); // أزرق سماوي زي Primary Blue

    // private static final Color RENT_ACCENT_COLOR = ;
    private JTable rentsTable;
    // الحقول المطلوبة لصفحة الإيجارات
    private JTextField carRegNo, customerName, rentDate, returnDate, totalPaid, searchField;
    private JButton addBtn, editBtn, deleteBtn, clearBtn;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;

    // 💡 لون Accent جديد لصفحة الإيجارات (أخضر النجاح)
    //private static final Color RENT_ACCENT_COLOR = AppTheme.SUCCESS_GREEN.darker();

    public Rents() {
        super("Rental Transactions", "Rentals");
        buildRentsPage();
    }

    private void buildRentsPage() {
        // --- 1. Header and Search Panel ---
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(AppTheme.BACKGROUND_LIGHT);

        JLabel title = new JLabel("Rental Transactions", SwingConstants.LEFT);
        title.setFont(AppTheme.HEADER_FONT);
        // تغيير لون العنوان لتمييزه قليلاً
        title.setForeground(RENT_ACCENT_COLOR.darker());

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        searchPanel.setBackground(AppTheme.BACKGROUND_LIGHT);

        searchField = new HintTextField("Search by Registration No. or Customer...");
        StyleUtils.applyTextFieldStyle(searchField);
        searchField.setPreferredSize(new Dimension(300, 40));

        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setFont(AppTheme.LABEL_FONT);
        searchLabel.setForeground(AppTheme.SIDEBAR_BG_DARK);

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);

        headerPanel.add(title, BorderLayout.WEST);
        headerPanel.add(searchPanel, BorderLayout.EAST);

        // --- 2. Table and Table Setup ---
        String[] columns = {"ID", "Car Reg. No.", "Customer Name", "Rent Date", "Return Date", "Total Paid"};
        tableModel = new DefaultTableModel(columns, 0);

        // إضافة بيانات وهمية للاختبار//
        tableModel.addRow(new Object[]{101, "A123BCD", "Karim Ebrahim", "10/11/2025", "20/11/2025", "500$"});
        tableModel.addRow(new Object[]{102, "X987YZA", "Aya Mohamed", "01/11/2025", "05/11/2025", "200$"});


        rentsTable = new JTable(tableModel);
        rentsTable.setRowHeight(35);
        rentsTable.setFont(AppTheme.FIELD_FONT);
        rentsTable.getTableHeader().setFont(AppTheme.BUTTON_FONT.deriveFont(Font.BOLD, 14f));
        rentsTable.getTableHeader().setReorderingAllowed(false);

        // 🌟 تنسيق خاص لرأس الجدول (Header)



        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < rentsTable.getColumnCount(); i++) {
            rentsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(rentsTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(AppTheme.FIELD_BORDER_GRAY));

        // إعداد الفرز والبحث
        sorter = new TableRowSorter<>(tableModel);
        rentsTable.setRowSorter(sorter);
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filterTable(); }
            public void removeUpdate(DocumentEvent e) { filterTable(); }
            public void changedUpdate(DocumentEvent e) { filterTable(); }
        });

        // --- 3. Form Fields (Bottom Panel) ---
        carRegNo = new HintTextField("Enter Car Registration No.");
        customerName = new HintTextField("Enter Customer Name");
        rentDate = new HintTextField("DD/MM/YYYY");
        returnDate = new HintTextField("DD/MM/YYYY");
        totalPaid = new HintTextField("Total Amount Paid ($)");

        StyleUtils.applyTextFieldStyle(carRegNo);
        StyleUtils.applyTextFieldStyle(customerName);
        StyleUtils.applyTextFieldStyle(rentDate);
        StyleUtils.applyTextFieldStyle(returnDate);
        StyleUtils.applyTextFieldStyle(totalPaid);

        JPanel bottomPanel = new JPanel(new BorderLayout(20, 20));
        bottomPanel.setBackground(AppTheme.FIELD_BACKGROUND_WHITE);
        bottomPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppTheme.FIELD_BORDER_GRAY),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // شبكة إدخال الحقول
        JPanel inputGrid = new JPanel(new GridLayout(2, 3, 25, 15));
        inputGrid.setBackground(AppTheme.FIELD_BACKGROUND_WHITE);

        inputGrid.add(StyleUtils.createLabelFieldPanel("Car Registration No.", carRegNo));
        inputGrid.add(StyleUtils.createLabelFieldPanel("Customer Name", customerName));
        inputGrid.add(StyleUtils.createLabelFieldPanel("Rent Date", rentDate));
        inputGrid.add(StyleUtils.createLabelFieldPanel("Return Date", returnDate));
        inputGrid.add(StyleUtils.createLabelFieldPanel("Total Paid ($)", totalPaid));
        inputGrid.add(Box.createGlue());

        bottomPanel.add(inputGrid, BorderLayout.CENTER);

        // --- 4. Action Buttons (مع أيقونات وألوان مختلفة) ---
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        buttonsPanel.setBackground(AppTheme.FIELD_BACKGROUND_WHITE);

        // 🌟 إضافة أيقونات وتغيير لون الإضافة إلى الأخضر
        addBtn = StyleUtils.createActionStyledButton(" Add Rental", RENT_ACCENT_COLOR, RENT_ACCENT_COLOR.darker());
        addBtn.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/plus_icon.png")))); // يجب أن يكون لديك هذا المسار

        editBtn = StyleUtils.createActionStyledButton(" Update", AppTheme.PRIMARY_BLUE.darker(), AppTheme.PRIMARY_BLUE.darker().darker());
        editBtn.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/edit_icon.png"))));

        deleteBtn = StyleUtils.createActionStyledButton(" Delete", AppTheme.DANGER_RED, AppTheme.DANGER_RED.darker());
        deleteBtn.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/delete_icon.png"))));

        clearBtn = StyleUtils.createActionStyledButton(" Clear Form", AppTheme.FIELD_BORDER_GRAY.darker(), AppTheme.FIELD_BORDER_GRAY.darker().darker());
        clearBtn.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/clear_icon.png"))));

        buttonsPanel.add(clearBtn);
        buttonsPanel.add(deleteBtn);
        buttonsPanel.add(editBtn);
        buttonsPanel.add(addBtn);
        bottomPanel.add(buttonsPanel, BorderLayout.SOUTH);

        // --- 5. Add Action Listeners & Table Selection ---
        addBtn.addActionListener(e -> addRent());
        editBtn.addActionListener(e -> editRent());
        deleteBtn.addActionListener(e -> deleteRent());
        clearBtn.addActionListener(e -> clearFields());

        // 🌟 تعبئة الحقول تلقائياً عند اختيار صف
        setupTableSelectionListener();

        // --- 6. Final Layout ---
        JPanel contentPanel = new JPanel(new BorderLayout(25, 25));
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPanel(contentPanel);
    }

    // 🌟 ميزة جديدة: تعبئة الحقول تلقائياً للتعديل/الحذف
    private void setupTableSelectionListener() {
        rentsTable.getSelectionModel().addListSelectionListener(e -> {
            // التحقق للتأكد من أن الحدث ليس مؤقتاً أو فارغاً
            if (!e.getValueIsAdjusting() && rentsTable.getSelectedRow() != -1) {
                int selectedRow = rentsTable.getSelectedRow();
                int modelRow = rentsTable.convertRowIndexToModel(selectedRow);

                // تعبئة الحقول من قيم الجدول (باستثناء ID)
                carRegNo.setText(rentsTable.getModel().getValueAt(modelRow, 1).toString());
                customerName.setText(rentsTable.getModel().getValueAt(modelRow, 2).toString());
                rentDate.setText(rentsTable.getModel().getValueAt(modelRow, 3).toString());
                returnDate.setText(rentsTable.getModel().getValueAt(modelRow, 4).toString());
                totalPaid.setText(rentsTable.getModel().getValueAt(modelRow, 5).toString());
            }
        });
    }


    // --- Action Methods ---

    private int generateRentID() {
        // نضمن أن يكون الـ ID فريداً وأعلى من جميع الـ IDs الموجودة
        int maxId = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            try {
                int currentId = Integer.parseInt(tableModel.getValueAt(i, 0).toString());
                if (currentId > maxId) {
                    maxId = currentId;
                }
            } catch (NumberFormatException ignored) {}
        }
        return maxId + 1;
    }

    private void addRent() {
        if (carRegNo.getText().isEmpty() || customerName.getText().isEmpty() || rentDate.getText().isEmpty() || returnDate.getText().isEmpty() || totalPaid.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all rental details!", "Missing Fields", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int rentID = generateRentID();
        tableModel.addRow(new Object[]{
                rentID,
                carRegNo.getText(),
                customerName.getText(),
                rentDate.getText(),
                returnDate.getText(),
                totalPaid.getText()
        });
        clearFields();
        JOptionPane.showMessageDialog(this, "Rental ID " + rentID + " added successfully! 🎉", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void editRent() {
        int row = rentsTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a rental transaction to update!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int modelRow = rentsTable.convertRowIndexToModel(row);

        if (carRegNo.getText().isEmpty() || customerName.getText().isEmpty() || rentDate.getText().isEmpty() || returnDate.getText().isEmpty() || totalPaid.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all rental details!", "Missing Fields", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // تحديث قيم الصف المختار (ID هو العمود 0، لا يتم تحديثه يدوياً)
        tableModel.setValueAt(carRegNo.getText(), modelRow, 1);
        tableModel.setValueAt(customerName.getText(), modelRow, 2);
        tableModel.setValueAt(rentDate.getText(), modelRow, 3);
        tableModel.setValueAt(returnDate.getText(), modelRow, 4);
        tableModel.setValueAt(totalPaid.getText(), modelRow, 5);
        clearFields();
        JOptionPane.showMessageDialog(this, "Rental updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteRent() {
        int row = rentsTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a rental transaction to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // تأكيد الحذف
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this rental transaction?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            int modelRow = rentsTable.convertRowIndexToModel(row);
            tableModel.removeRow(modelRow);
            clearFields();
            JOptionPane.showMessageDialog(this, "Rental transaction deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void clearFields() {
        carRegNo.setText("");
        customerName.setText("");
        rentDate.setText("");
        returnDate.setText("");
        totalPaid.setText("");
        rentsTable.clearSelection();
    }

    private void filterTable() {
        String query = searchField.getText().trim();
        sorter.setRowFilter(query.isEmpty() ? null : RowFilter.regexFilter("(?i)" + query));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Rents().setVisible(true));
    }
}