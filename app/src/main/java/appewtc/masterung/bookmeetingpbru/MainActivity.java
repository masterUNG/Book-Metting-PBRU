package appewtc.masterung.bookmeetingpbru;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private MyManage myManage;
    private String strURL = "http://swiftcodingthai.com/pbru/get_user.php";
    private String strLogo = "http://swiftcodingthai.com/pbru/Image/logo_pbru1.png";
    private ImageView imageView;
    private EditText userEditText, passwordEditText;
    private String userString, passwordString;
    private String[] userLoginStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        imageView = (ImageView) findViewById(R.id.imageView);
        userEditText = (EditText) findViewById(R.id.editText6);
        passwordEditText = (EditText) findViewById(R.id.editText7);

        //Show logo
        Picasso.with(this).load(strLogo).into(imageView);


        myManage = new MyManage(this);

        //Test Add User
        //myManage.addUser("name", "sur", "idcard", "Off", "user", "pass");

        //Delete All SQLite
        deleteAllSQLite();

        synJSON();

    }   // Main Method

    public void clickSignIn(View view) {

        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //Check Space
        if (userString.equals("") || passwordString.equals("")) {
            //Have Space
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "มีช่องว่าง", "กรุณากรอกทุกช่อง คะ");

        } else {
            //No Space
            searchUser(userString);
        }

    }   // clickSignIn

    private void searchUser(String userString) {

        try {

            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                    MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userTABLE WHERE User = " + "'" + userString + "'", null);
            cursor.moveToFirst();
            userLoginStrings = new String[cursor.getColumnCount()];
            for (int i=0;i<cursor.getColumnCount();i++) {
                userLoginStrings[i] = cursor.getString(i);
            }   // for
            cursor.close();

            //Check Password
            if (passwordString.equals(userLoginStrings[6])) {
                //Password True
                Intent intent = new Intent(MainActivity.this, ServiceActivity.class);
                intent.putExtra("User", userLoginStrings);
                startActivity(intent);
                finish();

            } else {
                //Password False
                MyAlert myAlert = new MyAlert();
                myAlert.myDialog(this, "Password False", "Please Try Again Password False");

            }


        } catch (Exception e) {
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "ไม่มี User นี่้", "ไม่มี " + userString + " ในฐานข้อมูลของเรา");
        }

    }   // searchUser

    private void synJSON() {
        SynUser synUser = new SynUser();
        synUser.execute();
    }

    //Create Inner Class
    public class SynUser extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(strURL).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                return null;
            }

        }   // doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("pbruV1", "strJSON ==> " + s);

            try {

                JSONArray jsonArray = new JSONArray(s);

                for (int i=0;i<jsonArray.length();i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String strName = jsonObject.getString(MyManage.column_Name);
                    String strSurname = jsonObject.getString(MyManage.column_Surname);
                    String strIDcard = jsonObject.getString(MyManage.column_IDcard);
                    String strOffice = jsonObject.getString(MyManage.column_Office);
                    String strUser = jsonObject.getString(MyManage.column_User);
                    String strPassword = jsonObject.getString(MyManage.column_Password);
                    myManage.addUser(strName, strSurname, strIDcard,
                            strOffice, strUser, strPassword);

                }   // for

            } catch (Exception e) {
                e.printStackTrace();

            }

        }   // onPost
    }   // SynUser Class

    private void deleteAllSQLite() {

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        sqLiteDatabase.delete(MyManage.user_table, null, null);
    }

    public void clickSingUpMain(View view) {
        startActivity(new Intent(MainActivity.this, SingUpActivity.class));
    }

}   // Main Class
