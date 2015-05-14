package com.khmerapp.khmeralphabet.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.khmerapp.khmeralphabet.R;
import com.khmerapp.khmeralphabet.dto.AlphabetData;
import com.khmerapp.khmeralphabet.dto.KhmerAlphabetData;
import com.khmerapp.khmeralphabet.gui.custom.AlphabetDrawingCanvas;


/*
 * KhmerAlphabetDisplayDetailScreen is used to display an alphabet detail.
 * For example, Kor consonant has its sound and points for drawing. * 
 * To implement swap view screen, see ViewPager, PagerAdapter and GestureDetector class
 */
public class KhmerAlphabetDisplayDetailScreen extends Activity {

    public AlphabetDrawingCanvas alphabetDrawingCanvas;
    public String path;
    public int C[], S[];
    float x1, x2;
    float y1, y2;
    int dataType;
    int dataKey;
    AlphabetData data;
    MediaPlayer mp;
    boolean isPlayingGif = false;
    boolean drawAlphabet = false;
    KhmerAlphabetDisplayDetailScreen gethw;
    AlphabetDrawingCanvas alphabetDrawingCanvasGlobal = null;
    int color;
    int sumDiff;
    int tSolPixels = 0;
    private Animation shakeAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_khmer_alphabet_display_detail_screen);
        gethw = this;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            dataType = extras.getInt(KhmerAlphabetData.VIEW_TYPE);
            dataKey = extras.getInt(KhmerAlphabetData.VIEW_KEY);
        }
        data = (AlphabetData) KhmerAlphabetData.getInstance()
                .getDataType(dataType).get(dataKey);

        //	Log.i("data>>>>>>>>>>>>>>>>>>>", "" + data);
        Log.i("dataType>>>>>>>>>>>", "" + dataType);
        //	Log.i("dataKey>>>>>>>>>>>>>>>>>>>", "" + dataKey);

        try {
            mp = MediaPlayer.create(this, data.getSoundId());
            mp.prepare();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

		/*
         * the imageview control which is used to display a big alphabet in
		 * detail
		 */
        final ImageView imageViewDetail = (ImageView) findViewById(R.id.imageViewDetail);
        imageViewDetail.setImageResource(data.getImageHDId());
        final ImageView btnWatch = (ImageView) findViewById(R.id.imageViewWatch);
        btnWatch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                FrameLayout fl = (FrameLayout) findViewById(R.id.frameLayoutDetail);
                if (isPlayingGif) {
                    btnWatch.setImageResource(R.drawable.ic_wooden_watch);
                    fl.removeViewAt(fl.getChildCount() - 1);
                    isPlayingGif = false;
                } else {
                    btnWatch.setImageResource(R.drawable.ic_wooden_close);
                    String gifURL = "file:///android_asset/"
                            + data.getGifFileName();
                    // Note: to access a resource from assets folder, use
                    // "file:///android_asset/resourceName"
                    WebView gif = new WebView(getApplicationContext());
                    gif.loadUrl(gifURL);
                    LayoutParams lp = new LayoutParams(
                            LayoutParams.WRAP_CONTENT,
                            LayoutParams.WRAP_CONTENT);
                    lp.gravity = Gravity.CENTER;
                    gif.setLayoutParams(lp);
                    fl.addView(gif);
                    isPlayingGif = true;
                }
            }
        });

        ImageView btnRedraw = (ImageView) findViewById(R.id.imageViewView);
        btnRedraw.setOnClickListener(new OnClickListener() {

            /* (non-Javadoc)
             * @see android.view.View.OnClickListener#onClick(android.view.View)
             */
            @Override
            public void onClick(View v) {

                if (alphabetDrawingCanvasGlobal != null) { // it dont need to
                    // renew
                    if (alphabetDrawingCanvasGlobal.recordPoints.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Don't have any previous drawing",
                                Toast.LENGTH_LONG).show();
                    } else {
                        alphabetDrawingCanvasGlobal.reDraw();
                    }
                }

            }

        });

        ImageView btnListen = (ImageView) findViewById(R.id.imageViewListen);
        btnListen.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
            }
        });

        final ImageView btnDraw = (ImageView) findViewById(R.id.imageViewWrite);
        btnDraw.setOnClickListener(new OnClickListener() {
            public FrameLayout fl = (FrameLayout) findViewById(R.id.frameLayoutDetail);

            @Override
            public void onClick(View v) {

                AlphabetDrawingCanvas alphabetDrawingCanvas = new AlphabetDrawingCanvas(getApplicationContext(), data.getPoints());
                alphabetDrawingCanvasGlobal = alphabetDrawingCanvas;

                //Get H and W of Screen
                Display display = getWindowManager().getDefaultDisplay();
                int w = display.getWidth(); // deprecated
                int h = display.getHeight();
                //Toast.makeText(gethw,"height"+h+" width"+w,Toast.LENGTH_LONG).show();
                if (drawAlphabet) {
                    btnDraw.setImageResource(R.drawable.ic_wooden_write);
                    fl.removeViewAt(fl.getChildCount() - 1);
                    alphabetDrawingCanvas.onClearDraw(v);// clear all the point
                    // fl.addView(alphabetDrawingCanvas);
                    drawAlphabet = false;

                } else {
                    btnDraw.setImageResource(R.drawable.ic_wooden_resetdraw);
                    LayoutParams lp = new LayoutParams(
                            LayoutParams.WRAP_CONTENT,
                            LayoutParams.WRAP_CONTENT);
                    alphabetDrawingCanvas.setLayoutParams(lp);
                    fl.addView(alphabetDrawingCanvas);
                    drawAlphabet = true;
                }


            }

        });
        ImageView btnScore = (ImageView) findViewById(R.id.imageViewScore);
        btnScore.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (alphabetDrawingCanvasGlobal != null) { // it doesn't need to renew
                    if (alphabetDrawingCanvasGlobal.recordPoints.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Don't have any drawing",
                                Toast.LENGTH_LONG).show();
                    } else {
                        saveDrawingCanvasAsImage();
                        saveSolutionAsImage();
                        getPixelDrawingCanvasImage(path);
                        getPixelSolutionImage();
                        calPixelDiff();
                        displayRate();
                    }
                }
            }

        });
        ImageView btnClose = (ImageView) findViewById(R.id.imageViewStop);
        btnClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                KhmerAlphabetDisplayDetailScreen.this.finish();
            }
        });

    }

    // save image child draw
    private void saveDrawingCanvasAsImage() {
        Bitmap bitmap1 = alphabetDrawingCanvasGlobal.canvasBitmap;
        path = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file1 = new File(path + File.separator + "drawing_canvas" + ".png");
        //	Toast.makeText(getApplicationContext(), file1.getAbsolutePath(),Toast.LENGTH_LONG).show();
        try {
            if ( !file1.exists() ) {
                file1.createNewFile();
            }
            FileOutputStream ostream1 = new FileOutputStream(file1);
            bitmap1.compress(CompressFormat.PNG, 100, ostream1);
            ostream1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveSolutionAsImage(){
        alphabetDrawingCanvasGlobal.drawSolutionAsImage(dataType + "_" + dataKey);
    }

    private void getPixelDrawingCanvasImage(String path) {

        try {
            File f = new File(path, "drawing_canvas.png");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            //  ImageView img=(ImageView)findViewById(R.id.imgPicker);
            //  img.setImageBitmap(b);
            int w = b.getWidth();
            int h = b.getHeight();

            C = new int[b.getWidth()];
            //	Toast.makeText(getApplication(), "width"+w+"height"+h, Toast.LENGTH_LONG).show(); 433, 330

            //count the number of pixel of each columns
            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    int pixel = b.getPixel(x, y);
                    // System.out.println( "x,y: " + y + ", " + x+" "+String.format("%x", pixel));
                    if (pixel == 0xffbc084c) {
                        //	System.out.println("x,y: " + x + ", " + y+ " "+String.format("%x", pixel));
                        //	Log.i("pixel>>>>>>>>>>>>>>>>>>>", + x + ", " + y+ " "+String.format("%x", pixel));
                        C[x]++;
                    }
                }
                //Log.i("Number of X=",+C[x]+"");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void getPixelSolutionImage() {
        try {
            //Bitmap b = BitmapFactory.decodeResource(getResources(), data.getImageSol());

            String path = Environment.getExternalStorageDirectory().getAbsolutePath();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap b = BitmapFactory.decodeFile(path + File.separator + dataType + "_" + dataKey + ".png", options);

            Bitmap drawingCanvas = alphabetDrawingCanvasGlobal.canvasBitmap;
            Bitmap resized = Bitmap.createScaledBitmap(b, drawingCanvas.getWidth(), drawingCanvas.getHeight(), true);
            //resized=BitmapFactory.decodeResource(getResources(), data.getImageSol());
            int w = resized.getWidth();
            int h = resized.getHeight();
            //	Toast.makeText(getApplication(), "width"+w+"height"+h, Toast.LENGTH_LONG).show();//289,213
            S = new int[resized.getWidth()];
            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    int pixel = resized.getPixel(x, y);
                    // System.out.println( "x,y: " + y + ", " + x+" "+String.format("%x", pixel));
                    if (pixel == 0xffbc084c) {
                        //	System.out.println("x,y: " + x + ", " + y+ " "+String.format("%x", pixel));
                        //	Log.i("pixel>>>>>>>>>>>>>>>>>>>", + x + ", " + y+ " "+String.format("%x", pixel));
                        S[x]++;
                        tSolPixels++;
                    }
                }
                //	Log.i("Number of X2=",+S[x]+"");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void calPixelDiff() {
        sumDiff = 0;
        for (int i = 0; i < alphabetDrawingCanvasGlobal.canvasBitmap.getWidth(); i++) {
            sumDiff += Math.abs( S[i] - C[i] );
        }
        //Log.i("me", "Total sumDiff:>>>>>>>"+sumDiff);
        //Toast.makeText(getApplicationContext(), "sumDiff"+sumDiff, Toast.LENGTH_LONG).show();
    }

    @SuppressLint("NewApi")
    private void displayRate() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int dpi = (int) displayMetrics.densityDpi;
        //	Toast.makeText(getApplication(), "dpi"+dpi,Toast.LENGTH_LONG).show();

        int rate = 0;

        int diffPercentag = sumDiff * 100 / tSolPixels;

        /*
        *
        * 0 Star  : diff ]90, 100] %
        * 1 Star  : diff ]60, 90] %
        * 2 Stars : diff ]30, 60] %
        * 3 Stars : diff [0, 30] %
        * */
        //the less difference of images, the more similar of images
        if (diffPercentag >= 0 && diffPercentag <= 30) {
            rate = 3;
        } else if (diffPercentag > 30 && diffPercentag <= 60) {
            rate = 2;
        } else if (diffPercentag > 60 && diffPercentag <= 90) {
            rate = 1;
        }

        shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.incorrect_shake);
        shakeAnimation.setRepeatCount(3);
        final AlertDialog.Builder popDialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));
        final RatingBar rb = new RatingBar(new ContextThemeWrapper(this, R.style.teststar));
        popDialog.setIcon(android.R.drawable.btn_star_big_on);
        rb.startAnimation(shakeAnimation);
        rb.setBackgroundColor(0xFFF380);
        rb.setRating(rate);
        //rb.setNumStars(3);
        popDialog.setTitle("Your Score!! ");
        popDialog.setView(rb);
        // Button OK
        popDialog.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }

                });
        popDialog.create();
        popDialog.show();
        rb.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;// follow the setting , fill parent will assign star to fill content
    }

    @Override
    public void onBackPressed() {// if they click the button back
        // TODO Auto-generated method stub
        super.onBackPressed();
        mp.stop();
    }

    @Override
    public void finish() {
        // TODO Auto-generated method stub
        super.finish();
        mp.stop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.khmer_alphabet_display_detail_screen,
                menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_watch:
                playGif();
                return true;
            case R.id.action_listen:
                playSound();
                return true;
            case R.id.imageViewWrite:
                alphabetDrawingCanvas.setColor(color);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void playGif() {
        // TODO: play animation here
    }

    private void playSound() {
        if (mp.isPlaying()) {
            mp.pause();
        } else {
            mp.start();
        }
    }

}
