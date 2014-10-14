package com.amabyte.vtucslabmanual.main;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
/*
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
*/
import com.amabyte.vtucslabmanual.helper.Helper;

import java.io.IOException;
import java.util.ArrayList;

import com.amabyte.vtucslabmanual.R;


public class MainListActivity extends Activity implements AdapterView.OnItemClickListener {

    private boolean doubleBackToExitPressedOnce = false;
    private String[] items;
    private ListView l;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        l = (ListView) findViewById(R.id.ListView);
        l.setAdapter(new ArrayAdapter<String>(this,R.layout.simple_list_item_1,new ArrayList<String>()));
        new MyTask().execute("docs");
    }

    class MyTask extends AsyncTask<String,String,Void>{

        private ArrayAdapter<String> adapter;
        @Override
        protected void onPreExecute() {
            adapter = (ArrayAdapter<String>) l.getAdapter();
        }

        @Override
        protected Void doInBackground(String[] objects) {

            String pathToAssets = objects[0];
            try {
                items = MainListActivity.this.getAssets().list(pathToAssets);
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
            l.setOnItemClickListener(MainListActivity.this);
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


    // Press back button 2ce to exit
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click back again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent;

        if(Helper.isDirectory(this,"docs", items[i])){
            intent = new Intent(this,ListActivity.class);
            intent.putExtra("folder",items[i]);
            intent.putExtra("path","docs");
            startActivity(intent);
        }else{
            intent = new Intent(this,DisplayActivity.class);
            intent.putExtra("path","docs");
            intent.putExtra("filename",items[i]);
            startActivity(intent);
        }

    }

}


