package com.yufei.zakka.sheke;

import org.apache.commons.collections.MapUtils;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Process {

    public static void main(String[] args) throws IOException {

		/*
         * String s = "你好/d我再北京/sn看看"; s = s.replaceAll("/\\w\\w?", "");
		 * System.out.println(s);
		 */
        String pathString = "E:\\工作\\4辞海\\社科文献书籍分词后的结果.txt";
//		String pathString = "E:\\工作\\4辞海\\社科文献书籍分词后的结果test.txt";
        StringBuffer sb = new StringBuffer();
        readToBuffer(sb, pathString);
        String a = sb.toString();
        String[] sentences = a.split("。| ？ | ！"); // 中文的句子分隔符

        // key应该是同一个句子中出现的实体对，方便起见，把实体对放到set对象中，
        //key存储对象的hashcode
        Map<Set<String>, Map<String, Long>> hashWord = new HashMap<Set<String>, Map<String, Long>>();
        StringBuffer allEntity = new StringBuffer();

        for (int i = 0; i < sentences.length; i++) { // 循环所有句子
            String s = sentences[i];
            String[] words = s.split("  ");
            Set<String> entityWord = new HashSet<String>();
            Set<String> otherWord = new HashSet<String>();
            // 先区分实体和谓词
            for (int j = 0; j < words.length; j++) { // 循环一句中的所有单词
                if (words[j].contains("/n")) {
                    String preString = words[j].replaceAll("/\\w\\w?", ""); // 去掉词性标示
                    if (preString.trim().length() > 1) { // 去除单字词,去掉可能有空格的情况
                        entityWord.add(preString.trim()); // 名词放一组
                    }
                } else { // 其他词放一组
                    if (words[j].contains("/v")) {
                        String preString = words[j].replaceAll("/\\w\\w?", ""); // 去掉词性标示
                        if (preString.trim().length() > 1) { // 去除单字词,去掉可能有空格的情况
                            otherWord.add(preString.trim()); // 其他词只要动词，动词作谓语的概率最大
                        }
                    }
                }
            }
            allEntity.append(entityWord.toString()).append("\n");
//			System.out.println(entityWord);
//		}

            // 构造实体对儿和相关词的记录
            Object[] entityWordArray = (Object[]) entityWord.toArray();
            Object[] otherWordArray = (Object[]) otherWord.toArray();
//			for(Object object : entityWordArray) {
//				System.out.println((String)object);
//			}
            for (int m = 0; m < entityWordArray.length; m++) { // 实体对中的第一个实体
                for (int k = 0; k < entityWordArray.length; k++) { // 实体对中的第2个实体
//					KeyObject keyObject = new KeyObject((String)entityWordArray[m], (String)entityWordArray[k]);
                    Set<String> keyObject = new HashSet<String>();
                    keyObject.add((String) entityWordArray[m]);
                    keyObject.add((String) entityWordArray[k]);
                    // !! 注意，这里因为是同一个set循环两次，所以同一个词会被抵消
//					System.out.println(keyObject);
                    if (keyObject.size() == 1) { // 比如中国这个词，两次循环中都有的话，keyObject中会只有一个元素
                        continue;
                    }
                    Map<String, Long> relationWords = hashWord.get(keyObject); // 取出本实体对对应的，相关的其他词构成的map
                    if (relationWords == null) { // 可能是第一次添加
                        relationWords = new HashMap<String, Long>();
                    }
                    for (int n = 0; n < otherWordArray.length; n++) { // 对这句中的所有其他词
                        Long oldCount = MapUtils.getLong(relationWords, otherWordArray[n], 0L); // 取得这个词的次数
                        String aaaString = (String) otherWordArray[n];
                        relationWords.put(aaaString, ++oldCount); // 次数加1再写回去
                    }
                    hashWord.put(keyObject, relationWords); // 完成了一个实体对儿的统计，要写回
                }
            }
        }
//		System.out.println("开始写所有实体文件\n");
//		writeFile1(allEntity.toString(), "E:\\工作\\4辞海\\社科文献书籍图谱所有实体数据.txt");
//		System.out.println("所有实体文件写完了\n");

        // 循环完所有句子
        Object[] keys = (Object[]) hashWord.keySet().toArray();
        Map<String, String> outCome = new HashMap<String, String>();
        for (int k = 0; k < keys.length; k++) {
            Map<String, Long> aa = hashWord.get(keys[k]);
            Map<String, Long> bb = sortMap(aa); // 对相关词的频度进行排序
            hashWord.put((Set<String>) keys[k], bb); // 排序完再写回原始数据
            if (bb.entrySet().size() > 0) {
                Entry<String, Long> ccEntry = bb.entrySet().iterator().next();
                outCome.put(keys[k].toString(), ccEntry.getKey()); // 构造新的map，key是实体对，value是出现频次最高的相关词
            }
        }

        Set<Entry<Set<String>, Map<String, Long>>> contEntry = hashWord.entrySet();
        StringBuffer sb1 = new StringBuffer();
        for (Entry<Set<String>, Map<String, Long>> entry : contEntry) {
            sb1.append(entry.getKey()).append(", ").append(entry.getValue()).append("\n");
        }
        System.out.println("开始写原始文件\n");
        writeFile1(sb1.toString(), "E:\\工作\\4辞海\\社科文献书籍图谱原始数据.txt");
        System.out.println("原始文件写完了\n");


        System.out.println("开始写文件\n");
        writeFile(outCome, "E:\\工作\\4辞海\\社科文献书籍图谱.txt");
        System.out.println("文件写完了\n");
//		System.out.println(hashWord);

    }

    public static Map sortMap(Map oldMap) {
        ArrayList<Map.Entry<String, Long>> list = new ArrayList<Map.Entry<String, Long>>(oldMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Long>>() {

            @Override
            public int compare(Entry<java.lang.String, Long> arg0,
                               Entry<java.lang.String, Long> arg1) {
                return (int) (arg1.getValue() - arg0.getValue());
            }
        });
        Map newMap = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {
            newMap.put(list.get(i).getKey(), list.get(i).getValue());
        }
        return newMap;
    }


    public static void readToBuffer(StringBuffer buffer, String filePath)
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


    public static void writeFile(Map<String, String> content, String filePath)
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
        for (Entry<String, String> entry : contEntry) {
            sb.append(entry.getKey()).append(", ").append(entry.getValue()).append("\n");
        }
        writer.write(sb.toString());
        writer.flush();
        writer.close();
        // is.close();
    }

    public static void writeFile1(String content, String filePath)
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
	
	/*
	 * String preString = words[j].replaceAll("/\\w\\w?", "");
	 * // 去掉词性标示 Pattern p =
	 * Pattern.compile("[\\p{P}+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]"); Matcher
	 * m = p.matcher(preString);
	 * 
	 * // if(preString.contains("[\\p{P}+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]"))
	 * if (m.matches()) { // 去掉标点符号 continue; } else { if
	 * (preString.length() > 1) { // 去除单字词 Pattern p1 =
	 * Pattern.compile("\\d+年|\\d+月|\\d+日"); Matcher m1 =
	 * p1.matcher(preString); if (!m1.matches())
	 * otherWord.add(preString); } }
	 */
}
