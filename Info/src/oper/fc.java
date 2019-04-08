package oper;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.nlp.AipNlp;

public class fc {
	public static final String APP_ID = "15361955";
    public static final String API_KEY = "GAVgrveHMiFnmhOhBdcPG2ae";
    public static final String SECRET_KEY = "ScqzFvN0GjiDnzbZrQxbtbljh4d4Zhvr";
	public static void sample(AipNlp client) {
	    String text = "百度是一家区块链有关的高科技公司";

	    // 传入可选参数调用接口
	    HashMap<String, Object> options = new HashMap<String, Object>();
	    
	    // 词法分析
	    JSONObject res = client.lexer(text, options);
	    System.out.println(res.toString(2));

	}
	public static void main(String[] args) {
        AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);
		sample(client);
	}
}
