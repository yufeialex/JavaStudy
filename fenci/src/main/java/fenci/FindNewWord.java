package fenci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.WordUtil;

import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.CRF.CRFSegment;
import com.hankcs.hanlp.seg.common.Term;

public class FindNewWord {

	public static void getNewWord(String pathString, String newWordOutputPath) throws IOException {
		
		
/*		Segment segment = new CRFSegment();
		segment.enablePartOfSpeechTagging(true);
		List<Term> termList = segment.seg("你看过辛雨非吗，辛雨非在哪里。刘迪爱放屁，你说他是不是大屁王，哈哈哈哈");
		System.out.println(termList);
		for (Term term : termList)
		{
		    if (term.nature == Nature.nz)
		    {
		        System.out.println("识别到新词：" + term.word);
		    }
		}*/
		
		StringBuffer sb = new StringBuffer();
		WordUtil.readFileToBuffer(sb, pathString);
		String a = sb.toString();
		
		Segment segment = new CRFSegment();
		segment.enablePartOfSpeechTagging(true);
		List<Term> termList = segment.seg(a);
		StringBuffer allNewWords = new StringBuffer();
		
		Set<String> quchongSet = new HashSet<String>();
		
		for (Term term : termList)
		{
		    if (term.nature == Nature.nz)
		    {
		    	quchongSet.add(term.word); // 所有新词添加进来，去重
		    }
		}
		
		String reg = "^[a-zA-Z]+$"; // regex that matches English
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher("");
		String reg1 = "^\\d+$"; // 去掉纯数字的
		Pattern pattern1 = Pattern.compile(reg1);
		Matcher matcher1 = pattern1.matcher("");
		
		for(String s : quchongSet) {
	    	String secondString =s.replaceAll( "[\\p{P}+~$`^=|<>～｀＄＾＋＝｜	＜＞	￥　× ]" , ""); //把所有符号替换为空
	    	if(1 < secondString.length() && secondString.length() < 10) { // 去掉单字词，或者过长的词
	    		matcher.reset(secondString);
	    		matcher1.reset(secondString);
	    		if(!matcher.matches() && !matcher1.matches()) {
	    			allNewWords.append(secondString).append("\n");
	    		}
	    	}
		}
	
		System.out.println("Begin write newWord file!\n");
		WordUtil.writeStringToFile(allNewWords.toString(), newWordOutputPath);
		System.out.println("NewWord file write finished!\n");
	}
}
