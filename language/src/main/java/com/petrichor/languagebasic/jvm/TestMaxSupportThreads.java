package com.petrichor.languagebasic.jvm;

/**
 * Created by XinYufei on 2018/1/11.
 */
public class TestMaxSupportThreads {
    private static final class TestThread extends Thread {
        private final int number;

        public TestThread(int number) {
            this.number = number;
        }

        TestThread(int number, int stackSize) {
            super(null, null, "Thread-Hi-" + number, stackSize);
            this.number = number;
        }

        @Override
        public void run() {
            if (number % 100 == 0) {
                System.out.println("Thread " + number + " started.");
            }
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        int i = 0;
        try {
            for (i = 0; i < 200; i++) {
                Thread t = new TestThread(i, 2 << 19);//512K
                t.start();
            }
        } catch (Throwable e) {
            System.out.println("Error occured when creating thread " + i);
            e.printStackTrace();
        }
    }
}
