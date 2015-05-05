package baozi.webcrawler.common.urlidentifier;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import baozi.webcralwer.common.utils.LogManager;
import baozi.webcrawler.common.entry.InstanceFactory;
import baozi.webcrawler.common.metainfo.BaseRawUrlsOnThePage;
import baozi.webcrawler.common.metainfo.BaseURL;
import baozi.webcrawler.common.metainfo.JsoupDocWebPage;

public class JsoupBasedURLIdentifier implements URLIdentifier{
  private LogManager logger = new LogManager(JsoupBasedURLIdentifier.class);

  @Override
  public BaseRawUrlsOnThePage extractUrls(BaseURL url) {
    List<BaseURL> result = grabHTMLLinks(url);
    BaseRawUrlsOnThePage urls = InstanceFactory
        .getOneBaseRawUrlsOnThePageInstance();
    urls.putRawUrls(result);
    return urls;
  }

  private List<BaseURL> grabHTMLLinks(BaseURL url) {
    List<BaseURL> urlsToCrawl = new ArrayList<>();
    if(!(url.getPageContent() instanceof JsoupDocWebPage)){
      logger.logError("JsoupBasedURLIdentifier must be used together with JsoupDocWebPage");
      return urlsToCrawl;
    }
        
    Document doc = ((JsoupDocWebPage)url.getPageContent()).getDoc();
    for (Element ahref : doc.select("a[href]")){
      String absUrl = ahref.attr("abs:href");
      logger.logInfo("discover url on page: " + absUrl);
      putUrlToCrawl(absUrl, url, urlsToCrawl);
    }
    for (Element linkhref : doc.select("link[href]")){
      String absUrl = linkhref.attr("abs:href");
      logger.logInfo("discover link on page: " + absUrl);
      putUrlToCrawl(absUrl, url, urlsToCrawl);
    }
    return urlsToCrawl;
  }
  
  private void putUrlToCrawl(String absUrl, BaseURL url, List<BaseURL> urlsToCrawl){
    int questionMarkLocation = absUrl.indexOf("?");
    if (questionMarkLocation > -1) {
      logger.logDebug("link before chunking: " + absUrl);
      absUrl = absUrl.substring(0, questionMarkLocation);
      logger.logDebug("link after chunking: " + absUrl);
    }
    BaseURL obj = null;
    try {
      obj = new BaseURL(new URL(absUrl));
      obj.setDepthFromSeed(url.getDepthFromSeed()+1);
      urlsToCrawl.add(obj);
    } catch (MalformedURLException e) {
      logger.logError("exception during creating url: " + e.toString());
    }
  }
}
