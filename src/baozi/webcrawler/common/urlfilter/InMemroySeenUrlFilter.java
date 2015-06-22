package baozi.webcrawler.common.urlfilter;

import java.util.HashSet;
import java.util.Set;

import baozi.webcralwer.common.utils.LogManager;
import baozi.webcrawler.common.metainfo.BaseURL;
import baozi.webcrawler.common.webcomm.HTTPWebCommManager;

public class InMemroySeenUrlFilter implements Filter {
  private static LogManager logger = new LogManager(InMemroySeenUrlFilter.class);

  private Set<BaseURL> seen = new HashSet<>();

  @Override
  public boolean filter(BaseURL url) {
    //logger.logDebug("current seen: " + seen.toString());
    if(seen.contains(url)){
      logger.logDebug("url: " + url.getUrl() + " has been seen before");
      return false;
    } else {
      seen.add(url);
      logger.logDebug("url: " + url.getUrl() + " has not been seen before");
      return true;
    }
  }
}
