package fenci;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.MapUtils;

import util.WordUtil;

import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.CRF.CRFSegment;
import com.hankcs.hanlp.seg.Viterbi.ViterbiSegment;
import com.hankcs.hanlp.seg.common.Term;

public class WordFrequency {

	public static void getWordFrequency(String pathString, String frequencyOutpuPath) throws IOException {

		StringBuffer sb = new StringBuffer();
		WordUtil.readFileToBuffer(sb, pathString);
		String sourceText = sb.toString();
		StringBuffer allNewWords = new StringBuffer();

		String reg = "^[a-zA-Z]+$"; // regex that matches English
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher("");
		String reg1 = "^\\d+$"; // 去掉纯数字的
		Pattern pattern1 = Pattern.compile(reg1);
		Matcher matcher1 = pattern1.matcher(""); 

		// 所有的分词
		Segment shortestSegment = new ViterbiSegment().enableCustomDictionary(
				false).enableAllNamedEntityRecognize(true);
		List<Term> termList = shortestSegment.seg(sourceText);
		List<String> allWords = new ArrayList<String>();
		Map<String, Long> wordCountMap = new HashMap<String, Long>();

		for (Term term : termList) // 所有的分词
		{
			String secondString = term.word.replaceAll(
					"[\\p{P}+~$`^=|<>～｀＄＾＋＝ ｜	＜＞	￥　× ]", ""); // 把所有符号替换为空
	    	if(secondString.equals("857")) {
	    		System.out.println("dd");
	    	}
			if (1 < secondString.length() && secondString.length() < 10) { // 去掉单字词，或者过长的词
				matcher.reset(secondString); // 去掉纯英文的
				matcher1.reset(secondString); // 去掉纯数字的
				if (!matcher.matches() || !matcher1.matches()) {
					// 进来的才是有意义的词
					allWords.add(secondString);
				}
			}
		}

		for (String s : allWords) {
			// 进来的都是需要统计的词
			Long oldCountLong = MapUtils.getLong(wordCountMap, s, 0L);
			wordCountMap.put(s, ++oldCountLong);

		}

		// 新词
		Segment segment = new CRFSegment();
		segment.enablePartOfSpeechTagging(true);
		List<Term> termList1 = segment.seg(sourceText);
		List<String> allWords1 = new ArrayList<String>();
		Map<String, Long> wordCountMap1 = new HashMap<String, Long>();

		for (Term term : termList1) {
			if (term.nature == Nature.nz) {
				String secondString1 = term.word.replaceAll(
						"[\\p{P}+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]", ""); // 把所有符号替换为空
				if (1 < secondString1.length() && secondString1.length() < 10) { // 去掉单字词，或者过长的词
					matcher.reset(secondString1); // 去掉纯数字的
					if (!matcher.matches()) {
						// 进来的才是有意义的词
						allWords1.add(secondString1);
					}
				}
			}
		}

		for (String s : allWords1) {
			// 进来的都是需要统计的词
			Long oldCountLong = MapUtils.getLong(wordCountMap1, s, 0L);
			wordCountMap1.put(s, ++oldCountLong);
		}

		Set<Entry<String, Long>> wordCountEntries1 = wordCountMap1.entrySet();
		for (Entry<String, Long> entry : wordCountEntries1) {
			if(!wordCountMap.containsKey(entry.getKey())) { // 两个统计map要合并，相同key的就丢弃；循环小的，新词发现那个
				wordCountMap.put(entry.getKey(), entry.getValue());
			}
//			allNewWords.append(entry.toString()).append("\n");
		}
		
		Map<String, Long> wordCountMapFinal = WordUtil.sortMap(wordCountMap); // 按照词频降序排量
		
		Set<Entry<String, Long>> wordCountEntries = wordCountMapFinal.entrySet();
		for (Entry<String, Long> entry : wordCountEntries) { // 这次循环是向外写文件
			allNewWords.append(entry.toString()).append("\n");
		}

		System.out.println("开始写词频文件\n");
		WordUtil.writeStringToFile(allNewWords.toString(), frequencyOutpuPath);
		System.out.println("词频文件写完了\n");
	}
}
