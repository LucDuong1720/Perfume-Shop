package utils;

import java.util.regex.Pattern;

public class ValidateUtils {
    public static final String USERNAME_REGEX = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
    public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[-_!@#&()[{}]:;',?/*~$^+=<>\\.]).{8,20}$";
    public static final String NAME_REGEX = "^([A-ZÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬĐÈẺẼÉẸÊỀỂỄẾỆÌỈĨÍỊÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢÙỦŨÚỤƯỪỬỮỨỰỲỶỸÝỴ][a-zàảãáạăằẳẵắặâầẩẫấậđèẻẽéẹêềểễếệiìỉĩíịòỏõóọôồổỗốộơờởỡớợùủũúụỤưừửữứựỳỷỹýỵ]{0,6} ?)*$";
    public static final String PHONE_REGEX = "^0[1-9][0-9]{8}$";
    public static final String CAPACITY_REGEX = "^[1-9][0-9]{1,2}ml$";
    private static final String ADDRESS_REGEX = "^([A-ZÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬĐÈẺẼÉẸÊỀỂỄẾỆÌỈĨÍỊÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢÙỦŨÚỤƯỪỬỮỨỰỲỶỸÝỴ][a-zàảãáạăằẳẵắặâầẩẫấậđèẻẽéẹêềểễếệiìỉĩíịòỏõóọôồổỗốộơờởỡớợùủũúụỤưừửữứựỳỷỹýỵ]{0,6} ?)*$";
    private static final String YEAR_PATTERN = "\\d{4}$";
    public static boolean isNameValid(String name) {
        return Pattern.matches(NAME_REGEX, name);
    }

    public static boolean isPhoneValid(String phone) {
        return Pattern.matches((PHONE_REGEX), phone);
    }
    public static boolean isUserNameValid(String name) {
        return Pattern.matches((USERNAME_REGEX), name);
    }
    public static boolean isPassWordValid(String password) {
        return Pattern.matches((PASSWORD_REGEX), password);
    }

    public static boolean isAddressValid(String address) {
        return Pattern.matches((ADDRESS_REGEX), address);
    }

    public static boolean isProductNameValid(String s) {
        return Pattern.matches((ADDRESS_REGEX), s);
    }

    public static boolean isBrandValid(String s) {
        return Pattern.matches((ADDRESS_REGEX), s);
    }

    public static boolean isOriginValid(String s) {
        return Pattern.matches((ADDRESS_REGEX), s);
    }

    public static boolean isCapacityValid(String s) {
        return Pattern.matches((CAPACITY_REGEX), s);
    }

    public static boolean isYearValid(String s) {
        return Pattern.matches((YEAR_PATTERN), s);
    }
}
