package org.openauto.localspeedcam.modules;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class Landeswelle extends TrafficModule{

    public String getFeedContent() throws IOException {
        StringBuilder builder = new StringBuilder();

        //This code has to be adapted for your local speed cam source
        Document doc = Jsoup.parse(new URL("https://www.landeswelle.de/aktuell/verkehr"), DEFAULT_READ_TIMEOUT);
        builder.append("Blitzer\n");
        Elements container = doc.getElementsByClass("mobile-speed-trap");
        for (Element child : container) {
            builder.append(child.text());
            builder.append("\n");
        }
        builder.append("Verkehr\n");
        Elements traffics = doc.getElementsByClass("traffic-messages");
        for (Element child : traffics) {
            builder.append(child.text());
            builder.append("\n");
        }
        return builder.toString();

    }

    @Override
    public String getFeedTitle() {
        return "Landeswelle (Thüringen)";
    }


}
