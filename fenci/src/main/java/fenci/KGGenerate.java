package fenci;

import java.io.IOException;

public class KGGenerate {

	public static void main(String[] args) {
		String pathString = "E:\\工作\\文联\\all.txt";
		String KGpath = "E:\\工作\\文联\\文联图谱.txt";
		String relListPath = "E:\\工作\\文联\\文联关系列表.txt";
		String frequencyOutpuPath = "E:\\工作\\文联\\文联图谱词频统计.txt";
		String newWordOutputPath = "E:\\工作\\文联\\文联图谱新词发现.txt";
		
//		WordFrequency wFrequency = new WordFrequency();
//		EntityAndRelation eRelation = new EntityAndRelation();
//		FindNewWord fNewWord = new FindNewWord();
		
		try {
//			EntityAndRelation.getKGAndRelLists(pathString, KGpath, relListPath);
//			FindNewWord.getNewWord(pathString, newWordOutputPath);
			WordFrequency.getWordFrequency(pathString, frequencyOutpuPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
