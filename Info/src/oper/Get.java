package oper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Dao.MiddleInfoDaoImpl;
import Model.MiddleInfo;
import cn.wanghaomiao.xpath.exception.NoSuchAxisException;
import cn.wanghaomiao.xpath.exception.NoSuchFunctionException;
import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;

public class Get {
	public static List<String> readFiles(String filePath) throws IOException {
		File file = new File(filePath);
		List<String> fileString = new ArrayList<>();
		if(file.isDirectory()) {
			String[] ss = file.list();
			for(int i = 0; i < ss.length; i++) {
				String s = filePath+"\\"+ss[i];
				//fileString.add(s);
				//System.out.println(s);
				String str = readFile(s);
				fileString.add(str);
			}
		}
		return fileString;
	}
	public static String readFile(String path) throws IOException {
		File file = new File(path);
		FileReader reader = new FileReader(file);
		BufferedReader bReader = new BufferedReader(reader);
		StringBuilder sb = new StringBuilder();
		String s = "";
		while ((s = bReader.readLine()) != null) {
			sb.append(s);
		}
		bReader.close();
		String str = sb.toString();
		return str;
	}
	public static void main(String[] args) throws IOException {
		//readFile("D:\\data\\edu\\www.itjie.com\\02338dadff9a1cb794d12124e800670c.html");
		List<String> files = readFiles("D:\\data\\edu4\\www.itjie.com");
		MiddleInfo middleInfo = null;
		for(String s : files) {
			JXDocument document = new JXDocument(s);
			try {
				middleInfo = new MiddleInfo();
				String title = document
						.sel("//h1[@class='entry-title']/text()")
						.get(0).toString();
				String content = document
						.sel("//div[@class='entry-content']/allText()")
						.get(0).toString();
				String webadd = s.substring(s.indexOf("http"),s.indexOf("html")+4);
				//System.out.println(content+" "+webadd);
				middleInfo.setTitle(title);
				middleInfo.setContent(content);
				middleInfo.setWebadd(webadd);
				MiddleInfoDaoImpl.add(middleInfo);
			} catch (XpathSyntaxErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (NoSuchAxisException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFunctionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
