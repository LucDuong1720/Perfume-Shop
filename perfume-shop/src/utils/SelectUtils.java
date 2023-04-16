package utils;

import java.util.Scanner;

public class SelectUtils {
    private static Scanner scanner = new Scanner(System.in);
    public static boolean isSelect(SelectOption selectOption) {
        do {
            switch (selectOption) {
                case ADD :
                    System.out.println("Bấm Chọn 'y' để tiếp tục thêm \t|\t 'b' để trở lại \t|\t 'e' để thoát.");
                    break;
                case UPDATE :
                    System.out.println("Bấm 'y' để tiếp tục sửa \t|\t 'b' để trở lại \t|\t 'e' để thoát.");
                    break;
                case DELETE :
                    System.out.println("Bấm 'y' để tiếp tục xóa \t|\t 'b' để trở lại \t|\t 'e' để thoát.");
                    break;
                case SHOW :
                    System.out.println("Bấm 'b' để trở lại \t|\t 't' để thoát.");
                    break;
                case FIND :
                    System.out.println("Bấm 'y' để tiếp tục tìm kiếm \t|\t 'b' để quay lại\t|\t 'e' để thoát.");
                    break;
                case ORDER:
                    System.out.println("Bấm 'y' để tiếp tục thêm đơn hàng \t|\t 'b' để quay lại\t|\t 'e' để thoát.");
                case STATISTICAL :
                    System.out.println("Bấm 'y' để tiếp tục xem \t|\t 'b' để quay lại\t|\t 'e' để thoát.");
                    break;
                default :
                    throw new IllegalStateException("Unexpected value: " + selectOption);
            }

            System.out.print(" => ");
            String option = scanner.nextLine();
            switch (option) {
                case "y":
                    return true;
                case "b":
                    return false;
                case "e":
                    System.out.println("Đang thoát chương trình...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không đúng. Vui lòng nhập lại!");
                    System.out.print(" => ");
                    break;
            }
        } while (true);
    }
}
