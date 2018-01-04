package com.github.aba2l.taswast;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class CreditsActivity extends AppCompatActivity {

    public static GridView contributorsView;

    /**
     * Contributors
     */
    private static String[] contributors = new String[]{
            "AbA2L\n Developer and project manager",
            "Ferhat\n Documentation contributor",
            "Araw n telleli\n Documentation contributors and facebook group",
            "GitHub Project\n Take a look to the GitHub project",
    };

    /**
     * Links of the contributors
     */
    private static String[] links = new String[]{
            "https://www.twitter.com/AbA2L1",
            "https://www.facebook.com/aqvayli.athahmed.7",
            "https://www.facebook.com/ArrawnTlellii/",
            "https://www.github.com/AbA2L/Taswast"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        contributorsView = (GridView) findViewById(R.id.contributors_view);

        contributorsView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, contributors));
        contributorsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(links[position])));
            }
        });
    }
}
