package org.openauto.localspeedcam.modules;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class RadioRT1 extends TrafficModule{

    public String getFeedContent() throws IOException {
        StringBuilder builder = new StringBuilder();

        //This code has to be adapted for your local speed cam source
        Document doc = Jsoup.parse(new URL("https://www.rt1.de/verkehr/"), 5000);
        Elements container = doc.getElementsByClass("element rt1 trafficMessages");
        Elements speed_cams = container.get(0).getElementsByClass("speed_cams");
        for (Element child : speed_cams.tagName("div").get(0).children()) {
            builder.append(child.text());
            builder.append("\n");
        }
        Elements traffic = container.get(0).getElementsByClass("traffic");
        for (Element child : traffic.tagName("div").get(0).children()) {
            builder.append(child.text());
            builder.append("\n");
        }
        return builder.toString();

    }

    @Override
    public String getFeedTitle() {
        return "HITRADIO RT1 Augsburg";
    }


}
