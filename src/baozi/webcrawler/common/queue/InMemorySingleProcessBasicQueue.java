package baozi.webcrawler.common.queue;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

import baozi.webcralwer.common.utils.LogManager;
import baozi.webcrawler.common.metainfo.BaseURL;

public class InMemorySingleProcessBasicQueue implements URLQueue {
  private LogManager logger = new LogManager(InMemorySingleProcessBasicQueue.class);

  private Queue<BaseURL> nextUrls = new LinkedBlockingDeque<>();

  @Override
  public boolean hasMoreUrls() {
    logger.logInfo("current queue: " + nextUrls.toString());
    return !this.nextUrls.isEmpty();
  }

  @Override
  public BaseURL getNextUrl() {
    return this.nextUrls.poll();
  }

  @Override
  public void putNextUrls(List<BaseURL> nextUrls) {
    if(nextUrls.size() != 0 && this.nextUrls.size() < 10000){
      this.nextUrls.addAll(nextUrls);
    }
  }

}
