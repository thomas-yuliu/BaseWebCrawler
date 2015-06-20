package baozi.webcrawler.common.urlfilter;

import java.util.ArrayList;
import java.util.List;

import baozi.webcralwer.common.utils.LogManager;
import baozi.webcrawler.common.entry.InstanceFactory;
import baozi.webcrawler.common.metainfo.BaseURL;

public class PostExpansionFilterEnforcer extends FilterEnforcer{
  private LogManager logger = new LogManager(FilterEnforcer.class);

  public List<BaseURL> filterUrls(List<BaseURL> rawUrls){
    List<BaseURL> resultList = new ArrayList<>();
    for (BaseURL baseUrl : rawUrls){
      logger.logDebug("Url: " + baseUrl.getUrl() + " being applied filters");
      if(applyFilters(baseUrl)){
        resultList.add(baseUrl);
      }
    }
    return resultList;
  }
}
