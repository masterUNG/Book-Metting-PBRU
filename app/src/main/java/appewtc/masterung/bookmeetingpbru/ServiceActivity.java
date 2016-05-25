package appewtc.masterung.bookmeetingpbru;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class ServiceActivity extends AppCompatActivity {

    private TextView textView;
    private ListView listView;
    private String urlJSON = "http://swiftcodingthai.com/pbru/get_service.php";
    private String[] nameRoomStrings, nameBuildStrings, sizeStrings,
            priceDayStrings, priceHoliStrings, iconStrings, userLoginStrings,
            image2Strings, image3Strings, image4Strings, image5Strings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        userLoginStrings = getIntent().getStringArrayExtra("User");

        //Bind Widget
        textView = (TextView) findViewById(R.id.textView7);
        listView = (ListView) findViewById(R.id.listView);

        textView.setText("Welcome ==> " + userLoginStrings[1] + " " + userLoginStrings[2]);

        SynService synService = new SynService();
        synService.execute();

    }   // Main Method

    public class SynService extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(urlJSON).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                return null;
            }

        }   // doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("pbruV2", "JSON ==> " + s);

            try {

                JSONArray jsonArray = new JSONArray(s);

                nameRoomStrings = new String[jsonArray.length()];
                nameBuildStrings = new String[jsonArray.length()];
                sizeStrings = new String[jsonArray.length()];
                priceDayStrings = new String[jsonArray.length()];
                priceHoliStrings = new String[jsonArray.length()];
                iconStrings = new String[jsonArray.length()];
                image2Strings = new String[jsonArray.length()];
                image3Strings = new String[jsonArray.length()];
                image4Strings = new String[jsonArray.length()];
                image5Strings = new String[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    nameRoomStrings[i] = jsonObject.getString("NameRoom");
                    nameBuildStrings[i] = jsonObject.getString("NameBuild");
                    sizeStrings[i] = jsonObject.getString("Size");
                    priceDayStrings[i] = jsonObject.getString("PriceDay");
                    priceHoliStrings[i] = jsonObject.getString("PriceHoliday");
                    iconStrings[i] = jsonObject.getString("Image1");
                    image2Strings[i] = jsonObject.getString("Image2");
                    image3Strings[i] = jsonObject.getString("Image3");
                    image4Strings[i] = jsonObject.getString("Image4");
                    image5Strings[i] = jsonObject.getString("Image5");

                }   // for

                MyAdapter myAdapter = new MyAdapter(ServiceActivity.this,
                        nameRoomStrings, nameBuildStrings, sizeStrings,
                        priceDayStrings, priceHoliStrings, iconStrings);
                listView.setAdapter(myAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                });


            } catch (Exception e) {
                e.printStackTrace();
            }


        }   // onPost
    }   // SynService Class

}   // Main Class
