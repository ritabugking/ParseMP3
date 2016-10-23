/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParseMp3;

import java.io.File;
import java.io.FileOutputStream;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;  
/**
 *
 * @author yijuwang
 */
public class ParseMp3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int file_num =0;
        
        for(int page=1; page <=7; page++){
            
            String url = "http://www.xeno-canto.org/explore?query=Haemorhous+mexicanus+&dir=0&order=loc&pg="+page;

            try {
                Document doc = Jsoup.connect(url).get();

                System.out.println(doc.title());
                Elements h1s = doc.select(".jp-type-single"); 
                System.out.println("Number of results: " + h1s.size());
                for (Element element : h1s) { 
                    String mp3Url = element.attr("data-xc-filepath"); 
                    System.out.println("mp3 url: " + mp3Url);
                    file_num++;

                    URLConnection conn = new URL(mp3Url).openConnection();
                    InputStream is = conn.getInputStream();

                    OutputStream outstream = new FileOutputStream(new File("/users/yijuwang/downloads/"+file_num+"file.mp3"));
                    byte[] buffer = new byte[4096];
                    int len;
                    while ((len = is.read(buffer)) > 0) {
                        outstream.write(buffer, 0, len);
                    }
                    outstream.close();           
                }
            }   catch (Exception ex) {
                ex.printStackTrace();
            }

        
        }  
    }
   
}
