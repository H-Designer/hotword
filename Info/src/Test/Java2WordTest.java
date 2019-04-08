package Test;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import Dao.InfoDaoImpl;
import Model.Info;
import word.api.interfaces.IDocument;
import word.w2004.Document2004;
import word.w2004.elements.BreakLine;
import word.w2004.elements.Heading1;
import word.w2004.elements.Paragraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Java2WordTest {
//    @Test
//    public void Java2WordTest(){
//        IDocument myDoc = new Document2004();
//        myDoc.addEle(Heading1.with("标题").create());
//        myDoc.addEle(BreakLine.times(2).create());
//        myDoc.addEle(Paragraph.with("Powered by jas").create());
//        myDoc.addEle(Paragraph.with("陈佳傲").create());
//        File file = new File("Java2Word.doc");
//        PrintWriter printWriter = null;
//        try{
//            printWriter = new PrintWriter(file);
//        }catch (FileNotFoundException e){
//            e.printStackTrace();
//        }
//        String myWord = myDoc.getContent();
//        printWriter.println(myWord);
//        printWriter.close();
//    }
    @Test
    public void ToWordUtilTest(){
        ToWordUtil.setFile("Words.doc");
        int num = InfoDaoImpl.getTypeNum();
        String[] types = {"（人工智能）","（大数据）","（物联网）","（其他）"};
        List<Info> infos = InfoDaoImpl.load();
        for(int i = 0; i < num; i++) {
        	ToWordUtil.addHeading(types[i]+"相关热词", 1);
            for(Info info : infos) {
            	if(info.getType().equals(types[i])) {
	            	ToWordUtil.addHeading(info.getName(),2);
	            	String[] ss = info.getInformation().split(";");
	            	for(int j = 0; j < ss.length; j ++) {
	                    ToWordUtil.addPara(ss[j]);
	            	}
            	}
            }
        }
        ToWordUtil.flush();
//    }
//    @Test
//    public void txtToWord() throws IOException {
//        List<String> lines = FileUtils.readLines(new File("result1.txt"),"utf-8");
//        ToWordUtil.setFile("区块链.doc");
//        for (String s:lines
//             ) {
//            System.out.println(s);
//            if(s.contains("[")){
//                ToWordUtil.addPara(s.replaceAll("\\[","").replaceAll("\\]","").replaceAll("&","").trim());
//            }else {
//                ToWordUtil.addHeading(s.replaceAll("\\[","").replaceAll("\\]","").replaceAll("&","").trim(),2);
//            }
//        }
//        ToWordUtil.flush();
//
//     }
    }
}
