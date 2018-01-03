package com.github.aba2l.taswast;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by aba2l on 23/12/17.
 */

public class AmazighCalendar {

    private static Calendar calendar;       // Georgian calendar
    private static Calendar AmazighCal;     // Amazigh calendar

    private static int dec = 0;             // Count month decalage!
    private static int firstDayOfMonth = 0; // Save Fist day of month

    private static String[] eventsOfMonth;  // Events of month

    /**
     * Table of months in tamaziƔt:
     *        En          Tm
     *      --------------------
     *      January     Yennayer
     *      February    Furar
     *      March       Meɤres
     *      April       Yebrir
     *      May         Mayyu
     *      June        Yunyu
     *      July        Yulyu
     *      August      Ɣuct
     *      September   Ctember
     *      October     Tuber
     *      November    Unbir
     *      December    Dumber
     */
    private static String[] months = new String[]{
            "Yennayer", "Furar", "Meɤres", "Yebrir", "Mayyu", "Yunyu",
            "Yulyu",    "Ɣuct",  "Ctember","Tuber",  "Unbir", "Dumber"};

    /**
     * Table of week days in tamaziƔt:
     *       En      Tm
     *      -------------
     *      Mon     Arim
     *      Tue     Aram
     *      Wed     Ahad
     *      Thu     Amhad
     *      Fri     Sem
     *      Sat     Sed
     *      Sun     Ačer
     */
    private static String[] week_days = new String[]{
            "ari", "ara", "aha", "amh", "sem", "sed", "ače"};

    /**
     * Initialize calendar:
     *      Working:
     *          Init (calendar) as georgian calendar.
     *          Set (AmazighCal) as amazigh calendar with convertDateToAmazigh(calendar) funtion.
     */
    public static void initCalendar(){
        calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        AmazighCal = convertDateToAmazigh(calendar);
    }

    /**
     * Get amazigh date.
     * @return Date as String with format: "dd MMMM yyyy"
     */
    public static String getAmazighDate(){
        if (dec==0){
            return
                    new SimpleDateFormat("dd").format(AmazighCal.getTime())+" "+
                            months[Integer.parseInt(new SimpleDateFormat("MM").format(AmazighCal.getTime()))-1]+" "+
                            Integer.parseInt(new SimpleDateFormat("yyyy").format(AmazighCal.getTime()));
        } else{
            return
                    months[Integer.parseInt(new SimpleDateFormat("MM").format(AmazighCal.getTime()))-1]+" "+
                            Integer.parseInt(new SimpleDateFormat("yyyy").format(AmazighCal.getTime()));
        }
    }

    /**
     * Get Week Days Table.
     * @return week days as String table.
     */
    public static String[] getWeekDays(){
        return week_days;
    }

    /**
     * Get Months Table.
     * @return months as String table.
     */
    public static String[] getMonths(){
        return months;
    }

    /**
     * Get table of month days:
     * @return month days as 1D table:
     *      Using functions:
     *          - getDaysOfMonth()
     *          - convertCalendarToTable()
     */
    public static String[] tableMonth(){
        return getDaysOfMonth(convertCalendarToTable(AmazighCal));
    }

    /**
     * Get table of month days images.
     * @return month day image in 1D table.
     */
    public static int[] tableMonthImgDay(){
        int[] gridViewImageId = new int[42];
        for (int i=0; i<gridViewImageId.length; i++){
            if (i==Integer.parseInt(
                    new SimpleDateFormat("dd").format(AmazighCal.getTime())) && dec==0){
                gridViewImageId[i] = R.drawable.ic_bar_now_day;
            } else {
                gridViewImageId[i]=0;
            }
        }
        return gridViewImageId;
    }

    /**
     * Get table of month events images.
     * @return month events images as 1D table.
     */
    public static int[] tableMonthImgEvent(){
        int[] gridViewImageId = new int[42];
        boolean[] event = getEventsOfThisMonth();
        for (int i=0; i<gridViewImageId.length; i++){
            if (event[i]){
                gridViewImageId[i] = R.drawable.ic_cyrcle_event_day;
            } else {
                gridViewImageId[i]=0;
            }
        }
        return gridViewImageId;
    }

