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
import android.widget.FrameLayout;
import android.widget.TextView;

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
    private String type;
    private WebView webView =null;
    private Button dayNight=null;
    private GestureDetector gestureDetector;
    private WebSettings webSettings = null;
    private View controlsView;
    private TextView tv;
    private FrameLayout parent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Intent intent = getIntent();
        fileName = intent.getStringExtra("filename");
        path = intent.getStringExtra("path");
        type = intent.getStringExtra("type");

        if(type==null){
            type="";
        }
        dayNight = (Button) findViewById(R.id.disp_btn_day_night);
        controlsView = findViewById(R.id.fullscreen_content_controls);
        webView = (WebView) findViewById(R.id.webView);
        tv = (TextView) findViewById(R.id.TvFileName);
        parent = (FrameLayout) findViewById(R.id.ParentLayout);

        tv.setText(Helper.removeExtention(fileName));
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

        String css ="";
        String BasePath;

        if(type.equals("options")){
            BasePath = "file:///android_asset/others/";
            css="<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />";
            parent.removeView(controlsView);
        }else{
            BasePath = "file:///android_asset/"+path+ File.separator+"img"+File.separator;

            css = "<style type=\"text/css\">"+
                    "html { font-size:100%; }\n" +
                    "pre,code,kbd,samp {\n" +
                    "\tbackground-color:#F4F4F4;\n" +
                    "\tfont-size: 80%;\n" +
                    "\tborder-radius: 3px;\n" +
                    "\tfont-family: Monaco, Menlo, Consolas, \"Courier New\", monospace;\n" +
                    "}\n" +
                    "b,strong {\n" +
                    "\tfont-weight: bold;\n" +
                    "}\n" +
                    "img { max-width: 100%; }"+
                    "</style>";
        }


        String preText = "<html><head>"
                + css
                + "</head>"
                + "<body>";
        String postText = "</body></html>";

        htmlText = preText + htmlText + postText;

        webView.loadDataWithBaseURL(BasePath, htmlText, "text/html", "UTF-8", null);

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

        if(!type.equals("options")){
            setConfiguaration();
        }else{
            if (!isNightTheme){
                toggleTheme();
            }
        }

    }


    private void setConfiguaration(){
        Helper.loadPreferences(this);

        if (Helper.THEME.equals("Day")) {
            isNightTheme = false;
        } else {
            isNightTheme = true;
        }

        toggleTheme();

      /*
        font family
        normal (Droid Sans), serif (Droid Serif), and monospace (Droid Sans Mono).
       */

        if (Helper.FONT == 0) {
            webSettings.setFixedFontFamily("monospace");
        } else if (Helper.FONT == 1) {
            webSettings.setFixedFontFamily("normal");
        } else {
            webSettings.setFixedFontFamily("serif");
        }

        //set Font size
        webSettings.setDefaultFontSize(Helper.FONT_SIZE);

        if (Helper.AUTO_SLEEP_DISABLED) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

}
