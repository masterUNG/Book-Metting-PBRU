package appewtc.masterung.bookmeetingpbru;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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
    private int dayAnInt, monthAnInt, yearAnInt, loopDayAnInt = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calenda);

        bindWidget();

        getValue();

        createSpinner();

        calendarController();

        radioController();


    }   // Main Method

    private void radioController() {

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {
                    case R.id.radioButton3:
                        timeString = "0";
                        break;
                    case R.id.radioButton4:
                        timeString = "1";
                        break;
                    case R.id.radioButton5:
                        timeString = "2";
                        break;
                }

            }
        });

    }   // radioController

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

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loopDayAnInt = i + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                loopDayAnInt = 1;
            }
        });


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

        if (checkRadioButton()) {
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "ยังไม่เลื่อกเวลา", "โปรดเลือกเวลา");
        } else {
            updateToServer();
        }


    }   // clickOrder

    private void updateToServer() {

    }   // updateToServer

    private boolean checkRadioButton() {

        if (beforeNoonRadioButton.isChecked() ||
                afterNoonRadioButton.isChecked() ||
                fullDayRadioButton.isChecked()) {
            return false;
        } else {
            return true;
        }

    }

}   // Main Class
