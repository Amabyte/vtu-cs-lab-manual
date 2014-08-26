package com.valento.vtucslabmanual.helper;

import android.content.Context;

import org.markdown4j.Markdown4jProcessor;

import java.io.File;
import java.io.IOException;

/**
 * VTU CS LAB manual Android Project by Ashwin Valento
 */
public class ToHtml {
    Context context;
    public ToHtml(Context context){
        this.context = context;
    }




    public String parseMarkDown(String path,String fileName){
        String absolutePath = path+ File.separator +fileName;

        String html="";
        try {
            if(absolutePath.endsWith(".md")) {
                html = new Markdown4jProcessor().process(Helper.readFromFile(context, absolutePath));
            }else{
                html="<pre>"+Helper.readFromFile(context, absolutePath)+"<pre>";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return html;
    }


}
