package com.valento.vtucslabmanual.main;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.valento.vtucslabmanual.helper.Helper;
import com.valento.vtucslabmanual.helper.ToHtml;
import com.valento.vtucslabmanual.util.SystemUiHider;

import java.io.File;
import java.lang.reflect.Method;

import blink9.com.vtucslabmanual.R;


public class DisplayActivityBk extends Activity {

    private boolean isDragged = false;
    //to set the theme to back or white
    private boolean isDarkTheme = false;

    //File to display;
    private String fileName = null;
    private String path = null;

    WebView markdownView=null;

    Button dayNight=null;

    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */

    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    private static final boolean TOGGLE_ON_CLICK = true;

    /**
     * The flags to pass to {@link com.valento.vtucslabmanual.util.SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    /**
     * The instance of the {@link com.valento.vtucslabmanual.util.SystemUiHider} for this activity.
     */
    private SystemUiHider mSystemUiHider;
    String htmlText;
    WebSettings webSettings = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);


        Intent intent = getIntent();

        fileName = intent.getStringExtra("filename");
        path = intent.getStringExtra("path");

        dayNight = (Button) findViewById(R.id.disp_btn_day_night);



        final View controlsView = findViewById(R.id.fullscreen_content_controls);
        markdownView = (WebView) findViewById(R.id.webView);
        webSettings = markdownView.getSettings();

        ToHtml toHtmlObj = new ToHtml(this);
        htmlText = toHtmlObj.parseMarkDown(path + File.separator + fileName);
        String preText = "<html><head>"
                + "<style type=\"text/css\">body{color: #fff; background-color: #000;}"
                + "</style></head>"
                + "<body>";
        String postText = "</body></html>";

        htmlText = preText + htmlText + postText;
        markdownView.loadDataWithBaseURL("file:///android_asset/theme/", htmlText, "text/html", "UTF-8", null);


        // Set up an instance of SystemUiHider to control the system UI for
        // this activity.

        mSystemUiHider = SystemUiHider.getInstance(this, markdownView, HIDER_FLAGS);
        mSystemUiHider.setup();
        mSystemUiHider.setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
            // Cached values.


            int mControlsHeight;
            int mShortAnimTime;

            @Override
            @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
            public void onVisibilityChange(boolean visible) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                    // If the ViewPropertyAnimator API is available
                    // (Honeycomb MR2 and later), use it to animate the
                    // in-layout UI controls at the bottom of the
                    // screen.
                    if (mControlsHeight == 0) {
                        mControlsHeight = controlsView.getHeight();
                    }
                    if (mShortAnimTime == 0) {
                        mShortAnimTime = getResources().getInteger(
                                android.R.integer.config_shortAnimTime);
                    }


                    controlsView.animate()
                            .translationY(visible ? 0 : mControlsHeight)
                            .setDuration(mShortAnimTime);
                } else {
                    // If the ViewPropertyAnimator APIs aren't
                    // available, simply show or hide the in-layout UI
                    // controls.
                    controlsView.setVisibility(visible ? View.VISIBLE : View.GONE);
                }

                if (visible && AUTO_HIDE) {
                    // Schedule a hide().
                    delayedHide(AUTO_HIDE_DELAY_MILLIS);
                }
            }
        });





        // Set up the user interaction to manually show or hide the system UI.
      /* markdownView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Log.d("hello","clicked");
               if (TOGGLE_ON_CLICK) {
                   mSystemUiHider.toggle();
               } else {
                   mSystemUiHider.show();
               }

           }
       });
*/



        markdownView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {

                if (dragEvent.getAction() == DragEvent.ACTION_DRAG_STARTED) {
                    Log.d("hello", "dragged");
                }
                return false;
            }
        });


    }



    private void toggleTheme(){
        try {
            Class clsWebSettingsClassic;
            clsWebSettingsClassic = getClassLoader().loadClass("android.webkit.WebSettingsClassic");
            Method md = clsWebSettingsClassic.getMethod("setProperty", String.class, String.class);

            if(isDarkTheme) {
                md.invoke(markdownView.getSettings(), "inverted", String.valueOf(!isDarkTheme));
                md.invoke(markdownView.getSettings(), "inverted_contrast", "1");
                dayNight.setText("Day Mode");
                isDarkTheme =false;
            }else{
                md.invoke(markdownView.getSettings(), "inverted", String.valueOf(!isDarkTheme));
                md.invoke(markdownView.getSettings(), "inverted_contrast", "1");
                dayNight.setText("Night Mode");
                isDarkTheme =true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void btnDayNightOnClick(View view){
        toggleTheme();
        if (AUTO_HIDE) {
            delayedHide(AUTO_HIDE_DELAY_MILLIS);
        }


    }

    public void btnFontDownOnClick(View view){
        webSettings.setDefaultFontSize(webSettings.getDefaultFontSize()-2);
        if (AUTO_HIDE) {
            delayedHide(AUTO_HIDE_DELAY_MILLIS);
        }
    }

    public void btnFontUpOnClick(View view){
        webSettings.setDefaultFontSize(webSettings.getDefaultFontSize()+2);
        if (AUTO_HIDE) {
            delayedHide(AUTO_HIDE_DELAY_MILLIS);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Helper.loadPreferences(this);

        isDarkTheme = !Helper.THEME.equals("Day");
        //set the theme of the display (Dark theme or Light theme)
        toggleTheme();

      /*
        font family
        normal (Droid Sans), serif (Droid Serif), and monospace (Droid Sans Mono).
       */

        if(Helper.FONT == 0){
            webSettings.setFixedFontFamily("monospace");
        }else if(Helper.FONT == 1){
            webSettings.setFixedFontFamily("normal");
        }else{
            webSettings.setFixedFontFamily("serif");
        }

        //set Font size
        webSettings.setDefaultFontSize(Helper.FONT_SIZE);


        if(Helper.AUTO_SLEEP_DISABLED){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }


    }


    Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            mSystemUiHider.hide();
        }
    };

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
