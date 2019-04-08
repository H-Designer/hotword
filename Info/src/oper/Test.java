package oper;

import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.mining.word.WordInfo;

import Dao.InfoDaoImpl;
import Dao.MiddleInfoDaoImpl;
import Model.Info;
import Model.MiddleInfo;

public class Test {
	public static void main(String[] args) {
		List<MiddleInfo> infos = MiddleInfoDaoImpl.load();
		List<WordInfo> keywordList = null;
		for(MiddleInfo info : infos) {
			keywordList = HanLP.extractWords(info.getContent(), 10, false);
			System.out.println(keywordList);
		}
	}
}
