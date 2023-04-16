package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
    private static final String YEAR_PATTERN_FORMAT = "yyyy";
    private static SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm");
    public static String dateToString(Date date) {
        return format.format(date);
    }

    public static Date parseDate(String sDate) {
        try {
            return format.parse(sDate);
        } catch (ParseException parseException) {
            System.out.println("Lỗi định dạng");
        }
        return null;
    }

    public static String dateToStringYear(Date date) {
        return dateToStringYear(date, null);
    }

    public static String dateToStringYear(Date date, String patternFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patternFormat != null ? patternFormat : YEAR_PATTERN_FORMAT).withZone(ZoneId.systemDefault());
        return formatter.format(date.toInstant());
    }
}
