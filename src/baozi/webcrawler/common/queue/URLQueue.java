package baozi.webcrawler.common.queue;

import baozi.webcrawler.common.metainfo.BaseToCrawlUrls;
import baozi.webcrawler.common.metainfo.BaseURL;

public interface URLQueue {
  
  public boolean hasMoreUrls();
  
  public BaseURL getNextUrl();
  
  public void putNextUrls(BaseToCrawlUrls nextUrls);
}
