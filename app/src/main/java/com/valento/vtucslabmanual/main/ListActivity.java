package com.valento.vtucslabmanual.main;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.valento.vtucslabmanual.helper.Helper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import blink9.com.vtucslabmanual.R;

/**
 * VTU CS LAB manual Android Project by Ashwin Valento
 */
public class ListActivity extends Activity implements AdapterView.OnItemClickListener {

    String path=null;
    String folderName=null;
    private ListView l;
    String items[]=null;


    //Google Ads
    private AdView adView;

    /* Your ad unit id. Replace with your actual ad unit id. */
    private static final String AD_UNIT_ID = "ca-app-pub-9272592099448804/7647853267";


    private void setUpGoogleAds(){
        // Create an ad.
        adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(AD_UNIT_ID);

        // Add the AdView to the view hierarchy. The view will have no size
        // until the ad is loaded.
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
        layout.addView(adView);

        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device.
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("9A5D07C7A32423F1F16F988F53E21E7B")
                .build();

        // Start loading the ad in the background.
        adView.loadAd(adRequest);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        Bundle extras = getIntent().getExtras();
        folderName=extras.getString("folder");
        path = extras.getString("path");

        l = (ListView) findViewById(R.id.ListView);
        l.setAdapter(new ListAdapter(this,R.layout.single_row,new ArrayList<String>()));
        setUpGoogleAds();
        new ListActivity.MyTask().execute(path+ File.separator+folderName);

    }


    class MyTask extends AsyncTask {

        private int count=0;
        private ArrayAdapter<String> adapter;
        @Override
        protected void onPreExecute() {
            setProgressBarIndeterminate(false);
            setProgressBarVisibility(true);
            adapter = (ArrayAdapter<String>) l.getAdapter();
        }

        @Override
        protected Object doInBackground(Object[] objects) {

            String pathToAssets = (String)objects[0];
            try {
                items = ListActivity.this.getAssets().list(pathToAssets);
            } catch (IOException e) {
                e.printStackTrace();
            }
            items = Helper.removeUnwantedFiles(items);

            for(String item :items){
                publishProgress(item);
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            adapter.add((String)values[0]);
            count++;
            setProgress((int)((double)(count/items.length)*10000));
        }

        @Override
        protected void onPostExecute(Object o) {
            setProgressBarVisibility(false);
            l.setOnItemClickListener(ListActivity.this);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent i=null;
        switch (id) {
            case R.id.settings:
                i = new Intent(this,SettingsActivity.class);
                break;
            case R.id.contributors:
                i = new Intent(this,DisplayActivity.class);
                i.putExtra("filename","Contributors.md");
                i.putExtra("path","others");
                break;
            case R.id.about:
                i = new Intent(this,DisplayActivity.class);
                i.putExtra("filename","ABOUT");
                i.putExtra("path","others");
                break;
            case R.id.license:
                i = new Intent(this,DisplayActivity.class);
                i.putExtra("filename","LICENSE");
                i.putExtra("path","others");
                break;
        }
        if (i!=null) {
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent;

        if(Helper.isDirectory(this,path + File.separator + folderName, items[i])){

            intent = new Intent(this,ListActivity.class);
            intent.putExtra("folder",items[i]);
            intent.putExtra("path",path+ File.separator+folderName);
            startActivity(intent);
        }else{
            intent = new Intent(this,DisplayActivity.class);
            intent.putExtra("path",path+ File.separator+folderName);
            intent.putExtra("filename",items[i]);
            startActivity(intent);
        }

    }


}
