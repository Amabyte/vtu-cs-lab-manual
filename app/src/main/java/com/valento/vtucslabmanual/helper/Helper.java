package com.valento.vtucslabmanual.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * VTU CS LAB manual Android Project by Ashwin Valento
 */
public class Helper {

    public static double VERSION = 1.0;
    public static String THEME = "Day" ;
    public static int FONT = 0;
    public static int FONT_SIZE = 0;
    public static boolean AUTO_SLEEP_DISABLED = true;

    public static String[] FILES_TO_IGNORE = {".png",".gif",".jpg",".pdf",".doc"};



    public static String[] removeUnwantedFiles(String[] stringArray){
        String[] n;
        final List<String> list =  new ArrayList<String>();
        Collections.addAll(list, stringArray);


      //  for (int i = 0; i < FILES_TO_IGNORE.length; i++) {
        for(String fileExt : FILES_TO_IGNORE){
            for (int j = 0; j < list.size(); j++) {
                if(list.get(j).endsWith(fileExt)){
                    list.remove(j);
                }
            }

        }

        n = list.toArray(new String[list.size()]);
        return n;
    }


    public static String removeExtention(String name) {
        if (!name.contains("."))
            return name;
        return name.substring(0, name.lastIndexOf('.'));
    }

    public static boolean isDirectory(Context context,String path,String fileName){
        try {
            if(context.getAssets().list(path + File.separator + fileName).length >0){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }



    public static  String readFromFile(Context context,String fileName){
        StringBuilder returnString = new StringBuilder();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try {
            fIn = context.getResources().getAssets()
                    .open(fileName, Context.MODE_WORLD_READABLE);
            isr = new InputStreamReader(fIn);
            input = new BufferedReader(isr);
            String line;
            while ((line = input.readLine()) != null) {
                returnString.append(line);
                returnString.append("\n");
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (isr != null)
                    isr.close();
                if (fIn != null)
                    fIn.close();
                if (input != null)
                    input.close();
            } catch (Exception e2) {
                e2.getMessage();
            }
        }
        return returnString.toString();
    }

    public static void loadPreferences(Context context){
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
        AUTO_SLEEP_DISABLED = preferences.getBoolean("gen_sleep_mode_checkbox",true);
        THEME = preferences.getString("theme_default_theme", "0");
        FONT_SIZE = Integer.parseInt(preferences.getString("theme_font_size_list", "16"));
        FONT = Integer.parseInt(preferences.getString("theme_font_name_list", "0"));
    }
}
