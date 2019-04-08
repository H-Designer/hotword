package oper;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

public class GetRaw implements PageProcessor {
	private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
//		Selectable st = page.getHtml().links().regex("(https://www.itjie.com/\\w+.html)");
//		System.out.println(st.toString());
//		String title = st.xpath("//h1[@class='entry-title']/text()").get();
//		System.out.println(title);
		page.putField("allhtml", page.getHtml().toString());
		String urlstr = null;
		for(int i = 1; i < 1033; i++) {
			urlstr = "https://www.itjie.com/page/" + i;
			page.addTargetRequest(urlstr);
			page.addTargetRequests(page.getHtml().links().regex("(https://www.itjie.com/\\w+.html)").all());
		}
	}
	public static void main(String[] args) {
		System.out.println("开始爬取。。。。");
		Spider.create(new GetRaw())
			.addUrl("https://www.itjie.com/page/1")
			.addPipeline(new FilePipeline("/data/edu4"))
			.setScheduler(new FileCacheQueueScheduler("/data/edu4"))
			.thread(5)
			.run();
		System.out.println("爬取完成！");
	}
}
