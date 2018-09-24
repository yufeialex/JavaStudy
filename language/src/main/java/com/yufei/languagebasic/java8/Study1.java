//package com.yufei.languagebasic.java8;
//
//
//import java.util.*;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
//public class Study1 {
//    @Value("${com.wise.url}")
//    private void setWiseUrl(String wiseUrl){
//        // 应该用类来访问静态变量，不要用this.wiseUrl
//        AnalysisHelper.wiseUrl = wiseUrl;
//    }
//
//void s3() {
//
//}
//
//
//
//    void s2(){
//        // 1
//        ArrayList<Integer> libIds = new ArrayList<>();
//        StringBuilder sb = new StringBuilder();
//        for(Integer i : libIds) {
//            if(null != i && i > 0)
//                sb.append(i + ",");
//        }
//
//        // 改进Java8
//        libIds.stream().filter(i -> null != i && i > 0).forEach(i -> sb.append(i).append(","));
//
//        // 2
//        HashMap<String, Object> n = new HashMap<>();
//        n.put("a", "b");
//        ArrayList<HashMap<String, Object>> nodes1 = new ArrayList<>();
//        nodes1.add(n);
//        Set<Long> allCodes = nodes1.stream().map(node -> (Long) node.get("code")).collect(Collectors.toSet());
//    }
//
//    void s1(){
//        BiConsumer<List<ThemewordRelation>, List<Map<String, Object>>> fillLinksFunc = (nodes, scopeLinks) -> nodes.forEach(node -> {
//            Map<String, Object> map = Maps.newHashMap();
//            String sourceName = themewordService.get(node.getToId()) == null ? "" : themewordService.get(node.getFromId()).getName();
//            map.put("source", sourceName);
//
//            String targetName = themewordService.get(node.getToId()) == null ? "" : themewordService.get(node.getToId()).getName();
//            map.put("target", targetName);
//
//            String relName = defaultRelations.get(node.getRelationCode());
//            if(relName == null) {
//                relName = domainRelationService.getByDomainIdAndCode(domainID, node.getRelationCode()).getName(); // 自定义属性
//            }
//            map.put("name", relName);
//            map.put("weight", random.nextInt(10));
//            scopeLinks.add(map);
//        });
//
//        HashMap<String, Object> colValMap = new HashMap<>();
//        // 把map中的value都放到一个list中
//        List<Object> values = colValMap.keySet().stream().map(colValMap::get).collect(Collectors.toList());
//        List<Object> values1 = new ArrayList<>(colValMap.values());
//
//    }
//}
