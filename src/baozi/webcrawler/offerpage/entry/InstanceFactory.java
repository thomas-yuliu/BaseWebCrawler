package baozi.webcrawler.offerpage.entry;

import baozi.webcrawler.offerpage.offerpagestorage.LocalFileOfferPageStorage;
import baozi.webcrawler.offerpage.offerpagestorage.OfferPageStorage;
import baozi.webcrawler.offerpage.offerpagestorage.ParseOfferPageStorage;

public class InstanceFactory {
  
  //private static final OfferPageStorage offerPageStorage = new LocalFileOfferPageStorage();
  private static final OfferPageStorage offerPageStorage = new ParseOfferPageStorage();

  public static OfferPageStorage getOfferPageStorage(){
    return offerPageStorage;
  }
}
