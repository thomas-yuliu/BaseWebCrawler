package baozi.webcrawler.common.metainfo;

import java.net.URL;

import baozi.webcralwer.common.utils.LogManager;
import baozi.webcrawler.common.webcomm.HTTPWebCommManager;
import baozi.webcrawler.common.webcomm.WebCommManager;

public class BaseURL {
  private LogManager logger = new LogManager(BaseURL.class);

  private URL url;
  private int depthFromSeed = -1;
  private BaseWebPage page;
  
  public BaseURL(URL input){
    this.url = input;
  }

  public BaseURL(URL input, BaseWebPage page){
    this.url = input;
    this.page = page;
  }
  
  public void setDepthFromSeed(int currentDepth){
    if(depthFromSeed == -1 || depthFromSeed > currentDepth){
      depthFromSeed = currentDepth;
    }
  }
  
  public int getDepthFromSeed(){
    return depthFromSeed;
  }

  public URL getUrl() {
    return url;
  }

  public void setUrl(URL url) {
    this.url = url;
  }
  
  public BaseWebPage getPageContent(){
    return this.page;
  }
  
  public void downloadPageContent(WebCommManager webcomm){
    page = webcomm.fetchPage(this);
  }
  
  public boolean isValid(){
    return page!=null && page.hasContent();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((url == null) ? 0 : url.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    BaseURL other = (BaseURL) obj;
    if (url == null) {
      if (other.url != null)
        return false;
    } else if (!url.equals(other.url))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "BaseURL [url=" + url + ", depthFromSeed=" + depthFromSeed + "]";
  }
}
