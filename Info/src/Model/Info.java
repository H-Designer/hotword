package Model;

public class Info {
	private String name;//热词名称
	private String type;//所属领域
	private String information;//详细信息
	private String title;//所在标题名称
	private String webadd;//所在网页地址
	private String news;
	private String newsadd;
	private int sum;
	public String getNews() {
		return news;
	}
	public void setNews(String news) {
		this.news = news;
	}
	public String getNewsadd() {
		return newsadd;
	}
	public void setNewsadd(String newsadd) {
		this.newsadd = newsadd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWebadd() {
		return webadd;
	}
	public void setWebadd(String webadd) {
		this.webadd = webadd;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
}
