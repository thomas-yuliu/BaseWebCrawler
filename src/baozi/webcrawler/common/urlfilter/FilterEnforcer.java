package baozi.webcrawler.common.urlfilter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import baozi.webcralwer.common.utils.LogManager;
import baozi.webcrawler.common.metainfo.BaseURL;

public abstract class FilterEnforcer implements Serializable{
  /**
   * 
   */
  private static final long serialVersionUID = 6147898546435982641L;

  private static transient LogManager logger = new LogManager(FilterEnforcer.class);

  protected List<Filter> filters = new ArrayList<Filter>();

  //used by initializer to include filters
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

  @Override
  public String toString() {
    return "FilterEnforcer [filters=" + filters.toString() + "]";
  }
}
