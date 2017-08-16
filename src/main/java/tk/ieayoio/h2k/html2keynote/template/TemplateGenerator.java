package tk.ieayoio.h2k.html2keynote.template;

import tk.ieayoio.h2k.html2keynote.convert.IndexApxlGenerator;
import tk.ieayoio.h2k.html2keynote.data.DataBasic;
import tk.ieayoio.h2k.html2keynote.data.VarPackage;
import tk.ieayoio.h2k.utils.DSResourceLoader;
import tk.ieayoio.h2k.utils.FileUtils;

import java.io.*;

/**
 * Created by ieayoio on 2017/7/14.
 */

/**
 * 生成keynote的index.apxl文件模板
 * 控制台打印出DataBasic中dataMap的初始化内容
 * <p>
 * 注意：目前每行只处理了一处不同 2017-07-14
 */
public class TemplateGenerator {

    private static String getExpression(String dataMapKey) {

        String format = String.format("${(%s.%s.%s+(%s?index)*%s.%s.%s)?c}",
                IndexApxlGenerator.rootKey, dataMapKey, VarPackage.BASE, foreachVar, IndexApxlGenerator.rootKey, dataMapKey, VarPackage.STEP);
        return format;

    }

    public static final String foreachVar = "x";

    public static void writeHead(BufferedWriter bufferedWriter) throws IOException {


        // 写入头部代码
        String headpath = DSResourceLoader.getDSResourceLoader("example/head.txt").getPath();
//        String headpath = TemplateGenerator.class.getClassLoader().getResource("example/head.txt").getPath();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(headpath));
        String str = null;
        while ((str = bufferedReader.readLine()) != null) {
            bufferedWriter.write(str);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
        bufferedReader.close();


        // 写入循环起始
        String foreachHead = "<#list 0.." + IndexApxlGenerator.pageCount + " as " + foreachVar + " >";
        bufferedWriter.write(foreachHead);
        bufferedWriter.newLine();
        bufferedWriter.flush();

    }

    public static void writeTail(BufferedWriter bufferedWriter) throws IOException {

        // 写入循环终止
        String foreachTail = "</#list>";
        bufferedWriter.write(foreachTail);
        bufferedWriter.newLine();
        bufferedWriter.flush();

        // 写入尾代码
        String tailpath = DSResourceLoader.getDSResourceLoader("example/tail.txt").getPath();
//        String tailpath = TemplateGenerator.class.getClassLoader().getResource("example/tail.txt").getPath();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(tailpath));
        String str = null;
        while ((str = bufferedReader.readLine()) != null) {
            bufferedWriter.write(str);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
        bufferedReader.close();

    }


    public static void process() throws IOException {

        // 清空dataMap，防止每次生成的key不同
        DataBasic.dataMap.clear();

        // 创建 index_apxl.ftl
        String ftlPath = new File("").getAbsolutePath() + "/src/main/resources/ftl";
        String index_apxlPath = ftlPath + "/index_apxl.ftl";
        FileUtils.createIfNotExists(index_apxlPath);


        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(index_apxlPath));


        writeHead(bufferedWriter);

        String bgslide0_path = DSResourceLoader.getDSResourceLoader("example/BGSlide-1000.txt").getPath();
        String bgslide1_path = DSResourceLoader.getDSResourceLoader("example/BGSlide-1001.txt").getPath();
//        String bgslide0_path = TemplateGenerator.class.getClassLoader().getResource("example/BGSlide-1000.txt").getPath();
//        String bgslide1_path = TemplateGenerator.class.getClassLoader().getResource("example/BGSlide-1001.txt").getPath();

        FileReader reader0 = new FileReader(bgslide0_path);
        BufferedReader br0 = new BufferedReader(reader0);

        FileReader reader1 = new FileReader(bgslide1_path);
        BufferedReader br1 = new BufferedReader(reader1);

        String str0 = null;
        String str1 = null;
        while ((str0 = br0.readLine()) != null) {
            str1 = br1.readLine();
            int index = -1;
            for (int i = 0; i < str0.length(); i++) {
                if (str0.charAt(i) != str1.charAt(i)) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                // TODO:
//                System.out.println(str0);
                bufferedWriter.write(str0);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                continue;
            }

            // 获取参数与变量范围
            Range paramRange0 = LineExtractor.findParam(str0, index);
            Range nameRange0 = LineExtractor.findName(str0, index);
            Range paramRange1 = LineExtractor.findParam(str1, index);

            // 获取参数与变量值
            String param0 = LineExtractor.fetch(str0, paramRange0);
            String name0 = LineExtractor.fetch(str0, nameRange0);

            // 将“-”符号替换为"_"防止freemarker误认为减号
            name0 = name0.replace("-", "_");
            String param1 = LineExtractor.fetch(str1, paramRange1);


            // 更新数据映射
            String dataMapKey = DataBasic.getDataMapKey(name0);
            int baseParam = new Integer(param0);
            int nextParam = new Integer(param1);
            DataBasic.put(dataMapKey, new VarPackage(baseParam, nextParam - baseParam));


            // 构造模板内容
            // TODO: 替换内容
            String replaceString = getExpression(dataMapKey);
            String newLine = LineExtractor.replace(str0, paramRange0, replaceString);

//            System.out.println(newLine);
            bufferedWriter.write(newLine);
            bufferedWriter.newLine();
            bufferedWriter.flush();


        }

        System.out.println("// --------------DataBasic static method----------------");

        for (String key : DataBasic.dataMap.keySet()) {
            VarPackage varPackage = DataBasic.dataMap.get(key);

            String format = String.format("put(\"%s\", VarPackage.var(%s, %s));", key, varPackage.getBase(), varPackage.getStep());
            System.out.println(format);

//            System.out.println("key:" + key + " base:" + varPackage.getBase() + " step:" + varPackage.getStep());
        }

        writeTail(bufferedWriter);


        br0.close();
        br1.close();
        bufferedWriter.close();

        System.out.println("template done , path:" + index_apxlPath);


    }

    public static void main(String[] args) throws IOException {

        // 生成keynote的index.apxl文件模板
        TemplateGenerator.process();

//        System.out.println(getExpression("sdfsdf-sdf"));
    }
}
