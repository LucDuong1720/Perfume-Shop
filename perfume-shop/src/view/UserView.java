package view;

import model.Role;
import model.User;
import service.IUserService;
import service.UserService;
import utils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private IUserService userService;
    private static Scanner scanner = new Scanner(System.in);
    public UserView() {
        userService = UserService.getInstance();
    }

    public void showUser(List<User> users) {
        System.out.println("════════════════════════════════════════════════════════ DANH SÁCH TÀI KHOẢN ══════════════════════════════════════════════════════");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-5s%-10s | %-5s%-12s | %-6s%-14s | %-6s%-14s | %-6s%-10s | %-2s%-22s |\n",
                "", "ID",
                "", "TÀI KHOẢN",
                "", "TÊN",
                "", "SĐT",
                "", "ĐỊA CHỈ",
                "", "QUYỀN"
        );
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
        for (User user : users) {
            System.out.printf("| %-5s%-10s | %-5s%-12s | %-6s%-14s | %-6s%-14s | %-6s%-10s | %-2s%-22s |\n",
                    "", user.getId(),
                    "", user.getNameAccount(),
                    "", user.getNameUser(),
                    "", user.getPhone(),
                    "", user.getAddress(),
                    "", user.getRole()
            );
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
        CheckUtils.pressEnterToContinue();
    }
    public void addUser() {
        do {
            try {
                long id = System.currentTimeMillis() / 1000;
                String nameAccount = inputNameAccount();
                String password = inputPassWord();
                String nameUser = inputNameUser();
                String phone = inputPhone();
                String address = inputAddress();
                User user = new User(id, nameAccount, password, nameUser, phone, address, Role.USER);
                setRole(user);
                userService.add(user);
                System.out.println("Thêm tài khoản " + user.getNameAccount() + " thành công!");
                CheckUtils.pressEnterToContinue();
            } catch (Exception e) {
                System.out.println("Sai cú pháp. Vui lòng nhập lại!");
            }
        } while (SelectUtils.isSelect(SelectOption.ADD));
    }
    private static void setRole(User user) {
        boolean isTrue = true;
        int option;
        menuSetRole();
        do {
            try {
                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1 -> {
                        user.setRole(Role.ADMIN);
                        isTrue = false;
                    }

                    case 2 -> {
                        user.setRole(Role.USER);
                        isTrue = false;
                    }

                    default -> {
                        System.out.println("Lựa chọn sai. Vui lòng nhập lại!");
                    }
                }
            } catch (Exception ex) {
                System.out.println("Sai cú pháp. Vui lòng nhập lại!");
            }
        } while (isTrue);
    }
    private String inputAddress() {
        System.out.println("Nhập địa chỉ (Ký tự đầu của từng từ phải viết hoa, VD: Huế)");
        String address;
        while (!ValidateUtils.isAddressValid(address = CheckUtils.stringEmpty())) {
            System.out.println("Địa chỉ không đúng định dạng, vui lòng nhập lại!");
        }
        return address;
    }

    private String inputPhone() {
        System.out.println("Nhập số điện thoại (Số điện thoại bao gồm 10 chữ số và bắt đầu bằng số 0. VD: 0123456789)");
        String phone;
        do {
            if (!ValidateUtils.isPhoneValid(phone = CheckUtils.stringEmpty())) {
                System.out.println("Số điện thoại bao gồm 10 chữ số và bắt đầu bằng số 0 (VD: 0123456789). Xin mời nhập lại!");
                continue;
            }
            if (userService.existsByPhone(phone)) {
                System.out.println("Số điện thoại đã tồn tại. Xin mời nhập lại!");
                continue;
            }
            break;
        } while (true);
        return phone;
    }

    private String inputNameUser() {
        System.out.println("Nhập tên (Ký tự đầu của từng từ phải ghi hoa, VD: Dương Văn Lực)");
        String nameUser;
        while (!ValidateUtils.isNameValid(nameUser = CheckUtils.stringEmpty())) {
            System.out.println("Ký tự đầu của từng từ phải ghi hoa, VD: Dương Văn Lực");
        }
        return nameUser;
    }

    private String inputPassWord() {
        System.out.println("Nhập mật khẩu (Mật khẩu >= 8 kí tự, VD: Vidu1234@)");
        String password;
        while (!ValidateUtils.isPassWordValid(password = scanner.nextLine())) {
            System.out.println("Mật khẩu >= 8 kí tự trong đó chứa  " +
                    "ít nhất 1 ký tự viết hoa, viết thường, chữ số và kí tự đặt biệt. VD: Vidu1234@");
        }
        return password;
    }

    private String inputNameAccount() {
        System.out.println("Nhập tài khoản (VD: Vidu123)");
        String nameAccount;
        do {
            if (!ValidateUtils.isUserNameValid(nameAccount = CheckUtils.stringEmpty())) {
                System.out.println(nameAccount + " không đúng định dạng. Xin mời nhập lại!");
                continue;
            }
            if (userService.existsByUserName(nameAccount)) {
                System.out.println(nameAccount + " đã tồn tại. Xin mời nhập lại!");
                continue;
            }
            break;
        } while (true);
        return nameAccount;
    }
    private static void menuSetRole() {
        System.out.println("═════════ CHỌN QUYỀN ═════════");
        System.out.println("║          1. ADMIN          ║");
        System.out.println("║          2. USER           ║");
        System.out.println("══════════════════════════════");
        System.out.println("Nhập lựa chọn: ");
    }

    public void updateUser() {
        int choice;
        boolean checkActionMenu = true;
        do {
            try {
                showUser(userService.getAllUsers());
                long id = inputId();
                User newUser = userService.findById(id);
                menuUpdateUser();
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        updateNameUser(newUser);
                        break;
                    case 2:
                        updatePhone(newUser);
                        break;
                    case 3:
                        updateAddress(newUser);
                        break;
                    case 4:
                        setRole(newUser);
                        updateRole(newUser);
                        break;
                    case 5:
                        break;
                    case 0:
                        System.out.println("Đang thoát chương trình...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Nhập sai. Xin mời nhập lại!");
                        break;
                }
                checkActionMenu = choice != 5 && SelectUtils.isSelect(SelectOption.UPDATE);

            } catch (Exception e) {
                System.out.println("Sai cú pháp. Xin mời nhập lại!");
            }
        } while (checkActionMenu);
    }

    private void updateRole(User newUser) {
        userService.update(newUser);
        System.out.println("Cập nhật thành công!");
        CheckUtils.pressEnterToContinue();
    }

    private void updateAddress(User newUser) {
        String oldAddress = newUser.getAddress();
        String address = inputAddress();
        newUser.setAddress(address);
        userService.update(newUser);
        System.out.printf("Đã thay đổi địa chỉ từ %s thành %s\n", oldAddress, address);
        CheckUtils.pressEnterToContinue();
    }

    private void updatePhone(User newUser) {
        String oldPhone = newUser.getPhone();
        String phone = inputPhone();
        newUser.setPhone(phone);
        userService.update(newUser);
        System.out.printf("Đã thay đổi số điện thoại từ %s thành %s\n", oldPhone, phone);
        CheckUtils.pressEnterToContinue();
    }

    private void updateNameUser(User newUser) {
        String oldName = newUser.getNameUser();
        String name = inputNameUser();
        newUser.setNameUser(name);
        userService.update(newUser);
        System.out.printf("Đã thay đổi tên từ %s thành '%s'.\n", oldName, name);
        CheckUtils.pressEnterToContinue();
    }

    private long inputId() {
        long id;
        System.out.println("Nhập Id: ");
        boolean checkActionMenu = true;
        do {
            id = CheckUtils.longFormatCheck();
            boolean isFindId = userService.existById(id);
            if (isFindId) {
                checkActionMenu = false;
            } else {
                System.out.println("Không tìm thấy. Vui lòng nhập lại!");
            }
        } while (checkActionMenu);
        return id;
    }
    private static void menuUpdateUser() {
        System.out.println("═══════ CHỈNH SỬA TÀI KHOẢN ═══════");
        System.out.println("║                                 ║");
        System.out.println("║    1. Chỉnh sửa tên.            ║");
        System.out.println("║    2. Chỉnh sửa số điện thoại.  ║");
        System.out.println("║    3. Chỉnh sửa địa chỉ.        ║");
        System.out.println("║    4. Chỉnh sửa quyền.          ║");
        System.out.println("║    5. Trở lại.                  ║");
        System.out.println("║    0. Thoát.                    ║");
        System.out.println("║                                 ║");
        System.out.println("═══════════════════════════════════");
        System.out.println("Nhập lựa chọn: ");
    }

    public void deleteUser() {
        boolean isRetry = true;
        do {
            showUser(userService.getAllUsers());
            long id = inputId();
            User user = userService.findById(id);
            int choice;
            boolean checkActionMenu = true;
            do {
                try {
                    menuDelete();
                    choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1 -> {
                            if (user.getRole() == Role.ADMIN) {
                                System.out.println("Không thể xóa tài khoản Admin!");
                            } else {
                                System.out.println("Xóa tài khoản thành công!");
                                userService.deleteById(id);
                            }
                            CheckUtils.pressEnterToContinue();
                            checkActionMenu = false;
                        }

                        case 2 -> checkActionMenu = false;

                        case 0 -> {
                            System.out.println("Đang thoát chương trình...");
                            System.exit(0);
                        }

                        default -> {
                            System.out.println("Nhập sai. Xin mời nhập lại!");
                        }
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
        System.out.println("║            0. Thoát.           ║");
        System.out.println("══════════════════════════════════");
        System.out.println("Nhập lựa chọn: ");
        System.out.print(" => ");
    }

    public void findUser() {
        int choice;
        boolean checkActionMenu = true;
        do {
            try {
                menuFindUser();
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        findById();
                        break;
                    case 2:
                        findByNameUser();
                        break;
                    case 3:
                        findByAddress();
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

    private void findByAddress() {
        showUser(userService.getAllUsers());
        System.out.print("Nhập địa chỉ: ");
        String value = scanner.nextLine();
        List<User> usersFind = userService.findByAddress(value);
        if (usersFind != null) {
            showUser(usersFind);
        } else {
            System.out.println("Không tìm thấy địa chỉ!");
        }
    }

    private void findByNameUser() {
        showUser(userService.getAllUsers());
        System.out.print("Nhập tên người dùng: ");
        String value = scanner.nextLine();
        List<User> users = userService.findByFullName(value);
        if (users != null) {
            showUser(users);
        } else {
            System.out.println("Không tìm thấy tên người dùng!");
        }
    }

    private void findById() {
        showUser(userService.getAllUsers());
        System.out.print("Nhập id: ");
        long value = Long.parseLong(scanner.nextLine());
        User user = userService.findById(value);
        if (user != null) {
            List<User> usersFind = new ArrayList<>();
            usersFind.add(user);
            showUser(usersFind);
        } else {
            System.out.println("Không tìm thấy!");
        }
    }

    private void menuFindUser() {
        System.out.println("═════════ TÌM KIẾM TÀI KHOẢN ═════════");
        System.out.println("║                                    ║");
        System.out.println("║    1. Tìm kiếm theo Id.            ║");
        System.out.println("║    2. Tìm kiếm theo tên.           ║");
        System.out.println("║    3. Tìm kiếm theo địa chỉ.       ║");
        System.out.println("║    4. Trở lại.                     ║");
        System.out.println("║    0. Thoát.                       ║");
        System.out.println("║                                    ║");
        System.out.println("══════════════════════════════════════");
        System.out.println("Nhập lựa chọn: ");
    }
    public void sortUser() {
        int choice;
        boolean checkActionMenu = true;
        do {
            try {
                menuSortUser();
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        sortById();
                        break;
                    case 2:
                        sortByNameUser();
                        break;
                    case 3:
                        sortByAddress();
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
        } while (checkActionMenu);
    }

    private void sortByAddress() {
        System.out.println("══════════ SẮP XẾP THEO ĐỊA CHỈ ══════════");
        menuSort();
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    showUser(userService.sortByAddress(TypeSort.ASC));
                    break;
                case 2:
                    showUser(userService.sortByAddress(TypeSort.DESC));
                    break;
                default:
                    System.out.println("Nhập sai. Xin mời nhập lại!");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Sai cú pháp. Xin mời nhập lại!");
        }

    }

    private void menuSort() {
            System.out.println("═══════════════════════════════");
            System.out.println("║        1. Tăng dần.         ║");
            System.out.println("║        2. Giảm dần.         ║");
            System.out.println("═══════════════════════════════");
            System.out.println("Enter your choice: ");
    }

    private void sortByNameUser() {
        System.out.println("════════ SẮP XẾP THEO TÊN NGƯỜI DÙNG ════════");
        menuSort();
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    showUser(userService.sortByNameUser(TypeSort.ASC));
                    break;
                case 2:
                    showUser(userService.sortByNameUser(TypeSort.DESC));
                    break;
                default:
                    System.out.println("Nhập sai. Xin mời nhập lại!");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Sai cú pháp. Xin mời nhập lại!");
        }
    }

    private void sortById() {
        System.out.println("════════ SẮP XẾP THEO ID ════════");
        menuSort();
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    showUser(userService.sortById(TypeSort.ASC));
                    break;
                case 2:
                    showUser(userService.sortById(TypeSort.DESC));
                    break;
                default:
                    System.out.println("Nhập sai. Xin mời nhập lại!");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Sai cú pháp. Xin mời nhập lại!");
        }
    }

    private void menuSortUser() {
        System.out.println("══════════ SẮP XẾP TÀI KHOẢN ══════════");
        System.out.println("║                                     ║");
        System.out.println("║    1. Sắp xếp theo Id.              ║");
        System.out.println("║    2. Sắp xếp theo tên.             ║");
        System.out.println("║    3. Sắp xếp theo địa chỉ.         ║");
        System.out.println("║    4. Trở lại.                      ║");
        System.out.println("║    0. Thoát.                        ║");
        System.out.println("║                                     ║");
        System.out.println("═══════════════════════════════════════");
        System.out.println("Nhập lựa chọn: ");
    }
}
