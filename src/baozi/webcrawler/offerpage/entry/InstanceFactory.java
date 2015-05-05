package baozi.webcrawler.offerpage.entry;

import baozi.webcrawler.offerpage.offerpagestorage.LocalFileOfferPageStorage;
import baozi.webcrawler.offerpage.offerpagestorage.OfferPageStorage;

public class InstanceFactory {
  
  private static final OfferPageStorage offerPageStorage = new LocalFileOfferPageStorage();

  public static OfferPageStorage getOfferPageStorage(){
    return offerPageStorage;
  }
}
