package pl.drat.dominik.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static final String DB_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm";

    public static String toDBStringFormat(Date date) {
        SimpleDateFormat dbFormat = new SimpleDateFormat(DB_DATE_FORMAT);
        return dbFormat.format(date);
    }

    public static String to_yyyy_MM_dd(Date date) {
        SimpleDateFormat format_yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");
        return format_yyyy_MM_dd.format(date);
    }

    public static boolean nowIsBetween(String startDateStr, String endDateStr) throws ParseException {
        SimpleDateFormat dbFormat = new SimpleDateFormat(DB_DATE_FORMAT);
        Date startDate = dbFormat.parse(startDateStr);
        Date endDate = dbFormat.parse(endDateStr);

        Date now = new Date();

        if (now.after(startDate) && now.before(endDate)) return true;

        return false;
    }

    public static String nowDbFormat(){
        return toDBStringFormat(new Date());
    }
}
