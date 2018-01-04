package util;


import java.util.*;

/**
 * Created by XinYufei on 2017/12/18.
 */
public class WorkUtil {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(21, 75, 75, 75, 75, 75, 75, 24, 24, 24, 1029, 22, 332, 1032, 25, 837, 1061, 1062, 28, 29, 850, 1031, 73, 1033, 72, 1034, 1034, 1056, 1057, 1058, 744, 745, 760, 1059, 21, 1065, 1074, 1072);
        Set<Integer> integers1 = new TreeSet<>(integers);
//        System.out.println(integers1);
//        System.out.println(integers1.size());
        ArrayList<Integer> integers2 = new ArrayList<>(integers1);
        for(int i=0;i<29;i++) {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            System.out.println(uuid + "' ," + integers2.get(i));
        }
    }
}
