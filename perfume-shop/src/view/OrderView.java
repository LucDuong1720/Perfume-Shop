package view;

import model.Order;
import model.Product;
import service.*;
import utils.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class OrderView {
    private final Scanner scanner = new Scanner(System.in);
    private final IProductService productService;
    private final IOrderService orderService;
    private final IOrderItemService orderItemService;
    private final OrderItemView orderItemView = new OrderItemView();

    public OrderView() {
        productService = ProductService.getInstance();
        orderItemService = OrderItemService.getInstance();
        orderService = OrderService.getInstance();
    }
    public void showOrders(List<Order> orders) {
        System.out.println("════════════════════════════════════════════════ DANH SÁCH ĐƠN HÀNG ═══════════════════════════════════════════════════════════════════════════");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-5s%-9s | %-8s%-18s | %-6s%-10s | %-4s%-12s | %-7s%-15s | %-11s%-19s |\n",
                "", "ID",
                "", "TÊN KHÁCH HÀNG",
                "", "SĐT",
                "", "ĐỊA CHỈ",
                "", "TỔNG TIỀN",
                "", "THỜI GIAN TẠO"
        );
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
        for (Order order : orders) {
            System.out.printf("| %-5s%-9s | %-8s%-18s | %-6s%-10s | %-4s%-12s | %-7s%-15s | %-11s%-19s |\n",
                    "", order.getId(),
                    "", order.getFullName(),
                    "", order.getPhone(),
                    "", order.getAddress(),
                    "", CheckUtils.doubleToVND(order.getGrandTotal()),
                    "", DateUtils.dateToString(order.getCreatAt()));
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
        CheckUtils.pressEnterToContinue();
    }

    public void addOrder() {
        long orderId = 0;
        do {
            try {
                long id = System.currentTimeMillis() / 1000;
                String fullName = inputFullName();
                String phone = inputPhone();
                String address = inputAddress();
                Order order = new Order(id, fullName, phone, address);
                order.setCreatAt(new Date());
                orderService.add(order);
                System.out.println("Tạo đơn hàng thành công! Thêm sản phẩm vào giỏ hàng.");
                orderItemView.addOrderItem(order.getId());
            } catch (Exception e) {
                System.out.println("Sai cú pháp. Vui lòng nhập lại!");
            }
        } while (isRetryOrder(orderId));
    }

    private boolean isRetryOrder(long orderId) {
        do {
            System.out.println("Bấm 'y' để tiếp tục thêm đơn hàng \t|\t 'b' để quay lại\t|\t 'e' để thoát.");
            String option = scanner.nextLine();
            switch (option) {
                case "y":
                    return true;
                case "b":
                    return false;
                case "e":
                    System.out.println("Exit the program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn sai. Vui lòng nhập lại!");
                    System.out.print(" => ");
                    break;
            }
        } while (true);
    }

    private String inputAddress() {
        System.out.println("Nhập địa chỉ (Ký tự đầu của từng từ phải viết hoa, VD: Huế)");
        String address;
        while (!ValidateUtils.isAddressValid(address = CheckUtils.stringEmpty())) {
            System.out.println("Địa chỉ không đúng định dạng, xin mời nhập lại!");
        }
        return address;
    }

    private String inputPhone() {
        System.out.println("Nhập số điện thoại (Số điện thoại bao gồm 10 chữ số và bắt đầu bằng số 0. VD: 0123456789)");
        String phone;
        while (!ValidateUtils.isPhoneValid(phone = scanner.nextLine())) {
            System.out.println("Số điện thoại gồm 10 số và bắt đầu bằng chữ số 0, VD: 0123456789");
        }
        return phone;
    }

    private String inputFullName() {
        System.out.println("Nhập tên (Ký tự đầu của từng từ phải ghi hoa, VD: Dương Văn Lực)");
        String fullName;
        while (!ValidateUtils.isNameValid(fullName = CheckUtils.stringEmpty())) {
            System.out.println("Ký tự đầu của từng từ phải ghi hoa, VD: Dương Văn Lực");
        }
        return fullName;
    }

    public void totalRevenueByYear() {
        System.out.println("═════════════ THỐNG KÊ THEO NĂM ═════════════");
        String year = inputYear();
        List<Order> ordersFind = new ArrayList<>();
        List<Order> orders = orderService.getAllOrders();
        for (Order order : orders) {
            String createdDate = DateUtils.dateToStringYear(order.getCreatAt());
            if (year.equals(createdDate)) {
                ordersFind.add(order);
            }
        }
        System.out.printf("════════════════════════════════════════ DOANH THU NĂM %s ══════════════════════════════════════════════\n", year);
        System.out.println("║                                                                                                     ║");
        System.out.println("║-----------------------------------------------------------------------------------------------------║");
        System.out.printf("║ %-2s%-5s | %-8s%-16s | %-5s%-9s | %-6s%-14s | %-5s%-17s ║\n",
                "", "STT",
                "", "KHÁCH HÀNG",
                "", "SĐT",
                "", "ĐỊA CHỈ",
                "", "THÀNH TIỀN"
        );
        System.out.println("║-----------------------------------------------------------------------------------------------------║");
        double revenueTotal = 0;
        for (int i = 0; i < ordersFind.size(); i++) {
            Order order = ordersFind.get(i);
            revenueTotal += order.getGrandTotal();
            System.out.printf("║ %-3s%-4s | %-2s%-22s | %-2s%-12s | %-2s%-18s | %-2s%-20s ║\n",
                    "", i + 1,
                    "", order.getFullName(),
                    "", order.getPhone(),
                    "", order.getAddress(),
                    "", CheckUtils.doubleToVND(order.getGrandTotal())
            );
        }
        System.out.println("║-----------------------------------------------------------------------------------------------------║");
        System.out.println("║                                                                                                     ║");
        System.out.println("║-----------------------------------------------------------------------------------------------------║");
        System.out.printf("║                                                         TỔNG DOANH THU: %-20s%6s  ║\n", CheckUtils.doubleToVND(revenueTotal), "");
        System.out.println("║-----------------------------------------------------------------------------------------------------║");
        System.out.println("═══════════════════════════════════════════════════════════════════════════════════════════════════════");
    }

    private String inputYear() {
        System.out.println("Nhập năm (Ví Dụ: 2022): ");
        String year;
        while (!ValidateUtils.isYearValid(year = scanner.nextLine().trim())) {
            System.out.println("Năm gồm 4 chữ số (Ví Dụ: 2022).");
        }
        return year;
    }
}
