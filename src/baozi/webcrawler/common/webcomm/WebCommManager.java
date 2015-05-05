package baozi.webcrawler.common.webcomm;

import baozi.webcrawler.common.metainfo.BaseURL;
import baozi.webcrawler.common.metainfo.BaseWebPage;

public interface WebCommManager {

  public BaseWebPage fetchPage(BaseURL url);
}
