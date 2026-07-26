package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainView extends JFrame {
    // component tab sinh viên
    private JTable tblSinhVien;
    private DefaultTableModel modelSinhVien;
    private JButton btnAddSV, btnEditSV, btnDeleteSV, btnSearchSV;
    private JTextField txtSearchSV;

    // Component tab học phí
    private JTable tblHocPhi;
    private DefaultTableModel modelHocPhi;
    private JButton btnAddHP, btnPayHP, btnSearchHP;
    private JTextField txtSearchHP;

    // Component tab ktx
    private JTable tblKtx;
    private DefaultTableModel modelKtx;
    private JButton btnAddRoom, btnAssignRoom;

    public MainView() {
        setTitle("Hệ Thống Quản Lý Sinh Viên");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo JTabbedPane (thanh tab)
        JTabbedPane tabbedPane = new JTabbedPane();

        // thêm tab vào hệ thống
        tabbedPane.addTab("Quản lý Sinh viên", createTabSinhVien());
        tabbedPane.addTab("Quản lý Học phí", createTabHocPhi());
        tabbedPane.addTab("Quản lý Ký túc xá", createTabKTX());

        add(tabbedPane);
    }

    // Sinh viên tab
    private JPanel createTabSinhVien() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] columns = {"Mã SV", "Họ Tên", "Ngày Sinh", "Giới Tính", "Chuyên Ngành", "Điểm TB"};

        modelSinhVien = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        tblSinhVien = new JTable(modelSinhVien);

        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtSearchSV = new JTextField(20);
        btnSearchSV = new JButton("Tìm kiếm");
        btnAddSV = new JButton("Thêm mới");
        btnEditSV = new JButton("Sửa");
        btnDeleteSV = new JButton("Xóa");

        panelTop.add(new JLabel("Từ khóa: "));
        panelTop.add(txtSearchSV);
        panelTop.add(btnSearchSV);
        panelTop.add(btnAddSV);
        panelTop.add(btnEditSV);
        panelTop.add(btnDeleteSV);

        panel.add(panelTop, BorderLayout.NORTH);
        panel.add(new JScrollPane(tblSinhVien), BorderLayout.CENTER);
        return panel;
    }

    // Học phí tab
    private JPanel createTabHocPhi() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] columns = {"Mã Hóa Đơn", "Mã SV", "Kỳ Học", "Số Tiền", "Trạng Thái"};

        modelHocPhi = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        tblHocPhi = new JTable(modelHocPhi);

        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtSearchHP = new JTextField(15);
        btnSearchHP = new JButton("Tìm theo Mã SV");
        btnAddHP = new JButton("Tạo Hóa Đơn Mới");
        btnPayHP = new JButton("Xác nhận Đã Thu Tiền");

        panelTop.add(new JLabel("Mã SV: "));
        panelTop.add(txtSearchHP);
        panelTop.add(btnSearchHP);
        panelTop.add(btnAddHP);
        panelTop.add(btnPayHP);

        panel.add(panelTop, BorderLayout.NORTH);
        panel.add(new JScrollPane(tblHocPhi), BorderLayout.CENTER);
        return panel;
    }

    // Ký túc xá tab
    private JPanel createTabKTX() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] columns = {"Mã Phòng", "Loại Phòng", "Sức Chứa", "Đang Ở"};

        modelKtx = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        tblKtx = new JTable(modelKtx);

        tblKtx.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int row = tblKtx.getSelectedRow();
                    if (row >= 0) {
                        String maPhong = (String) tblKtx.getValueAt(row, 0);
                        new DanhSachSinhVienKtxDialog(MainView.this, maPhong).setVisible(true);
                    }
                }
            }
        });

        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnAddRoom = new JButton("Thêm Phòng Mới");
        btnAssignRoom = new JButton("Xếp Sinh Viên Vào Phòng");

        panelTop.add(btnAddRoom);
        panelTop.add(btnAssignRoom);

        panel.add(panelTop, BorderLayout.NORTH);
        panel.add(new JScrollPane(tblKtx), BorderLayout.CENTER);
        return panel;
    }

    // Getter bên tab sinh viên
    public JTable getTable() { return tblSinhVien; }
    public DefaultTableModel getTableModel() { return modelSinhVien; }
    public JButton getBtnAdd() { return btnAddSV; }
    public JButton getBtnEdit() { return btnEditSV; }
    public JButton getBtnDelete() { return btnDeleteSV; }
    public JButton getBtnSearch() { return btnSearchSV; }
    public JTextField getTxtSearch() { return txtSearchSV; }

    // Getter tab học phí + ktx
    public DefaultTableModel getModelHocPhi() { return modelHocPhi; }
    public DefaultTableModel getModelKtx() { return modelKtx; }

    public JButton getBtnPayHP() { return btnPayHP; }
    public JButton getBtnAssignRoom() { return btnAssignRoom; }
    public JTable getTblHocPhi() { return tblHocPhi; }
    public JTable getTblKtx() { return tblKtx; }

    public JButton getBtnSearchHP() { return btnSearchHP; }
    public JTextField getTxtSearchHP() { return txtSearchHP; }
    public JButton getBtnAddHP() { return btnAddHP; }
    public JButton getBtnAddRoom() { return btnAddRoom; }
}