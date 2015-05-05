package baozi.webcrawler.common.metainfo;

public abstract class BaseWebPage {
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
