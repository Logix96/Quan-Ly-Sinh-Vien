package view;

import repository.TaiKhoanRepository;
import model.TaiKhoan;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private TaiKhoanRepository repo = new TaiKhoanRepository();

    public LoginView() {
        setTitle("Đăng nhập Hệ thống");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel(" Tên đăng nhập:"));
        txtUsername = new JTextField();
        add(txtUsername);

        add(new JLabel(" Mật khẩu:"));
        txtPassword = new JPasswordField();
        add(txtPassword);

        btnLogin = new JButton("Đăng nhập");
        add(new JLabel(""));
        add(btnLogin);

        btnLogin.addActionListener(e -> {
            String user = txtUsername.getText();
            String pass = new String(txtPassword.getPassword());
            TaiKhoan tk = repo.kienTraDangNhap(user, pass);

            if (tk != null) {
                JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");
                dispose();

                MainView main = new MainView();

                repository.SinhVienRepository sinhVienRepo = new repository.SinhVienRepository();
                service.SinhVienService sinhVienService = new service.SinhVienService(sinhVienRepo);
                new controller.MainController(main, sinhVienService);

                main.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Sai tài khoản hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}