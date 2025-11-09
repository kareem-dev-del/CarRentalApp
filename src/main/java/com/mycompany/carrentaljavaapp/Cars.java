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

public class Cars extends BaseFrame {
    // Kareem
    private JTable jTable1;
    private JTextField regName, brand, model, price, searchField;
    private JButton addBtn, editBtn, deleteBtn, clearBtn;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;

    public Cars() {
        super("Vehicle Inventory", "Vehicles");
        buildCarsPage();
    }

    private void buildCarsPage() {
        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(AppTheme.BACKGROUND_LIGHT);

        JLabel title = new JLabel("Vehicle Inventory", SwingConstants.LEFT);
        title.setFont(AppTheme.HEADER_FONT);
        title.setForeground(AppTheme.SIDEBAR_BG_DARK);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        searchPanel.setBackground(AppTheme.BACKGROUND_LIGHT);

        // Search with hint text
        searchField = new HintTextField("Search...");
        StyleUtils.applyTextFieldStyle(searchField);
        searchField.setPreferredSize(new Dimension(250, 40));

        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setFont(AppTheme.LABEL_FONT);
        searchLabel.setForeground(AppTheme.SIDEBAR_BG_DARK);

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);

        headerPanel.add(title, BorderLayout.WEST);
        headerPanel.add(searchPanel, BorderLayout.EAST);

        // Table
        String[] columns = {"Registration No.", "Manufacturer", "Model", "Daily Price"};
        tableModel = new DefaultTableModel(columns, 0);
        jTable1 = new JTable(tableModel);
        jTable1.setRowHeight(35);
        jTable1.setFont(AppTheme.FIELD_FONT);
        jTable1.getTableHeader().setFont(AppTheme.BUTTON_FONT);
        jTable1.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(jTable1);
        scrollPane.setBorder(BorderFactory.createLineBorder(AppTheme.FIELD_BORDER_GRAY));

        sorter = new TableRowSorter<>(tableModel);
        jTable1.setRowSorter(sorter);
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filterTable(); }
            public void removeUpdate(DocumentEvent e) { filterTable(); }
            public void changedUpdate(DocumentEvent e) { filterTable(); }
        });

        // Form Fields with hint text
        regName = new HintTextField("Enter registration number");
        brand = new HintTextField("Enter manufacturer name");
        model = new HintTextField("Enter vehicle model");
        price = new HintTextField("Enter daily price ($)");

        // Apply same styling from StyleUtils
        StyleUtils.applyTextFieldStyle(regName);
        StyleUtils.applyTextFieldStyle(brand);
        StyleUtils.applyTextFieldStyle(model);
        StyleUtils.applyTextFieldStyle(price);

        JPanel bottomPanel = new JPanel(new BorderLayout(20, 20));
        bottomPanel.setBackground(AppTheme.FIELD_BACKGROUND_WHITE);
        bottomPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppTheme.FIELD_BORDER_GRAY),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JPanel inputGrid = new JPanel(new GridLayout(2, 4, 25, 15));
        inputGrid.setBackground(AppTheme.FIELD_BACKGROUND_WHITE);

        inputGrid.add(StyleUtils.createLabelFieldPanel("Registration No.", regName));
        inputGrid.add(StyleUtils.createLabelFieldPanel("Manufacturer", brand));
        inputGrid.add(StyleUtils.createLabelFieldPanel("Model", model));
        inputGrid.add(StyleUtils.createLabelFieldPanel("Daily Price ($)", price));

        bottomPanel.add(inputGrid, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        buttonsPanel.setBackground(AppTheme.FIELD_BACKGROUND_WHITE);

        addBtn = StyleUtils.createActionStyledButton("Add Vehicle", AppTheme.PRIMARY_BLUE, AppTheme.PRIMARY_BLUE.darker());
        editBtn = StyleUtils.createActionStyledButton("Update", AppTheme.PRIMARY_BLUE, AppTheme.PRIMARY_BLUE.darker());
        deleteBtn = StyleUtils.createActionStyledButton("Delete", AppTheme.DANGER_RED, AppTheme.DANGER_RED.darker());
        clearBtn = StyleUtils.createActionStyledButton("Clear Form", AppTheme.FIELD_BORDER_GRAY.darker(), AppTheme.FIELD_BORDER_GRAY.darker().darker());

        buttonsPanel.add(clearBtn);
        buttonsPanel.add(deleteBtn);
        buttonsPanel.add(editBtn);
        buttonsPanel.add(addBtn);
        bottomPanel.add(buttonsPanel, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> addCar());
        editBtn.addActionListener(e -> editCar());
        deleteBtn.addActionListener(e -> deleteCar());
        clearBtn.addActionListener(e -> clearFields());

        // Combine
        JPanel contentPanel = new JPanel(new BorderLayout(25, 25));
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPanel(contentPanel);
    }

    private void addCar() {
        if (regName.getText().isEmpty() || brand.getText().isEmpty() || model.getText().isEmpty() || price.getText().isEmpty()) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        tableModel.addRow(new Object[]{regName.getText(), brand.getText(), model.getText(), price.getText()});
        clearFields();
    }

    private void editCar() {
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a vehicle to update!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int modelRow = jTable1.convertRowIndexToModel(row);
        tableModel.setValueAt(regName.getText(), modelRow, 0);
        tableModel.setValueAt(brand.getText(), modelRow, 1);
        tableModel.setValueAt(model.getText(), modelRow, 2);
        tableModel.setValueAt(price.getText(), modelRow, 3);
        clearFields();
    }

    private void deleteCar() {
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a vehicle to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int modelRow = jTable1.convertRowIndexToModel(row);
        tableModel.removeRow(modelRow);
        clearFields();
    }

    private void clearFields() {
        regName.setText("");
        brand.setText("");
        model.setText("");
        price.setText("");
        jTable1.clearSelection();
    }

    private void filterTable() {
        String query = searchField.getText().trim();
        sorter.setRowFilter(query.isEmpty() ? null : RowFilter.regexFilter("(?i)" + query));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Cars().setVisible(true));
    }
}