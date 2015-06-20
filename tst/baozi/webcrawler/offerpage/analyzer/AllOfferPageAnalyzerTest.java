package baozi.webcrawler.offerpage.analyzer;

import org.junit.Test;

import baozi.webcrawler.common.entry.InstanceFactory;
import baozi.webcrawler.common.metainfo.BaseURL;
import baozi.webcrawler.common.metainfo.BaseWebPage;

public class AllOfferPageAnalyzerTest {

  private static final OfferPageAnalyzer offerPageAnalyzer = new JavaPatternBasedOfferPageAnalyzer();
  
  private boolean isAnOfferPage(String html){
    BaseWebPage page = InstanceFactory.getOneWebPage();
    BaseURL url = new BaseURL(null, page);
    return offerPageAnalyzer.isAnOfferPage(url);
  }
  
  @Test
  public void testNoOfferPage() {
    String html = "random html";
    boolean result = isAnOfferPage(html);
    assert !result;
  }

  @Test
  public void testIsAnOfferPage() {
    String html = "random html offer random html";
    boolean result = isAnOfferPage(html);
    assert result;
  }

  @Test
  public void testIsAnOfferPage2() {
    String html = "random html OFFER random html";
    boolean result = isAnOfferPage(html);
    assert result;
  }

}
