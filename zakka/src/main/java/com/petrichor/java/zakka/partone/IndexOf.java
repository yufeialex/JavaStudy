package com.petrichor.java.zakka.partone;

public class IndexOf {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
//		String a = "我爱北京天安门，你爱北京天安门吗";
//		String b = "北京";
//		System.out.println(a.indexOf(b));


//		String content = "无 标题 本报 讯 （ 尚 建 ） 动 静脉 ！大出血 怎么办 ？ 伤者 很 可能 因 失血 面临 生命 危险 。 但 在 日前 结束 的 第 九 届 中国 国际 高新技术 成果 交易会 上 ， 一 种 名 为 “ 血 盾 ” 的 速效 止 血粉 将 可以 解决 这 一 问题 。 据 介绍 ， 当 人体 失血 超过 ８００ 毫升 时 就 会 危及 生命 ， 而 一旦 受到 意外 伤害 导致 动脉 出血 ， 就 可能 在 １０分 钟 内出血 超过 ８００ 毫升 ， 因此 在 急救 医学 上 对于 动脉 出血 抢救 有 “ 白金 １０分 钟 ” 的 说法 。 但 在 实际 应用 特别 是 在 战争 或 交通 事故 中 ， 因 缺乏 有效 的 止血 药物 ， 很 多 伤员 在 送 救 途中 错过 治疗 最佳 阶段 ， 丧失 生命 或 造成 伤残 。 1999年 ， 郑 永 刚 将 杉杉 总部 搬 到 上海 ， 除了 剥离 生产 环节 ， 销售 模式 也 大变 身 ， 尝试 “ 虚拟 经营 ” ， 杉杉 只 负责 品牌 的 核心 运作 和 推广 ， 结果 ， 2000年 杉杉 西装 丢掉 了 市场占有率 第一 的 宝座 ， 雅戈尔 取而代之 ， 这 也 让 李 如 成 至今 对 虚拟 经营 不 感冒 ， 一直 持续 开 出 新店 。";
//		String[] sentences = content.split("。| ？ | ！");
//		System.out.println("aaa");		


        String content = "北京上海 证 券 交易所 统计 显示 ， 招商 证券 股份 有限 公司 北京 金融 大街 证券 营业部 昨日 甩卖 招商银行 股份 达到 3041万 股 ， 成交 价格 为 每 股 16.65 元 ， 总 成交 金额 达到 5. 06亿 元 人民币 。";
        String sentences = content.replaceAll("上 ?海 ?证 ?券 ?交 ?易 ?所", "上海证券交易所");
        String sentences2 = content.replaceAll(" ", "");
        System.out.println(sentences);
        System.out.println(sentences2);
    }

}
