package view;

import model.Role;

import java.util.Scanner;

public class MainLauncher {
    private static final LoginView loginView = new LoginView();

    public static void mainLauncher(Role role) {
        loginView.login(role);
    }
    public static void launcher() {
        Scanner scan = new Scanner(System.in);
        boolean checkActionMenu = true;
        do {
            try {
                System.out.println("══════════════ MENU ══════════════");
                System.out.println("║       1. Đăng nhập ADMIN.      ║");
                System.out.println("║       2. Đăng nhập USER.       ║");
                System.out.println("║       0. Thoát.                ║");
                System.out.println("══════════════════════════════════");
                int choice = Integer.parseInt(scan.nextLine());
                switch (choice) {
                    case 1:
                        MainLauncher.mainLauncher(Role.ADMIN);
                        break;
                    case 2:
                        MainLauncher.mainLauncher(Role.USER);
                        break;
                    case 3:
                        System.exit(3);
                        break;
                    default:
                        System.out.println("Nhập sai rồi bạn ơi! Vui lòng nhập lại");
                }
            } catch (Exception e) {
                System.out.println("Sai cú pháp. Vui lòng nhập lại!");
            }
        } while (checkActionMenu);
    }
}
