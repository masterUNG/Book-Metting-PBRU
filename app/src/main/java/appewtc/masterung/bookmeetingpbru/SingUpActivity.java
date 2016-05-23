package appewtc.masterung.bookmeetingpbru;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SingUpActivity extends AppCompatActivity {

    //Explicit
    private EditText nameEditText, surnameEditText, idCardEditText,
            userEditText, passwordEditText;
    private RadioGroup radioGroup;
    private RadioButton officeRadioButton, outOfficeRadioButton;
    private String nameString, surnameString, idCardString,
            userString, passwordString, officeString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        //Bind Widget
        bindWidget();


    }   // Main Method

    private void bindWidget() {
        nameEditText = (EditText) findViewById(R.id.editText);
        surnameEditText = (EditText) findViewById(R.id.editText2);
        idCardEditText = (EditText) findViewById(R.id.editText3);
        userEditText = (EditText) findViewById(R.id.editText4);
        passwordEditText = (EditText) findViewById(R.id.editText5);
        radioGroup = (RadioGroup) findViewById(R.id.ragOffice);
        officeRadioButton = (RadioButton) findViewById(R.id.radioButton);
        outOfficeRadioButton = (RadioButton) findViewById(R.id.radioButton2);
    }

    public void clickSignUpSign(View view) {

        nameString = nameEditText.getText().toString().trim();
        surnameString = surnameEditText.getText().toString().trim();
        idCardString = idCardEditText.getText().toString().trim();
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //Check Space
        if (checkSpace()) {
            //Have Space
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "มีช่องว่าง",
                    "กรุณากรอกทุกช่อง คะ");
        } else if (idCardString.length() != 13) {  // Check idCard
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "รหัสบัตรประชาชนผิด",
                    "รหัสบัตรประชาชน ต้องมี 13 หลักเท่านั้น");
        }



    }   // clickSign

    private boolean checkSpace() {

        boolean result = true;

        result = nameString.equals("") ||
                surnameString.equals("") ||
                idCardString.equals("") ||
                userString.equals("") ||
                passwordString.equals("");

        return result;
    }

}   // Main Class
