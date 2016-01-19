package sample.jsp;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class AbstractController {
    private static final String DATE_FORMAT = "yyyymmdd";
    private static final String VOXEO_FLD_TOMORROW = "tomorrow";

    protected Date getEndDate(Date startDate, int length) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DATE, length);

        return cal.getTime();
    }

    protected Date getStartDate(String stringDate) {
        Date startDate;
        try {
            startDate = new SimpleDateFormat(DATE_FORMAT).parse(stringDate);
        } catch (ParseException e) {
            startDate = new Date();
            if (stringDate.equalsIgnoreCase(VOXEO_FLD_TOMORROW))
                startDate = new Date(startDate.getTime() + (1000 * 60 * 60 * 24));
        }

        return startDate;
    }

    protected void printRequest(String name, HttpServletRequest request) {
        System.out.println(name);
        for (java.util.Map.Entry<String, String[]> s : request.getParameterMap().entrySet()) {
            System.out.print(s.getKey() + ": ");
            for (String v : s.getValue())
                System.out.println(v + ", ");
        }
    }

}
