package baozi.webcrawler.common.metainfo;

import java.util.List;

public interface BaseToCrawlUrls {

  public void putToCrawlUrls(List<BaseURL> urls);
  
  public List<BaseURL> getToCrawlUrls();
}
