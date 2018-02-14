//package danowski19.hw2;
//
//import android.content.res.Resources;
//import android.provider.MediaStore;
//import android.view.SurfaceView;
//import android.view.View;
//import android.widget.Button;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.SeekBar;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
///**
// * Created by Luke on 2/10/2018.
// */
//
//public class ListenerClass implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
//
//
//    private ArrayList<TextView> allTVs = new ArrayList<>();
//    private ArrayList<SeekBar> allSBs = new ArrayList<>();
//    private ArrayList<RadioButton> allRBs = new ArrayList<>();
//    private ArrayList<Button> allBtns = new ArrayList<>();
//    private Face face;
//
//
//    public ListenerClass(Face newView) {
//        this.face = newView;
//    }
//
//
//    public void addTV(TextView another) {
//        allTVs.add(another);
//    }
//
//    public void addSB(SeekBar another) {
//        allSBs.add(another);
//    }
//
//    public void addRB(RadioButton another) {
//        allRBs.add(another);
//    }
//
//    public void addBtn(Button another) {
//        allBtns.add(another);
//    }
//
//
//    @Override
//    public void onClick(View v) {
//
//        Button clicked;
//
//        //idea came from https://developer.android.com/guide/topics/ui/controls/radiobutton.html
//        switch (v.getId()) {
//            case R.id.buttonRANDOMIZE:
//                clicked = (Button) v;
//                clicked.setClickable(false);
//
//                face.randomize();
//
//
//                //Update all SeekBars and TextViews
//
//                int R_progress =  ( face.getSkinColor() & 0x00ff0000 ) >> 16 ;
//                int G_progress =  ( face.getSkinColor() & 0x0000ff00 ) >> 8 ;
//                int B_progress = face.getSkinColor() & 0x000000ff;
//
//                for (SeekBar sb : allSBs) {
//
//                    switch (sb.getId()) {
//                        case R.id.seekBarRED:
//                            sb.setProgress(R_progress);
//                            break;
//                        case R.id.seekBarGREEN:
//                            sb.setProgress(G_progress);
//                            break;
//                        case R.id.seekBarBLUE:
//                            sb.setProgress(B_progress);
//                            break;
//                    }
//                }
//
//
//                for (TextView tv : allTVs) {
//
//
//                    switch (tv.getId()) {
//                        case R.id.textViewRED_VALUE:
//                            tv.setText("" + R_progress);
//                            break;
//                        case R.id.textViewGREEN_VALUE:
//                            tv.setText("" + G_progress);
//                            break;
//                        case R.id.textViewBLUE_VALUE:
//                            tv.setText("" + B_progress);
//                            break;
//                    }
//                }
//
//
//                clicked.setClickable(true);
//
//
//                break;
//
//
//        }
//    }
//
//    @Override
//    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//
//        for( TextView tv : allTVs) {
//
//            switch (seekBar.getId()) {
//                case R.id.seekBarRED:
//
//                    break;
//                case R.id.seekBarGREEN:
//
//                    break;
//
//                case R.id.seekBarBLUE:
//
//                    break;
//            }
//
//        }
//    }
//
//    @Override
//    public void onStartTrackingTouch(SeekBar seekBar) {
//
//    }
//
//    @Override
//    public void onStopTrackingTouch(SeekBar seekBar) {
//
//    }
//
//
//}
