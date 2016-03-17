package kent.kentapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.CookieManager;
import android.widget.ExpandableListView;
import android.widget.ListView;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.property.DtStamp;

import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

public class CalendarTest extends AppCompatActivity {

    net.fortuna.ical4j.model.Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_test);

        new getIcalIdTask().execute();



        return;
    }

    private class getIcalIdTask extends AsyncTask<Void, Void, Boolean> {

        protected Boolean doInBackground(Void... params) {
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
                calendar = calendarBuilder.build(calendarInput);
                System.out.println("Made calendar");
            }
            catch(Exception e) {
                System.out.println(e);
                return false;
            }
            return true;
        }

        protected void onPostExecute(Boolean complete) {
            ComponentList calendarComponents = calendar.getComponents();
            ArrayList<String[]> stringComponents = new ArrayList<>();
            for(int i = 0; i < calendarComponents.size(); i++) {
                stringComponents.add(calendarComponents.get(i).toString().split("\r\n"));
            }
            String[][] events = new String[stringComponents.size()][5];
            for(int i = 0; i < stringComponents.size(); i++) {
                String[] event = stringComponents.get(i);
                try {
                    DateTime start = new DateTime(event[4].substring(8));
                    events[i][0] = start.toString();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
            final ExpandableListView calendarList = (ExpandableListView) findViewById(R.id.calendar_list);
        }
    }
}