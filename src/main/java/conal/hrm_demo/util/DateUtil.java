package conal.hrm_demo.util;

import org.joda.time.DateTimeZone;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class DateUtil {
    public static final String _DATE_MAX = "9999-12-31 23:59:59";
    public static final Date MAX_DATE = new Date(9999, 1, 1);
    public static final String _DATE_MIN = "1000-01-02 00:00:00";
    private static final SimpleDateFormat _fmtYear = new SimpleDateFormat("yyyy");
    private static final SimpleDateFormat _fmtMonth = new SimpleDateFormat("MM");
    private static TimeZone _tz = TimeZone.getTimeZone("UTC");


    public static Date getDatefromString(String dateStr) throws ParseException {
        String actualDate = "";
        String[] dates = dateStr.split("/");
        System.out.println("date leng" + dates.length);
        if (dates.length >= 3) {
            actualDate = dates[1] + "-" + dates[0] + "-" + dates[2];
            DateFormat mediumFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            return mediumFormat.parse(actualDate);
        }
        return MAX_DATE;
    }

    public static Date toDate(String dateStr) {
        return toDate(dateStr, null);
    }


    public static Date toDate(String dateStr, TimeZone tz) {
        if (tz == null) {
            tz = TimeZone.getDefault();
        }
        if (dateStr == null) {
            return null;
        }

        SimpleDateFormat fmt = null;
        if (dateStr.length() == 19) {
            fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            fmt.setTimeZone(tz);
        } else {
            fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        }
        try {
            return fmt.parse(dateStr);
        } catch (ParseException e) {

            return null;
        }
    }

    public static String formatXml(Date date) {
        return formatXml(date, null);
    }

    public static String formatXml(Date date, TimeZone tz) {
        if (tz == null) {
            tz = TimeZone.getDefault();
        }
        if (date.after(getMax())) {
            date = getMax();
        }
        SimpleDateFormat outputXmlFmt
                = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        outputXmlFmt.setTimeZone(tz);
        String result
                = outputXmlFmt.format(date) + " " + getGMTString(date, tz);
        return result;
    }

    public static String toStringWithoutGMT(Date date) {
        return toStringWithoutGMT(date, null);
    }

    public static String toStringWithoutGMT(Date date, TimeZone tz) {
        if (tz == null) {
            tz = TimeZone.getDefault();
        }
        if (date.after(getMax())) {
            date = getMax();
        }
        SimpleDateFormat outputXmlFmt
                = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        outputXmlFmt.setTimeZone(tz);
        return outputXmlFmt.format(date);
    }

    public static String toDbString(Date date) {
        return toDbString(date, (TimeZone) null);
    }

    public static String toDbString(Date date, String timeZone) {
        return toDbString(date, TimeZone.getTimeZone(timeZone));
    }

    public static String toDbString(Date date, TimeZone timezone) {
        if (date == null) {
            return null;
        }
        if (timezone == null) {
            timezone = TimeZone.getDefault();
        }
        SimpleDateFormat dbFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dbFmt.setTimeZone(timezone);
        return dbFmt.format(date);
    }

    public static String toDateString(Date date, TimeZone timezone) {
        if (date == null) {
            return null;
        }
        if (timezone == null) {
            timezone = TimeZone.getDefault();
        }
        SimpleDateFormat dbFmt = new SimpleDateFormat(Constant.API_FORMAT_DATE_TIME);
        dbFmt.setTimeZone(timezone);
        return dbFmt.format(date);
    }

    public static Date getMax() {
        return toDate(_DATE_MAX);
    }

    public static Date getMin() {
        return toDate(_DATE_MIN);
    }

    public static String toYYYYMM(Date date) {
        return toYYYYMM(date, null);
    }

    public static String toYYYYMM(Date date, TimeZone timezone) {
        if (date == null) {
            throw new IllegalArgumentException("date is null.");
        }
        if (timezone == null) {
            timezone = TimeZone.getDefault();
        }
        SimpleDateFormat dbFmt = new SimpleDateFormat("yyyyMM");
        dbFmt.setTimeZone(timezone);
        return dbFmt.format(date);
    }

    public static int getYear(Date date, TimeZone timeZone) {
        if (date == null) {
            throw new IllegalArgumentException("date is required.");
        }
        if (timeZone == null) {
            timeZone = TimeZone.getDefault();
        }
        _fmtYear.setTimeZone(timeZone);
        try {
            return Integer.parseInt(_fmtYear.format(date));
        } catch (NumberFormatException e) {
            throw new IllegalStateException(
                    "Failed to format date : " + e.getMessage());
        }
    }

    public static int getMonth(Date date, TimeZone timeZone) {
        if (date == null) {
            throw new IllegalArgumentException("date is required.");
        }
        if (timeZone == null) {
            timeZone = TimeZone.getDefault();
        }
        _fmtMonth.setTimeZone(timeZone);
        try {
            return Integer.parseInt(_fmtMonth.format(date));
        } catch (NumberFormatException e) {
            throw new IllegalStateException(
                    "Failed to format date : " + e.getMessage());
        }
    }


    public static Date getUTCNow() {
        int offset = TimeZone.getDefault().getOffset(new Date().getTime());
        return new Date(new Date().getTime() - offset);
    }

    public static Date getConvertDate(Date date, TimeZone tz) {
        if (date == null) {
            return null;
        }
        int offset = tz.getRawOffset();
        if (tz.inDaylightTime(date)) {
            offset += 3600000;
        }
        return new Date(date.getTime() + offset);
    }

    public static Date getUTCDate(Date date, TimeZone tz) {
        if (date == null) {
            return null;
        }
        int offset = tz.getRawOffset();
        if (tz.inDaylightTime(date)) {
            offset += 3600000;
        }
        return new Date(date.getTime() - offset);
    }

    public static String toString(Date date) {
        return getConvertDateString(date, _tz, "yyyy-MM-dd HH:mm:ss");
    }

    public static String getConvertDateString(Date date, TimeZone tz, String fmt) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(fmt);
        Date newDete = getConvertDate(date, tz);
        return format.format(newDete);
    }

    public static Date addDate(Date date, TimeZone tz, int add) {
        Calendar cal = Calendar.getInstance(tz);
        cal.setTime(date);
        cal.add(Calendar.DATE, add);
        Date toDate = cal.getTime();
        return toDate;
    }

    ////////////////////////////////////////////////////////////
    // Private methods.
    private static String getGMTString(Date date, TimeZone tz) {
        DecimalFormat fmt = new DecimalFormat("'GMT'+00':00';'GMT'-00':00'");
        int off = tz.getRawOffset();
        if (tz.inDaylightTime(date)) {
            off += 3600000;
        }
        off = off / 3600000;
        return fmt.format(off);
    }


    public static String gethttpDate() {
        final String DateFormat = "EEE, dd MMM yyyy HH:mm:ss ";
        SimpleDateFormat format = new SimpleDateFormat(DateFormat, Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        return format.format(new Date()) + "GMT";
    }

    //We use Joda API time to convert


    public static Date convertToUTC(Date date) {
        DateTimeZone d = DateTimeZone.getDefault();
        return new Date(d.convertLocalToUTC(date.getTime(), false));
    }


    public static Date convertLocalToUTC(Date date, int userTimeZone) {
        DateTimeZone d = DateTimeZone.forOffsetHours(userTimeZone);
        //DateTimeZone d = DateTimeZone.getDefault();
        return new Date(d.convertLocalToUTC(date.getTime(), false));
    }

    public static Date convertToLocalTime(Date date, int hour) {
        DateTimeZone d = DateTimeZone.forOffsetHours(hour);
        return new Date(d.convertUTCToLocal(date.getTime()));
    }

    public static String convertToLocalTimeString(Date date, int hour) {
        String dateString = "";
        Date _date = convertToLocalTime(date, hour);
        String dateFormat = "yyyy/MM/dd hh:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(dateFormat, Locale.US);
        dateString = format.format(_date);
        return dateString;
    }


    public static String getDefaultExpiryDocumentDate() {
        //Date newExpiryDate = new Date(2000, 1, 1);
        //DateFormat fortmatDate = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        //return fortmatDate.format(newExpiryDate);
        return "2012-01-01 23:59:59";
    }


    public static boolean isLastDayOfMonth() {
        try {

            Date today = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(today);
            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.DATE, -1);

            Date lastDayOfMonth = calendar.getTime();

            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (sdf.format(today).equals(sdf.format(lastDayOfMonth))) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.err.println("Checking isLastDayOfMonth error: " + e.getMessage());
            return false;
        }
    }

    public static Date getLastDayOfNextMonth() {
        Date lastDateOfNextMonth = null;
        try {

            Date today = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(today);
            calendar.add(Calendar.MONTH, 2);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.DATE, -1);
            lastDateOfNextMonth = calendar.getTime();

        } catch (Exception e) {
            System.err.println("getLastDayOfNextMonth error: " + e.getMessage());
        }

        return lastDateOfNextMonth;
    }

    public static Date getStartDateTime(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getEndDateTime(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }
}
