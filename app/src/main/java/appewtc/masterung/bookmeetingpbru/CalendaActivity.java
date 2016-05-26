package appewtc.masterung.bookmeetingpbru;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class CalendaActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private Spinner spinner;
    private RadioGroup radioGroup;
    private RadioButton beforeNoonRadioButton, afterNoonRadioButton,
            fullDayRadioButton;
    private String idCardString, nameRoomString, dateString, timeString;
    private String[] userLoginStrings;
    private int dayAnInt, monthAnInt, yearAnInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calenda);

        bindWidget();

        getValue();

        createSpinner();

        calendarController();


    }   // Main Method

    private void calendarController() {

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int day) {

                dayAnInt = day;
                monthAnInt = month;
                yearAnInt = year;

            }
        });

    }   // calendar

    private void getValue() {

        userLoginStrings = getIntent().getStringArrayExtra("User");
        idCardString = userLoginStrings[3];
        nameRoomString = getIntent().getStringExtra("NameRoom");

    }

    private void createSpinner() {

        String[] strAmount = {"1 วัน", "2 วัน", "3 วัน", "4 วัน", "5 วัน",
                "6 วัน", "7 วัน", "8 วัน", "9 วัน", "10 วัน"};
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, strAmount);
        spinner.setAdapter(stringArrayAdapter);


    }   // createSpinner

    private void bindWidget() {
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        spinner = (Spinner) findViewById(R.id.spinner);
        radioGroup = (RadioGroup) findViewById(R.id.ragTime);
        beforeNoonRadioButton = (RadioButton) findViewById(R.id.radioButton3);
        afterNoonRadioButton = (RadioButton) findViewById(R.id.radioButton4);
        fullDayRadioButton = (RadioButton) findViewById(R.id.radioButton5);
    }

    public void clickOrderCalendar(View view) {


    }   // clickOrder

}   // Main Class
