package view;

import java.util.Scanner;

public class StaffView {
    private static Scanner scanner = new Scanner(System.in);

    public static void launch(long userId) {
        boolean checkActionMenu = true;
        do {
            try {
                menuStaff();
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        ProductViewLauncher.staffLauncher();
                        break;
                    case 2:
                        OrderViewLauncher.staffLauncher();
                        break;
                    case 3:
                        MainLauncher.launcher();
                        break;
                    case 4:
                        System.out.println("Đang thoát chương trình...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Lựa chọn sai. Vui lòng nhập lại!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Sai cú pháp. Vui lòng nhập lại!");
            }
        } while (checkActionMenu);
    }

    private static void menuStaff() {
        System.out.println("═══════════════ STAFF MENU ═══════════════");
        System.out.println("║                                        ║");
        System.out.println("║          1. Quản lý sản phẩm.          ║");
        System.out.println("║          2. Quản lý đơn hàng.          ║");
        System.out.println("║          3. Đăng xuất.                 ║");
        System.out.println("║          0. Thoát.                     ║");
        System.out.println("║                                        ║");
        System.out.println("══════════════════════════════════════════");
        System.out.println("Nhập lựa chọn: ");
    }
}
