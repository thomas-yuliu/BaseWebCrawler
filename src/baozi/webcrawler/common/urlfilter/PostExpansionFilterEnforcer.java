package baozi.webcrawler.common.urlfilter;

import java.util.ArrayList;
import java.util.List;

import baozi.webcralwer.common.utils.LogManager;
import baozi.webcrawler.common.entry.InstanceFactory;
import baozi.webcrawler.common.metainfo.BaseRawUrlsOnThePage;
import baozi.webcrawler.common.metainfo.BaseToCrawlUrls;
import baozi.webcrawler.common.metainfo.BaseURL;

public class PostExpansionFilterEnforcer extends FilterEnforcer{
  private LogManager logger = new LogManager(FilterEnforcer.class);

  public BaseToCrawlUrls filterUrls(BaseRawUrlsOnThePage rawURLs){
    BaseToCrawlUrls result = InstanceFactory.getOneBaseToCrawlUrlsInstance();
    List<BaseURL> resultList = new ArrayList<>();
    for (BaseURL baseUrl : rawURLs.getRawUrls()){
      logger.logDebug("Url: " + baseUrl.getUrl() + " being applied filters");
      boolean shouldCrawl = applyFilters(baseUrl);
      
      if(shouldCrawl){
        resultList.add(baseUrl);
      }
    }
    result.putToCrawlUrls(resultList);
    return result;
  }
}
