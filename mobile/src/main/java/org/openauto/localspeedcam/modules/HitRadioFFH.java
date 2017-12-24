package org.openauto.localspeedcam.modules;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class HitRadioFFH extends TrafficModule {

    public String getFeedContent() throws IOException {
        StringBuilder builder = new StringBuilder();
        //This code has to be adapted for your local speed cam source
        Document doc = Jsoup.parse(new URL("https://www.ffh.de/verkehr/blitzer.html"), 5000);
        Elements container = doc.getElementsByClass("tabContent");
        for (Element child : container.get(0).children()) {
            builder.append(child.text());
            builder.append("\n");
        }
        return builder.toString();
    }

    @Override
    public String getFeedTitle() {
        return "Hit Radio FFH (Hessen)";
    }

}
