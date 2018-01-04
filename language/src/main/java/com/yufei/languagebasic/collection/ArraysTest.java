package com.yufei.languagebasic.collection;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by XinYufei on 2018/1/3.
 */
public class ArraysTest {
    public static void main(String[] args) {

    }

    void func1() {
        List<Integer> list = Arrays.asList(3, 4, 2, 1, 5, 7, 6);
        System.out.println(list);
    }

    void func2() {
        int a[] = new int[]{1, 9, 5, 4, 6, 4, 7, 1};
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
    }

    void func3() {
        String str[] = {"s2", "s4", "s1", "s3"};
        Arrays.sort(str);
        System.out.println(Arrays.toString(str));
    }

    void func4() {
        Person1 persons[] = new Person1[]{
                new Person1("zzh", 18), new Person1("jj", 17), new Person1("qq", 19)
        };
        Arrays.sort(persons);
        System.out.println(Arrays.toString(persons));
    }

    void func5() {
        Person2 persons2[] = new Person2[]{
                new Person2("zzh", 18), new Person2("jj", 17), new Person2("qq", 19)
        };
        Arrays.sort(persons2, new Comparator<Person2>() {

            @Override
            public int compare(Person2 o1, Person2 o2) {
                if (o1 == null || o2 == null)
                    return 0;
                return o1.getAge() - o2.getAge();
            }

        });
        System.out.println(Arrays.toString(persons2));
    }

    void func6() {
        String str[] = {"s2", "s4", "s1", "s3"};
        Arrays.sort(str);
        System.out.println(Arrays.toString(str));
        int ans = Arrays.binarySearch(str, "s1");
        System.out.println(ans);
    }

    void func7() {
        String str[] = {"s2", "s4", "s1", "s3"};
        String str2[] = Arrays.copyOf(str, str.length);
        System.out.println(Arrays.toString(str2));
    }

    void func8() {
        String str[] = {"s2", "s4", "s1", "s3"};
        String str2[] = Arrays.copyOfRange(str, 1, 3);
        System.out.println(Arrays.toString(str2));
    }

    void func9() {
        String str1[] = {"s2", "s4", "s1", "s3", null};
        String str2[] = Arrays.copyOf(str1, str1.length);
        System.out.println(Arrays.equals(str1, str2));
    }

    void func10() {
        int a1[] = new int[]{1, 2, 3};
        int a2[] = new int[]{1, 3, 3};
        int a3[] = new int[]{4, 3, 2, 1};
        int a4[] = new int[]{1, 2, 3};
        int a5[] = new int[]{1, 3, 3};
        int a6[] = new int[]{4, 3, 2, 1};
        int[] a[] = new int[][]{a1, a2, a3};
        int[] b[] = new int[][]{a4, a5, a6};

        System.out.println(Arrays.equals(a, b));
        System.out.println(Arrays.deepEquals(a, b));
    }

    void func11() {
        String str[] = {"s2", "s4", "s1", "s3", null};
        System.out.println(Arrays.toString(str));
        Arrays.fill(str, "s5");
        System.out.println(Arrays.toString(str));
    }

    void func12() {
        int a1[] = new int[]{1, 2, 3};
        int a2[] = new int[]{1, 3, 3};
        int a3[] = new int[]{4, 3, 2, 1};
        int[] a[] = new int[][]{a1, a2, a3};
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.deepToString(a));
    }

    void func13() {
        int a1[] = new int[]{1, 2, 3};
        int a2[] = new int[]{1, 3, 3};
        int a3[] = new int[]{4, 3, 2, 1};
        int[] a[] = new int[][]{a1, a2, a3};
        System.out.println(Arrays.hashCode(a));
        System.out.println(Arrays.deepHashCode(a));
    }

}
