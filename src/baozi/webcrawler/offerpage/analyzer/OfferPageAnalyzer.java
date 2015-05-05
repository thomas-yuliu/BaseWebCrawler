package baozi.webcrawler.offerpage.analyzer;

import baozi.webcrawler.common.analyzer.Analyzer;
import baozi.webcrawler.common.metainfo.BaseURL;

public interface OfferPageAnalyzer extends Analyzer {

  public boolean isAnOfferPage(BaseURL url);
}
