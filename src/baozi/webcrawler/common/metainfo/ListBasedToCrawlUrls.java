package baozi.webcrawler.common.metainfo;

import java.util.ArrayList;
import java.util.List;

public class ListBasedToCrawlUrls implements BaseToCrawlUrls{
  private List<BaseURL> toCrawlUrls = new ArrayList<>();
  
  public List<BaseURL> getToCrawlUrls(){
    return this.toCrawlUrls;
  }

  @Override
  public void putToCrawlUrls(List<BaseURL> urls) {
    toCrawlUrls.addAll(urls);
  }
}
