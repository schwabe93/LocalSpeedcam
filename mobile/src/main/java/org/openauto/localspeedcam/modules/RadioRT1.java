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
        Document doc = Jsoup.parse(new URL("https://www.rt1.de/verkehr/"), DEFAULT_READ_TIMEOUT);
        Elements container = doc.getElementsByClass("element rt1 trafficMessages");
        Elements speed_cams = container.get(0).getElementsByClass("speed_cams");
        for (Element child : speed_cams.get(0).getElementsByTag("li")) {
            builder.append(child.text());
            builder.append("\n");
        }
        Elements traffic = container.get(0).getElementsByClass("traffic");
        for (Element child : traffic.get(0).getElementsByTag("li")) {
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
