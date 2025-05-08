package view;

import business.UserController;
import core.Helper;
import dao.UserDao;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI extends JFrame {
    private JPanel container;
    private JPanel pnl_top;
    private JPanel pnl_bottom;
    private JTextField fld_mail;
    private JButton btn_login;
    private JLabel lbl_mail;
    private JLabel lbl_password;
    private JPasswordField fld_password;
    private UserController userController;

    public LoginUI(){
        this.userController = new UserController();
        this.add(container);
        this.setSize(400,400);
        this.setTitle("Müşteri Yönetim Sistemi");


        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;

        this.setLocation(x, y);
        this.setVisible(true);


        this.btn_login.addActionListener(e -> {
            JTextField[] checkList = new JTextField[]{fld_mail, fld_password};
            if (!Helper.isMailValid(fld_mail.getText())){
                Helper.showMessage("Geçerli bir e-posta değeri giriniz!");
            }
            else if (Helper.isFieldListEmpty(checkList)){
                Helper.showMessage("fill");
            } else{
                User user = this.userController.findByLogin(this.fld_mail.getText(), this.fld_password.getText());

                if (user.getName() == null){
                    Helper.showMessage("Girdiğiniz bilgilere göre kullanıcı bulunamadı!");

                }else {
                    this.dispose();
                    DashboardUI dashboardUI = new DashboardUI(user);
                }
            }
        });
    }
}
