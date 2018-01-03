package org.openauto.localspeedcam.modules;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class Radiosaw extends TrafficModule{

    public String getFeedContent() throws IOException {
        StringBuilder builder = new StringBuilder();

        //This code has to be adapted for your local speed cam source
        Document doc = Jsoup.parse(new URL("http://www.radiosaw.de/flitzerblitzer"), DEFAULT_READ_TIMEOUT);
        Elements container = doc.getElementsByAttributeValueContaining("style", "padding:7px 0px 7px 0px;text-align:left;font-weight:bold;");
        for (Element child : container) {
            builder.append(child.text());
            builder.append("\n");
        }
        return builder.toString();

    }

    @Override
    public String getFeedTitle() {
        return "radio SAW (Sachsen Anhalt)";
    }


}
