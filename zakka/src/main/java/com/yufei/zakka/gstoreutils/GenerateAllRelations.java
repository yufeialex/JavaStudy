package com.yufei.zakka.gstoreutils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GenerateAllRelations {

    public static void main(String[] args) {
        // 关系数据库的所有关系转为n3文件
        String inputPath = "E:\\工作\\迁移到gstore\\data\\relation.json";
        String outputPath = "E:\\工作\\迁移到gstore\\data\\gstore关系.n3";
        StringBuffer sb = new StringBuffer();
        try {
            PrepareDataUtil.readToBuffer(sb, inputPath);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        JSONArray relArray = JSON.parseObject(sb.toString()).getJSONArray("RECORDS");
        sb.delete(0, sb.capacity());

        System.out.println(relArray.size());

//		<http://www.founder/100> <http://www.founder.20.link:50> <http://www.founder/17>.
//		<http://www.founder/100> <http://www.founder.link:50> <http://www.founder/17>. 新的schema少了领域信息
        for (int i = 0; i < relArray.size(); i++) {
            JSONObject relJson = relArray.getJSONObject(i);
            StringBuffer sbBuffer = new StringBuffer();
            StringBuffer sbBuffer1 = new StringBuffer();
            sbBuffer.append("<").append(Constant.RDF_PREFIX).append("/")
                    .append(relJson.get("FROM_ID")).append("> <").append(Constant.RDF_PREFIX)
                    .append(".").append(Constant.LINK_FLAG)
                    .append(":").append(relJson.get("ID")).append("> <").append(Constant.RDF_PREFIX)
                    .append("/").append(relJson.get("TO_ID")).append(">.\n");
            sb.append(sbBuffer);
        }
        try {
            PrepareDataUtil.writeFile(sb.toString(), outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void a() {
        // neo4j节点转为n3文件
        String inputPath = "E:\\工作\\计算所合作\\gstore相关\\系统原来的数据\\neo4j用于抽取知识元\\neo4j兽医.json";
        String inputPath2 = "E:\\工作\\计算所合作\\gstore相关\\系统原来的数据\\neo4j用于抽取知识元\\兽医所有知识元id.json";
        String outputPath = "E:\\工作\\计算所合作\\gstore相关\\系统原来的数据\\neo4j用于抽取知识元\\兽医属性.n3";

        // 所有知识元id
        StringBuffer sb1 = new StringBuffer();
        try {
            PrepareDataUtil.readToBuffer(sb1, inputPath2);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        JSONArray relArray = JSON.parseObject(sb1.toString()).getJSONArray("RECORDS");
        Set<Long> idsLongs = new HashSet<Long>();
        for (int i = 0; i < relArray.size(); i++) {
            idsLongs.add(relArray.getJSONObject(i).getLongValue("ID"));
        }


        StringBuffer sb = new StringBuffer();
        try {
            PrepareDataUtil.readToBuffer(sb, inputPath);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        List<String> n3Triples = new ArrayList<String>();
        JSONArray neo4jArray = JSON.parseObject(sb.toString()).getJSONArray("data");
        sb.delete(0, sb.capacity());

        System.out.println(neo4jArray.size());

//				<http://www.founder/100> <http://www.founder.20.attr:name> "17ec".
        // 每个元素是一个node
        for (int i = 0; i < neo4jArray.size(); i++) {
            JSONObject neo4Record = neo4jArray.getJSONObject(i);
            JSONObject node = neo4Record.getJSONArray("row").getJSONObject(0);

            Set<String> keys = node.keySet();
            int klId = node.getIntValue("klID");
            long pId = node.getLongValue("pId");
            keys.remove("pId");
            if (idsLongs.contains(pId)) {
                keys.stream().forEach(key -> {
                    StringBuffer sbBuffer = new StringBuffer();
                    sbBuffer.append("<").append(Constant.RDF_PREFIX).append("/")
                            .append(pId).append("> <").append(Constant.RDF_PREFIX)
                            .append(".").append(klId).append(".").append(Constant.ATTR_FLAG)
                            .append(":").append(key).append("> ").append("\"\"\"").append(node.get(key)).append("\"\"\".\n");
                    sb.append(sbBuffer);
                });
            }
        }
        try {
            PrepareDataUtil.writeFile(sb.toString(), outputPath);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
