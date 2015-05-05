package baozi.webcrawler.common.urlidentifier;

import baozi.webcrawler.common.metainfo.BaseRawUrlsOnThePage;
import baozi.webcrawler.common.metainfo.BaseURL;

public interface URLIdentifier {
  
  public BaseRawUrlsOnThePage extractUrls(BaseURL url);

}
