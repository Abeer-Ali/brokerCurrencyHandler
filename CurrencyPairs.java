/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd5;

public class CurrencyPairs {
 
    private final String[] currencyPair = {"EURUSD","USDJPY","GBPUSD","USDCHF","EURCHF","AUDUSD",
                                           "USDCAD","NZDUSD","EURJPY","GBPJPY","EURGBP","CHFJPY",
                                           "GBPCHF","EURAUD","EURCAD","AUDCAD","AUDJPY","CADJPY",
                                           "NZDJPY","GBPAUD","AUDNZD","AUDCHF","EURNZD","USDHKD",
                                           "USDMXN","GBPNZD","USDSEK","EURSEK","EURNOk","USDNOK",
                                           "USDZAR","GBPCAD","USDTRY","EURTRY","NZDCHF","CADCHF",
                                           "NZDCAD","US30","NAS100","UK100","GER30","ESP35","FRA40",
                                           "HKG33","JPN225","AUS200","USOil","UKOil","XAUUSD","XAGUSD",
                                           "USDOLLAR","USDILS","TRYJPY","USDCNH","NGAS","Copper","EUSTX50","Bund",};

    public boolean validateCurrencyPair(String currency)
    {
        for (String i : currencyPair)
        {
            if(currency.equalsIgnoreCase(i))
                return true;
        }

        return false;
    }

}
