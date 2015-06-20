package baozi.webcrawler.common.urlidentifier;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import baozi.webcralwer.common.utils.LogManager;
import baozi.webcrawler.common.metainfo.BaseURL;

public class JavaRegexBasedURLIdentifier implements URLIdentifier{
  private LogManager logger = new LogManager(JavaRegexBasedURLIdentifier.class);

  @Override
  public List<BaseURL> extractUrls(BaseURL url) {
    return grabHTMLLinks(url);
  }

  private Pattern patternTag, patternLink;
  private Matcher matcherTag, matcherLink;

  // need to allow links like: <link href>
  // need to allow relative path (we need to construct full path ourselves from
  // domain)

  private static final String HTML_A_TAG_PATTERN = "(?i)<a([^>]+)>(.+?)</a>";
  private static final String HTML_A_HREF_TAG_PATTERN = "\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))";

  // private static final String HTML_A_TAG_PATTERN = "<a([^>]+)>(.+?)</a>";
  // private static final String HTML_A_HREF_TAG_PATTERN = "http://\\*";
  // "\\s*(?i)href\\s*=\\s*http*";

  private List<BaseURL> grabHTMLLinks(BaseURL url) {
    String html = url.getPageContent().getPageHtml();
    int currentPageDepth = url.getDepthFromSeed();

    patternTag = Pattern.compile(HTML_A_TAG_PATTERN);
    patternLink = Pattern.compile(HTML_A_HREF_TAG_PATTERN);
    List<BaseURL> result = new ArrayList<BaseURL>();
    matcherTag = patternTag.matcher(html);
    while (matcherTag.find()) {
      String href = matcherTag.group(1); // href
      String linkText = matcherTag.group(2); // link text
      matcherLink = patternLink.matcher(href);
      logger.logDebug("href: " + href);
      
      while (matcherLink.find()) {
        String link = matcherLink.group(1); // link
        /* tmp to use java regex */
        if(link.length()<2){
          continue;
        }
        link = link.substring(1, link.length() - 1); // remove "" sign
        int questionMarkLocation = link.indexOf("?");
        if (questionMarkLocation > -1) {
          logger.logDebug("link before chunking: " + link);
          link = link.substring(0, questionMarkLocation);
          logger.logDebug("link after chunking: " + link);
        }
        /* tmp end */
        BaseURL obj = null;
        try {
          if (!link.startsWith("http")) {
            String host = url.getUrl().getHost();
            String protocol = url.getUrl().getProtocol();
            obj = new BaseURL(new URL(protocol, host, link));
            logger.logInfo("composed from base: " + obj.getUrl().toString());
          } else {
            logger.logInfo("discover link on the page: " + link + "; depth: "
                + (currentPageDepth + 1));
            obj = new BaseURL(new URL(link));
          }
        } catch (MalformedURLException e) {
          logger.logError("exception during creating url: " + e.toString());
        }
        obj.setDepthFromSeed(currentPageDepth + 1);
        result.add(obj);
      }
    }
    return result;
  }
}
