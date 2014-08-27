package com.valento.vtucslabmanual.main;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.SeekBar;

import com.valento.vtucslabmanual.helper.Helper;
import com.valento.vtucslabmanual.helper.ToHtml;

import java.io.File;
import java.lang.reflect.Method;

import blink9.com.vtucslabmanual.R;


public class DisplayActivity extends Activity {


    // to check if the buttons bar(fonts size and day night) is visible or no
    private boolean isControlVisible=true;
    //to set the theme to back or white
    private boolean isNightTheme = false;
    private String htmlText;

    private String fileName = null;
    private String path = null;
    private int brightness;

    private WebView webView =null;
    private Button dayNight=null;
    private GestureDetector gestureDetector;
    private WebSettings webSettings = null;
    private View controlsView;
    private SeekBar bar;
    private ContentResolver cResolver;
    //Window object, that will store a reference to the current window
    private Window window;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Intent intent = getIntent();
        fileName = intent.getStringExtra("filename");
        path = intent.getStringExtra("path");

        bar = (SeekBar) findViewById(R.id.seekBar);
        dayNight = (Button) findViewById(R.id.disp_btn_day_night);
        controlsView = findViewById(R.id.fullscreen_content_controls);
        webView = (WebView) findViewById(R.id.webView);

        webSettings = webView.getSettings();
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setAppCacheEnabled(false);
        webSettings.setBlockNetworkImage(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setGeolocationEnabled(false);
        webSettings.setNeedInitialFocus(false);
        webSettings.setSaveFormData(false);

        ToHtml toHtmlObj = new ToHtml(this);
        htmlText = toHtmlObj.parseMarkDown(path, fileName);

        String css = "\n" +
                "html { font-size:100%; }\n" +
                "\n" +
                "pre,code,kbd,samp {\n" +
                "\tbackground-color:#F4F4F4;\n" +
                "\tfont-size: 80%;\n" +
                "\tborder-radius: 3px;\n" +
                "\tfont-family: Monaco, Menlo, Consolas, \"Courier New\", monospace;\n" +
                "}\n" +
                "\n" +
                "\n" +
                "b,strong {\n" +
                "\tfont-weight: bold;\n" +
                "}\n" +
                "\n" +
                "sub,sup {\n" +
                "\tfont-size: 75%;\n" +
                "\tline-height: 0;\n" +
                "\tposition: relative;\n" +
                "\tvertical-align: baseline;\n" +
                "}\n" +
                "\n" +
                "img { max-width: 100%; }";

        String preText = "<html><head>"
                +"<style type=\"text/css\">"
                +css
                + "</style></head>"
                + "<body>";
        String postText = "</body></html>";

        htmlText = preText + htmlText + postText;


        String BaseImgPath = "file:///android_asset/"+path+ File.separator+"img"+File.separator;
        webView.loadDataWithBaseURL(BaseImgPath, htmlText, "text/html", "UTF-8", null);


        cResolver = getContentResolver();

        //Get the current window
        window = getWindow();

        bar.setMax(255);
        bar.setKeyProgressIncrement(1);

        try
        {
            //Get the current system brightness
            brightness = android.provider.Settings.System.getInt(cResolver, android.provider.Settings.System.SCREEN_BRIGHTNESS);
        }
        catch (Settings.SettingNotFoundException e)
        {
            //Throw an error case it couldn't be retrieved
            Log.e("Error", "Cannot access system brightness");
            e.printStackTrace();
        }

        //Set the progress of the seek bar based on the system's brightness
        bar.setProgress(brightness);


        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                //Set the system brightness using the brightness variable value
                android.provider.Settings.System.putInt(cResolver, android.provider.Settings.System.SCREEN_BRIGHTNESS, brightness);
                //Get the current window attributes
                WindowManager.LayoutParams layoutpars = window.getAttributes();
                //Set the brightness of this window
                layoutpars.screenBrightness = brightness / (float)255;
                //Apply attribute changes to this window
                window.setAttributes(layoutpars);
            }

            public void onStartTrackingTouch(SeekBar seekBar)
            {
                //Nothing handled here
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                //Set the minimal brightness level
                //if seek bar is 20 or any value below
                if(progress<=20)
                {
                    //Set the brightness to 20
                    brightness=20;
                }
                else //brightness is greater than 20
                {
                    //Set brightness variable based on the progress bar
                    brightness = progress;
                }
            }
        });
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
            md.invoke(webView.getSettings(), "inverted", String.valueOf(isNightTheme));
            md.invoke(webView.getSettings(), "inverted_contrast", "1");

            if(isNightTheme) {
                dayNight.setText("Day Mode");
                isNightTheme =false;
            }else{
                dayNight.setText("Night Mode");
                isNightTheme =true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void btnDayNightOnClick(View view) {
        toggleTheme();
    }

    public void btnFontDownOnClick(View view){
        WebSettings settings = webView.getSettings();
        settings.setTextZoom( (int)(settings.getTextZoom() / 1.1) );

    }

    public void btnFontUpOnClick(View view){
        WebSettings settings = webView.getSettings();
        settings.setTextZoom( (int)(settings.getTextZoom() * 1.1) );

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Helper.loadPreferences(this);

        if(Helper.THEME.equals("Day")){
            isNightTheme = false;
        }else{
            isNightTheme=true;
        }
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
