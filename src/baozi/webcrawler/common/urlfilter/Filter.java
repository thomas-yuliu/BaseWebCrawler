package baozi.webcrawler.common.urlfilter;

import baozi.webcrawler.common.metainfo.BaseURL;

public interface Filter {

  public boolean filter(BaseURL url);
}
