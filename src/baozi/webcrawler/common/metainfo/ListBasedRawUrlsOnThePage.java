package baozi.webcrawler.common.metainfo;

import java.util.ArrayList;
import java.util.List;

public class ListBasedRawUrlsOnThePage implements BaseRawUrlsOnThePage{

  private List<BaseURL> urls = new ArrayList<>();
  
  @Override
  public void putRawUrls(List<BaseURL> url) {
    urls.addAll(url);
  }

  @Override
  public List<BaseURL> getRawUrls() {
    return urls;
  }
}
