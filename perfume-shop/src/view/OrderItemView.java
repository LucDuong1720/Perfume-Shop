package view;

import model.Order;
import model.OrderItem;
import model.Product;
import service.*;
import utils.CheckUtils;
import utils.DateUtils;

import java.util.List;
import java.util.Scanner;

public class OrderItemView {
    private static Scanner scanner = new Scanner(System.in);
    private ProductView productView = new ProductView();
    private IProductService productService;
    private IOrderItemService orderItemService;
    private IOrderService orderService;

    public OrderItemView() {
        orderItemService = OrderItemService.getInstance();
        productService = ProductService.getInstance();
        orderService = OrderService.getInstance();
    }

    public void addOrderItem(long orderId) {
        do {
            try {
                productView.showProduct(productService.getAllProdutcs());
                long id = System.currentTimeMillis() / 1000;
                long productId = inputProductId();
                int quantity = inputQuantity(productId);
                Product product = productService.findProductById(productId);
                double price = product.getPrice();
                OrderItem newOrderItem = new OrderItem(id, price, quantity, productId, orderId);
                List<OrderItem> orderItems = orderItemService.findByOrderId(orderId);
                if (orderItems == null) {
                    orderItemService.add(newOrderItem);
                } else {
                    int count = 0;
                    for (OrderItem orderItem : orderItems) {
                        if (orderItem.getProductId() == productId) {
                            int temp = quantity + orderItem.getQuantity();
                            orderItem.setQuantity(temp);
                            orderItemService.update(orderItem);
                            count++;
                        }
                    }
                    if (count == 0) {
                        orderItemService.add(newOrderItem);
                    }
                }
                showOrderItem(orderItemService.findByOrderId(orderId));
                setProductQuantity(productId, -(orderItemService.findById(id).getQuantity()));
                setGrandTotal(orderId);
                System.out.printf("Đã thêm '%s' số lượng '%s' vào giỏ hàng.\n", product.getName(), quantity);
            } catch (Exception e) {
                System.out.println("Lỗi cú pháp. Vui lòng nhập lại!");
            }
        } while (isRetryAddOrderItem(orderId));
    }

    private boolean isRetryAddOrderItem(long orderId) {
        do {
            System.out.println("Chọn 'y' tiếp tục thêm sản phẩm \t|\t 'q' để in hóa đơn \t|\t 'e' để thoát.");
            String choice = scanner.nextLine();
            switch (choice) {
                case "y":
                    return true;
                case "q":
                    setGrandTotal(orderId);
                    printProductInvoice(orderId);
                    return false;
                case "e":
//                    List<OrderItem> orderItems = orderItemService.findByOrderId(orderId);
//                    for (OrderItem orderItem : orderItems) {
//                        setProductQuantity(orderItem.getProductId(), orderItem.getQuantity());
//                    }
                    System.out.println("Exit the program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn sai. Vui lòng nhập lại!");
                    break;
            }
        } while (true);
    }

    private void printProductInvoice(long orderId) {
        List<OrderItem> orderItems = orderItemService.findByOrderId(orderId);
        Order order = orderService.findById(orderId);

        System.out.println("═══════════════════════════════════════ HÓA ĐƠN THANH TOÁN ════════════════════════════════════════");
        System.out.println("║                                                                                                 ║");
        System.out.printf("║   Người mua: %-25s                           In lúc:    %16s    ║\n", order.getFullName(), DateUtils.dateToString(order.getCreatAt()));
        System.out.printf("║   Số điện thoại: %-30s                                                 ║\n", order.getPhone());
        System.out.printf("║   Địa chỉ: %-30s                                                       ║\n", order.getAddress());
        System.out.println("║                                                                                                 ║");
        System.out.println("║-------------------------------------------------------------------------------------------------║");
        System.out.printf("║ %-2s%-5s | %-11s%-19s | %-7s%-11s | %-1s%-9s | %-2s%-16s ║\n",
                "", "STT",
                "", "SẢN PHẨM",
                "", "GIÁ",
                "", "SỐ LƯỢNG",
                "", "THÀNH TIỀN"
        );
        System.out.println("║-------------------------------------------------------------------------------------------------║");
        for (int i = 0; i < orderItems.size(); i++) {
            OrderItem orderItem = orderItems.get(i);
            System.out.printf("║ %-3s%-4s | %-2s%-28s | %-2s%-16s | %-4s%-6s | %-2s%-16s ║\n",
                    "", i + 1,
                    "", productService.findProductById(orderItem.getProductId()).getName(),
                    "", CheckUtils.doubleToVND(orderItem.getPrice()),
                    "", orderItem.getQuantity(),
                    "", CheckUtils.doubleToVND(orderItem.getPrice() * orderItem.getQuantity())
            );
        }
        System.out.println("║-------------------------------------------------------------------------------------------------║");
        System.out.println("║                                                                                                 ║");
        System.out.printf("║            Cám Ơn Quý Khách Hẹn Gặp Lại                        Tổng tiền: %-20s  ║\n", CheckUtils.doubleToVND(order.getGrandTotal()));
        System.out.println("║                                                                                                 ║");
        System.out.println("═══════════════════════════════════════════════════════════════════════════════════════════════════");
    }

