package com.lg.weather.Util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

// Html 소스코드 읽어오기
public class HtmlParser {
    public String HtmlParser(String urlToRead) {
        StringBuffer result = new StringBuffer();
        try {
            URL url = new URL(urlToRead);
            InputStream is = url.openStream();
            int ch;

            while ((ch = is.read()) != -1) {
                //System.out.print((char) ch);
                result.append((char) ch);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
