package appewtc.masterung.bookmeetingpbru;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private TextView nameRoomTextView, nameBuildTextView, sizeTextView,
            priceDayTextView, priceHoliTextView;
    private ImageView imageView;
    private String nameRoomString, nameBuildString, sizeString,
            priceDayString, priceHoliString,
            image1String, image2String, image3String, image4String, image5String;
    private int myImage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        bindWidget();

        receiveValue();

        showTextView();

        showImage(1);


    }   // Main Method

    public void clickBack(View view) {
        finish();
    }

    public void clickOrder(View view) {

    }

    public void clickIncrease(View view) {

        if (myImage == 5) {
            myImage = 1;
        } else {
            myImage += 1;
        }

        showImage(myImage);

    }

    public void clickDecrease(View view) {

        if (myImage == 1) {
            myImage = 5;
        } else {
            myImage -= 1;
        }

        showImage(myImage);

    }



    private void showImage(int intImage) {

        String urlImage = image1String;

        switch (intImage) {
            case 1:
                urlImage = image1String;
                break;
            case 2:
                urlImage = image2String;
                break;
            case 3:
                urlImage = image3String;
                break;
            case 4:
                urlImage = image4String;
                break;
            case 5:
                urlImage = image5String;
                break;
        }   // switch

        Picasso.with(this).load(urlImage).resize(250,200).into(imageView);

    }

    private void showTextView() {
        nameRoomTextView.setText(nameRoomString);
        nameBuildTextView.setText(nameBuildString);
        sizeTextView.setText(sizeString);
        priceDayTextView.setText(priceDayString);
        priceHoliTextView.setText(priceHoliString);
    }

    private void receiveValue() {

        nameRoomString = getIntent().getStringExtra("NameRoom");
        nameBuildString = getIntent().getStringExtra("NameBuild");
        sizeString = getIntent().getStringExtra("Size");
        priceDayString = getIntent().getStringExtra("PriceDay");
        priceHoliString = getIntent().getStringExtra("PriceHoliday");
        image1String = getIntent().getStringExtra("Image1");
        image2String = getIntent().getStringExtra("Image2");
        image3String = getIntent().getStringExtra("Image3");
        image4String = getIntent().getStringExtra("Image4");
        image5String = getIntent().getStringExtra("Image5");

    }

    private void bindWidget() {

        nameRoomTextView = (TextView) findViewById(R.id.textView13);
        nameBuildTextView = (TextView) findViewById(R.id.textView14);
        sizeTextView = (TextView) findViewById(R.id.textView15);
        priceDayTextView = (TextView) findViewById(R.id.textView16);
        priceHoliTextView = (TextView) findViewById(R.id.textView17);
        imageView = (ImageView) findViewById(R.id.imageView3);

    }

}   // Main Class
