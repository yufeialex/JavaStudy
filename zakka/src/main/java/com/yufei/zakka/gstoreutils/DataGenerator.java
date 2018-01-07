package com.yufei.zakka.gstoreutils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DataGenerator {

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

    public static String getRandomHobby(String[] options) { //length表示生成字符串的长度  
        Random random = new Random();
        int number = random.nextInt(4);
        return options[number];
    }

    public static int getToKpIdExpert() {
        Random toKpId = new Random();
        return toKpId.nextInt(100) + 101;
    }

    public static int getToKpIdProduct() {
        Random toKpId = new Random();
        return toKpId.nextInt(100) + 1;
    }

    public static void main(String[] args) throws IOException {
        String nameString = "筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲"
                + "芬芳燕彩春菊勤珍贞莉兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛 艳瑞凡佳嘉琼桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾"
                + "颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥";
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random toKpId = new Random();

        File node = new File("C://n3Data.txt");
        node.createNewFile();// 不存在则创建  
        BufferedWriter output = new BufferedWriter(new FileWriter(node));

        String[] hobby = {"跳高", "绘画", "音乐", "电影"};
        String[] feature = {"高可用", "扩展性", "稳定性", "开放性"};


        //关系id 
        //        compete:50  product:22 vs product:22
        //        patentHolder:51  product:22 vs zhuanjia:11
        //        invent:52  zhuanjia:11 vs product:22
        //        friend:53  zhuanjia:11 vs zhuanjia:11

        //因为同领域可以有同名的关系，所以predict不能用关系名，要用关系id，
        //但是又有code，code有自己独有的特点，是否全系统每个表都要用code？ 暂时不用

        //自定义属性id 
        //        hobby:33
        //        feature:35

        // 领域id 
        //        testOnto:20

        // 模型id 
        //        product:22, id范围是1-100
        //        zhuanjia:11 id范围是101-200

        int i = 100;

//		while(i > 0) {
//			StringBuilder sbBuilder = new StringBuilder();
//			String subject = "<http://www.founder/" + i +">";
//			sbBuilder.append(subject).append(" <http://www.founder.20.attr:dmID> ").append("\"22\"").append(".\n");
//			sbBuilder.append(subject).append(" <http://www.founder.20.attr:klID> ").append("\"20\"").append(".\n");
//			sbBuilder.append(subject).append(" <http://www.founder.20.attr:name> ").append("\"" + getRandomString(4, base)).append("\".\n");
//			sbBuilder.append(subject).append(" <http://www.founder.20.attr:35> ").append("\"" + getRandomHobby(feature)).append("\".\n");
//			
//			sbBuilder.append(subject).append(" <http://www.founder.20.link:50> ")
//			.append("<http://www.founder/" + getToKpIdProduct() +">").append(".\n");
//			sbBuilder.append(subject).append(" <http://www.founder.20.link:50> ")
//			.append("<http://www.founder/" + getToKpIdProduct() +">").append(".\n");
//			sbBuilder.append(subject).append(" <http://www.founder.20.link:50> ")
//			.append("<http://www.founder/" + getToKpIdProduct() +">").append(".\n");
//			sbBuilder.append(subject).append(" <http://www.founder.20.link:51> ")
//			.append("<http://www.founder/" + getToKpIdExpert() +">").append(".\n");
//			sbBuilder.append(subject).append(" <http://www.founder.20.link:51> ")
//			.append("<http://www.founder/" + getToKpIdExpert() +">").append(".\n");
//			sbBuilder.append(subject).append(" <http://www.founder.20.link:51> ")
//			.append("<http://www.founder/" + getToKpIdExpert() +">").append(".");
//			System.out.println(sbBuilder.toString());
//			output.write(sbBuilder.toString());	
//			i--;
//		}
//		
        i = 200;
        while (i > 100) {
            StringBuilder sbBuilder = new StringBuilder();
            String subject = "<http://www.founder/" + i + ">";
            sbBuilder.append(subject).append(" <http://www.founder.20.attr:dmID> ").append("\"11\"").append(".\n");
            sbBuilder.append(subject).append(" <http://www.founder.20.attr:klID> ").append("\"20\"").append(".\n");
            sbBuilder.append(subject).append(" <http://www.founder.20.attr:name> ").append("\"" + getRandomString(3, nameString)).append("\".\n");
            sbBuilder.append(subject).append(" <http://www.founder.20.attr:33> ").append("\"" + getRandomHobby(hobby)).append("\".\n");

            sbBuilder.append(subject).append(" <http://www.founder.20.link:52> ")
                    .append("<http://www.founder/" + getToKpIdProduct() + ">").append(".\n");
            sbBuilder.append(subject).append(" <http://www.founder.20.link:52> ")
                    .append("<http://www.founder/" + getToKpIdProduct() + ">").append(".\n");
            sbBuilder.append(subject).append(" <http://www.founder.20.link:52> ")
                    .append("<http://www.founder/" + getToKpIdProduct() + ">").append(".\n");
            sbBuilder.append(subject).append(" <http://www.founder.20.link:53> ")
                    .append("<http://www.founder/" + getToKpIdExpert() + ">").append(".\n");
            sbBuilder.append(subject).append(" <http://www.founder.20.link:53> ")
                    .append("<http://www.founder/" + getToKpIdExpert() + ">").append(".\n");
            sbBuilder.append(subject).append(" <http://www.founder.20.link:53> ")
                    .append("<http://www.founder/" + getToKpIdExpert() + ">").append(".");
            System.out.println(sbBuilder.toString());
            output.write(sbBuilder.toString());
            i--;
        }

    }

}
