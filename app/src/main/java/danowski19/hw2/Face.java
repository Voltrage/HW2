package danowski19.hw2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.util.Random;

/**
 * Created by Luke on 2/10/2018.
 */

public class Face extends SurfaceView {

    private static int skinColor = 0xff000000;
    private static int eyeColor = 0xff000000;
    private static int hairColor = 0xff000000;
    private static int hairStyle = 0xff000000;
    private static int style = 0;

    private Random rand_gen = new Random();


    /**
     * constructor
     *
     * @param context
     */
    public Face(Context context) {
        super(context);
        general_init();
    }

    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);
        general_init();
    }

    public Face(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        general_init();
    }


    private void general_init() {
        //initialize as drawable
        setWillNotDraw(false);

        //initalize variables
        randomize();

        this.setZOrderOnTop(true);

    }

    /**
     * getter methods
     * @return
     */
    public static int getSkinColor() {
        return skinColor;
    }

    public static int getEyeColor() {
        return eyeColor;
    }

    public static int getHairColor() {
        return hairColor;
    }

    public static int getHairStyle() {
        return hairStyle;
    }



    /**
     * setter methods
     *
     * @param R
     * @param G
     * @param B
     */
    public static void setSkinColor(int R, int G, int B) {
        skinColor = Color.rgb(R, G, B);
    }

    public static void setEyeColor(int R, int G, int B) {
        eyeColor = Color.rgb(R, G, B);
    }

    public static void setHairColor(int R, int G, int B) {
        hairColor = Color.rgb(R, G, B);
    }

    public Paint toPaint(int color) {
        Paint x = new Paint();
        x.setColor(color);
        return x;
    }

    /**
     * Initializes all the variables to randomly selected valid values.
     */
    public void randomize() {

        /** External Citation  Date:     2/10/2018
         * Problem:  I need to only select skin tones in RGB
         * Resource:
         * http://photokaboom.com/photography/learn/Photoshop_Elements/color_correction/skin_tone/1_skin_tone_samples.htm
         * www.tutorialspoint.com/java/util/random_nextint_inc_exc.htm
         * Solution: I used the concepts in this website to structure my code and the turotialspoint to make sure I never did .nextInt(0)
         */

        //randomly generate skin tone with 255 >= R > G > B >= 0
        int R = rand_gen.nextInt(255) + 1;
        int G = rand_gen.nextInt(R);
        int B = rand_gen.nextInt(G);
//        this.skinColor = Color.rgb(R, G, B);
        setSkinColor(R, G, B);

        //randomly sets the eye color to a shade of blue, green, or brown
        int blue_green_or_brown = rand_gen.nextInt(3);
        switch (blue_green_or_brown) {
            case 0:
                setEyeColor(0, rand_gen.nextInt(10) * 25, 200 + rand_gen.nextInt(50));
                break;
            case 1:
                setEyeColor(rand_gen.nextInt(100), 150 + rand_gen.nextInt(50) * 2, rand_gen.nextInt(50));
                break;
            case 2:
                setEyeColor(100 + rand_gen.nextInt(50), 50 + rand_gen.nextInt(50), 0);
                break;
        }

        //randomly set hair color to a shade of brown
        setHairColor(100 + rand_gen.nextInt(50), 50 + rand_gen.nextInt(50), rand_gen.nextInt(20));


        //view has changed and needs to be redrawn
        postInvalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {


        int width = canvas.getWidth();
        int height = canvas.getHeight();


//        Paint skinPaint = new Paint();
//        skinPaint.setColor(skinColor);
        canvas.drawOval(Math.round( (float) width * 1 / 4 ), Math.round( (float) height * 3 / 10), Math.round( width * (float) 3 / 4 ),  height -80, toPaint(skinColor));


        drawEye(canvas, Math.round( (float) width / 2 ) - 240, 800, Math.round( (float) width / 2 ) - 140 , 900, toPaint(eyeColor));
        drawEye(canvas, Math.round( (float) width / 2 ) + 140 , 800, Math.round( (float) width / 2 ) + 240 , 900, toPaint(eyeColor));


    }


    public void drawHair() {

        switch (style) {

            case 1:

                break;
            case 2:

                break;

            case 3:

                break;

            default:

                break;

        }

    }

    public void drawEye(Canvas canvas, int left, int top, int right, int bottom, Paint color) {        //draw black eyelashes by painting axial lines starting at outer edge of length radius/10 over an 180deg arc.

        Paint BLACK = toPaint(Color.BLACK);
        Paint WHITE = toPaint(Color.WHITE);

        int diameter = right - left;

        for (double a = 3.14159265; a <= 2.01 * 3.14159265; a += 3.14159265 / 20 ) {
            int start_x = left + diameter / 2 + (int) Math.round(diameter / 2 * Math.cos(a));
            int start_y = top + diameter / 2 + (int) Math.round(diameter / 2 * Math.sin(a));
            int end_x = left + diameter / 2 + (int) Math.round(diameter * 0.65 * Math.cos(a));
            int end_y = top + diameter / 2 + (int) Math.round(diameter * 0.65 * Math.sin(a));
            canvas.drawLine(start_x, start_y, end_x, end_y, BLACK);
        }

        //dimensioning the eye
        int size_pupil = (int) Math.round(diameter / 3.2);
        int size_iris = (int) Math.round(diameter / 1.5);
        int shift_pupil = (int) Math.round(diameter / 2 - size_pupil / 2);
        int shift_iris = (int) Math.round(diameter / 2 - size_iris / 2);

        //draw white background
        canvas.drawOval(left, top, right, bottom, WHITE);

        //fill with iris
        canvas.drawOval(left + shift_iris, top + shift_iris, right - shift_iris, bottom - shift_iris, toPaint(eyeColor));

        //fill with pupil
        canvas.drawOval(left + shift_pupil, top + shift_pupil, right - shift_pupil, bottom - shift_pupil, BLACK);

    }
}
