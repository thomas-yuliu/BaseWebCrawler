package baozi.webcrawler.common.webcomm;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import baozi.webcralwer.common.utils.LogManager;
import baozi.webcrawler.common.entry.InstanceFactory;
import baozi.webcrawler.common.metainfo.BaseURL;
import baozi.webcrawler.common.metainfo.BaseWebPage;
import baozi.webcrawler.common.metainfo.JsoupDocWebPage;

public class JsoupWebCommManager implements WebCommManager {
  private LogManager logger = new LogManager(JsoupWebCommManager.class);

  @Override
  public BaseWebPage fetchPage(BaseURL url) {
    
    try {
      Document doc = Jsoup.connect(url.getUrl().toString()).userAgent("Mozilla").get();
      //logger.logDebug("doc: " + doc.toString());
      BaseWebPage result = InstanceFactory.getOneWebPage();
      ((JsoupDocWebPage)result).setDoc(doc);
      return result;
    } catch (IOException e) {
      logger.logError(e.toString());
    }
    return null;
  }
}
