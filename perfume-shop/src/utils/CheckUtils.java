package utils;

import java.text.DecimalFormat;
import java.util.Scanner;

public class CheckUtils {
    private static Scanner scanner = new Scanner(System.in);
    public static String stringEmpty() {
        String result;
        while (((result = scanner.nextLine()).trim()).isEmpty()) {
            System.out.println("Chuỗi rỗng hoặc chỉ chứa khoảng trống!");
            System.out.print("Xin mời nhập lại: ");
        }
        return result;
    }
    public static long longFormatCheck() {
        long result;
        do {
            try {
                result = Long.parseLong(scanner.nextLine());
                return result;
            } catch (Exception exception) {
                System.out.println("Nhập sai! Xin mời nhập lại");
            }
        } while (true);
    }
    public static double doubleFormatCheck() {
        double result;
        do {
            System.out.print(" => ");
            try {
                result = Double.parseDouble(scanner.nextLine());
                return result;
            } catch (Exception ex) {
                System.out.println("Sai cú pháp, vui lòng nhập lại!");
            }
        } while (true);
    }
    public static void pressEnterToContinue() {
        System.out.print("Ấn Enter để tiếp tục. ");
        scanner.nextLine();
    }
    public static String doubleToVND(double value) {
        String patternVND = ",### VNĐ";
        DecimalFormat decimalFormat = new DecimalFormat(patternVND);
        return decimalFormat.format(value);
    }

    public static int intFormatCheck() {
        int result;
        do {
            try {
                result = Integer.parseInt(scanner.nextLine());
                return result;
            } catch (Exception ex) {
                System.out.println("Sai cú pháp, vui lòng nhập lại!");
            }
        } while (true);
    }
}
