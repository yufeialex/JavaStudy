package com.yufei.zakka.partone;

public class KMPTest {
    // public static void main(String[] args) {
    // int[] a = {};
    // search("我爱北京天安门，你爱北京天安门吗", "天安门", a);
    // // System.out.println(a);
    // }
    // public static void search(String original, String find, int next[]) {
    // int j = 0;
    // for (int i = 0; i < original.length(); i++) {
    // while (j > 0 && original.charAt(i) != find.charAt(j))
    // j = next[j];
    // if (original.charAt(i) == find.charAt(j))
    // j++;
    // if (j == find.length()) {
    // System.out.println("find at position " + (i - j));
    // System.out.println(original.subSequence(i - j + 1, i + 1));
    // j = next[j];
    // }
    // }
    // }
    // public int[] getNext(String b)
    // {
    // int len=b.length();
    // int j=0;
    //
    // int next[]=new int[len+1];//next表示长度为i的字符串前缀和后缀的最长公共部分，从1开始
    // next[0]=next[1]=0;
    //
    // for(int i=1;i<len;i++)//i表示字符串的下标，从0开始
    // {//j在每次循环开始都表示next[i]的值，同时也表示需要比较的下一个位置
    // while(j>0&&b.charAt(i)!=b.charAt(j))j=next[j];
    // if(b.charAt(i)==b.charAt(j))j++;
    // next[i+1]=j;
    // }
    //
    // return next;
    // }

    // public static void main(String[] args) {
    // String s = "我爱北京天安门，你爱北京天安门吗"; // 主串
    // String t = "天安门"; // 模式串
    // char[] ss = s.toCharArray();
    // char[] tt = t.toCharArray();
    // System.out.println(KMP_Index(ss, tt)); // KMP匹配字符串
    // }
    //
    // /**
    // * 获得字符串的next函数值
    // *
    // * @param t
    // * 字符串
    // * @return next函数值
    // */
    // public static int[] next(char[] t) {
    // int[] next = new int[t.length];
    // next[0] = -1;
    // int i = 0;
    // int j = -1;
    // while (i < t.length - 1) {
    // if (j == -1 || t[i] == t[j]) {
    // i++;
    // j++;
    // if (t[i] != t[j]) {
    // next[i] = j;
    // } else {
    // next[i] = next[j];
    // }
    // } else {
    // j = next[j];
    // }
    // }
    // return next;
    // }
    //
    // /**
    // * KMP匹配字符串
    // *
    // * @param s
    // * 主串
    // * @param t
    // * 模式串
    // * @return 若匹配成功，返回下标，否则返回-1
    // */
    // public static int KMP_Index(char[] s, char[] t) {
    // int[] next = next(t);
    // int i = 0;
    // int j = 0;
    // while (i <= s.length - 1 && j <= t.length - 1) {
    // if (j == -1 || s[i] == t[j]) {
    // i++;
    // j++;
    // } else {
    // j = next[j];
    // System.out.println(j);
    // }
    // }
    // if (j < t.length) {
    // return -1;
    // } else
    // return i - t.length; // 返回模式串在主串中的头下标
    // }

    public static void main(String[] args) {
        KMPTest ktest = new KMPTest("BBC ABCDAB ABCDABCDABDE", "AB");

        ktest.debugNextArr();
        int theLoc = ktest.getIndexOfStr();

        System.out.println();
        System.out.println("匹配位置在：" + theLoc);

    }

    private int[] _nextArr = null;
    private String _originStr = null;
    private String _moduleStr = null;

    private int[] _resultArr = null;

    public KMPTest(String originStr, String moduleStr) {
        _originStr = originStr;
        _moduleStr = moduleStr;
        _nextArr = caculate_nextArr();
    }

    /**
     * 计算next 数组的值。
     */
    private int[] caculate_nextArr() {
        if (_moduleStr == null || _moduleStr.length() == 0) {
            return null;
        }
        int[] theNextArr = new int[_moduleStr.length()];
        for (int i = 0; i < _moduleStr.length(); i++) {
            if (i == 0) {
                theNextArr[i] = 0;
            } else if (i == 1) {
                if (_moduleStr.charAt(0) == _moduleStr.charAt(1)) {
                    theNextArr[i] = 1;
                } else {
                    theNextArr[i] = 0;
                }

            } else {
                int theLength2 = i;
                boolean hasEqual = false;

                for (int j = theLength2 - 1; j >= 0; j--) {
                    String prefix_str = _moduleStr.substring(0, j + 1);
                    String suffix_str = _moduleStr.substring(theLength2 - j,
                            theLength2 + 1);
                    if (prefix_str.endsWith(suffix_str)) {
                        hasEqual = true;
                        theNextArr[i] = prefix_str.length();

                        break;
                    } else {

                    }
                }
                if (hasEqual == false) {
                    theNextArr[i] = 0;
                }
            }
        }
        // ---

        return theNextArr;
    }

    public void debugNextArr() {
        if (_nextArr != null) {
            System.out.println("next array的值：");
            for (int tmp : _nextArr) {
                System.out.print(tmp + "   ");
            }
        }
    }

    public int getIndexOfStr() {

        if (_moduleStr == null || _moduleStr.length() <= 0) {
            return -1;
        }
        if (_originStr == null || _originStr.length() <= 0) {
            return -1;
        }
        if (_originStr.length() < _moduleStr.length()) {
            return -1;
        }
        int res = -1;
        int totalLength = _originStr.length();
        boolean flag_end = false;

        int origin_loc = 0;
        int module_loc = 0;
        while (flag_end == false) {

            char c_origin = _originStr.charAt(origin_loc);
            char c_module = _moduleStr.charAt(module_loc);
            boolean needtoGoOn = true;
            int childLoc = 1;

            if (c_origin == c_module) {
                if (module_loc == _moduleStr.length() - 1) {
                    res = origin_loc - module_loc;
                    break;
                } else {

                    origin_loc++;
                    module_loc++;
                }

            } else {
                if (module_loc == 0) {
                    origin_loc++;
                    module_loc = 0;
                    if (origin_loc >= totalLength) {
                        break;
                    }
                } else {
                    if (module_loc <= 0) {
                        module_loc++;
                        origin_loc++;
                    } else {
                        int m_callback = _nextArr[module_loc - 1];
                        module_loc = m_callback;
                    }
                }
                continue;

            }
            if (origin_loc >= totalLength) {
                break;
            }

        }
        return res;
    }
}
