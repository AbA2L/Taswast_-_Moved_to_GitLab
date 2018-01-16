package com.github.aba2l.taswast;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static String vertion = "V0.9-Beta2";

    private static TextView monthView;
    private static Button previous;
    private static Button next;
    private static GridView gridView;
    private static GridView week_daysView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**
         * Init UI components:
         *      monthView (Views "dd mmmm yyyy" in amazigh date)
         *      previous (Button to increment by (-1) number of months)
         *      next (Button to increment by (+1) number of months)
         *      week_daysView (Grid view to show the 7 days of month in thamazight)
         *      gridView (Grid view to show the 42 days of (month + ~2week))
         */
        monthView = (TextView) findViewById(R.id.month);
        previous = (Button) findViewById(R.id.previous);
        next = (Button) findViewById(R.id.next);
        week_daysView = (GridView) findViewById(R.id.week_daysView);
        gridView = (GridView) findViewById(R.id.gridview);

        /**
         * Default init:
         */
        AmazighCalendar.initCalendar();

        /**
         * Display Date:
         */
        monthView.setText(AmazighCalendar.getAmazighDate());

        /**
         * Calendar Month Table:
         *  Week Days (GridView)
         *  Month Table(GridView)
         */
        week_daysView.setAdapter(new WeekGridView(
                MainActivity.this,
                AmazighCalendar.getWeekDays()));
        gridView.setAdapter(new MonthGridView(
                MainActivity.this,
                AmazighCalendar.tableMonth(),
                AmazighCalendar.tableMonthImgDay(),
                AmazighCalendar.tableMonthImgEvent()));

        /**
         * OnClick:
         *  Previous
         *  Next
         *  ManthTable(GrideView)
         *  On swipe (Next/Previous) from week_daysView
         */
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousMonth(MainActivity.this);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextMonth(MainActivity.this);
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                int day = Integer.parseInt(
                        ((TextView) v.findViewById(R.id.android_gridview_text)).getText()+"");
                if(!(((position<6)&&(day>27)) || ((position>30)&&(day<12)))){
                    Toast.makeText(
                            getApplicationContext(),
                            AmazighCalendar.getDayEvents(day), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(
                            getApplicationContext(),
                            "Ifeɤ deg lehsav", Toast.LENGTH_SHORT).show();
                }
            }
        });
        week_daysView.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeRight() {
                previousMonth(MainActivity.this);
            }
            public void onSwipeLeft() {
                nextMonth(MainActivity.this);
            }
        });
    }

    /**
     * Initialize menu
     * @param menu
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * OnClick actions of items from the 3 dots at the top of activity
     * @param item
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            /**
             * Do not start settings because there are the default activity automatically generated.
             */
            //startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        } else if (id == R.id.action_cerdits) {
            startActivity(new Intent(MainActivity.this, CreditsActivity.class));
            return true;
        } else if (id == R.id.report_bug){
            startActivity(new Intent(
                    Intent.ACTION_VIEW, Uri.parse("https://www.github.com/AbA2L/Taswast/issues/new")));
            return true;
        } else if (id == R.id.action_vertion){
            Toast.makeText(
                    getApplicationContext(),
                    "Taswast "+vertion, Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Jump to the next month
     * @param context
     */
    public static void nextMonth(Context context){
        // decrement 1 month
        AmazighCalendar.monthAdd(true);
        // display date
        monthView.setText(AmazighCalendar.getAmazighDate());
        // update days of month
        gridView.setAdapter(new MonthGridView(
                context,
                AmazighCalendar.tableMonth(),
                AmazighCalendar.tableMonthImgDay(),
                AmazighCalendar.tableMonthImgEvent()));
    }

    /**
     * Jump to the previous month
     * @param context
     */
    public static void previousMonth(Context context){
        // increment 1 month
        AmazighCalendar.monthAdd(false);
        // display date
        monthView.setText(AmazighCalendar.getAmazighDate());
        // update days of month
        gridView.setAdapter(new MonthGridView(
                context,
                AmazighCalendar.tableMonth(),
                AmazighCalendar.tableMonthImgDay(),
                AmazighCalendar.tableMonthImgEvent()));
    }
}