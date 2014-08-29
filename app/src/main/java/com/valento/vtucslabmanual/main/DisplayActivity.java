package com.valento.vtucslabmanual.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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

import blink9.com.vtucslabmanual.R;


public class DisplayActivity extends Activity {


    // to check if the buttons bar(fonts size and day night) is visible or no
    private boolean isControlVisible=true;
    //to set the theme to back or white
    private boolean isNightTheme;
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
        fileName = intent.getStringExtra(Helper.FILE_NAME);
        path = intent.getStringExtra(Helper.PATH);
        type = intent.getStringExtra(Helper.TYPE);

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
        webSettings.setJavaScriptEnabled(true);


        //Load the Shared Preferences.
        setConfiguaration();

        ToHtml toHtmlObj = new ToHtml(this);
        htmlText = toHtmlObj.parseMarkDown(path, fileName);

        String css;
        String BasePath;
        String InitialBodyCss;  // initialize the Html body color.

        //Java Script for day and Night mode
        String jScript = "<script language=\"javascript\">\n" +
                "   function nightTheme(){\n" +
                "   document.body.style.backgroundColor='black';\n" +
                "   document.body.style.color='white';\n" +
                "   }\n" +
                "   function dayTheme(){\n" +
                "    document.body.style.backgroundColor='white';\n" +
                "    document.body.style.color='black';\n" +
                "   }\n" +
                "</script>";


        if(type.equals("options")){
           setDayTheme();
            BasePath = "file:///android_asset/others/";
            css="<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />";
            parent.removeView(controlsView);
        }else{

            // Assign Body Background color and text color based on the preference value.
            if (isNightTheme){
                InitialBodyCss = "body {\n" +
                        "background-color:black;\n" +
                        "color: white;\n" +
                        "}";
            }else{
                InitialBodyCss = "body {\n" +
                        "background-color:white;\n" +
                        "color: black;\n" +
                        "}";
            }

            BasePath = "file:///android_asset/"+path+ File.separator+"img"+File.separator;

            css = "<style type=\"text/css\">"+
                    InitialBodyCss+
                    "html { font-size:100%; }\n" +
                    "pre,code,kbd,samp {\n" +
                    //"\tbackground-color:#F4F4F4;\n" +             // messes up night mode so removed
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

        String preText = "<html>"
                + css
                + "<body>"
                + jScript;
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
            if(e1.getY() > e2.getY()){
                //Scroll UP
                if(isControlVisible){
                    ControlViewToggle(true);
                }

            }else{
                //Scroll Down
                if(!isControlVisible){
                    ControlViewToggle(false);
                }

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


/*

// Old method .. only works in my phone :P
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
        */
    }


    public void btnDayNightOnClick(View view) {
        //toggleTheme();
        if(isNightTheme){
            setDayTheme();
        }else{
            setNightTheme();
        }
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




    private void setDayTheme(){
        isNightTheme = false;
        webView.loadUrl("javascript:dayTheme()");
        dayNight.setText("Night Mode");
    }

    private void setNightTheme(){
        isNightTheme = true;
        webView.loadUrl("javascript:nightTheme()");
        dayNight.setText("Day Mode");
    }


    private void setConfiguaration(){
        Helper.loadPreferences(this);

        if (Helper.THEME.equals("Day")) {
            isNightTheme=false;
            setDayTheme();
        } else {
            isNightTheme=true;
            setNightTheme();
        }

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
