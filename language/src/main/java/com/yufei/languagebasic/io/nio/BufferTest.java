package com.yufei.languagebasic.io.nio;

import java.nio.IntBuffer;

public class BufferTest {

    public static void main(String[] args) {
        IntBuffer iBuffer = IntBuffer.allocate(10);
//		System.out.println(iBuffer);

        int[] a = new int[]{3, 2, 1};
        // wrap是静态方法，最好通过类来调用
//		iBuffer = iBuffer.wrap(a);
        iBuffer = IntBuffer.wrap(a);
//		System.out.println(iBuffer);

        int[] b = new int[]{3, 2, 1, 0};
        iBuffer = IntBuffer.wrap(b);
//		System.out.println(iBuffer);

        iBuffer.put(1, 8); //这个不改变pos
        iBuffer.put(10); // 这个会改变pos
        iBuffer.clear();

//		System.out.println(iBuffer);
        System.out.println("遍历：");

//		for(int i = 0; i < iBuffer.limit(); i++) {
//			System.out.println(iBuffer.get());
//		}

        System.out.println(iBuffer.get());
        System.out.println(iBuffer.get());

//		System.out.println(iBuffer);

        IntBuffer iBuffer2 = iBuffer.duplicate();
        iBuffer2.flip(); // 对2操作和对1是一样的，limit = pos, pos=0
        iBuffer2.clear(); // 这个是 pos=0
        System.out.println(iBuffer2);

    }

}
