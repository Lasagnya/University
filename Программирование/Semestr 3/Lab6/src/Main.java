import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args){
        String str1 = "99 Nov 2021 AD";
        Date date = new Date(1212121212121L);
        SimpleDateFormat df = new SimpleDateFormat("k a z WW yyy GG", Locale.ENGLISH);
        System.out.println(df.format(date));
        SimpleDateFormat formater = new SimpleDateFormat("d MMM yyy G", Locale.ENGLISH);    //"d MMM yyy G"
        formater.setLenient(false);
        Calendar calendar = new GregorianCalendar();
        calendar = Calendar.getInstance(Locale.ENGLISH);
        try {
            calendar.setTime(formater.parse(str1));
            calendar.set(Calendar.ERA, GregorianCalendar.AD);
            System.out.println(formater.format(calendar.getTime()));
            calendar.roll(Calendar.MONTH, -2);
            System.out.println(formater.format(calendar.getTime()));
        } catch (ParseException e) {
            System.out.println("Date is incorrect");
        }
        calendar.setTime(new Date());
        Formatter f = new Formatter(Locale.ENGLISH);
        f.format("%ta %tI %tN %tI:%tM %tp", calendar, calendar, calendar, calendar, calendar, calendar);
        System.out.println(f);
    }
}
