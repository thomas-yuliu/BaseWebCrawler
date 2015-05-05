package baozi.webcrawler.common.urlfilter;

import java.util.ArrayList;
import java.util.List;

import baozi.webcralwer.common.utils.LogManager;
import baozi.webcrawler.common.metainfo.BaseURL;

public abstract class FilterEnforcer {
  private LogManager logger = new LogManager(FilterEnforcer.class);

  private List<Filter> filters = new ArrayList<Filter>();

  public void addFilter(Filter filter) {
    filters.add(filter);
  }

  public boolean applyFilters(BaseURL baseUrl) {
    boolean shouldCrawl = true;
    for (Filter filter : filters) {
      shouldCrawl = filter.filter(baseUrl);
      logger.logDebug("Applying filter: " + filter.getClass() + " result is: "
          + shouldCrawl);
      if (!shouldCrawl) {
        break;
      }
    }
    return shouldCrawl;
  }
}
