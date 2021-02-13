package ru.itis.javalab.services;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;

@Service
public class ParsingServiceImpl implements ParsingService {
    @Override
    public String getPageText(String url) {
        try {
            return Jsoup.parse(new URL(url), 3000).select("body").first().text();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
       }
    }
}
