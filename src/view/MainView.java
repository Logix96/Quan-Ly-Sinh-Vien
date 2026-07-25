package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnAdd, btnEdit, btnDelete, btnSearch;
    private JTextField txtSearch;

    public MainView() {
        setTitle("Phần Mềm Quản Lý Sinh Viên");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Cột của bảng
        String[] columns = {"Mã SV", "Họ Tên", "Ngày Sinh", "Giới Tính", "Chuyên Ngành", "Điểm TB"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        // Panel chứa công cụ tìm kiếm và các nút
        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtSearch = new JTextField(20);
        btnSearch = new JButton("Tìm kiếm");
        btnAdd = new JButton("Thêm mới");
        btnEdit = new JButton("Sửa");
        btnDelete = new JButton("Xóa");

        panelTop.add(new JLabel("Từ khóa: "));
        panelTop.add(txtSearch);
        panelTop.add(btnSearch);
        panelTop.add(btnAdd);
        panelTop.add(btnEdit);
        panelTop.add(btnDelete);

        add(panelTop, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    // Cung cấp getter để controller gọi đến
    public JTable getTable() { return table; }
    public DefaultTableModel getTableModel() { return tableModel; }
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnEdit() { return btnEdit; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnSearch() { return btnSearch; }
    public JTextField getTxtSearch() { return txtSearch; }
}