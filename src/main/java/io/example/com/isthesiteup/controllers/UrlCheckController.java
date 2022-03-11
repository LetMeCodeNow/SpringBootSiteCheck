package io.example.com.isthesiteup.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {
    private final String Is_Site_Up="Site is up!";
    private final String Is_Site_Down="Site is Down!";
    private final String IncorrectUrl="URL is incorrect";

  @GetMapping("/check")  
   public String getUrlStatusMessage(@RequestParam String url){
       String returnMessage="";
     try {
        URL urlObj= new URL(url);
        HttpURLConnection conn=(HttpURLConnection) urlObj.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int responseCodeCategory=conn.getResponseCode()/100;
        if(responseCodeCategory !=2 || responseCodeCategory!=3){
           returnMessage= Is_Site_Down;
        }
        else{
            returnMessage=Is_Site_Up;
        }
    } 
     catch(MalformedURLException e){
             returnMessage=IncorrectUrl;
         }
     
     catch (IOException e) {
        returnMessage=Is_Site_Down;
    }
       return  returnMessage;
   }

}
