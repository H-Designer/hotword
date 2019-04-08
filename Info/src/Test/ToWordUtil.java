package Test;

import word.api.interfaces.IDocument;
import word.w2004.Document2004;
import word.w2004.elements.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ToWordUtil {
    private static File file;
    private static PrintWriter printWriter;
    private static String myWord;
    private static IDocument document = new Document2004();
    static {

    }
    public static void setFile(String filePath) {
        ToWordUtil.file = new File(filePath);
        try {
            printWriter = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("创建文件失败");
        }
    }
    public static void addHeading(String content,int titleProv){
        switch (titleProv){
            case 1 : document.addEle(Heading1.with(content).create());break;
            case 2 : document.addEle(Heading2.with(content).create());break;
            case 3 : document.addEle(Heading3.with(content).create());break;
        }

    }
    public static void addPara(String content){
        document.addEle(Paragraph.with(content).withStyle().create());
    }
    public static void addBreakLine(int times){
        document.addEle(BreakLine.times(times).create());
    }
    public static void flush(){
        myWord = document.getContent();
        printWriter.println(myWord);
        printWriter.close();
    }

}
