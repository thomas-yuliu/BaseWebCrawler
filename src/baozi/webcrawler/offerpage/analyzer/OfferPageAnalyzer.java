package baozi.webcrawler.offerpage.analyzer;

import baozi.webcralwer.common.utils.LogManager;
import baozi.webcrawler.common.analyzer.Analyzer;
import baozi.webcrawler.common.metainfo.BaseURL;
import baozi.webcrawler.offerpage.entry.InstanceFactory;

public abstract class OfferPageAnalyzer implements Analyzer {
  private LogManager lm = new LogManager(OfferPageAnalyzer.class);
  
  @Override
  public void analyze(BaseURL url) {
    boolean result = isAnOfferPage(url);
    if(result){
      lm.logInfo(url.getUrl().toString() + " is possibly an offer page");
      InstanceFactory.getOfferPageStorage().storeOfferPage(url);
    } else {
      lm.logInfo(url.getUrl().toString() + " is not an offer page");
    }
  }

  public abstract boolean isAnOfferPage(BaseURL url);
}
