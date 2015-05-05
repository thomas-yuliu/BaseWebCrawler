package baozi.webcrawler.offerpage.analyzer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import baozi.webcralwer.common.utils.LogManager;
import baozi.webcrawler.common.metainfo.BaseURL;
import baozi.webcrawler.common.metainfo.HtmlStringWebPage;
import baozi.webcrawler.common.metainfo.JsoupDocWebPage;
import baozi.webcrawler.common.urlidentifier.UrlIdentifierTest;
import baozi.webcrawler.offerpage.entry.InstanceFactory;

public class JsoupBasedOfferPageAnalyzer implements OfferPageAnalyzer {
  private LogManager lm = new LogManager(JsoupBasedOfferPageAnalyzer.class);
  private static final String JAVA_PATTERN = ".(offer|OFFER|Offer).";

  @Override
  public void analyze(BaseURL url) {
    isAnOfferPage(url);
  }

  @Override
  public boolean isAnOfferPage(BaseURL url) {
    if(!(url.getPageContent() instanceof JsoupDocWebPage)){
      lm.logError("JsoupBasedOfferPageAnalyzer must be used together with JsoupDocWebPage");
      return false;
    }
        
    Document doc = ((JsoupDocWebPage)url.getPageContent()).getDoc();
    String title = doc.title();
    url.getPageContent().setTitle(title);
    Pattern patternTag = Pattern.compile(JAVA_PATTERN);
    Matcher matcherTag = patternTag.matcher(title);
    boolean result = matcherTag.find();
    if(result){
      lm.logInfo(url.getUrl().toString() + " is possibly an offer page");
      InstanceFactory.getOfferPageStorage().storeOfferPage(url);
    } else {
      lm.logInfo(url.getUrl().toString() + " is not an offer page");
    }
    return result;
  }

}
