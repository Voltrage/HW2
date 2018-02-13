package danowski19.hw2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String[] face_attributes = {"Afro", "Spike", "Military", "Bowl Cut"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gui);

        //create associations with widgets:
        //SurfaceView
        Face face = (Face) findViewById(R.id.canvas);
        //Button
        Button randomize = (Button) findViewById(R.id.buttonRANDOMIZE);
        //SeekBars
        SeekBar red_bar = (SeekBar) findViewById(R.id.seekBarRED);
        SeekBar green_bar = (SeekBar) findViewById(R.id.seekBarGREEN);
        SeekBar blue_bar = (SeekBar) findViewById(R.id.seekBarBLUE);
        //TextViews
        TextView red_val = (TextView) findViewById(R.id.textViewRED_VALUE);
        TextView green_val = (TextView) findViewById(R.id.textViewGREEN_VALUE);
        TextView blue_val = (TextView) findViewById(R.id.textViewBLUE_VALUE);
        //RadioButton
        RadioGroup attribute = (RadioGroup) findViewById(R.id.ra)

        //Event handler class
        ListenerClass event = new ListenerClass(face);
        event.addBtn(randomize);
        event.addSB(red_bar);
        event.addSB(green_bar);
        event.addSB(blue_bar);
        event.addTV(red_val);
        event.addTV(green_val);
        event.addTV(blue_val);

        //link each widget to the event handler class
        randomize.setOnClickListener(event);
        red_bar.setOnSeekBarChangeListener(event);
        green_bar.setOnSeekBarChangeListener(event);
        blue_bar.setOnSeekBarChangeListener(event);

        //fill Spinner
        ArrayAdapter<String> faceAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, face_attributes);
        Spinner schoolSpinner = (Spinner) findViewById(R.id.spinnerSELECT);
        schoolSpinner.setAdapter(faceAdapter);

    }

}


//https://developer.android.com/guide/topics/ui/controls/radiobutton.html