package com.petrichor.java.zakka.partone;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Groupss {
    public static void main(String[] args) {

//	    String regex = "(x)(y\\w*)(z)";
//
//        String input = "exy123z,xy456z";
//        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
//        Matcher m = p.matcher(input);
//
//        while (m.find()) {
//            System.out.println(m.group(2));
//        }

//		String aaString = ">直隶霸县（今河北霸州）人，字向方。";
//		String aaString = ">南宋大臣。字节夫，相州安阳（今属河南）人。韩琦";

//		String aaString = "即“<consultword>牛津学派</consultword>”";
//		String aaString = "a3333444";
//		String aaString = "许多系统同时采用并馈、串馈技术、串得到馈技术、多个串馈组件技术"; 
//		String aaString = "   <pagenums role=\"pdfpage\">52</pagenums>"; 
//		String aaString = " 本院不予采纳。原告在诉讼期间，撤回部分诉讼请求，是对其诉讼权利的处分，予以准许。本院作出.*号民事判决书，准予被告.*与原告.*离婚，该判决书现已发生法律效力.据此，依照《中华人民共和国婚姻法》第三条、最高人民法院《关于适用﹤中华人民共和国婚姻法﹥若干问题的解释（二）》第十条第（一）项之规定，判决如下 "; 
//		String aaString = " 1.7.1 数字T/R组件 1.7.1 数字T/R组件 52 26 数字阵列雷达是一种收、发均采用数字波束形成技术的全数字化阵列扫描雷达，它采取的是在数字域实现幅相加权（即数字波束形成）。数字阵列一般由天线、数字收/发组件（DTR）、时钟、数据传输系统、数字处理机组成。与常规的相控阵雷达相比，全 DBF 相控阵雷达的收发波束形成均采用了数字波束形成技术，如图1.18所示。 图1.18 全 DBF 相控阵雷达天线阵列 52 26 1995年，Adrian Garrod 提出了数字T/R组件的概念，并对基于DDS 的数字T/R组件进行了深入研究，研制成功了全 DBF相控阵雷达实验平台[13]。数字阵列雷达的核心技术是数字 T/R组件，它包含了整个发射机、接收机、激励器和本振信号发生器。 数字 T/R组件基于 DDS 技术完成信号产生和幅相控制，组件间通过统一时钟来实现同步。发射时，利用 DDS 技术完成发射波束形成所需要的幅度、相位加权及波形产生和上变频所必需的本振信号；接收时，利用DDS 技术产生接收信号和下变频所必需的本振信号，并采用数字I/Q 形成接收输出[14]。 数字 T/R组件的组成原理框图如图1.19所示。 图1.19 数字 T/R组件 52 26 数字 T/R组件包括 DBF 发射通道、DBF 接收通道、电源和控制等设备单元，组件的控制信息、控制时序、波形参数都由控制总线提供，接收到的数字数据送至信息处理系统，所有模块的同步都是通过公共时钟信号实现的。该结构收发状态是独立的，发支路由 DDS 产生的波形、经上变频形成发射信号通过环形器输出，接收支路由环行器输入经限幅低噪声放大、下变频、A/D变换、I/Q 分离形成数字信号输出。 在数字 T/R组件内部设计频率跨度从数字到射频，电路形式中既有小信号低噪声又包含了大功率电路，既有模拟电路又有高速数据采集与产生。采用传统电路形式必然会将体积无限制扩大，丧失了数字 T/R 灵活控制所带来的优势，限制了数字 T/R组件的应用范围。采用一体化射频到数字设计技术，射频电路与数字电路采用无缝式连接方式，没有了电缆，减小了尺寸及不确定因素的影响，电路实现中应大量应用微波单片集成电路及新材料、新工艺。 根据不同工作频率、带宽及数字接收处理能力，组件的接收前端可以采用固定本振变频、可变本振变频、不变频方式传输和处理雷达回波信号。具体实现为： （1）当 RF 回波信号频率大于 A/D 模拟输入频率（带宽），而其带宽又小于数字接收（包括 A/D）处理带宽时，需采用固定本振变频； （2）当 RF 回波信号带宽大于数字接收（包括 A/D）处理带宽时，需采用可变本振变频； （3）当 RF 回波信号频率小于 A/D 模拟输入频率（带宽），而其带宽又小于数字接收（包括 A/D）处理带宽时，采用不变频方式。 数字化组件的设计要根据频段、功能、成本等多种因素，有多种实现方案。数字 T/R组件的主要研究内容有： （1）数字 T/R组件的体系结构； （2）基于 DDS 技术的发射信号产生技术，包括波形产生技术和频率扩展技术； （3）基于 DDS 的幅相控制技术，包括幅相控制技术和频率扩展对幅相的影响； （4）DDS 寄生响应、相位截断误差、幅度量化误差等对波形产生的影响； （5）数模一体化设计理论； （6）数字 T/R组件的一致性和稳定性。 ";
//		String aaString = "啊啊而过。是打发，采用有源电子扫描阵（AESA）技术的雷达,有源电子扫描阵（AESA）技术利。用mainaa技术有源电子扫描阵（AESA）技术面实,但采用拉拉bb技术人员，我的主要技术即所谓零电压（ZVS）/零电流（ZCS）开关核心技术。所谓实验技术";
//		String aaString = "啊啊而过。是打发，采用有源电子扫描阵（AESA）技术的雷达,利。用mainaa技术面实,但采用拉拉bb技术人员，我的主要技术即所谓零电压（ZVS）/零电流（ZCS）开关核心技术。所谓实验技术";
//		String aaString = ">⑴ 古都邑、县名。";
//		String aaString = "<headword>货币学派</headword>";
//		String aaString = "<bddate>约前8世纪</bddate>";
//		String aaString = "<bddate>约1580―1666</bddate>";
//		String aaString = "<bddate>？―1271</bddate>";
//		String aaString = "1945年10月生于上海。祖籍河北省武强县。1962年毕业于天津南开中学，";
//		String aaString = "[；、，。 ]生于(.*)[；、，。 ]";
//		String aaString = "[；、，。 ](.*)出生";
//		String aaString = "[；、，。 ](.*)(年月日)生";
//		String aaString = "（(\\d)～\\d）";
//		String aaString = "（(.*)[—-　]）";
//		String aaString = "[；、，。 ]生于(.*)[；、，。 ]";

//		String REG = "^.*[>，。](.*?)人[，。].*$";  
//		String REG = ".*<bddate>(.*)―.*</bddate>.*";  
//		String REG = ".*<bddate>(.*)</bddate>.*";  
//		String REG = "^.*[，。]字(.*?)[，。].*$";  
//		String REG = "^.*<headword>.*[学派,主义]</headword>.*$";  
//		String REG = "^.*[> 、](旧|古)?(县名|市名|市名|府名|州名|都邑|地名)[。、].*$";  
//		String REG = "^.*[>, ]古地名。.*$";  
//		String REG = "^.*<consultword>(.*)</consultword>.*$";   （）\u4e00-\u9fa5/ 
//		String REG = "(?<![a-z])\\d{3}"; &&[^主要^新^关键^相关^主要^各项^核心]
//		String REG = "(?<=(采用|了|通过|实现|包括|和|而|应用|依赖|基于|如|以|与|所谓|的|基于|借鉴))([\\w（/）\u4e00-\u9fa5&&[^技]]+?)(?<!(主要|新|关键|相关|主要|各项|核心))技术(?!(专业|人员))"; 
//		String REG = "[；、，。 ]([\\w（/）\u4e00-\u9fa5&&[^(技如采用了通过实现包括和而应用依赖基于如以与所谓的借鉴)]]+?)(?<!(主要|新|关键|相关|主要|各项|核心))(组件|雷达)?技术(?!(专业|人员))"; 
//		String REG = "^.*<pagenums role=\"pdfpage\">(\\d+)</pagenums>.*$"; 
//		String REG = "。?[^。]*幅相控制技术.*?。"; 
//		String REG = "(?<=(采用|了|通过|实现|包括|和|而|应用|依赖|基于|如|以|与|所谓|的|是|基于|借鉴))([\\w（/）\u4e00-\u9fa5&&[^技]]+?)(?<!(主要|新|关键|相关|主要|各项|核心))(组件|雷达)?技术(?!(专业|人员))"; 
//		String REG = "[籍贯|祖籍](.*?)[；、，。 ]"; 
        String REG = ".*本院作出.*号民事判决书，准予被告.*与原告.*离婚，该判决书现已发生法律效力.*";

        Pattern p = Pattern.compile(REG);
        Matcher m = p.matcher("aa");
//		if(m.matches()) {
//            System.out.println("match");
//		}

//		List<String> matchRegexList = new ArrayList<String>();  
//		while(m.find()){  
//		    matchRegexList.add(m.group());  
//		}
        System.out.println(m.matches());
        while (m.matches()) {
//			while(m.find()) {
            System.out.println(m.group(0));
            System.out.println(m.group(1));
            System.out.println(m.group(2));
        }

//		String str="2012-08-12 2012-12 abcde 2012-08-23 ";  
//		String regex = "\\d{4}[ /-]{1}\\d{2}([ /-]{1}\\d{2})?"; //正则表达式  
//		Pattern pattern = Pattern.compile(regex);   
//		Matcher m1 = pattern.matcher(str);  
//		List<String> matchRegexList = new ArrayList<String>();  
//		while(m1.find()){  
//		    matchRegexList.add(m1.group());  
//		}  
//		System.out.println(m.groupCount()); 
//		System.out.println(matchRegexList); 

		
	/*	String aSt = "我爱北京天安门";
		String bString = "北京";
		System.out.println(aSt.contains(bString));*/
    }
} 

