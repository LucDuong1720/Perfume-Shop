package view;

import model.User;
import model.Role;
import service.IUserService;
import service.UserService;
import utils.CheckUtils;

import java.util.Scanner;

public class LoginView {
    private final static Scanner scanner = new Scanner(System.in);
    private IUserService userService;

    public static User user = new User();

    public LoginView() {
        userService = UserService.getInstance();
    }

    public void login(Role role) {
        int choice = -1;
        do {
            menuLogin();
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 :
                        loginProgram(role);
                        break;
                    case 0 :
                        System.out.println("Đang thoát chương trình...");
                        System.exit(0);
                        break;
                    default :
                        System.out.println("Lựa chọn sai. Vui lòng nhập lại!");
                        break;
                    }
            } catch (Exception exception) {
                System.out.println("Sai cú pháp. Vui lòng nhập lại!");
            }
        } while (choice != 0);
    }

    private void loginProgram(Role role) {
        String userName, passWord;
        System.out.println("═════════ ĐĂNG NHẬP ═════════");
        System.out.print("Tài khoản: ");
        userName = CheckUtils.stringEmpty();
        System.out.print("Mật khẩu:  ");
        passWord = CheckUtils.stringEmpty();
        user = userService.login(userName, passWord, role);
        long userId = user.getId();
        if (user == null) {
            System.out.println("Tài khoản hoặc mật khẩu không đúng!");
            CheckUtils.pressEnterToContinue();
        } else {
            System.out.println("\n════ ĐĂNG NHẬP THÀNH CÔNG ════");
            CheckUtils.pressEnterToContinue();
            if (role == Role.ADMIN) {
                AdminView.launch(userId);
            } else {
                StaffView.launch(userId);
            }
        }
    }

    private static void menuLogin() {
        System.out.println("══════════════ MENU ══════════════");
        System.out.println("║       1. Đăng nhập.            ║");
        System.out.println("║       0. Thoát.                ║");
        System.out.println("══════════════════════════════════");
        System.out.println("Nhập lựa chọn: ");
    }
}