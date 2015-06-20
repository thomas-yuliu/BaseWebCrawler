package baozi.webcrawler.common.queue;

import java.util.List;

import baozi.webcrawler.common.metainfo.BaseURL;

public interface URLQueue {
  
  public boolean hasMoreUrls();
  
  public BaseURL getNextUrl();
  
  public void putNextUrls(List<BaseURL> nextUrls);
}
