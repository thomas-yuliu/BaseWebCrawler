package baozi.webcrawler.offerpage.analyzer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;

import baozi.webcralwer.common.utils.LogManager;
import baozi.webcrawler.common.metainfo.BaseURL;
import baozi.webcrawler.common.metainfo.JsoupDocWebPage;
import baozi.webcrawler.offerpage.entry.InstanceFactory;

public class JsoupBasedOfferPageAnalyzer extends OfferPageAnalyzer {
  private LogManager lm = new LogManager(JsoupBasedOfferPageAnalyzer.class);
  private static final String JAVA_PATTERN = ".(offer|OFFER|Offer).";

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
    return result;
  }

}
