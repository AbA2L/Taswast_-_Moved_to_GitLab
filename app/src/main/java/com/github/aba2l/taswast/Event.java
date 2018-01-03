package com.github.aba2l.taswast;

/**
 * Created by aba2l on 31/12/17.
 */

public class Event {

    /**
     * Events of amazigh year:
     *      Format:
     *          0: day
     *          1: month
     *          2: event id
     */
    private static int[][] events = new int[][]{
            {1, 1, 1},   {1, 1, 2},   {21, 1, 3},   {24, 1, 4},   {8, 2, 5},   {15, 2, 6},  {15, 2, 7},
            {25, 2, 8},  {4, 3, 9},   {11, 3, 10},  {18, 3, 11},  {25, 3, 12}, {7, 4, 13},  {15, 4, 14},
            {22, 4, 15}, {3, 5, 16},  {22, 5, 17},  {30, 5, 18},  {5, 6, 19},  {14, 6, 20}, {24, 6, 21},
            {12, 7, 22}, {17, 8, 23}, {17, 10, 24}, {29, 10, 25}, {5, 10, 26}, {5, 12, 27}, {12, 12, 28}
    };

    /**
     * Event names:
     *      Working with events id from events table.
     */
    private static String[] eventsNames = new String[]{
            "Lxef ussegas","Udan n yennayer", "Lɛzla", "Imirɤan", "Iɛezriyen", "Anekchum n tefsut", "Tizeggaɤin",
            "Timɤarin", "Legwareh", "Imelhan", "Imehzan", "Aheggan", "Aheggan ihiziyen", "Aheggan n waklan",
            "Gar uheggan d nissan", "Nissan", "Izegzawen", "Iwraɤen", "Iquranen", "Adawel n tefukt", "Lɛinsra",
            "Smayem unevdu", "Smayem umiwan (Lexrif)", "Amedmun n tyerza", "Tagara n tyerza", "Iqecacen n tegrest", "Tuɤalin n tfukt", "Isemaden ivarkanen"};

    /**
     * @param month
     * @return All events of month in 2d table:
     *      format of line:
     *          0: day
     *          1: month
     *          2: event id
     */
    public static int[][] getEventsOfMonth(int month){
        int[] event;
        int[][] monthEvents = new int[0][0];

        for (int i=0; i<events.length; i++){
            event = new int[3];
            if(events[i][1]==month){
                event = events[i];
                monthEvents = addElement(monthEvents, event);
            }
        }

        return monthEvents;
    }

    /**
     * @param id (id of event)
     * @return name of event from table: eventsNames
     */
    public static String getEventById(int id){
        return eventsNames[id-1];
    }

    /**
     * @param old_tab old 2d tab you want to add line
     * @param add_tab line to add as 1d tab
     * @return new 2d table as old_tab+add_tab
     */
    public static int[][] addElement(int[][] old_tab, int[] add_tab) {
        int[][] tab = new int[old_tab.length+1][add_tab.length];

        for (int i=0; i<old_tab.length; i++){
            tab[i] = old_tab[i];
        }
        tab[tab.length-1]=add_tab;

        return tab;
    }

}
