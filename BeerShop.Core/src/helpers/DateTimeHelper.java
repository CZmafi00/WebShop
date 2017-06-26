package helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mario on 27.5.2017..
 */
public class DateTimeHelper {

    public static String DATETIMEPATTERN = "yyyy/MM/dd hh:mm:ss";
    public static String DATEPATTERN = "yyyy/MM/dd";

    public static String getCurrentDateTime() {

        SimpleDateFormat formatter = new SimpleDateFormat(DateTimeHelper.DATETIMEPATTERN);
        Date date = new Date();

        return formatter.format(date);
    }

    public static boolean laterThen(String dateFrom, String dateToCompare) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat(DateTimeHelper.DATEPATTERN);
        Date from = formatter.parse(dateFrom);
        Date compare = formatter.parse(dateToCompare);

        return from.before(compare);
    }

}
