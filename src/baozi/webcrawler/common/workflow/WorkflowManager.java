package baozi.webcrawler.common.workflow;

import baozi.webcralwer.common.utils.LogManager;
import baozi.webcrawler.common.analyzer.Analyzer;
import baozi.webcrawler.common.entry.InstanceFactory;
import baozi.webcrawler.common.metainfo.BaseRawUrlsOnThePage;
import baozi.webcrawler.common.metainfo.BaseToCrawlUrls;
import baozi.webcrawler.common.metainfo.BaseURL;
import baozi.webcrawler.common.metainfo.BaseWebPage;
import baozi.webcrawler.common.queue.URLQueue;
import baozi.webcrawler.common.urlfilter.PostExpansionFilterEnforcer;
import baozi.webcrawler.common.urlfilter.PreExpansionFilterEnforcer;
import baozi.webcrawler.common.urlfilter.UrlDepthFilter;
import baozi.webcrawler.common.urlidentifier.URLIdentifier;
import baozi.webcrawler.common.webcomm.HTTPWebCommManager;
import baozi.webcrawler.common.webcomm.WebCommManager;

public class WorkflowManager {
  private LogManager logger = new LogManager(HTTPWebCommManager.class);

  private URLQueue nextQueue = InstanceFactory.getNextURLQueueInstance();
  private WebCommManager webcomm = InstanceFactory.getWebCommManager();
  private Analyzer analyzer = InstanceFactory.getAnalyzer();
  private URLIdentifier urlIdentifier = InstanceFactory.getURLIdentifier();
  private PreExpansionFilterEnforcer preExpansionfilterEnforcer = InstanceFactory.getPreExpansionFilterEnforcer();
  private PostExpansionFilterEnforcer postExpansionfilterEnforcer = InstanceFactory.getPostExpansionFilterEnforcer();

  public void crawl(){
    while(shouldContinue()){
      BaseURL currUrl = nextQueue.getNextUrl();
      currUrl.downloadPageContent(webcomm);
      if (currUrl.isValid()) {
        analyzer.analyze(currUrl);
        if(preExpansionfilterEnforcer.applyFilters(currUrl)){
          BaseRawUrlsOnThePage rawUrls = urlIdentifier.extractUrls(currUrl);
          BaseToCrawlUrls nextUrls = postExpansionfilterEnforcer.filterUrls(rawUrls);
          nextQueue.putNextUrls(nextUrls);
        }
      }
      //TODO implement a pace keeper
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  
  protected boolean shouldContinue(){
    logger.logInfo("should continue: " + nextQueue.hasMoreUrls());
    return nextQueue.hasMoreUrls();
  }
}
