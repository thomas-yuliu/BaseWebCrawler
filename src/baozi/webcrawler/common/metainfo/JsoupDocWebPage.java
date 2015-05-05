package baozi.webcrawler.common.metainfo;

import org.jsoup.nodes.Document;

public class JsoupDocWebPage extends BaseWebPage{
  private Document doc;
  
  
  @Override
  public String toString() {
    return "JsoupDocWebPage [pageHtml=" + doc.toString() + "]";
  }
  
  @Override
  public boolean hasContent(){
    return doc!=null && doc.hasText();
  }

  public Document getDoc() {
    return doc;
  }

  public void setDoc(Document doc) {
    this.doc = doc;
  }

  @Override
  public String getPageHtml() {
    return doc.toString();
  }
}
