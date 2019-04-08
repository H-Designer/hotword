package oper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.mining.word.WordInfo;

import Dao.InfoDaoImpl;
import Dao.MiddleInfoDaoImpl;
import Model.Info;
import Model.MiddleInfo;

public class TestHanlp {
	//判断整个字符串是不是都是中文
//	public boolean checkname(String name)
//    {
//        int n = 0;
//        for(int i = 0; i < name.length(); i++) {
//            n = (int)name.charAt(i);
//            if(!(19968 <= n && n <40869)) {
//                return false;
//            }
//        }
//        return true;
//    }
	//判断是否含有特殊符号
	public static boolean isSpecialChar(String str) {
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }
	public static void main(String[] args) {
		//Segment segment = HanLP.newSegment().enableCustomDictionaryForcing(true);
		List<MiddleInfo> middleInfos = MiddleInfoDaoImpl.load();
		String s = "";
		List<List<String>> dyss = new ArrayList<>();
		for(MiddleInfo mi : middleInfos) {
			s = mi.getContent();
			List<String> dys = HanLP.extractKeyword(s, 2);
			for(int i = 0; i < dys.size(); i++) {
				if(dys.get(i).length() <= 1) {
					dys.set(i, "");
				}
				if(dys.get(i).equals("区块")) {
					dys.set(i, "区块链");
				}
			}
			dys.add(mi.getTitle());
			dys.add(mi.getWebadd());
			dyss.add(dys);
		}
		Info info = null;
		for(List<String> ls : dyss) {
			for(String si : ls) {
				if(si.endsWith("html"))
					continue;
				if(si.equals("")) {
					
				}else {
					info = new Info();
					info.setName(si);
					info.setTitle(ls.get(ls.size()-2));
					info.setWebadd(ls.get(ls.size()-1));
					InfoDaoImpl.add(info);
				}
			}
		}
	}
}
