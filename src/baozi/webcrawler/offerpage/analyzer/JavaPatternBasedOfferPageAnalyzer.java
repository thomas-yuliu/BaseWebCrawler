package baozi.webcrawler.offerpage.analyzer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import baozi.webcralwer.common.utils.LogManager;
import baozi.webcrawler.common.metainfo.BaseURL;
import baozi.webcrawler.offerpage.entry.InstanceFactory;

public class JavaPatternBasedOfferPageAnalyzer extends OfferPageAnalyzer {
  private LogManager lm = new LogManager(JavaPatternBasedOfferPageAnalyzer.class);
  private static final String JAVA_PATTERN = ".(offer|OFFER).";

  @Override
  public boolean isAnOfferPage(BaseURL url) {
    Pattern patternTag = Pattern.compile(JAVA_PATTERN);
    Matcher matcherTag = patternTag.matcher(url.getPageContent().getPageHtml());
    boolean result = matcherTag.find();
    
    String TITLE_PATTERN = "<title>.</title>";
    Pattern titleTag = Pattern.compile(TITLE_PATTERN);
    Matcher titleMatcherTag = titleTag.matcher(url.getPageContent().getPageHtml());
    url.getPageContent().setTitle(titleMatcherTag.group(1));
    
    return result;
  }

}
