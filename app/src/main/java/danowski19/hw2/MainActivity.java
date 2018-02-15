package danowski19.hw2;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Luke on 2/10/2018.
 *  Meant to be run on a Pixel C with API 25.1.1 in vertical format
 * @author Luke Danowski
 */

public class MainActivity
        extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener, View.OnClickListener,
        SeekBar.OnSeekBarChangeListener, RadioGroup.OnCheckedChangeListener {

    /**
     * External Citation  Date:     2/12/2018
     * Problem:  Trouble passing in values
     * Resource: https://www.journaldev.com/10251/android-radio-button
     * Solution: I restructured my code so that I didn't need a listener class like in the example.
     */

    //
    Face face;
    Button randomizeBtn;
    SeekBar red_bar;
    SeekBar green_bar;
    SeekBar blue_bar;
    TextView red_val;
    TextView green_val;
    TextView blue_val;
    RadioGroup attrib_RB;
    Spinner style_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gui);

        //create associations with widgets:
        //SurfaceView
        face = (Face) findViewById(R.id.canvas);
        //Button
        randomizeBtn = (Button) findViewById(R.id.buttonRANDOMIZE);
        //SeekBars
        red_bar = (SeekBar) findViewById(R.id.seekBarRED);
        green_bar = (SeekBar) findViewById(R.id.seekBarGREEN);
        blue_bar = (SeekBar) findViewById(R.id.seekBarBLUE);
        //TextViews
        red_val = (TextView) findViewById(R.id.textViewRED_VALUE);
        green_val = (TextView) findViewById(R.id.textViewGREEN_VALUE);
        blue_val = (TextView) findViewById(R.id.textViewBLUE_VALUE);
        //RadioButton
        attrib_RB = (RadioGroup) findViewById(R.id.radioGroup_ATTRIBUTE);
        //spinner
        style_spinner = (Spinner) findViewById(R.id.spinnerSELECT);

        //link each widget to the event handler class
        randomizeBtn.setOnClickListener(this);
        attrib_RB.setOnCheckedChangeListener(this);
        red_bar.setOnSeekBarChangeListener(this);
        green_bar.setOnSeekBarChangeListener(this);
        blue_bar.setOnSeekBarChangeListener(this);
        style_spinner.setOnItemSelectedListener(this);

        /** External Citation  Date:     2/14/2018
         * Problem:  Wanted to list the strings in xml
         * Resource: I used the 376 lab files
         * Solution: I got the string using the getResources().getStringArray(R.array.hair_style_list)
         */
        //Creating the ArrayAdapter instance having the hairstyle list
        ArrayAdapter<String> faceAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                        getResources().getStringArray(R.array.hair_style_list));

        //Setting the ArrayAdapter data on the Spinner
        style_spinner.setAdapter(faceAdapter);

        updateSettingsFromFaceValues();

    }


    /**
     * Changes the seekbar progress and textview values to match
     * the color values in the instance of "face" based on which
     * radiobutton is checked.
     */
    private void updateSettingsFromFaceValues() {


        /** External Citation  Date:     2/14/2018
         * Problem:  Couldn't get spinner to change
         * Resource: I asked and Stelios answered
         * Solution: i added the setSelection method
         */
        style_spinner.setSelection(face.getHairStyle());

        //variable to hold the color value
        int useThisColorRGB[] = {0, 0, 0};

        //get current values from instance of "face"
        switch (attrib_RB.getCheckedRadioButtonId()) {
            case R.id.radioButton_SKIN:
                useThisColorRGB = face.getColorRGB(0);
                break;
            case R.id.radioButton_EYES:
                useThisColorRGB = face.getColorRGB(1);
                break;
            case R.id.radioButton_HAIR:
                useThisColorRGB = face.getColorRGB(2);
                break;
        }

        //update seekBars and textViews
        red_bar.setProgress(useThisColorRGB[0]);
        red_val.setText("" + red_bar.getProgress());
        green_bar.setProgress(useThisColorRGB[1]);
        green_val.setText("" + green_bar.getProgress());
        blue_bar.setProgress(useThisColorRGB[2]);
        blue_val.setText("" + blue_bar.getProgress());

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonRANDOMIZE:
                //for duration don't listen to another click
                randomizeBtn.setClickable(false);

                face.randomize();

                //Update all SeekBars and TextViews
                updateSettingsFromFaceValues();

                //allow button to be pressed again
                randomizeBtn.setClickable(true);
                break;

        }
    }


    //method used to update the GUI when a seekbar is changed
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        // update color value being changed in the face object
        // which color gets altered is based on the currently selected radioButton.
        switch (attrib_RB.getCheckedRadioButtonId()) {
            case R.id.radioButton_SKIN:
                face.setSkinColor(red_bar.getProgress(), green_bar.getProgress(), blue_bar.getProgress());
                break;
            case R.id.radioButton_EYES:
                face.setEyeColor(red_bar.getProgress(), green_bar.getProgress(), blue_bar.getProgress());
                break;
            case R.id.radioButton_HAIR:
                face.setHairColor(red_bar.getProgress(), green_bar.getProgress(), blue_bar.getProgress());
                break;
        }

        //update TextView
        switch (seekBar.getId()) {
            case R.id.seekBarRED:
                red_val.setText("" + progress);
                break;
            case R.id.seekBarGREEN:
                green_val.setText("" + progress);
                break;
            case R.id.seekBarBLUE:
                blue_val.setText("" + progress);
                break;
        }

    }


    //unused seekbar listener method
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //dont care
    }

    //unused seekbar listener method
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //dont care
    }

    /**
     * External Citation  Date:     2/13/2018
     * Problem:  Needed to know how to incorporate the spinner
     * Resource: https://developer.android.com/guide/topics/ui/controls/radiobutton.html
     * Solution: I learned about it and implemented the listener.
     */
    //listener method that updates the GUI when a RadioButton is selected
    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        if (group.getId() == R.id.radioGroup_ATTRIBUTE) {
            this.updateSettingsFromFaceValues();
        }

    }

    /**
     * External Citation  Date:     2/13/2018
     * Problem:  Needed to know how to incorporate the spinner
     * Resource: https://www.javatpoint.com/android-spinner-example
     * https://www.tutorialspoint.com/android/android_spinner_control.html
     * Solution: I used the listener and method.
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        face.setHairStyle(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}


