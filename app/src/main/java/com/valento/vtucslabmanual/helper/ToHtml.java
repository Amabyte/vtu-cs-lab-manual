package com.valento.vtucslabmanual.helper;

import android.content.Context;

import org.markdown4j.Markdown4jProcessor;

import java.io.IOException;

/**
 * VTU CS LAB manual Android Project by Ashwin Valento
 */
public class ToHtml {
    Context context;
    public ToHtml(Context context){
        this.context = context;
    }




    public String parseMarkDown(String FileName){
        String html="";
        try {
            if(FileName.endsWith(".md")) {
                html = new Markdown4jProcessor().process(Helper.readFromFile(context, FileName));
            }else{
                html="<pre>"+Helper.readFromFile(context, FileName)+"<pre>";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return html;
    }


}
