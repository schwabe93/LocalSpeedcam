package org.openauto.localspeedcam.fines;

/**
 * Needs rework
 */
public class Fines {

    private static String INNERORTS = "\uD83C\uDFE1";
    private static String AUSSERORTS = "\uD83D\uDEE3";

    public static String DE_FINES = "Stand: Dezember 2017\n"+
            INNERORTS + " ab 21km/h 1 Punkt + 80€" + "\n" +
            INNERORTS + " ab 31km/h 2 Punkte + 160€, 1 Monat Fahrverbot" + "\n" +
            INNERORTS + " ab 51km/h 2 Punkte + 280€, 2 Monate Fahrverbot" + "\n" +
            INNERORTS + " ab 61km/h 2 Punkte + 480€, 3 Monate Fahrverbot" + "\n" +

            AUSSERORTS + " ab 21km/h 1 Punkt + 70€" + "\n" +
            AUSSERORTS + " ab 41km/h 2 Punkte + 160€, 1 Monat Fahrverbot" + "\n" +
            AUSSERORTS + " ab 61km/h 2 Punkte + 280€, 2 Monate Fahrverbot" + "\n" +
            AUSSERORTS + " ab 71km/h 2 Punkte + 600€, 3 Monate Fahrverbot";

    public static String CH_FINES = "Stand: 2017\n"+
            "1-5km/h 40CHF" + INNERORTS + AUSSERORTS + "\n" +
            "6-10km/h 40CHF" + INNERORTS + "\n" +
            "11-15km/h 40CHF" + INNERORTS + "\n" +
            "16-20km/h 120CHF Autobahn, 240CHF Ausserorts, Anzeige innerorts" + "\n" +
            "21-25km/h 260CHF auf der Autobahn, ansonsten Anzeige" +"\n" +
            "25+km/h immer eine strafrechtliche Anzeige!";


    public static String AT_FINES = "Stand: 2017\n"+
            "Promillegrenze (0,5 Promille) überschritten ab 300 Euro\n"+
            "Geschwindigkeitsverstoß 20 km/h: ab 30 Euro, über 50 km/h: bis 2180 Euro\n"+
            "Rote Ampel überfahren	ab 70 Euro\n"+
            "Überholverstoß	ab 70 Euro\n"+
            "Handy am Steuer ab 50 Euro\n"+
            "Sicherheitsgurt nicht angelegt	ab 35 Euro\n"+
            "Parkverstoß ab 20 Euro\n"+
            "Warnwestenpflicht missachtet ab 14 Euro\n";

}
