package util;

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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class WordUtil {
	public static void readFileToBuffer(StringBuffer buffer, String filePath)
			throws IOException {
		String line; // 用来保存每行读取的内容
		// 用流和流的读写
		// InputStream is = new FileInputStream(filePath);
		// BufferedReader reader = new BufferedReader(new
		// InputStreamReader(is));

		// 用文件和文件的读写
		File is = new File(filePath);
		BufferedReader reader = new BufferedReader(new FileReader(is));

		line = reader.readLine(); // 读取第一行
		while (line != null) { // 如果 line 为空说明读完了
			buffer.append(line); // 将读到的内容添加到 buffer 中
			// buffer.append("\n"); // 添加换行符
			line = reader.readLine(); // 读取下一行
		}
		reader.close();
		// is.close();
	}

	public static void writeStringToFile(String content, String filePath)
			throws IOException {
		// 用OutputStream和OutputStreamWriter
		// 用流和流的读写
		OutputStream is = new FileOutputStream(filePath);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(is));

		// 用File和BufferedWriter
		// 用文件和文件的读写
//		File is = new File(filePath);
//		BufferedWriter writer = new BufferedWriter(new FileWriter(is));
		
		writer.write(content);
		writer.flush();
		writer.close();
		// is.close();
	}
	
	public static void writeMapToFile(Map<String, String> content, String filePath)
			throws IOException {
		// 用OutputStream和OutputStreamWriter
		// 用流和流的读写
		OutputStream is = new FileOutputStream(filePath);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(is));

		// 用File和BufferedWriter
		// 用文件和文件的读写
//		File is = new File(filePath);
//		BufferedWriter writer = new BufferedWriter(new FileWriter(is));
		
		Set<Entry<String, String>> contEntry = content.entrySet();
		StringBuffer sb = new StringBuffer();
		for(Entry<String, String> entry : contEntry) {
			sb.append(entry.getKey()).append(", ").append(entry.getValue()).append("\n");
		}
		writer.write(sb.toString());
		writer.flush();
		writer.close();
		// is.close();
	}
	
	public static Map sortMap(Map oldMap) {  // 降序排列
        ArrayList<Map.Entry<String, Long>> list = new ArrayList<Map.Entry<String, Long>>(oldMap.entrySet());  
        Collections.sort(list, new Comparator<Map.Entry<String, Long>>() {  
		public int compare(Entry<String, Long> o1, Entry<String, Long> o2) {
			return (int) (o2.getValue() - o1.getValue());
		}
        });  
        Map newMap = new LinkedHashMap();  
        for (int i = 0; i < list.size(); i++) {  
            newMap.put(list.get(i).getKey(), list.get(i).getValue());  
        }  
        return newMap;  
    }  

}
