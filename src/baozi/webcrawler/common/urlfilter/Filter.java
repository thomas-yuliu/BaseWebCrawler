package baozi.webcrawler.common.urlfilter;

import java.io.Serializable;

import baozi.webcrawler.common.metainfo.BaseURL;

public interface Filter extends Serializable{

  public boolean filter(BaseURL url);
}
