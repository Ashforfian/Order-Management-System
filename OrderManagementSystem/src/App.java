import business.UserController;
import core.Database;
import core.Helper;
import entity.User;
import view.DashboardUI;
import view.LoginUI;

import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        LoginUI login = new LoginUI();
        Helper.setTheme();

//        UserController userController = new UserController();
//        User user = userController.findByLogin("yusufkerem2607@gmail.com", "123123");
//        DashboardUI dashboardUI = new DashboardUI(user);
    }
}
