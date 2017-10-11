package com.yufei.zakka.tools;

public class NumberConvertor {

    public static void main(String[] args) {
        test(23);
    }

    public static String test(int number) {
        String[] str = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String ss[] = new String[]{"", "十", "百", "千", "万", "十", "百", "千", "亿"};
        String numberString = String.valueOf(number);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberString.length(); i++) {
            String index = String.valueOf(numberString.charAt(i));
            sb = sb.append(str[Integer.parseInt(index)]);
        }
        String preString = sb.toString();
        int i = 0;
        for (int j = preString.length(); j > 0; j--) {
            sb = sb.insert(j, ss[i++]);
        }
        return sb.toString();
    }
}
