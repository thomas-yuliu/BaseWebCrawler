package baozi.webcrawler.common.metainfo;

public class HtmlStringWebPage extends BaseWebPage{
  private String pageHtml;
  
  public void setPageHtml(String pageHtml){
    this.pageHtml = pageHtml;
  }

  public String getPageHtml(){
    return pageHtml;
  }
  
  @Override
  public boolean hasContent(){
    return !pageHtml.isEmpty();
  }

  @Override
  public String toString() {
    return "HtmlStringWebPage [pageHtml=" + pageHtml + "]";
  }
}
