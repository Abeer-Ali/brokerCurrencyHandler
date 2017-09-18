/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd5;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CurrencyHandler extends DefaultHandler {

private boolean foundCurrencyPairTag = false;
    private boolean bBid = false;
    private String currencySymbol;
    private float currentBid;

    CurrencyHandler(String currencySymbol)
    {
        this.currencySymbol = currencySymbol;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        //System.out.println("s " + qName);

        if(qName.equalsIgnoreCase("Rate"))
        {
            if (currencySymbol.equalsIgnoreCase(attributes.getValue("Symbol")))
                foundCurrencyPairTag = true;
        }
        else if(qName.equalsIgnoreCase("Bid"))
            this.bBid = true;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        //System.out.println("e " + qName);

        if(qName.equalsIgnoreCase("Rate"))
        {
            this.foundCurrencyPairTag = false;
        }
        else if(qName.equalsIgnoreCase("Bid"))
            this.bBid = false;
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

           //System.out.println("c " + new String(ch, start, length));

           if(this.foundCurrencyPairTag == true && this.bBid == true)
               this.currentBid = Float.parseFloat(new String(ch, start, length));
    }

    public float getCurrentBid() {

        return currentBid;
    }

}