    /**
     * Increment or decrement month
     * @param add if it true increment else decrement month
     */
    public static void  monthAdd(boolean add){
        if (add == true){
            AmazighCal.add(Calendar.MONTH, 1);
            dec++;
        } else if (add == false){
            AmazighCal.add(Calendar.MONTH, -1);
            dec--;
        }
    }

    /**
     * Get event name with position
     * @param position (position of day
     * @return String contains events of day.
     */
    public static String getDayEventName(int position){
        String name = eventsOfMonth[position];
        if(name!=null){
            return "Ass n "+position+": "+name;
        } else {
            return "Ulac walou ass n "+position+"!!!";
        }
    }

    /**
     * Convert normal date to Amazigh date:
     *      Time difference between the georgian calendar and the amazigh calendar is 950years and 11 days.
     *      Working:
     *          add (-11) days to the cretian calendar.
     *          add (950) years to the cretian calendar.
     * @param c georgian calendar
     * @return amazigh calendar
     */
    private static Calendar convertDateToAmazigh(Calendar c){
        c.add(Calendar.DATE, -11);
        c.add(Calendar.YEAR, 950);
        return c;
    }

    /**
     * Convert calendar to int table:
     * @param c calendar
     * @return int table who contains date as table.
     * Format:
     *          0: Day of week
     *          1: Day of month
     *          2: Month
     *          3: Year (Creatian)
     */
    private static int[] convertCalendarToTable(Calendar c){
        return new int[]{
                c.get(Calendar.DAY_OF_WEEK)-1,
                Integer.parseInt(new SimpleDateFormat("dd").format(c.getTime())),
                Integer.parseInt(new SimpleDateFormat("MM").format(c.getTime())),
                Integer.parseInt(new SimpleDateFormat("yyyy").format(c.getTime()))
        };
    }

    /**
     * Get Days Of Month
     *      This algorithm gives all days of month in a String table of (7*(4+2))=42 cases
     * @param dt
     * @return String table with all days of month view.
     */
    private static String[] getDaysOfMonth(int dt[]){
        String[] dom = new String[42];
        dt[1] = 1;
        dt[2] = dt[2];

        Calendar Cal = new GregorianCalendar(dt[3], dt[2], dt[1]);

        Cal.add(Calendar.MONTH, -1);
        int currentDay = (Arrays.asList(
                new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"}).indexOf(
                Cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()))+1);
        firstDayOfMonth = currentDay-2;
        Log.d("AmazighCalendar", "cerrent day: "+firstDayOfMonth);
        Cal.add(Calendar.MONTH, 1);

        Cal.add(Calendar.DAY_OF_YEAR, Calendar.SUNDAY - currentDay);
        int gridSizeX = 7, gridSizeY = 6, n=0;
        for (int i = 0; i < gridSizeY; i++)
        {
            for (int j = 0; j < gridSizeX; j++)
            {
                dom[n]=Cal.get(Calendar.DAY_OF_MONTH)+"";

                Cal.add(Calendar.DAY_OF_YEAR, 1);
                n++;
            }
        }
        dt = null;
        return dom;
    }

    /**
     * Get emplacement of all events of this month
     * @return boolean table, true cases are the day who contains events
     */
    private static boolean[] getEventsOfThisMonth(){
        boolean[] tab = new boolean[42];
        int[][] eventDays = Event.getEventsOfMonth(
                Integer.parseInt(new SimpleDateFormat("MM").format(AmazighCal.getTime())));
        eventsOfMonth = new String[31];

        for(int i=0; i<tab.length; i++){
            tab[i]=false;
        }
        for (int i=0; i<eventDays.length; i++){
            Log.d("########## Event", "day: "+eventDays[i][0]);
            tab[eventDays[i][0]+firstDayOfMonth]=true;
            eventsOfMonth[eventDays[i][0]]=Event.getEventById(eventDays[i][2]);
        }

        return tab;
    }
}
