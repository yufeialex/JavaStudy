package com.petrichor.languagebasic.java8;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ConsumeAndBiConsume {
    private class ThemewordRelation {
        Integer fromId;
        Integer toId;
        Integer relationCode;

        public Integer getFromId() {
            return fromId;
        }

        public void setFromId(Integer fromId) {
            this.fromId = fromId;
        }

        public Integer getToId() {
            return toId;
        }

        public void setToId(Integer toId) {
            this.toId = toId;
        }

        public Integer getRelationCode() {
            return relationCode;
        }

        public void setRelationCode(Integer relationCode) {
            this.relationCode = relationCode;
        }
    }

    public static void main(String[] args) {

        // 这其实就是一个单个参数的，没有返回值的函数。函数式接口其实就是定义了最常用的函数模板
        Consumer<Integer> mc = x -> {
            int a = x + 2;
            System.out.println(a);// 12
            System.out.println(a + "_");// 12_
        };
        // 这里是函数调用
        mc.accept(10);

        // 如果是一次性的方法处理，可以这样写。应该是在一个函数内部需要使用多次，但是又不值得拿出去做一个函数。就可以精简代码了。
        // 但其实感觉和写个函数差不多啊
        // 这里的函数体只有一行，用了forEach，刚好forEach里面也是传递Consumer对象，所以看着有点乱
        BiConsumer<List<ThemewordRelation>, List<Map<String, Object>>> fillLinksFunc = (nodes, scopeLinks) -> nodes.forEach(node -> {
            Map<String, Object> map = new HashMap<>();
            String sourceName = node.getToId() == null ? "" : "hh";
            map.put("source", sourceName);

            Integer relName = node.getRelationCode();
            if (relName == null) {
                relName = node.getFromId(); // 自定义属性
            }
            map.put("name", relName);
            map.put("weight", 10);
            scopeLinks.add(map);
        });

        fillLinksFunc.accept(null, null);
    }
}