    private void setGrandTotal(long orderId) {
        Order order = orderService.findById(orderId);
        List<OrderItem> orderItems = orderItemService.findByOrderId(orderId);
        if (orderItems == null) {
            order.setGrandTotal(0);
        } else {
            double grandTotal = 0;
            for (OrderItem orderItem : orderItems) {
                grandTotal = grandTotal + orderItem.getTotal();
            }
            order.setGrandTotal(grandTotal);
        }
        orderService.update(order);
    }

    public void setProductQuantity(long productId, int quantityDifference) {
        Product product = productService.findProductById(productId);
        product.setQuantity(product.getQuantity() + quantityDifference);
        productService.update(product);
    }

    public void showOrderItem(List<OrderItem> orderItems) {
        System.out.println("═══════════════════════════════════════════════════ GIỎ HÀNG ═══════════════════════════════════════════════════");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-5s%-10s | %-5s%-30s | %-6s%-10s | %-2s%-5s | %-6s%-10s |\n",
                "", "ID",
                "", "SẢN PHẨM",
                "", "GIÁ",
                "", "SỐ LƯỢNG",
                "", "THÀNH TIỀN"
        );
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        for (OrderItem orderItem : orderItems) {
            System.out.printf("| %-5s%-10s | %-5s%-30s | %-6s%-10s | %-2s%-5s | %-6s%-10s |\n",
                    "", orderItem.getId(),
                    "", productService.findProductById(orderItem.getProductId()).getName(),
                    "", CheckUtils.doubleToVND(orderItem.getPrice()),
                    "", orderItem.getQuantity(),
                    "", CheckUtils.doubleToVND(orderItem.getPrice() * orderItem.getQuantity())
            );
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        CheckUtils.pressEnterToContinue();
        }
    private int inputQuantity(long productId) {
        Product product = productService.findProductById(productId);
        System.out.println("Nhập số lượng:");
        int quantity;
        do {
            quantity = CheckUtils.intFormatCheck();
            if (quantity < 0) {
                System.out.println("Số lượng sản phẩm không thể âm, vui lòng nhập lại!");
            }
            if (quantity > product.getQuantity()) {
                System.out.printf("Số lượng '%s' vượt quá '%s' sản phẩm hiện có! Vui lòng nhập lại!\n", product.getName(), product.getQuantity());
            }

        } while (quantity < 0 || quantity > product.getQuantity());
        return quantity;
    }

    private long inputProductId() {
        System.out.println("Nhập ID sản phẩm: ");
        long id;
        boolean checkActionMenu = true;
        do {
            id = CheckUtils.longFormatCheck();
            boolean isFindId = productService.existById(id);
            if (isFindId) {
                Product product = productService.findById(id);
                if (product.getQuantity() == 0)
                    System.out.println("Số lượng sản phẩm đã hết, vui lòng chọn sản phẩm khác!");
                else checkActionMenu = false;
            } else {
                System.out.println("Không tìm thấy! Vui lòng nhập lại");
            }
        } while (checkActionMenu);
        return id;
    }
}
