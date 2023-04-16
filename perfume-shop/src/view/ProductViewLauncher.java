package view;

import service.ProductService;

import java.util.Scanner;

public class ProductViewLauncher {
    private static Scanner scanner = new Scanner(System.in);
    private static ProductView productView = new ProductView();
    private static ProductService productService = new ProductService();
    public static void launch() {
        boolean checkActionMenu = true;
        do {
            menuProductsManager();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        productView.showProduct(productService.getAllProdutcs());
                        break;
                    case 2:
                        productView.addProduct();
                        break;
                    case 3:
                        productView.updateProduct();
                        break;
                    case 4:
                        productView.deleteProduct();
                        break;
                    case 5:
                        productView.FindProduct();
                        break;
                    case 6:
                        productView.sortProduct();
                        break;
                    case 7:
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

    private static void menuProductsManager() {
        System.out.println("═══════════ ADMIN MENU PRODUCT ═══════════");
        System.out.println("║                                        ║");
        System.out.println("║        1. Hiện danh sách sản phẩm.     ║");
        System.out.println("║        2. Thêm sản phẩm.               ║");
        System.out.println("║        3. Sửa sản phẩm.                ║");
        System.out.println("║        4. Xóa sản phẩm.                ║");
        System.out.println("║        5. Tìm kiếm sản phẩm.           ║");
        System.out.println("║        6. Sắp xếp sản phẩm.            ║");
        System.out.println("║        7. Trở lại.                     ║");
        System.out.println("║        0. Thoát.                       ║");
        System.out.println("║                                        ║");
        System.out.println("══════════════════════════════════════════");
        System.out.println("Nhập lựa chọn: ");
    }

    public static void staffLauncher() {
        boolean isTrue = true;
        do {
            menuStaffProductsManager();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        productView.showProduct(productService.getAllProdutcs());
                        break;
                    case 2:
                        productView.FindProduct();
                        break;
                    case 3:
                        productView.sortProduct();
                        break;
                    case 4:
                        isTrue = false;
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
        } while (isTrue);
    }

    private static void menuStaffProductsManager() {
        System.out.println("═══════════ STAFF MENU PRODUCT ═══════════");
        System.out.println("║                                        ║");
        System.out.println("║        1. Hiện danh sách sản phẩm.     ║");
        System.out.println("║        2. Tìm kiếm sản phẩm.           ║");
        System.out.println("║        3. Sắp xếp sản phẩm.            ║");
        System.out.println("║        4. Trở lại.                     ║");
        System.out.println("║        0. Thoát.                       ║");
        System.out.println("║                                        ║");
        System.out.println("══════════════════════════════════════════");
        System.out.println("Nhập lựa chọn: ");
    }
}
