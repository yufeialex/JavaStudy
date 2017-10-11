package fenci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.MapUtils;

import util.WordUtil;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.Viterbi.ViterbiSegment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;

public class EntityAndRelation {

	public static void getKGAndRelLists(String inputFilePath, String KGpath, String relListPath) throws IOException {

		StringBuffer sb = new StringBuffer();
		WordUtil.readFileToBuffer(sb, inputFilePath);
		String fileContent = sb.toString();
		String[] sentences = fileContent.split("。| ？ | ！"); // Separator in Chinese; Extract relation needs statistics in sentence level

		Segment shortestSegment = new ViterbiSegment().enableAllNamedEntityRecognize(true);

		// key应该是同一个句子中出现的实体对，方便起见，把实体对放到set对象中，
		// value is other verbs and their occurrence count in the same sentences with the entity couple.
		// 比如 张三，李四 --> (朋友:5;邻居:8)
		Map<Set<String>, Map<String, Long>> entityRelationMap = new HashMap<Set<String>, Map<String, Long>>();

		String reg = "^[a-zA-Z]+$"; // regex that matches English
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher("");
		String reg1 = "^\\d+$"; // 去掉纯数字的
		Pattern pattern1 = Pattern.compile(reg1);
		Matcher matcher1 = pattern1.matcher("");
		for (int i = 0; i < sentences.length; i++) { // loop all sentences
			// 对每句话都做两个操作：
			// 1.分词，然后循环一次，分出实体词组和关系词组
			// 2.循环实体词组，构造实体对和（关系词，关系词词频）的映射
			String sentence = sentences[i];
			List<Term> termList = shortestSegment.seg(sentence);

			// **先区分实体和谓词**
			Set<String> entityWord = new HashSet<String>(); // 一句话中的实体不要重复
			List<String> otherWord = new ArrayList<String>(); // 一句话中的动词应该重，比如张三喜欢李四，真的喜欢李四。喜欢应该计算2遍

			
			for (int j = 0; j < termList.size(); j++) { // 循环一句中的所有单词
				Term term = termList.get(j);
				String targetWord = term.word.replaceAll("[\\p{P}+~$`^=|<>～｀＄＾＋＝｜	＜＞	￥　× ]", ""); // 把所有符号替换为空;这里慢慢积累不同情况的特殊字符
				matcher.reset(targetWord);
				matcher1.reset(targetWord);
				if(targetWord.length() <= 1 || matcher.matches() || matcher1.matches()) { // 不要纯英文的实体；不要纯数字的实体；长度还要大于1
					continue;
				}
				if (term.nature.toString().startsWith("n") ) { // n开头的都认为是实体
					entityWord.add(targetWord); // 名词放一组
				} else { // 其他词放一组
					if (term.nature.toString().startsWith("v")) { // v开头的都认为是动词，可以作为关系;同时要求长度
						otherWord.add(targetWord); // 动词放一组 // 其他词只要动词，动词作谓语的概率最大
					}
				}
			}
			// StringBuffer allEntity = new StringBuffer();
			// allEntity.append(entityWord.toString()).append("\n");
			// System.out.println(entityWord);
			// }

			// **构造实体对儿和相关词的记录**
			Object[] entityWordArray = entityWord.toArray();
			Object[] otherWordArray = otherWord.toArray();

			// 构造实体对-->（相关词，相关词词频），这样的列表
			for (int m = 0; m < entityWordArray.length; m++) { // 实体对中的第一个实体
				for (int k = 0; k < entityWordArray.length; k++) { // 实体对中的第2个实体
					Set<String> entityCouple = new HashSet<String>();
					entityCouple.add((String) entityWordArray[m]);
					entityCouple.add((String) entityWordArray[k]);
					// !! 注意，这里因为是同一个set循环两次，所以同一个词会被抵消
					if (entityCouple.size() == 1) { // 比如中国这个词，两次循环中都有的话，keyObject中会只有一个元素
						continue;
					}
					Map<String, Long> relationWordsMap = entityRelationMap.get(entityCouple); // 取出本实体对对应的，相关的其他词构成的map
					if (relationWordsMap == null) { // 可能是第一次添加
						relationWordsMap = new HashMap<String, Long>();
					}
					for (int n = 0; n < otherWordArray.length; n++) { // 对这句中的所有其他词
						Long oldCount = MapUtils.getLong(relationWordsMap, otherWordArray[n], 0L); // 取得这个词的次数
						String relationWord = (String) otherWordArray[n];
						relationWordsMap.put(relationWord, ++oldCount); // 次数加1再写回去
					}
					entityRelationMap.put(entityCouple, relationWordsMap); // 完成了一个实体对儿的统计，要写回
					// 每句话可能有重复的实体对，但是都会记录到同一个map记录中，只会添加后面的计数
				}
			}
		}

		// System.out.println("开始写所有实体文件\n");
		// writeFile1(allEntity.toString(),
		// "E:\\工作\\4辞海\\社科文献书籍图谱所有实体数据.txt");
		// System.out.println("所有实体文件写完了\n");

		// 上面已经循环完所有句子，现在根据实体对-->（相关词，相关词词频）这个映射构造输出文件
		Set<String> allRelationsSet = new HashSet<String>(); // 用来统计和展示到底有多少(不重复的！)关系词
		Object[] entityCouples = entityRelationMap.keySet().toArray();
		Map<String, String> outCome = new HashMap<String, String>();
		for (int k = 0; k < entityCouples.length; k++) {
			Set<String> entityCouple = (Set<String>) entityCouples[k];
			Map<String, Long> rawRelationWordsMap = entityRelationMap
					.get(entityCouple);
			Map<String, Long> relationWordsMap = WordUtil
					.sortMap(rawRelationWordsMap); // 对相关词的频度进行排序
			entityRelationMap.put(entityCouple, relationWordsMap); // 排序完再写回原始数据，为了下一段代码用，不是这个循环用到的
			// 根据关系词频统计拿频率最高的
			if (relationWordsMap.entrySet().size() > 0) {
				Entry<String, Long> properRelationWordEntry = relationWordsMap
						.entrySet().iterator().next(); // 因为相关词已经根据词频统计过，所以第一个entry就是词频最高的相关词
				outCome.put(entityCouple.toString(),
						properRelationWordEntry.getKey()); // 构造新的map，key是实体对，value是出现频次最高的相关词
				// System.out.println(properRelationWordEntry.getKey());
				allRelationsSet.add(properRelationWordEntry.getKey());
			}
		}

		// 这段是写原始文件的，暂时用不到
/*		Set<Entry<Set<String>, Map<String, Long>>> contEntry = entityRelationMap
				.entrySet();
		StringBuffer sb1 = new StringBuffer();
		for (Entry<Set<String>, Map<String, Long>> entry : contEntry) {
			sb1.append(entry.getKey()).append(", ").append(entry.getValue())
					.append("\n");
		}
		 System.out.println("开始写原始文件\n");
		 WordUtil.writeFile1(sb1.toString(),
		 "E:\\工作\\4辞海\\社科文献书籍图谱原始数据.txt");
		 System.out.println("原始文件写完了\n");*/
		
		
		System.out.println("开始写图谱文件\n");
		WordUtil.writeMapToFile(outCome, KGpath);
		System.out.println("图谱文件写完了\n");

		System.out.println("开始写关系文件\n");
		WordUtil.writeStringToFile(
				allRelationsSet.size() + allRelationsSet.toString(),
				relListPath);
		System.out.println("关系文件写完了\n");
	}
}
