package com.lukeli.appaday.day2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class MainActivity extends ActionBarActivity {

    private Button answerYesButton, answerNoButton;
    private EditText usersNameEditText;
    private TextView is_friday_textView;
    private String[] fridays  = {"January 2, 2015", "January 9, 2015", "January 16, 2015", "January 23, 2015", "January 30, 2015",
            "February 6, 2015", "February 13, 2015", "February 20, 2015", "February 27, 2015",
            "March 6, 2015", "March 13, 2015", "March 20, 2015", "March 27, 2015"};
    private String tested_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        is_friday_textView = (TextView) findViewById(R.id.is_friday_textView);

        ListAdapter friday_adapter = new CustomAdapter(this, fridays);

        final ListView friday_view = (ListView) findViewById(R.id.fridays_listView);
        friday_view.setAdapter(friday_adapter);

        friday_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String friday_picked = String.valueOf(friday_view.getItemAtPosition(position));
                updateFridayText(friday_picked);
            }
        });
        updateFridayText(randomDate());
    }

    private void updateFridayText(String temp){
        tested_date = temp;
        String question_string = getString(R.string.is_friday);
        String prompt = String.format(question_string, tested_date);
        is_friday_textView.setText(prompt);
    }

    private boolean isFriday(String d){
        try{
            Date date = new SimpleDateFormat("MMM d, yyyy").parse(d);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private String randomDate(){
        GregorianCalendar start_date = new GregorianCalendar(2014, 1, 1);
        GregorianCalendar end_date = new GregorianCalendar(2015, 12, 31);
        long end = end_date.getTimeInMillis();
        long start = start_date.getTimeInMillis();
        long days_between = TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
        Random rand = new Random();
        int random_days =  rand.nextInt((int) days_between + 1);
        start_date.add(Calendar.DAY_OF_MONTH, random_days);
        SimpleDateFormat format1 = new SimpleDateFormat("MMMM d, yyyy");
        return(format1.format(start_date.getTime()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onYesButtonClick(View view) {
        String response = isFriday(tested_date) ? getString(R.string.good_job) : getString(R.string.wrong);
        Toast.makeText(this, response, Toast.LENGTH_SHORT).show();
        updateFridayText(randomDate());
    }

    public void onNoButtonClick(View view) {
        String response = !isFriday(tested_date) ? getString(R.string.good_job) : getString(R.string.wrong);
        Toast.makeText(this, response, Toast.LENGTH_SHORT).show();
        updateFridayText(randomDate());
    }
}
