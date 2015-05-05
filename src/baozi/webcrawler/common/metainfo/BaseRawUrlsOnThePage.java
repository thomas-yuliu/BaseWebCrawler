package baozi.webcrawler.common.metainfo;

import java.util.List;

public interface BaseRawUrlsOnThePage {
  
  public void putRawUrls(List<BaseURL> urls);
  
  public List<BaseURL> getRawUrls();
}
