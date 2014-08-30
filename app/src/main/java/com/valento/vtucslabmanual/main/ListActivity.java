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
/*
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
*/
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
  //  private AdView adView;

    /* Your ad unit id. Replace with your actual ad unit id. */
    private static final String AD_UNIT_ID = "ca-app-pub-9272592099448804/7647853267";

/*
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
*/

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        Bundle extras = getIntent().getExtras();
        folderName=extras.getString(Helper.FOLDER_NAME);
        path = extras.getString(Helper.PATH);

        l = (ListView) findViewById(R.id.ListView);
        l.setAdapter(new ArrayAdapter<String>(this,R.layout.simple_list_item_1,new ArrayList<String>()));

        this.setTitle(folderName);
        new MyTask().execute(path+ File.separator+folderName);
        /*  Ads removed
        setUpGoogleAds();
        */
    }


    /*  Ads removed
    @Override
    protected void onResume() {
        super.onResume();

        if (adView != null) {
            adView.resume();
        }

    }

    @Override
    public void onPause() {

        if (adView != null) {
            adView.pause();
        }

        super.onPause();
    }

    @Override
    public void onDestroy() {
        // Destroy the AdView.

        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

*/


    class MyTask extends AsyncTask<String,String,Void> {

        private ArrayAdapter<String> adapter;
        @Override
        protected void onPreExecute() {
            adapter = (ArrayAdapter<String>) l.getAdapter();
        }

        @Override
        protected Void doInBackground(String[] objects) {

            String pathToAssets = objects[0];
            try {
                items = ListActivity.this.getAssets().list(pathToAssets);
            } catch (IOException e) {
                e.printStackTrace();
            }
            items = Helper.removeUnwantedFiles(items);

            for(String item :items){
                //Add each item to the listView by removing the extension
                publishProgress(Helper.removeExtention(item));
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String[] values) {
            adapter.add(values[0]);
        }

        @Override
        protected void onPostExecute(Void o) {
            l.setOnItemClickListener(ListActivity.this);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
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
                i.putExtra(Helper.FILE_NAME,"Contributors.md");
                i.putExtra(Helper.PATH,"others");
                i.putExtra(Helper.TYPE,"options");
                break;
            case R.id.about:
                i = new Intent(this,DisplayActivity.class);
                i.putExtra(Helper.FILE_NAME,"ABOUT.html");
                i.putExtra(Helper.PATH,"others");
                i.putExtra(Helper.TYPE,"options");
                break;
            case R.id.license:
                i = new Intent(this,DisplayActivity.class);
                i.putExtra(Helper.FILE_NAME,"LICENSE");
                i.putExtra(Helper.PATH,"others");
                i.putExtra(Helper.TYPE,"options");
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
            intent.putExtra(Helper.FOLDER_NAME,items[i]);
            intent.putExtra(Helper.PATH,path+ File.separator+folderName);
            startActivity(intent);
        }else{
            intent = new Intent(this,DisplayActivity.class);
            intent.putExtra(Helper.PATH,path+ File.separator+folderName);
            intent.putExtra(Helper.FILE_NAME,items[i]);
            startActivity(intent);
        }

    }


}
