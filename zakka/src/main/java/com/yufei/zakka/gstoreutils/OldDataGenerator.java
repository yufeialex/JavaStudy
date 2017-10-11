package com.yufei.zakka.gstoreutils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class OldDataGenerator {

    public static String getRandomString(int length, String base) { //length表示生成字符串的长度  
//        String base = "abcdefghijklmnopqrstuvwxyz0123456789";     
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        String nameString = "筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲"
                + "芬芳燕彩春菊勤珍贞莉兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛 艳瑞凡佳嘉琼桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾"
                + "颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥";
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        int i = 100;
        Random relationId = new Random();

        File node = new File("C://n3Data.txt");
        node.createNewFile();// 不存在则创建  
        BufferedWriter output = new BufferedWriter(new FileWriter(node));

        while (i-- > 0) {
            StringBuilder sbBuilder = new StringBuilder();
            String subject = "<http://www.founder.km.product/" + i + ">";
            sbBuilder.append(subject).append(" <test:dmID> ").append("<21>").append(".\n");
            sbBuilder.append(subject).append(" <test:klID> ").append("<19>").append(".\n");
            sbBuilder.append(subject).append(" <test:name> ").append("<" + getRandomString(4, base) + ">").append(".\n");
            sbBuilder.append(subject).append(" <test:compete> ")
                    .append("<http://www.founder.km.product/" + relationId.nextInt(100) + ">").append(".\n");
            sbBuilder.append(subject).append(" <test:compete> ")
                    .append("<http://www.founder.km.product/" + relationId.nextInt(100) + ">").append(".\n");
            sbBuilder.append(subject).append(" <test:compete> ")
                    .append("<http://www.founder.km.product/" + relationId.nextInt(100) + ">").append(".\n");
            sbBuilder.append(subject).append(" <test:patentHolder> ")
                    .append("<http://www.founder.km.zhuanjia/" + relationId.nextInt(100) + ">").append(".\n");
            sbBuilder.append(subject).append(" <test:patentHolder> ")
                    .append("<http://www.founder.km.zhuanjia/" + relationId.nextInt(100) + ">").append(".\n");
            sbBuilder.append(subject).append(" <test:patentHolder> ")
                    .append("<http://www.founder.km.zhuanjia/" + relationId.nextInt(100) + ">").append(".");
            System.out.println(sbBuilder.toString());
            output.write(sbBuilder.toString());
        }

//		i = 100;
//		while(i-- > 0) {
//			StringBuilder sbBuilder = new StringBuilder();
//			String subject = "<http://www.founder.km.zhuanjia/" + i +">";
//			sbBuilder.append(subject).append(" <test:dmID> ").append("<22>").append(".\n");
//			sbBuilder.append(subject).append(" <test:klID> ").append("<19>").append(".\n");
//			sbBuilder.append(subject).append(" <test:name> ").append("<" + getRandomString(3, nameString) + ">").append(".\n");
//			sbBuilder.append(subject).append(" <test:invent> ")
//			.append("<http://www.founder.km.product/" + relationId.nextInt(100) +">").append(".\n");
//			sbBuilder.append(subject).append(" <test:invent> ")
//			.append("<http://www.founder.km.product/" + relationId.nextInt(100) +">").append(".\n");
//			sbBuilder.append(subject).append(" <test:invent> ")
//			.append("<http://www.founder.km.product/" + relationId.nextInt(100) +">").append(".\n");
//			sbBuilder.append(subject).append(" <test:friend> ")
//			.append("<http://www.founder.km.zhuanjia/" + relationId.nextInt(100) +">").append(".\n");
//			sbBuilder.append(subject).append(" <test:friend> ")
//			.append("<http://www.founder.km.zhuanjia/" + relationId.nextInt(100) +">").append(".\n");
//			sbBuilder.append(subject).append(" <test:friend> ")
//			.append("<http://www.founder.km.zhuanjia/" + relationId.nextInt(100) +">").append(".");
//			System.out.println(sbBuilder.toString());
//			output.write(sbBuilder.toString());	
//		}

    }

}
