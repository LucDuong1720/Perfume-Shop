package view;

import model.Product;
import service.IProductService;
import service.ProductService;
import utils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductView {
    private IProductService productService;
    private static Scanner scanner = new Scanner(System.in);
    public ProductView() {
        productService = ProductService.getInstance();
    }
    public void showProduct(List<Product> products) {
        System.out.println("════════════════════════════════════════════════════════ DANH SÁCH SẢN PHẨM ══════════════════════════════════════════════════════════════════════");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-5s%-10s | %-5s%-30s |%-6s%-8s | %-6s%-12s | %-6s%-8s | %-2s%-8s | %-6s%-10s|\n",
                "", "ID",
                "", "TÊN",
                "", "THƯƠNG HIỆU",
                "", "XUẤT XỨ",
                "", "DUNG TÍCH",
                "", "SỐ LƯỢNG",
                "", "GIÁ"
        );
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
        for (Product product : products) {
            System.out.printf("| %-5s%-10s | %-5s%-30s | %-6s%-12s | %-6s%-8s | %-6s%-8s | %-2s%-8s | %-6s%-10s|\n",
                    "", product.getId(),
                    "", product.getName(),
                    "", product.getBrand(),
                    "", product.getOrigin(),
                    "", product.getCapacity(),
                    "", product.getQuantity(),
                    "", CheckUtils.doubleToVND(product.getPrice())
            );
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
        CheckUtils.pressEnterToContinue();
        }

    public void addProduct() {
        do {
            try {
                long id = System.currentTimeMillis() / 1000;
                String productName = inputProductName();
                String brand = inputBrand();
                String origin = inputOrigin();
                String capacity = inputCapacity();
                double price = inputPrice();
                int quantity = inputQuantity();
                Product product = new Product(id, productName, brand, origin, capacity, quantity, price);
                productService.addProduct(product);
                System.out.println("Thêm sản phẩm thành công!");
                CheckUtils.pressEnterToContinue();
            } catch (Exception e) {
                System.out.println("Sai cú pháp. Vui lòng nhập lại!");
            }
        } while (SelectUtils.isSelect(SelectOption.ADD));
    }

    private int inputQuantity() {
        System.out.println("Nhập số lượng sản phẩm: ");
        int quantity;
        do {
            quantity = CheckUtils.intFormatCheck();
            if (quantity < 0 || quantity > 1000)
                System.out.println("Số lượng sản phẩm không thể âm và phải nhỏ hơn 1000.");
        } while (quantity < 0|| quantity > 1000);
        return quantity;
    }

    private double inputPrice() {
        System.out.println("Nhập giá sản phẩm: ");
        double price;
        do {
            price = CheckUtils.doubleFormatCheck();
            if (price <= 100000 || price > 50000000)
                if (price < 100000)
                    System.out.println("Giá sản phẩm ít nhất là 100.000 VNĐ và nhỏ hơn 50.000.000 VNĐ.");
        } while (price <= 100000 || price > 50000000);
        return price;
    }

    private String inputCapacity() {
        System.out.println("Nhập dung tích (VD: 50ml)");
        String capacity;
        while (!ValidateUtils.isCapacityValid(capacity = CheckUtils.stringEmpty())) {
            System.out.println("Dung tích không đúng định dạng hoặc lớn hơn 1000, xin mời nhập lại!");
        }
        return capacity;
    }

    private String inputOrigin() {
        System.out.println("Nhập tên thương hiệu (Viết hoa chữ cái đầu tiên)");
        String origin;
        while (!ValidateUtils.isOriginValid(origin = CheckUtils.stringEmpty())) {
            System.out.println("Xuất xứ không đúng định dạng, xin mời nhập lại!");
        }
        return origin;
    }

    private String inputBrand() {
        System.out.println("Nhập tên thương hiệu (Viết hoa chữ cái đầu tiên)");
        String brand;
        while (!ValidateUtils.isBrandValid(brand = CheckUtils.stringEmpty())) {
            System.out.println("Thương hiệu không đúng định dạng, xin mời nhập lại!");
        }
        return brand;
    }

    private String inputProductName() {
        System.out.println("Nhập tên sản phẩm (Viết hoa chữ cái đầu tiên)");
        String productName;
        do {
            if (!ValidateUtils.isProductNameValid(productName = CheckUtils.stringEmpty())) {
                System.out.println("Nhập tên sản phẩm (Viết hoa chữ cái đầu tiên)");
                continue;
            }
            productName = productName.trim();
            if (productService.existsByName(productName)) {
                System.out.println("Tên sản phẩm này đã tồn tại. Vui lòng nhập lại");
                continue;
            }
            break;
        } while (true);
        return productName;
    }

    public void updateProduct() {
        int choice;
        boolean checkActionMenu = true;
        do {
            try {
                showProduct(productService.getAllProdutcs());
                long id = inputId();
                Product product = productService.findById(id);
                menuUpdateProduct();
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        updateName(product);
                        break;
                    case 2:
                        updateBrand(product);
                        break;
                    case 3:
                        updateOrigin(product);
                        break;
                    case 4:
                        updatePrice(product);
                        break;
                    case 5:
                        updateQuantity(product);
                        break;
                    case 6:
                        updateCapacity(product);
                        break;
                    case 7:
                        break;
                    case 0:
                        System.out.println("Đang thoát chương trình...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Nhập sai. Xin mời nhập lại!");
                        break;
                }
                checkActionMenu = choice != 7 && SelectUtils.isSelect(SelectOption.UPDATE);
            } catch (Exception e) {
                System.out.println("Sai cú pháp. Xin mời nhập lại!");
            }
        } while (checkActionMenu);
    }

    private void updateCapacity(Product newProduct) {
        String capacity = inputCapacity();
        newProduct.setCapacity(capacity);
        productService.update(newProduct);
        System.out.println("Cập nhật sản phẩm thành công!");
        CheckUtils.pressEnterToContinue();
    }

    private void updateQuantity(Product newProduct) {
        int quantity = inputQuantity();
        newProduct.setQuantity(quantity);
        productService.update(newProduct);
        System.out.println("Cập nhật sản phẩm thành công!");
        CheckUtils.pressEnterToContinue();
    }

    private void updatePrice(Product newProduct) {
        double price = inputPrice();
        newProduct.setPrice(price);
        productService.update(newProduct);
        System.out.println("Cập nhật sản phẩm thành công!");
        CheckUtils.pressEnterToContinue();
    }

    private void updateOrigin(Product newProduct) {
        String origin = inputOrigin();
        newProduct.setOrigin(origin);
        productService.update(newProduct);
        System.out.println("Cập nhật sản phẩm thành công!");
        CheckUtils.pressEnterToContinue();
    }

    private void updateBrand(Product newProduct) {
        String brand = inputBrand();
        newProduct.setBrand(brand);
        productService.update(newProduct);
        System.out.println("Cập nhật sản phẩm thành công!");
        CheckUtils.pressEnterToContinue();
    }

    private void updateName(Product newProduct) {
        String name = inputProductName();
        newProduct.setName(name);
        productService.update(newProduct);
        System.out.println("Cập nhật sản phẩm thành công!");
        CheckUtils.pressEnterToContinue();
    }

    private void menuUpdateProduct() {
        System.out.println("════════ CHỈNH SỬA SẢN PHẨM ════════");
        System.out.println("║                                  ║");
        System.out.println("║     1. Chỉnh sửa tên.            ║");
        System.out.println("║     2. Chỉnh sửa thương hiệu.    ║");
        System.out.println("║     3. Chỉnh sửa xuất xứ.        ║");
        System.out.println("║     4. Chỉnh sửa giá.            ║");
        System.out.println("║     5. Chỉnh sửa số lượng.       ║");
        System.out.println("║     6. Chỉnh sửa dung tích.      ║");
        System.out.println("║     7. Trở lại.                  ║");
        System.out.println("║     0. Thoát.                    ║");
        System.out.println("║                                  ║");
        System.out.println("════════════════════════════════════");
        System.out.println("Nhập lựa chọn: ");
    }

    private long inputId() {
        long id;
        System.out.println("Nhập Id: ");
        boolean checkActionMenu = true;
        do {
            id = CheckUtils.longFormatCheck();
            boolean isFindId = productService.existById(id);
            if (isFindId) {
                checkActionMenu = false;
            } else {
                System.out.println("Không tìm thấy. Vui lòng nhập lại!");
            }
        } while (checkActionMenu);
        return id;
    }

    public void deleteProduct() {
        boolean isRetry = true;
        do {
            showProduct(productService.getAllProdutcs());
            long id = inputId();
            int choice;
            boolean checkActionMenu = true;
            do {
                try {
                    menuDelete();
                    choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            productService.deleteById(id);
                            System.out.println("xóa sản phẩm thành công!");
                            CheckUtils.pressEnterToContinue();
                            checkActionMenu = false;
                            break;
                        case 2:
                            checkActionMenu = false;
                            break;
                        default:
                            System.out.println("Nhập sai. Xin mời nhập lại!");
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("Sai cú pháp. Xin mời nhập lại!");
                }
            } while (checkActionMenu);
        } while (isRetry == SelectUtils.isSelect(SelectOption.DELETE));
    }

    private void menuDelete() {
        System.out.println("═════ BẠN CÓ MUỐN XÓA KHÔNG? ═════");
        System.out.println("║            1. Có.              ║");
        System.out.println("║            2. Không.           ║");
        System.out.println("══════════════════════════════════");
        System.out.println("Nhập lựa chọn: ");
    }

    public void FindProduct() {
        int choice;
        boolean checkActionMenu = true;
        do {
            try {
                menuFindProduct();
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        findByProductId();
                        break;
                    case 2:
                        findByProductName();
                        break;
                    case 3:
                        findByOrigin();
                        break;
                    case 4:
                        checkActionMenu = false;
                        break;
                    case 0:
                        System.out.println("Đang thoát chương trình...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Nhập sai. Xin mời nhập lại!");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Sai cú pháp. Xin mời nhập lại!");
            }
        } while (checkActionMenu == SelectUtils.isSelect(SelectOption.FIND));
    }

    private void findByOrigin() {
        showProduct(productService.getAllProdutcs());
        System.out.println("═════ TÌM KIẾM THEO XUẤT XỨ ═════");
        System.out.print("Nhập xuất xứ: ");
        String productBrand = scanner.nextLine();
        List<Product> productsFind = productService.findByOrigin(productBrand);
        if (productsFind != null) {
            showProduct(productsFind);
        } else {
            System.out.println("Không tìm thấy!");
        }
    }

    private void findByProductName() {
        showProduct(productService.getAllProdutcs());
        System.out.println("═══════ TÌM KIẾM THEO TÊN SẢN PHẨM ═══════");
        System.out.print("Nhập tên sản phẩm: ");
        String productName = scanner.nextLine();
        List<Product> productsFind = productService.findByName(productName);
        if (productsFind != null) {
            showProduct(productsFind);
        } else {
            System.out.println("Không tìm thấy!");
        }
    }

    private void findByProductId() {
        showProduct(productService.getAllProdutcs());
        System.out.print("Nhập Id sản phẩm: ");
        long value = Long.parseLong(scanner.nextLine());
        Product product = productService.findById(value);
        if (product != null) {
            List<Product> productsFind = new ArrayList<>();
            productsFind.add(product);
            showProduct(productsFind);
        } else {
            System.out.println("Không tìm thấy!");
        }
    }

    private void menuFindProduct() {
        System.out.println("═════════ TÌM KIẾM SẢN PHẨM ═════════");
        System.out.println("║                                   ║");
        System.out.println("║    1. Tìm kiếm theo Id.           ║");
        System.out.println("║    2. Tìm kiếm theo tên.          ║");
        System.out.println("║    3. Tìm kiếm theo xuất xứ.      ║");
        System.out.println("║    4. Trở lại.                    ║");
        System.out.println("║    0. Thoát.                      ║");
        System.out.println("║                                   ║");
        System.out.println("═════════════════════════════════════");
        System.out.println("Nhập lựa chọn: ");
    }

    public void sortProduct() {
        int choice;
        boolean checkActionMenu = true;
        do {
            try {
                menuSortProduct();
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        sortById();
                        break;
                    case 2:
                        sortByProductName();
                        break;
                    case 3:
                        sortByBrand();
                        break;
                    case 4:
                        sortByQuantity();
                        break;
                    case 5:
                        sortByPrice();
                        break;
                    case 6:
                        checkActionMenu = false;
                        break;
                    case 0:
                        System.out.println("Đang thoát chương trình...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Nhập sai. Xin mời nhập lại!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Sai cú pháp. Xin mời nhập lại!");
            }
        } while (checkActionMenu);
    }

    private void sortByPrice() {
        System.out.println("══════ SẮP XẾP THEO GIÁ ══════");
        menuSort();
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    showProduct(productService.sortByPrice(TypeSort.ASC));
                    break;
                case 2:
                    showProduct(productService.sortByPrice(TypeSort.DESC));
                    break;
                default:
                    System.out.println("Lựa chọn sai. Vui lòng nhập lại!");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Sai cú pháp. Vui lòng nhập lại!");
        }
    }

    private void sortByQuantity() {
        System.out.println("══════ SẮP XẾP THEO SỐ LƯỢNG ══════");
        menuSort();
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    showProduct(productService.sortByQuantity(TypeSort.ASC));
                    break;
                case 2:
                    showProduct(productService.sortByQuantity(TypeSort.DESC));
                    break;
                default:
                    System.out.println("Lựa chọn sai. Vui lòng nhập lại!");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Sai cú pháp. Vui lòng nhập lại!");
        }
    }

    private void sortByBrand() {
        System.out.println("══════ SẮP XẾP THEO THƯƠNG HIỆU ══════");
        menuSort();
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    showProduct(productService.sortByBrand(TypeSort.ASC));
                    break;
                case 2:
                    showProduct(productService.sortByBrand(TypeSort.DESC));
                    break;
                default:
                    System.out.println("Lựa chọn sai. Vui lòng nhập lại!");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Sai cú pháp. Vui lòng nhập lại!");
        }
    }

    private void sortByProductName() {
        System.out.println("══════ SẮP XẾP THEO TÊN SẢN PHẨM ══════");
        menuSort();
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    showProduct(productService.sortByName(TypeSort.ASC));
                    break;
                case 2:
                    showProduct(productService.sortByName(TypeSort.DESC));
                    break;
                default:
                    System.out.println("Lựa chọn sai. Vui lòng nhập lại!");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Sai cú pháp. Vui lòng nhập lại!");
        }
    }

    private void sortById() {
        System.out.println("══════════ SẮP XẾP THEO ID ══════════");
        menuSort();
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    showProduct(productService.sortById(TypeSort.ASC));
                    break;
                case 2:
                    showProduct(productService.sortById(TypeSort.DESC));
                    break;
                default:
                    System.out.println("Lựa chọn sai. Vui lòng nhập lại!");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Sai cú pháp. Vui lòng nhập lại!");
        }
    }

    private void menuSort() {
        System.out.println("═══════════════════════════════");
        System.out.println("║        1. Tăng dần.         ║");
        System.out.println("║        2. Giảm dần.         ║");
        System.out.println("═══════════════════════════════");
        System.out.println("Enter your choice: ");
    }

    private void menuSortProduct() {
        System.out.println("══════════ SẮP XẾP SẢN PHẨM ══════════");
        System.out.println("║                                    ║");
        System.out.println("║    1. Sắp xếp theo Id.             ║");
        System.out.println("║    2. Sắp xếp theo tên sản phẩm.   ║");
        System.out.println("║    3. Sắp xếp theo thương hiệu.    ║");
        System.out.println("║    4. Sắp xếp theo số lượng.       ║");
        System.out.println("║    5. Sắp xếp theo giá.            ║");
        System.out.println("║    6. Trở lại.                     ║");
        System.out.println("║    0. Thoát.                       ║");
        System.out.println("║                                    ║");
        System.out.println("══════════════════════════════════════");
        System.out.println("Nhập lựa chọn: ");
    }
}

