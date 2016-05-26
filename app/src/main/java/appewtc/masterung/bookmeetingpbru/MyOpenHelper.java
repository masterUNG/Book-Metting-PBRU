package appewtc.masterung.bookmeetingpbru;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by masterUNG on 5/24/16 AD.
 */
public class MyOpenHelper extends SQLiteOpenHelper{

    //Explicit
    public static final String database_name = "Pbru.db";
    private static final int database_version = 1;
    private static final String create_user_table = "create table userTABLE (" +
            "_id integer primary key," +
            "Name text," +
            "Surname text," +
            "IDcard text," +
            "Office text," +
            "User text," +
            "Password text);";

    private static final String create_order_table = "create table orderTABLE (" +
            "_id integer primary key," +
            "IDcard text," +
            "NameRoom text," +
            "Date text," +
            "Time text);";

    public MyOpenHelper(Context context) {
        super(context, database_name, null,database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_user_table);
        sqLiteDatabase.execSQL(create_order_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}   // Main Class
