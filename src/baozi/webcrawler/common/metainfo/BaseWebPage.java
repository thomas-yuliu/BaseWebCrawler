package baozi.webcrawler.common.metainfo;

import java.io.Serializable;

public abstract class BaseWebPage implements Serializable{
  private String title;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
  
  abstract public boolean hasContent();
  
  abstract public String getPageHtml();
}
