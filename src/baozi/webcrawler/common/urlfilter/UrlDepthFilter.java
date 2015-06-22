package baozi.webcrawler.common.urlfilter;

import baozi.webcralwer.common.utils.LogManager;
import baozi.webcrawler.common.metainfo.BaseURL;

public class UrlDepthFilter implements Filter {
  private static transient LogManager lm = new LogManager(UrlDepthFilter.class);
  private int depthCeiling; //no more than this ceiling
  public UrlDepthFilter(int depth){
    depthCeiling = depth;
  }

  @Override
  public boolean filter(BaseURL url) {
    lm.logDebug("current url: " + url.getUrl().toString() + " has a depth of " + url.getDepthFromSeed());
    return url.getDepthFromSeed() < depthCeiling;
  }

}
