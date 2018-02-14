package danowski19.hw2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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
    private static int hairStyle = 0;
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
     *
     * @return
     */
    public int[] getColorRGB(int skin_eye_hair) {

        int useThisColor = 0xff000000;

        switch (skin_eye_hair) {
            case 0: //skin
                useThisColor = skinColor;
                break;
            case 1: //eye
                useThisColor = eyeColor;
                break;
            default: //hair
                useThisColor = hairColor;
                break;
        }

        int[] result = {getRed(useThisColor), getGreen(useThisColor), getBlue(useThisColor)};
        return result;
    }

    public int getHairStyle() {
        return hairStyle;
    }


    /**
     * screening vales
     *
     * @param color
     * @return
     */
    public int getRed(int color) {
        return (color & 0x00ff0000) >> 16;
    }

    public int getGreen(int color) {
        return (color & 0x0000ff00) >> 8;
    }

    public int getBlue(int color) {
        return color & 0x000000ff;
    }

    /**
     * setter methods
     *
     * @param R
     * @param G
     * @param B
     */
    public void setSkinColor(int R, int G, int B) {
        skinColor = Color.rgb(R, G, B);
        postInvalidate();
    }

    public void setEyeColor(int R, int G, int B) {
        eyeColor = Color.rgb(R, G, B);
        postInvalidate();
    }

    public void setHairColor(int R, int G, int B) {
        hairColor = Color.rgb(R, G, B);
        postInvalidate();
    }

    public void setHairStyle(int style) {
        hairStyle = style;
        postInvalidate();
    }


    /**
     * Helper method to transform Color objects to Paint objects
     *
     * @param color
     * @return
     */
    public Paint toPaint(int color) {
        Paint x = new Paint();
        x.setColor(color);
        return x;
    }

    /**
     * Initializes all the variables to randomly selected valid values.
     */
    public void randomize() {

        //random hair
        setHairStyle(rand_gen.nextInt(4));

        /** External Citation  Date:     2/10/2018
         * Problem:  I need to only select skin tones in RGB
         * Resource:
         * http://photokaboom.com/photography/learn/Photoshop_Elements/color_correction/skin_tone/1_skin_tone_samples.htm
         * www.tutorialspoint.com/java/util/random_nextint_inc_exc.htm
         * Solution: I used the concepts in this website to structure my code and the turotialspoint to make sure I never did .nextInt(0)
         */

        //randomly generate skin tone with 255 >= R > G > B >= 0
        int Red = rand_gen.nextInt(255) + 1;
        int Gre = rand_gen.nextInt(Red);
        int Blu = rand_gen.nextInt(Gre);
        setSkinColor(Red, Gre, Blu);

        //randomly sets the eye color to a shade of blue, green, or brown
        switch (rand_gen.nextInt(3)) {
            case 0: //blue
                Red = 0;
                Gre = rand_gen.nextInt(10) * 25;
                Blu = 200 + rand_gen.nextInt(50);
                break;
            case 1: //green
                Red = rand_gen.nextInt(100);
                Gre = 150 + rand_gen.nextInt(50) * 2;
                Blu = rand_gen.nextInt(50);
                break;
            default: //brown
                Red = 100 + rand_gen.nextInt(50);
                Gre = 50 + rand_gen.nextInt(50);
                Blu = 0;
                break;
        }
        setEyeColor(Red, Gre, Blu);


        //randomly set hair color to a shade of brown
        Red = rand_gen.nextInt(255);
        Gre = rand_gen.nextInt(255);
        Blu = rand_gen.nextInt(255);
        setHairColor(Red, Gre, Blu);


        //view has changed and needs to be redrawn
        postInvalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        switch (hairStyle) {

            case 0://bowl cut
                canvas.drawRect(width / 2 - 500, height / 2, width / 2 + 500, height / 2 + 500, toPaint(hairColor));
            case 1://afro
                canvas.drawCircle(width / 2, height / 2, 500, toPaint(hairColor));
                break;
            case 2://flat top
                canvas.drawRect((float) width * 1 / 3, Math.round((float) height * 3 / 10) - 50, Math.round(width * (float) 2 / 3), Math.round((float) height * 4 / 10) + 40, toPaint(hairColor));
                break;

            case 3://mohawk


                //https://stackoverflow.com/questions/20544668/how-to-draw-filled-triangle-on-android-canvas

                Path path = new Path();
                path.setFillType(Path.FillType.EVEN_ODD);
                path.lineTo(width / 2 - 266, height / 2 - 230);
                path.lineTo(width / 2 - 100, 420);
                path.lineTo(width / 2, 100);
                path.lineTo(width / 2 + 100, 420);
                path.lineTo(width / 2 + 266, height / 2 - 230);
                path.lineTo(width / 2 - 266, height / 2 - 230);
                path.close();

                canvas.drawPath(path, toPaint(hairColor));

                break;
        }

        //draw head
        canvas.drawOval(Math.round((float) width * 1 / 4), Math.round((float) height * 3 / 10), Math.round(width * (float) 3 / 4), height - 80, toPaint(skinColor));

        //draw eyes
        drawEye(canvas, Math.round((float) width / 2) - 240, 800, Math.round((float) width / 2) - 140, 900);
        drawEye(canvas, Math.round((float) width / 2) + 140, 800, Math.round((float) width / 2) + 240, 900);

        //draw last hair segment over forehead
        canvas.drawArc((float) width * 1 / 3, Math.round((float) height * 3 / 10) - 10, Math.round(width * (float) 2 / 3), Math.round((float) height * 4 / 10) + 60, (float) 0, (float) 360, true, toPaint(hairColor));

        


    }




    public void drawEye(Canvas canvas, int left, int top, int right, int bottom) {        //draw black eyelashes by painting axial lines starting at outer edge of length radius/10 over an 180deg arc.

        Paint BLACK = toPaint(Color.BLACK);
        Paint WHITE = toPaint(Color.WHITE);

        int diameter = right - left;

        for (double a = 3.14159265; a <= 2.01 * 3.14159265; a += 3.14159265 / 20) {
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
