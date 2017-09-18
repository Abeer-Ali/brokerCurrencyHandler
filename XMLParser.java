/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd5;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class XMLParser {
    
private CurrencyHandler handler;
    private final String url = "http://rates.fxcm.com/RatesXML";
    Scanner sc = new Scanner(System.in);
    private float targetBidRate = 0.0f;
    private int waitTimeInSec = 0;
    private String currencyPair;


    XMLParser() {
        this.setCurrencySymbol();
        this.setTargetRate();
        this.setWaitTime();
        this.handler = new CurrencyHandler(currencyPair);
    }

    public boolean scheduleXMLParser() {

        while (handler.getCurrentBid() <= this.targetBidRate) {
            try {
                System.out.println("Scheduler: Waiting for " + this.waitTimeInSec + " sec");
                Thread.sleep(waitTimeInSec * 1000);
            } catch (InterruptedException ex) {
                return false;
            }

            if(!this.parseXML())
                return false;
            //System.out.println("testing - current bid: " + this.handler.getCurrentBid()); //for testing
        }

        System.out.println("Target Reached for " + this.currencyPair + ": " + this.handler.getCurrentBid());
        return true;
    }

    private boolean parseXML() {

        SAXParserFactory parser = SAXParserFactory.newInstance();

        try {
            URLConnection connection = new URL(url).openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.29 Safari/537.36");
            InputStream is = connection.getInputStream();

            SAXParser saxParser = parser.newSAXParser();

            saxParser.parse(is, handler);

        } catch (IOException ex) {
            return false;
        } catch (ParserConfigurationException ex) {
            return false;
        } catch (SAXException ex) {
            return false;
        }

        return true;
    }

    private void setCurrencySymbol() {

        CurrencyPairs currency = new CurrencyPairs();

        System.out.print("Please Enter Currency Pair to Track: ");
        currencyPair = sc.next();

        while (!currency.validateCurrencyPair(currencyPair)) {
            System.out.print("Invalid: Currency pair could not be validated Please ReEnter: ");
            currencyPair = sc.next();
        }

        System.out.println("Successfully set currency pair to " + currencyPair);
    }

    private void setTargetRate() {

        System.out.print("Please Enter a Target Rate: ");

        while (!sc.hasNextFloat()) {
            System.out.print("Invalid: That's not a number!");
            sc.next();
        }

        targetBidRate = sc.nextFloat();
        System.out.println("Successfully set Target Bid Rate set to " + targetBidRate);
    }

    public float getTargetRate()
    {
        return targetBidRate;
    }

    private void setWaitTime() {

        System.out.print("Please Enter Time in Seconds: ");

        do {
            if (sc.hasNextInt()) {
                waitTimeInSec = sc.nextInt();
                if(waitTimeInSec <0)
                    System.out.print("Invalid: That's not a positive number! Please ReEnter: ");
            }
            else {
                System.out.print("Invalid: That's not a number! Please ReEnter: ");
                sc.nextLine();
            }
        } while (waitTimeInSec <= 0);

        System.out.println("Successfully set WaitTime set to " + waitTimeInSec + " Seconds");
    }

    public int getWaitTime()
    {
        return this.waitTimeInSec;
    }
    
}
