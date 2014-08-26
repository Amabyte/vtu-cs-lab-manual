package com.valento.vtucslabmanual.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.valento.vtucslabmanual.helper.Helper;
import com.valento.vtucslabmanual.helper.ToHtml;

import java.io.File;
import java.lang.reflect.Method;

import blink9.com.vtucslabmanual.R;


public class DisplayActivity extends Activity {


    // to check if the buttons bar(fonts size and day night) is visible or no
    private boolean isControlVisible=true;
    //to set the theme to back or white
    private boolean isDarkTheme = false;
    private String htmlText;

    private String fileName = null;
    private String path = null;

    private WebView webView =null;
    private Button dayNight=null;
    private GestureDetector gestureDetector;
    private WebSettings webSettings = null;
    private View controlsView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Intent intent = getIntent();
        fileName = intent.getStringExtra("filename");
        path = intent.getStringExtra("path");


        dayNight = (Button) findViewById(R.id.disp_btn_day_night);
        controlsView = findViewById(R.id.fullscreen_content_controls);
        webView = (WebView) findViewById(R.id.webView);
        webSettings = webView.getSettings();

        ToHtml toHtmlObj = new ToHtml(this);
        htmlText = toHtmlObj.parseMarkDown(path + File.separator + fileName);

        String preText = "<html><head>"
                + "<style type=\"text/css\">body{color: #fff; background-color: #000;}"
                + "</style></head>"
                + "<body>";
        String postText = "</body></html>";

        htmlText = preText + htmlText + postText;

        webView.loadDataWithBaseURL("file:///android_asset/theme/", htmlText, "text/html", "UTF-8", null);

        gestureDetector = new GestureDetector(this, new MyGestureDetector());
        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });

    }




    public void ControlViewToggle(boolean visible){

        int mControlsHeight;
        int mShortAnimTime;


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            // If the ViewPropertyAnimator API is available
            // (Honeycomb MR2 and later), use it to animate the
            // in-layout UI controls at the bottom of the
            // screen.
            mControlsHeight = controlsView.getHeight();
            mShortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);


            controlsView.animate()
                    .translationY(!visible ? 0 : mControlsHeight)
                    .setDuration(mShortAnimTime);
        } else {
            // If the ViewPropertyAnimator APIs aren't
            // available, simply show or hide the in-layout UI
            // controls.
            controlsView.setVisibility(!visible ? View.VISIBLE : View.GONE);
        }

        isControlVisible = !isControlVisible;

    }


    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if(isControlVisible){
                ControlViewToggle(true);
            }
            return false;
        }


        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            ControlViewToggle(isControlVisible);
            return false;
        }
    }




    private void toggleTheme(){
        try {
            Class clsWebSettingsClassic = getClassLoader().loadClass("android.webkit.WebSettingsClassic");
            Method md = clsWebSettingsClassic.getMethod("setProperty", String.class, String.class);
            md.invoke(webView.getSettings(), "inverted", String.valueOf(!isDarkTheme));
            md.invoke(webView.getSettings(), "inverted_contrast", "1");

            if(isDarkTheme) {
                dayNight.setText("Day Mode");
                isDarkTheme =false;
            }else{
                dayNight.setText("Night Mode");
                isDarkTheme =true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void btnDayNightOnClick(View view) {
        toggleTheme();
    }

    public void btnFontDownOnClick(View view){
        webSettings.setDefaultFontSize(webSettings.getDefaultFontSize()-2);

    }

    public void btnFontUpOnClick(View view){
        webSettings.setDefaultFontSize(webSettings.getDefaultFontSize()+2);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
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

}
