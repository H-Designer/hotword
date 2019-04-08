package oper;

import com.alibaba.fastjson.JSONObject;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

public class GetInfo implements PageProcessor {
	public static JSONObject jsonO;
	public static String type1 = "";
	public static String information = "";
	public static String news1 = "";
	public static String newsadd1 = "";
	private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
	
	@Override
	public void process(Page page) {
		JSONObject jsonObject = new JSONObject();
		information = "";
		// TODO Auto-generated method stub
		Html html = page.getHtml();
		String type = html.xpath("//dd[@class='lemmaWgt-lemmaTitle-title']/h2/text()").get();
		type1 = type;
		int i = 1;
		while(true) {
			String s = html.xpath("//div[@class='lemma-summary']/div["+i+"]/allText()").get();
			if(s != null) {
				information+=s+";";
				i ++;
			}else {
				break;
			}
		}
		//System.out.println(information);
		jsonObject.put("information", information);
		String news = html.xpath("//span[@class='hotspotminingContent_cnt_tit_tit']/a/text()").get();
		news1 = news==null?"无":news;
		//System.out.println(news1);
		jsonObject.put("news", news1);
		String newsadd = html.xpath("//span[@class='hotspotminingContent_cnt_tit_tit']/a/@href").get();
		newsadd1 = newsadd==null?"无":newsadd;
		//System.out.println(newsadd1);
		//Info info = new Info();
		jsonObject.put("newsadd", newsadd1);
		jsonO = jsonObject;
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}
	public static void gi(String name) {
		Spider.create(new GetInfo())
			.addUrl("https://baike.baidu.com/item/"+name)
			.thread(5)
			.run();
	}
}
