package com.yufei.zakka.partone;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;

public class Gurl {

    public static void main(String[] args) throws UnsupportedEncodingException {

        Object a = 0;
        int b = 1;
        int result = (int) a & 0x1;
        int result2 = b & 0x1;
        System.out.println("result:" + result);
        System.out.println("result2:" + result2);

	    /*final String CODEC_STR = "_KnowledgeManager_";

	    long timestamp = System.currentTimeMillis() / 1000;

	    String strss = CODEC_STR + "admin" + "_" + timestamp + "_";
	   
	    String codestr = DigestUtils.md5Hex(strss.getBytes("UTF-8"));
	    
	    System.out.println("userCode=admin&timestamp=" + timestamp + "&param=" + codestr);
//		a();
*/
    }

    public static void a() {
        String a = "d96be4ad-db4d-47a3-9529-1f9f3ef8ac80" + "yufei";
        String codestr = null;
        try {
            codestr = DigestUtils.md5Hex(a.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(codestr);
    }
}
