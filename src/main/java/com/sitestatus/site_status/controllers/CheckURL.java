package com.sitestatus.site_status.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckURL
{
    private final String SiteIsUp = "The site is up.";
    private final String SiteIsDown = "The site is down.";
    private final String IncorrectURL = "Incorrect URL entered.";

    @SuppressWarnings("deprecation")
    @GetMapping("/check")
    public String getURLStatus(@RequestParam String url)
    {
        String returnMsg = "";
        try {
            URL urlObject = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode() / 100;
            if (responseCode != 2 && responseCode != 3)
            {
                //System.out.println("Response code = " + responseCode);
                returnMsg = SiteIsDown;
            }
            else
            {
                //System.out.println("Response code = " + responseCode);
                returnMsg = SiteIsUp;
            }
        } catch (MalformedURLException e) {
            return IncorrectURL;
        } catch (IOException e) {
            return SiteIsDown;
        }

        return returnMsg;
    }
}
