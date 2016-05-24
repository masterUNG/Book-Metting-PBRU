package appewtc.masterung.bookmeetingpbru;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by masterUNG on 5/24/16 AD.
 */
public class MyManage {

    //Explicit
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public static final String user_table = "userTABLE";
    public static final String column_id = "_id";
    public static final String column_Name = "Name";
    public static final String column_Surname = "Surname";
    public static final String column_IDcard = "IDcard";
    public static final String column_Office = "Office";
    public static final String column_User = "User";
    public static final String column_Password = "Password";

    public MyManage(Context context) {

        myOpenHelper = new MyOpenHelper(context);
        sqLiteDatabase = myOpenHelper.getWritableDatabase();

    }   // Constructor

    public long addUser(String strName,
                        String strSurname,
                        String strIDcard,
                        String strOffice,
                        String strUser,
                        String strPassword) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(column_Name, strName);
        contentValues.put(column_Surname, strSurname);
        contentValues.put(column_IDcard, strIDcard);
        contentValues.put(column_Office, strOffice);
        contentValues.put(column_User, strUser);
        contentValues.put(column_Password, strPassword);

        return sqLiteDatabase.insert(user_table, null, contentValues);
    }

}   // Main Class
