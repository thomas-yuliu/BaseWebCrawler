package baozi.webcrawler.common.urlidentifier;

import java.util.List;

import baozi.webcrawler.common.metainfo.BaseURL;

public interface URLIdentifier {
  
  public List<BaseURL> extractUrls(BaseURL url);

}
