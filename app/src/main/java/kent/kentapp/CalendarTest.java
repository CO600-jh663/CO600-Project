package kent.kentapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.CookieManager;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.model.*;

import java.io.InputStream;
import java.net.URL;

public class CalendarTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_calendar_test);

        String url = "https://www.kent.ac.uk/student/my-study/";
        CookieManager cookieManager = CookieManager.getInstance();
        String cookies = cookieManager.getCookie(url);
        int stringIndex = cookies.indexOf("timetable") + 18;
        String calID = cookies.substring(stringIndex, stringIndex + 5);
        InputStream calendarInput;
        try {
            URL calendarUrl = new URL("https://www.kent.ac.uk/timetabling/ical/" + calID + ".ics");
            calendarInput = calendarUrl.openStream();
            CalendarBuilder calendarBuilder = new CalendarBuilder();
            net.fortuna.ical4j.model.Calendar calendar = calendarBuilder.build(calendarInput);
            System.out.println("Made calendar");
        }
        catch(Exception e) {
            System.out.println(e);
            return;
        }

        return;
        // TODO look at ical4j as ical parser
    }


}