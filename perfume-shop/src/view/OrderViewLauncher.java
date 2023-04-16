package view;

import model.Order;
import service.OrderService;

import java.util.List;
import java.util.Scanner;

public class OrderViewLauncher {
    private static Scanner scanner = new Scanner(System.in);
    private static OrderView orderView = new OrderView();
    private static OrderService orderService = new OrderService();
    public static void launch() {
        boolean checkActionMenu = true;
        do {
            menuOrderMangager();
        try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        orderView.showOrders(orderService.getAllOrders());
                        break;
                    case 2:
                        orderView.addOrder();
                        break;
                    case 3:
                        orderView.totalRevenueByYear();
                        break;
                    case 4:
                        checkActionMenu = false;
                        break;
                    case 0:
                        System.out.println("Đang thoát chương trình...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Lựa chọn sai. Xin mời nhập lại!");
                        break;
                }
            } catch (Exception exception) {
                System.out.println("Sai cú pháp. Xin mời nhập lại!");
            }
        } while (checkActionMenu);
    }

    private static void menuOrderMangager() {
        System.out.println("════════════ ADMIN MENU ORDER ════════════");
        System.out.println("║                                        ║");
        System.out.println("║       1. Hiện danh sách đơn hàng.      ║");
        System.out.println("║       2. Tạo đơn hàng.                 ║");
        System.out.println("║       3. Doanh thu theo năm.           ║");
        System.out.println("║       4. Trở lại.                      ║");
        System.out.println("║       0. Thoát.                        ║");
        System.out.println("║                                        ║");
        System.out.println("══════════════════════════════════════════");
        System.out.println("Nhập lựa chọn: ");
    }
    public static void staffLauncher() {
        boolean checkActionMenu = true;
        do {
            menuStaffOrderManager();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        orderView.showOrders(orderService.getAllOrders());
                        break;
                    case 2:
                        orderView.addOrder();
                        break;
                    case 3:
                        checkActionMenu = false;
                        break;
                    case 0:
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

    private static void menuStaffOrderManager() {
        System.out.println("════════════ STAFF MENU ORDER ════════════");
        System.out.println("║                                        ║");
        System.out.println("║       1. Hiện danh sách đơn hàng.      ║");
        System.out.println("║       2. Tạo đơn hàng.                 ║");
        System.out.println("║       3. Trở lại.                      ║");
        System.out.println("║       0. Thoát.                        ║");
        System.out.println("║                                        ║");
        System.out.println("══════════════════════════════════════════");
        System.out.println("Nhập lựa chọn: ");
    }
}
