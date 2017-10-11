package com.yufei.zakka.gstoreutils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Neo4jToN3Point {

    public static void main(String[] args) {
        // neo4j节点转为n3文件
        String inputPath = "E:\\工作\\迁移到gstore\\data\\neo4jnodes.json";
        String inputPath2 = "E:\\工作\\迁移到gstore\\data\\kpId.json";
        String outputPath = "E:\\工作\\迁移到gstore\\data\\gstorenodes.n3";

        // 因为我已经对于neo4j里面的数据进行了整理，所以不需要id验证了;
        // 方式就是通过这里打印的冗余节点的id列表，用一个neo4j的删除语句去删除
        // 所有知识元id
        StringBuffer sb1 = new StringBuffer();
        try {
            PrepareDataUtil.readToBuffer(sb1, inputPath2);
        } catch (IOException e2) {
        }
        JSONArray idArray = JSON.parseObject(sb1.toString()).getJSONArray("RECORDS");
        Set<Long> allRDBids = new HashSet<Long>();
        for (int i = 0; i < idArray.size(); i++) {
//			System.out.println(relArray.getJSONObject(i).getLongValue("ID"));
//			System.out.println(relArray.getJSONObject(i));
            allRDBids.add(idArray.getJSONObject(i).getLongValue("id"));
        }

        StringBuffer sb = new StringBuffer();
        try {
            PrepareDataUtil.readToBuffer(sb, inputPath);
        } catch (IOException e1) {
        }
        JSONArray neo4jArray = JSON.parseObject(sb.toString()).getJSONArray("data");
        sb.delete(0, sb.capacity());

//		System.out.println(neo4jArray.size());

        // <http://www.founder/100> <http://www.founder.20.attr:name> "17ec". 旧的schema
        // <http://www.founder/41> <http://www.founder.attr:pointStatus> """65552""". 新的schema少了领域信息，但是会单独加一条信息表示领域
        // 每个元素是一个node
        for (int i = 0; i < neo4jArray.size(); i++) {
            JSONObject neo4Record = neo4jArray.getJSONObject(i);
            // 拿到节点中我们关注的信息
            JSONObject node = neo4Record.getJSONArray("row").getJSONObject(0);

            // 单独拿出id信息
            Set<String> keys = node.keySet();
            long pId = node.getLongValue("pId");
            keys.remove("pId");
            if (allRDBids.contains(pId)) {
                keys.stream().forEach(
                        key -> {
                            StringBuffer sbBuffer = new StringBuffer();
                            sbBuffer.append("<").append(Constant.RDF_PREFIX)
                                    .append("/").append(pId).append("> <")
                                    .append(Constant.RDF_PREFIX).append(".")
                                    .append(Constant.ATTR_FLAG).append(":")
                                    .append(key).append("> ").append("\"\"\"")
                                    .append(node.get(key)).append("\"\"\".\n");
                            sb.append(sbBuffer);
                        });
            } else {
                System.out.print(pId + ",");
            }
        }
        try {
            PrepareDataUtil.writeFile(sb.toString(), outputPath);
        } catch (IOException e) {
        }
    }

}
